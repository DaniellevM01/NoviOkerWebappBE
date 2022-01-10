package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Function;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionService {

    private final FunctionRepository functionRepository;

    @Autowired
    public FunctionService(FunctionRepository functionRepository){
        this.functionRepository = functionRepository;
    }

    //get
    public Function getFunction(int function_id) {
        Optional<Function> optionalFunction = functionRepository.findById(function_id);
        if (optionalFunction.isPresent()) {
            return optionalFunction.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Iterable<Function> getFunctions() {
        return functionRepository.findAll();
    }

    //post
    public void addFunction (Function function) {
      //List<Function> functions = List<Function>;
      //if() {
      //    throw new BadRequestException("Function already exists");
      //}
    }

    //put
    public void updateFunction (String name, int basicsalary) {
    Optional<Function> optionalFunction = functionRepository.findById(function_id);

        if (false) {
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }


    // delete
    public void deleteFunction() {
        if (false) {
        } else {
            throw new RecordNotFoundException("ID does not exist")
        }
    }

}
