package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.ContactRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class ContactRequestController {

    @PostMapping(value = "/contactrequests")
    public ResponseEntity<Object> sendContactRequest(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        int newId = contactRequestService.sendContactRequest(contactRequestDto);
        return ResponseEntity.created().build();
    }

}
