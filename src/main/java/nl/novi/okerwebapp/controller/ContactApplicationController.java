package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.ContactApplicationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class ContactApplicationController {

    @PostMapping(value = "/contactapplications")
    public void sendContactApplication(@Valid @RequestBody ContactApplicationRequestDto contactApplicationRequestDto) {
        return;
    }


}
