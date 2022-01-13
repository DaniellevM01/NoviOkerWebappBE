package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransportController {
    
    @Autowired
    public TransportService transportService;

    @GetMapping(value = "/transports")
    public ResponseEntity<Object> getTransports() {
        return ResponseEntity.ok(transportService.getTransports());
    }

}
