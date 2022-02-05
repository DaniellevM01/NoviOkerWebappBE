package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.VacancyApplicationPatchRequestDto;
import nl.novi.okerwebapp.dto.requests.VacancyApplicationPostRequestDto;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.VacancyApplicationRepository;
import nl.novi.okerwebapp.repository.VacancyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VacancyApplicationServiceTest {

    @Mock
    JavaMailSender mailSender;

    @Mock
    UserService userService;

    @Mock
    VacancyRepository vacancyRepository;

    @Mock
    VacancyApplicationRepository vacancyApplicationRepository;

    @InjectMocks
    VacancyApplicationService vacancyApplicationService;

    @Captor
    ArgumentCaptor<VacancyApplication> vacancyApplicationArgumentCaptor;

    @Test
    public void partialUpdateVacancyApplication(){
        //Arrange
        User user = new User();
        VacancyApplication vacancyApplication = new VacancyApplication();
        vacancyApplication.setId(1);
        vacancyApplication.setStatus("aanvaard");
        vacancyApplication.setDescription("Hey hallo");
        vacancyApplication.setUser(user);

        VacancyApplicationPatchRequestDto vacancyApplicationPatchRequestDto = new VacancyApplicationPatchRequestDto();
        vacancyApplicationPatchRequestDto.setStatus("sinaasappel");

        Mockito
                .when(vacancyApplicationRepository.findById(vacancyApplication.getId()))
                .thenReturn(java.util.Optional.of(vacancyApplication));

        //Act
        vacancyApplicationService.partialUpdateVacancyApplication(vacancyApplication.getId(), vacancyApplicationPatchRequestDto);
        Mockito.verify(vacancyApplicationRepository).save(vacancyApplicationArgumentCaptor.capture());

        VacancyApplication saved_vacancy_application = vacancyApplicationArgumentCaptor.getValue();
                //Assert
        assertEquals(vacancyApplicationPatchRequestDto.getStatus(), saved_vacancy_application.getStatus());
        assertNotEquals("aanvaard", saved_vacancy_application.getStatus());
    }

    @Test
    public void getVacancyApplications(){
        //Arrange
        VacancyApplication vacancyApplication = new VacancyApplication();
        vacancyApplication.setId(1);
        vacancyApplication.setStatus("Ingediend");
        vacancyApplication.setDescription("Beschrijving2");

        List<VacancyApplication> vacancyApplications = new ArrayList<VacancyApplication>();
        vacancyApplications.add(vacancyApplication);

        Mockito
                .when(vacancyApplicationRepository.findAll())
                .thenReturn(vacancyApplications);

        //Act
        Iterable<VacancyApplication> vacancy_applications_result = vacancyApplicationService.getVacancyApplications();

        //Assert
        assertEquals(1, StreamSupport.stream(vacancy_applications_result.spliterator(), false).count());
        assertEquals("Ingediend",StreamSupport.stream(vacancy_applications_result.spliterator(), false).findFirst().get().getStatus());
    }

    @Test
    public void addVacancyApplication(){
        //Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("danielleoker@gmail.com");
        user.addAuthority("SOLLICITOR");

        Vacancy vacancy = new Vacancy();
        vacancy.setId(1);

        VacancyApplicationPostRequestDto vacancyApplicationPostRequestDto = new VacancyApplicationPostRequestDto();
        vacancyApplicationPostRequestDto.setFile(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE, "".getBytes()));
        vacancyApplicationPostRequestDto.setStatus("new");
        vacancyApplicationPostRequestDto.setDescription("Beschrijving");
        vacancyApplicationPostRequestDto.setVacancy_id(1);

        Mockito
                .when(userService.getCurrentUser())
                .thenReturn(java.util.Optional.of(user));

        Mockito
                .when(userService.verifyAuthority(user.getUserId(), "SOLLICITOR"))
                .thenReturn(true);

        Mockito
                .when(vacancyRepository.findById(vacancy.getId()))
                .thenReturn(java.util.Optional.of(vacancy));

        Mockito.when(vacancyApplicationRepository.save(Mockito.any(VacancyApplication.class)))
                .thenAnswer(i -> {
                    VacancyApplication temp = (VacancyApplication) i.getArguments()[0];
                    temp.setId(1);
                    return temp;
                });
        //act
        Integer result = vacancyApplicationService.addVacancyApplication(vacancyApplicationPostRequestDto);

        //Assert
        assertEquals(1, result);
    }
}