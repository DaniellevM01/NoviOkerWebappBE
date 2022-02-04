package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.ContactApplicationPostRequestDto;
import nl.novi.okerwebapp.service.ContactApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;


@RestController
public class ContactApplicationController {

    @Autowired
    private ContactApplicationService contactApplicationService;

    @PostMapping(value = "/contactapplications")
    public ResponseEntity<Object> sendContactApplication(@Valid @RequestBody ContactApplicationPostRequestDto contactApplicationPostRequestDto) {
        contactApplicationService.sendContactApplication(contactApplicationPostRequestDto);
        return ResponseEntity.noContent().build();
    }


}
