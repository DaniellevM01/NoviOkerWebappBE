package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.VacancyApplicationPatchRequestDto;
import nl.novi.okerwebapp.dto.requests.VacancyApplicationPostRequestDto;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.service.VacancyApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VacancyApplicationController {

    @Autowired
    private VacancyApplicationService vacancyApplicationService;

    @GetMapping(value = "/vacancyapplications")
    public ResponseEntity<Object> getVacancyApplications() {
        return ResponseEntity.ok(vacancyApplicationService.getVacancyApplications());
    }

    @PostMapping(value = "/vacancyapplications",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addVacancyApplications(VacancyApplicationPostRequestDto vacancyApplicationPostRequestDto) {
        int newId = vacancyApplicationService.addVacancyApplication(vacancyApplicationPostRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/vacancyapplications/{id}")
    public ResponseEntity<Object> partialUpdateVacancyApplications(@PathVariable int id, @RequestBody VacancyApplicationPatchRequestDto vacancyApplicationPatchRequestDto ){
        vacancyApplicationService.partialUpdateVacancyApplication(id, vacancyApplicationPatchRequestDto);
        return ResponseEntity.noContent().build();
    }

}
