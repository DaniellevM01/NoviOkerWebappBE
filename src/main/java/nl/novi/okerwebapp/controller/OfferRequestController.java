package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.OfferRequestRequestDto;
import nl.novi.okerwebapp.model.OfferRequest;
import nl.novi.okerwebapp.service.OfferRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class OfferRequestController {

    @Autowired
    private OfferRequestService offerRequestService;

    @GetMapping(value = "/offerrequests")
    public ResponseEntity<Object> getOfferRequests(@RequestParam(name="name", defaultValue="") String name) {
        return ResponseEntity.ok(offerRequestService.getOfferRequests(name));
    }

    @GetMapping(value = "/offerrequests/{id}")
    public ResponseEntity<Object> getOfferRequest(@PathVariable int id) {
        return ResponseEntity.ok(offerRequestService.getOfferRequest(id));
    }

    @DeleteMapping(value = "/offerrequests/{id}")
    public ResponseEntity<Object> deleteOfferRequest(@PathVariable("id") int id) {
        offerRequestService.deleteOfferRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/offerrequests")
    public ResponseEntity<Object> addOfferRequest(@Valid @RequestBody OfferRequestRequestDto offerRequestRequestDto) {
        int newId = offerRequestService.addOfferRequest(offerRequestRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/offerrequests/{id}")
    public ResponseEntity<Object> updateOfferRequests(@PathVariable int id, @RequestBody OfferRequest offerrequest) {
        offerRequestService.updateOfferRequest(id, offerrequest);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/offerrequests/{id}")
    public ResponseEntity<Object> partialUpdateOfferRequests(@PathVariable int id, @RequestBody OfferRequest offerrequest) {
        offerRequestService.partialUpdateOfferRequest(id, offerrequest);

        return ResponseEntity.noContent().build();
    }

}
