package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Function;
import nl.novi.okerwebapp.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FunctionService {

    private final FunctionRepository functionRepository;

    @Autowired
    public FunctionService(FunctionRepository functionRepository){
        this.functionRepository = functionRepository;
    }

    public Function getFunction(int function_id) {
        Iterable<Function> optionalFunction = functionRepository.findById(function_id);
        if (optionalFunction.isPresent()) {
            return optionalFunction.getFunction();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Iterable<Function> getFunctions() {
        return functionRepository.findAll();
    }

    //post check op id of/en name?
    public int addFunction (Function function) {
      List<Function> functions = List<Function>.functionRepository.findById(function_id);
      if(functions.size() > 0) {
          throw new BadRequestException("Function already exists");
      }
      return newFunction.getFunction(function_id);
    }

    public void updateFunction(long function_id, Function function) {
        if (!functionRepository.existsById(function_id)) throw new RecordNotFoundException();
        Function existingFunction = functionRepository.findById(function_id).get();
        existingFunction.setName(function.getName());
        existingFunction.setBasicSalary(function.getBasicSalary());
        functionRepository.save(existingFunction);
    }

    public void partialUpdateFunction(long id, Map<String, String> fields) {
        if (!functionRepository.existsById(id)) throw new RecordNotFoundException();
        Function book = functionRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "name":
                    book.setName((String) fields.get(field));
                    break;
                case "basicSalary":
                    book.setBasicSalary((String) fields.get(field));
                    break;
            }
        }
        functionRepository.save(existingFunction);
    }

    public void deleteFunction(long function_id) {
        if (!functionRepository.existsById(function_id)) throw new RecordNotFoundException("ID does not exist");
        functionRepository.deleteById(function_id);
    }




}
