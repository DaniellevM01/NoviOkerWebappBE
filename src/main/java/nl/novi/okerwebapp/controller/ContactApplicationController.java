package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.ContactApplicationRequestDto;
import nl.novi.okerwebapp.dto.service.ContactApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class ContactApplicationController {

    @PostMapping(value = "/contactapplications")
    public ResponseEntity<Object> sendContactApplication(@Valid @RequestBody ContactApplicationRequestDto contactApplicationRequestDto) {
        int newId = contactApplicationService.sendContactApplication(contactApplicationRequestDto);
        return ResponseEntity.created().build();
    }

}
