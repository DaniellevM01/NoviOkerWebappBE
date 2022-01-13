package nl.novi.okerwebapp.controller;

import com.sun.jdi.connect.Transport;
import com.sun.jdi.connect.spi.TransportService;
import nl.novi.okerwebapp.dto.TransportRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransportController {
    
    @Autowired
    private TransportService transportService;

    @GetMapping(value = "/transports")
    public ResponseEntity<Object> getTransports(@RequestParam(name="name", defaultValue="") String name) {
        return ResponseEntity.ok(transportService.getTransports(name));   // Jackson  object => json
    }

    @GetMapping(value = "/transports/{id}")
    public ResponseEntity<Object> getTransport(@PathVariable int id) {
        return ResponseEntity.ok(transportService.getTransport(id));
    }

    @DeleteMapping(value = "/transports/{id}")
    public ResponseEntity<Object> deleteTransport(@PathVariable("id") int id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/transports")
    public ResponseEntity<Object> addTransport(@Valid @RequestBody TransportRequestDto transportRequestDto) {
        int newId = transportService.addTransport(transportRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/transports/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Transport transport) {
        transportService.updateTransport(id, transport);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/transports/{id}")
    public ResponseEntity<Object> partialUpdateTransport(@PathVariable int id, @RequestBody Transport transport) {
        transportService.partialUpdateTransport(id, transport);

        return ResponseEntity.noContent().build();
    }

}
