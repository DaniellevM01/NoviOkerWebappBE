package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.model.Function;
import nl.novi.okerwebapp.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    public Iterable<Function> getFunctions() {
        return functionRepository.findAll();
    }

}
