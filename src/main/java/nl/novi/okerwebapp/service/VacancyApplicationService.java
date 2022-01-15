package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.VacancyApplicationRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.VacancyApplicationRepository;
import nl.novi.okerwebapp.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyApplicationService {

    @Autowired
    private VacancyApplicationRepository vacancyApplicationRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    public Iterable<VacancyApplication> getVacancyApplications() {
        return vacancyApplicationRepository.findAll();
    }

    public int addVacancyApplication(VacancyApplicationRequestDto vacancyApplicationRequestDto) {

        VacancyApplication vacancyApplication = new VacancyApplication();
        vacancyApplication.setStatus(vacancyApplicationRequestDto.getStatus());
        vacancyApplication.setDescription(vacancyApplicationRequestDto.getDescription());
        //vacancyRequest.setUserid(); => USER_ID VAN INGELOGDE GEBRUIKER
        //vacancyRequest.setFile(); => LES TERUGKIJKEN
        Vacancy vacancy = vacancyRepository.findById(vacancyApplicationRequestDto.getVacancy_id()).orElseThrow();
        vacancyApplication.setVacancy(vacancy);

        VacancyApplication newVacancyApplication = vacancyApplicationRepository.save(vacancyApplication);
        return newVacancyApplication.getId();
    }

    public void partialUpdateVacancyApplication(int id, VacancyApplication vacancyApplication) {
        Optional<VacancyApplication> optionalOfferRequest = vacancyApplicationRepository.findById(id);

        if (optionalOfferRequest.isPresent()) {
            VacancyApplication storedVacancyApplication = vacancyApplicationRepository.findById(id).orElse(null);

            if (vacancyApplication.getStatus() != null && !vacancyApplication.getStatus().isEmpty()) {
                storedVacancyApplication.setStatus(vacancyApplication.getStatus());
            }
            vacancyApplicationRepository.save(storedVacancyApplication);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
