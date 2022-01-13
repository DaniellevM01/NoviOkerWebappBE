package nl.novi.okerwebapp.repository;

import nl.novi.okerwebapp.model.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
}
