package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.model.Function;
import nl.novi.okerwebapp.service.FunctionService;
import nl.novi.okerwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FunctionController {

    private FunctionService functionService;

    @Autowired
    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    //get
    @GetMapping(value = "/function")
    public ResponseEntity<Object> getFunction() {
        return ResponseEntity.ok().body(functionService.getFunctions());
    }

    //post
    @PostMapping(value = "/function")
    public ResponseEntity<Object> addFunction() {
        return ResponseEntity.noContent().build();
    }

    //put
    @PutMapping(value = "/function")
    public ResponseEntity<Object> updateFunction(@PathVariable("function") String name, @RequestBody int basicSalary) {
        functionService.updateFunction(name, basicSalary);
        return ResponseEntity.noContent().build();
    }

    //delete


}
