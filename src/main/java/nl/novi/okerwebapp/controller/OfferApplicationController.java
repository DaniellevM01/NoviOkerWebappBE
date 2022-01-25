package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.OfferApplicationPatchRequestDto;
import nl.novi.okerwebapp.dto.requests.OfferApplicationPostRequestDto;
import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.service.OfferApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class OfferApplicationController {

    @Autowired
    private OfferApplicationService offerApplicationService;

    @GetMapping(value = "/offerapplications")
    public ResponseEntity<Object> getOfferApplications() {
        return ResponseEntity.ok(offerApplicationService.getOfferApplications());
    }

    @PostMapping(value = "/offerapplications",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addOfferApplication(OfferApplicationPostRequestDto offerApplicationPostRequestDto) {
        int newId = offerApplicationService.addOfferApplication(offerApplicationPostRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/offerapplications/{id}")
    public ResponseEntity<Object> partialUpdateOfferApplications(@PathVariable int id, @Valid @RequestBody OfferApplicationPatchRequestDto offerApplicationPatchRequestDto) {
        offerApplicationService.partialUpdateOfferApplication(id, offerApplicationPatchRequestDto);
        return ResponseEntity.noContent().build();
    }

}
