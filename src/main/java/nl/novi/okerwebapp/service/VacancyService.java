package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public Iterable<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancy(int id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);

        if (optionalVacancy.isPresent()) {
            return optionalVacancy.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
