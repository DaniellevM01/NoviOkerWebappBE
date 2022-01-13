package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.VacancyRequestRequestDto;
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
    public ResponseEntity<Object> getVacancyRequests(@RequestParam(name="name", defaultValue="") String name) {
        return ResponseEntity.ok(vacancyRequestService.getVacancyRequests(name));
    }

    @GetMapping(value = "/vacancyrequests/{id}")
    public ResponseEntity<Object> getvacancyRequest(@PathVariable int id) {
        return ResponseEntity.ok(vacancyRequestService.getVacancyRequest(id));
    }

    @DeleteMapping(value = "/vacancyrequests/{id}")
    public ResponseEntity<Object> deleteVacancyRequest(@PathVariable("id") int id) {
        vacancyRequestService.deleteVacancyRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/vacancyrequests")
    public ResponseEntity<Object> addVacancyRequest(@Valid @RequestBody VacancyRequestRequestDto vacancyRequestDto) {
        int newId = vacancyRequestService.addVacancyRequest(vacancyRequestRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/vacancyrequests/{id}")
    public ResponseEntity<Object> updateVacancyRequests(@PathVariable int id, @RequestBody VacancyRequest vacancyrequest) {
        vacancyRequestService.updateVacancyRequest(id, vacancyrequest);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/vacancyrequests/{id}")
    public ResponseEntity<Object> partialUpdateVacancyRequests(@PathVariable int id, @RequestBody VacancyRequest vacancyrequest) {
        vacancyRequestService.partialUpdateVacancyRequest(id, vacancyrequest);

        return ResponseEntity.noContent().build();
    }

}
