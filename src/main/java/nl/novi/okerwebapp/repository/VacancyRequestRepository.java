package nl.novi.okerwebapp.repository;

import nl.novi.okerwebapp.model.VacancyRequest;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRequestRepository extends CrudRepository<VacancyRequest, Integer> {
}
