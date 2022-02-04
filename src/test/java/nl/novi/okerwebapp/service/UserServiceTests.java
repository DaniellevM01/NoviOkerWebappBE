package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.model.User;
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
}
