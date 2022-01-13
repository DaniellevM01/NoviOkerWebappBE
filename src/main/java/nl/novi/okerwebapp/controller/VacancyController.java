package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.VacancyRequestDto;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping(value = "/vacancies")
    public ResponseEntity<Object> getVacancies(@RequestParam(name="name", defaultValue="") String name) {
        return ResponseEntity.ok(vacancyService.getVacancies(name));
    }

    @GetMapping(value = "/vacancies/{id}")
    public ResponseEntity<Object> getVacancy(@PathVariable int id) {
        return ResponseEntity.ok(vacancyService.getVacancy(id));
    }

    @DeleteMapping(value = "/vacancies/{id}")
    public ResponseEntity<Object> deleteVacancy(@PathVariable("id") int id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/vacancies")
    public ResponseEntity<Object> addOfferRequest(@Valid @RequestBody VacancyRequestDto vacancyRequestDto) {
        int newId = vacancyService.addVacancy(vacancyRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/vacancies/{id}")
    public ResponseEntity<Object> updateVacancy(@PathVariable int id, @RequestBody Vacancy vacancy) {
        vacancyService.updateVacancy(id, vacancy);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/vacancies/{id}")
    public ResponseEntity<Object> partialUpdateVacancy(@PathVariable int id, @RequestBody Vacancy vacancy) {
        vacancyService.partialUpdateVacancy(id, vacancy);

        return ResponseEntity.noContent().build();
    }

}
