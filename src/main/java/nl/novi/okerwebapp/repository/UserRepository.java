package nl.novi.okerwebapp.repository;

import nl.novi.okerwebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}