package nl.novi.okerwebapp.repository;

import nl.novi.okerwebapp.model.Function;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FunctionRepository extends CrudRepository<Function, Integer> {

   Iterable<Function> findByName(String name);
   Iterable<Function> findById(int function_id);

}
