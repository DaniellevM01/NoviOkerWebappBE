package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.service.FunctionService;
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

    @GetMapping(value = "/functions")
    public ResponseEntity<Object> getFunctions() {
        return ResponseEntity.ok().body(functionService.getFunctions());
    }


}
