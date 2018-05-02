package br.com.project.crud.daos;

import br.com.project.crud.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    List<Person> findByName(String name);

    List<Person> findByCountry(String country);

}
