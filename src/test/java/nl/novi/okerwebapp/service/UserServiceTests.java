package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.AuthenticationRequestDto;
import nl.novi.okerwebapp.dto.requests.UserPostRequestDto;
import nl.novi.okerwebapp.dto.requests.UserPutRequestDto;
import nl.novi.okerwebapp.dto.requests.VacancyApplicationPatchRequestDto;
import nl.novi.okerwebapp.dto.responses.AuthenticationResponseDto;
import nl.novi.okerwebapp.dto.responses.UserCreateResponseDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.exception.UserNotFoundException;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserAuthenticateService userAuthenticateService;

    @Mock
    JavaMailSender mailSender;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Captor
    ArgumentCaptor<SimpleMailMessage> mailCaptor;

    @Test
    public void getCurrentUserTest() {
        //Arrange
        String expected_username = "danielleoker@gmail.com";

        User user = new User();
        user.setUsername(expected_username);

        Mockito
                .when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        UserService userServiceSpy = Mockito.spy(userService);

        Mockito.doReturn(expected_username).when(userServiceSpy).getCurrentUserName();

        //Act
        Optional<User> found = userServiceSpy.getCurrentUser();

        //Assert
        assertTrue(found.isPresent());
        assertEquals(expected_username, found.get().getUsername());
    }

    @Test
    public void getUsersTest() {
        //Arrange
        User user = new User();
        user.setUsername("danielleoker@gmail.com");

        List<User> users = new ArrayList<User>();
        users.add(user);

        Mockito
                .when(userRepository.findAll())
                .thenReturn(users);

        //Act
        Iterable<User> users_result = userService.getUsers();

        //Assert
        assertEquals(1, StreamSupport.stream(users_result.spliterator(), false).count());
        assertEquals("danielleoker@gmail.com",StreamSupport.stream(users_result.spliterator(), false).findFirst().get().getUsername());
    }

    @Test
    public void resetUserPasswordTest() {
        //Arrange
        User user = new User();
        user.setUsername("danielleoker@gmail.com");

        Mockito
                .when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        //Act
        userService.resetUserPassword("danielleoker@gmail.com");
        Mockito.verify(userRepository).save(userCaptor.capture());

        User saved_user = userCaptor.getValue();

        //Assert
        assertEquals(user.getUsername(), saved_user.getUsername());
    }

    @Test
    public void setPasswordTest() {
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("danielleoker@gmail.com");

        UserService userServiceSpy = Mockito.spy(userService);

        Mockito
                .when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        Mockito.doReturn("danielleoker@gmail.com").when(userServiceSpy).getCurrentUserName();

        //Act
        userServiceSpy.setPassword(1, "Krokodillentent123!");
        Mockito.verify(userRepository).save(userCaptor.capture());

        User saved_user = userCaptor.getValue();

        //Assert
        assertEquals(user.getUsername(), saved_user.getUsername());
    }

    @Test
    public void createUser() {
        //Arrange
        UserPostRequestDto userPostRequestDto = new UserPostRequestDto();
        userPostRequestDto.setUsername("danielleoker@gmail.com");
        userPostRequestDto.setPassword("DitiseenAppelOnderDeGrond132@");
        userPostRequestDto.setName("Ali .B");
        userPostRequestDto.setEnabled(true);

        AuthenticationResponseDto authResult = new AuthenticationResponseDto("GEENECHTEJWT");

        //Act
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito
                .when(userAuthenticateService.authenticateUser(any(AuthenticationRequestDto.class)))
                .thenReturn(authResult);

        UserCreateResponseDto response = userService.createUser(userPostRequestDto);

        //Assert
        assertEquals(userPostRequestDto.getUsername(), response.getUsername());
        assertEquals("GEENECHTEJWT", response.getJwt());
    }

    @Test
    public void updateUser(){
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("danielleoker@gmail.com");
        user.setName("Danielle");
        user.setTelephonenumber("08008844");

        UserPutRequestDto userPutRequestDto = new UserPutRequestDto("Peter", "09009696");


        Mockito
                .when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        Mockito
                .when(userRepository.findById(user.getUserId()))
                .thenReturn(java.util.Optional.of(user));

        UserService userServiceSpy = Mockito.spy(userService);

        Mockito.doReturn(user.getUsername()).when(userServiceSpy).getCurrentUserName();

        //Act
        userServiceSpy.updateUser(user.getUserId(), userPutRequestDto);
        Mockito.verify(userRepository).save(userCaptor.capture());

        User saved_user = userCaptor.getValue();

        //Assert
        assertNotEquals("Danielle", saved_user.getName());
        assertNotEquals("08008844", saved_user.getTelephonenumber());
        assertEquals(userPutRequestDto.getName(), saved_user.getName());

        assertThrows(UserNotFoundException.class, () -> { userServiceSpy.updateUser(91000, userPutRequestDto);});
    }

    @Test
    public void verifyNewUserAuthority() {
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.addAuthority("USER");

        Mockito
                .when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        //Act
        Boolean result = userService.verifyAuthority(user.getUserId(), "APPEL");
        Mockito.verify(userRepository).save(userCaptor.capture());

        User saved_user = userCaptor.getValue();

        //Assert
        assertTrue(result);
        assertEquals(2, saved_user.getAuthorities().size());
    }

    @Test
    public void verifyExistingUserAuthority() {
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.addAuthority("USER");
        user.addAuthority("CUSTOMER");


        Mockito
                .when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        //Act
        Boolean result = userService.verifyAuthority(user.getUserId(), "APPEL");

        //Assert
        assertFalse(result);
    }

    @Test
    public void removeAuthority() {
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.addAuthority("USER");
        user.addAuthority("CUSTOMER");


        Mockito
                .when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        //Act
        userService.removeAuthority(user.getUserId(), "CUSTOMER");
        Mockito.verify(userRepository).save(userCaptor.capture());

        User saved_user = userCaptor.getValue();
        //Assert
        assertEquals(1, saved_user.getAuthorities().size());
    }

    @Test
    public void deleteNotExistsUser() {
        //Arrange
        Mockito
                .when(userRepository.existsById(1))
                .thenReturn(false);

        //Act

        //Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(1);
        });
    }

    @Test
    public void deleteExistsUser() {
        //Arrange
        Mockito
                .when(userRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(userRepository).deleteById(any(Integer.class));

        //Act

        //Assert
        assertDoesNotThrow(() -> {
            userService.deleteUser(1);
        });
    }

}
