package br.com.project.crud.daos;

import br.com.project.crud.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonDao extends CrudRepository<Person,Long> {

    List<Person> findById(long id);


}
