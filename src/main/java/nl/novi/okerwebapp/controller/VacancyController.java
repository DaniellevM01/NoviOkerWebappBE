package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping(value = "/vacancies")
    public ResponseEntity<Object> getVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies());
    }

    @GetMapping(value = "/vacancies/{id}")
    public ResponseEntity<Object> getVacancy(@PathVariable int id) {
        return ResponseEntity.ok(vacancyService.getVacancy(id));
    }
}
