package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.OfferApplicationRequestDto;
import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.service.OfferApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/offerapplications")
    public ResponseEntity<Object> addOfferApplication(@Valid @RequestBody OfferApplicationRequestDto offerApplicationRequestDto) {
        int newId = offerApplicationService.addOfferApplication(offerApplicationRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/offerapplications/{id}")
    public ResponseEntity<Object> partialUpdateOfferApplications(@PathVariable int id, @RequestBody OfferApplication offerapplication) {
        offerApplicationService.partialUpdateOfferApplication(id, offerapplication);
        return ResponseEntity.noContent().build();
    }

}
