package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.VacancyRequestRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.VacancyRequest;
import nl.novi.okerwebapp.repository.VacancyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyRequestService {

    @Autowired
    private VacancyRequestRepository vacancyRequestRepository;

    public Iterable<VacancyRequest> getVacancyRequests() {
        return vacancyRequestRepository.findAll();
    }

    public int addVacancyRequest(VacancyRequestRequestDto vacancyRequestRequestDto) {

        VacancyRequest vacancyRequest = new VacancyRequest();
        vacancyRequest.setStatus(vacancyRequestRequestDto.getStatus());
        vacancyRequest.setDescription(vacancyRequestRequestDto.getDescription());
        //vacancyRequest.setUserid(); => USER_ID VAN INGELOGDE GEBRUIKER
        //vacancyRequest.setFile(); => LES TERUGKIJKEN
        vacancyRequest.setVacancy(vacancyRequestRequestDto.getVacancy_id());

        VacancyRequest newVacancyRequest = vacancyRequestRepository.save(vacancyRequest);
        return newVacancyRequest.getId();
    }

    public void partialUpdateVacancyRequest(int id, VacancyRequest vacancyRequest) {
        Optional<VacancyRequest> optionalOfferRequest = vacancyRequestRepository.findById(id);

        if (optionalOfferRequest.isPresent()) {
            VacancyRequest storedVacancyRequest = vacancyRequestRepository.findById(id).orElse(null);

            if (vacancyRequest.getStatus() != null && !vacancyRequest.getStatus().isEmpty()) {
                storedVacancyRequest.setStatus(vacancyRequest.getStatus());
            }
            vacancyRequestRepository.save(storedVacancyRequest);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
