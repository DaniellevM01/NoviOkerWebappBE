package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.repository.VacancyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VacancyServiceTest {

    @Mock
    VacancyRepository vacancyRepository;

    @InjectMocks
    VacancyService vacancyService;

    @Test
    public void getVacancy(){
        //Arrange
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1);
        vacancy.setTitle("Titel");
        vacancy.setDescription("Beschrijving");


        Mockito
                .when(vacancyRepository.findById(vacancy.getId()))
                .thenReturn(java.util.Optional.of(vacancy));

        //Act
        Vacancy found_vacancy = vacancyService.getVacancy(vacancy.getId());

        //Assert
        assertEquals(found_vacancy.getId(), vacancy.getId());
        assertThrows(RecordNotFoundException.class, () -> { vacancyService.getVacancy(900000);});
    }

    @Test
    public void getVacancies(){
        //Arrange
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1);
        vacancy.setTitle("Titel1");
        vacancy.setDescription("Beschrijving");

        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        vacancies.add(vacancy);

        Mockito
                .when(vacancyRepository.findAll())
                .thenReturn(vacancies);

        //Act
        Iterable<Vacancy> vacancies_result = vacancyService.getVacancies();

        //Assert
        assertEquals(1, StreamSupport.stream(vacancies_result.spliterator(), false).count());
        assertEquals("Titel1",StreamSupport.stream(vacancies_result.spliterator(), false).findFirst().get().getTitle());
    }
}