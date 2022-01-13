package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.VacancyRequestRequestDto;
import nl.novi.okerwebapp.model.VacancyRequest;
import nl.novi.okerwebapp.service.VacancyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class VacancyRequestController {

    @Autowired
    private VacancyRequestService vacancyRequestService;

    @GetMapping(value = "/vacancyrequests")
    public ResponseEntity<Object> getVacancyRequests() {
        return ResponseEntity.ok(vacancyRequestService.getVacancyRequests());
    }

    @PostMapping(value = "/vacancyrequests")
    public ResponseEntity<Object> addVacancyRequest(@Valid @RequestBody VacancyRequestRequestDto vacancyRequestRequestDto) {
        int newId = vacancyRequestService.addVacancyRequest(vacancyRequestRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/vacancyrequests/{id}")
    public ResponseEntity<Object> partialUpdateVacancyRequests(@PathVariable int id, @RequestBody VacancyRequest vacancyrequest) {
        vacancyRequestService.partialUpdateVacancyRequest(id, vacancyrequest);
        return ResponseEntity.noContent().build();
    }

}
