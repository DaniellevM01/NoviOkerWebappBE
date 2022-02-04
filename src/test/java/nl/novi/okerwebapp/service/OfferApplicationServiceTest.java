package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.ContactApplicationPostRequestDto;
import nl.novi.okerwebapp.dto.requests.OfferApplicationPatchRequestDto;
import nl.novi.okerwebapp.dto.requests.OfferApplicationPostRequestDto;
import nl.novi.okerwebapp.dto.requests.VacancyApplicationPostRequestDto;
import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.OfferApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class OfferApplicationServiceTest {

    @Mock
    JavaMailSender mailSender;

    @Mock
    OfferApplicationRepository offerApplicationRepository;

    @Mock
    UserService userService;

    @InjectMocks
    OfferApplicationService offerApplicationService;

    @Captor
    ArgumentCaptor<OfferApplication> offerApplicationArgumentCaptor;

    @Test
    public void partialUpdateOfferApplication(){
        //Arrange
        User user = new User();
        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setId(1);
        offerApplication.setStatus("aanvaard");
        offerApplication.setDescription("Hey hallo");
        offerApplication.setUser(user);

        OfferApplicationPatchRequestDto offerApplicationPatchRequestDto = new OfferApplicationPatchRequestDto();
        offerApplicationPatchRequestDto.setStatus("sinaasappel");

        Mockito
                .when(offerApplicationRepository.findById(offerApplication.getId()))
                .thenReturn(java.util.Optional.of(offerApplication));

        //Act
        offerApplicationService.partialUpdateOfferApplication(offerApplication.getId(), offerApplicationPatchRequestDto);
        Mockito.verify(offerApplicationRepository).save(offerApplicationArgumentCaptor.capture());

        OfferApplication saved_offer_application = offerApplicationArgumentCaptor.getValue();
                //Assert
        assertEquals(offerApplicationPatchRequestDto.getStatus(), saved_offer_application.getStatus());
        assertNotEquals("aanvaard", saved_offer_application.getStatus());

    }

    @Test
    public void getOfferApplications(){
        //Arrange
        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setId(1);
        offerApplication.setStatus("Ingediend");
        offerApplication.setDescription("Beschrijving3");

        List<OfferApplication> offerApplications = new ArrayList<OfferApplication>();
        offerApplications.add(offerApplication);

        Mockito
                .when(offerApplicationRepository.findAll())
                .thenReturn(offerApplications);

        //Act
        Iterable<OfferApplication> offer_applications_result = offerApplicationService.getOfferApplications();

        //Assert
        assertEquals(1, StreamSupport.stream(offer_applications_result.spliterator(), false).count());
        assertEquals("Ingediend",StreamSupport.stream(offer_applications_result.spliterator(), false).findFirst().get().getStatus());
    }

    @Test
    public void addOfferApplication(){
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("danielleoker@gmail.com");
        user.addAuthority("CUSTOMER");

        OfferApplicationPostRequestDto offerApplicationPostRequestDto = new OfferApplicationPostRequestDto();
        offerApplicationPostRequestDto.setFile(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE, "".getBytes()));
        offerApplicationPostRequestDto.setStatus("new");
        offerApplicationPostRequestDto.setDescription("Beschrijving");

        Mockito
                .when(userService.getCurrentUser())
                .thenReturn(java.util.Optional.of(user));

        Mockito
                .when(userService.verifyAuthority(user.getUserId(), "CUSTOMER"))
                .thenReturn(true);

        Mockito.when(offerApplicationRepository.save(Mockito.any(OfferApplication.class)))
                .thenAnswer(i -> {
                    OfferApplication temp = (OfferApplication) i.getArguments()[0];
                    temp.setId(1);
                    return temp;
                });
        //act
        Integer result = offerApplicationService.addOfferApplication(offerApplicationPostRequestDto);

        //Assert
        assertEquals(1, result);
    }
}