package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.OfferRequestRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.OfferRequest;
import nl.novi.okerwebapp.repository.OfferRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OfferRequestService {

    @Autowired
    private OfferRequestRepository offerRequestRepository;

    public Iterable<OfferRequest> getOfferRequests() {
        return offerRequestRepository.findAll();
    }

    public int addOfferRequest(OfferRequestRequestDto offerRequestRequestDto) {

        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setStatus(offerRequestRequestDto.getStatus());
        offerRequest.setDescription(offerRequestRequestDto.getDescription());
        //offerRequest.setUserid(); => USER_ID VAN INGELOGDE GEBRUIKER
        //offerRequest.setFile(); => LES TERUGKIJKEN

        OfferRequest newOfferRequest = offerRequestRepository.save(offerRequest);
        return newOfferRequest.getId();
    }

    public void partialUpdateOfferRequest(int id, OfferRequest offerRequest) {
        Optional<OfferRequest> optionalOfferRequest = offerRequestRepository.findById(id);

        if (optionalOfferRequest.isPresent()) {
            OfferRequest storedOfferRequest = offerRequestRepository.findById(id).orElse(null);

            if (offerRequest.getStatus() != null && !offerRequest.getStatus().isEmpty()) {
                storedOfferRequest.setStatus(offerRequest.getStatus());
            }
            offerRequestRepository.save(storedOfferRequest);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
