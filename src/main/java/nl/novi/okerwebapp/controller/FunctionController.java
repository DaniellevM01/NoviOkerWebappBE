package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.model.Function;
import nl.novi.okerwebapp.service.FunctionService;
import nl.novi.okerwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class FunctionController {

    private FunctionService functionService;

    @Autowired
    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping(value = "/function")
    public ResponseEntity<Object> getFunctions() {
        return ResponseEntity.ok().body(functionService.getFunctions());
    }

    @GetMapping(value = "/function")
    public ResponseEntity<Object> getFunction() {
        return ResponseEntity.ok().body(functionService.getFunction());
    }

    //post
    @PostMapping(value = "/function")
    public ResponseEntity<Object> addFunction(@RequestBody Function function) {
        long newId = functionService.addFunction(function);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    //put
    @PutMapping(value = "/{function_id}")
    public ResponseEntity<Object> updateFunction(@PathVariable("function_id") long id, @RequestBody Function function) {
        functionService.updateFunction(long function_id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @PutMapping(value = "/{function_id}")
    public ResponseEntity<Object> partialUpdateFunction(@PathVariable("function_id") long id, @RequestBody Function function) {
        functionService.partialUpdateFunction(long function_id);
        return ResponseEntity.noContent().build();
    }

    //delete
    @DeleteMapping(value = "/function")
    public ResponseEntity<Object> deleteFunction(@PathVariable("function") int function_id) {
        functionService.deleteFunction(function_id);
        return ResponseEntity.noContent().build();
    }

}
