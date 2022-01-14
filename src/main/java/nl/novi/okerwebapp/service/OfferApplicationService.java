package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.OfferApplicationRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.repository.OfferApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OfferApplicationService {

    @Autowired
    private OfferApplicationRepository offerApplicationRepository;

    public Iterable<OfferApplication> getOfferApplications() {
        return offerApplicationRepository.findAll();
    }

    public int addOfferApplication(OfferApplicationRequestDto offerApplicationRequestDto) {

        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setStatus(offerApplicationRequestDto.getStatus());
        offerApplication.setDescription(offerApplicationRequestDto.getDescription());
        //offerRequest.setUserid(); => USER_ID VAN INGELOGDE GEBRUIKER
        //offerRequest.setFile(); => LES TERUGKIJKEN

        OfferApplication newOfferApplication = offerApplicationRepository.save(offerApplication);
        return newOfferApplication.getId();
    }

    public void partialUpdateOfferApplication(int id, OfferApplication offerApplication) {
        Optional<OfferApplication> optionalOfferApplication = offerApplicationRepository.findById(id);

        if (optionalOfferApplication.isPresent()) {
            OfferApplication storedOfferApplication = offerApplicationRepository.findById(id).orElse(null);

            if (offerApplication.getStatus() != null && !offerApplication.getStatus().isEmpty()) {
                storedOfferApplication.setStatus(offerApplication.getStatus());
            }
            offerApplicationRepository.save(storedOfferApplication);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
