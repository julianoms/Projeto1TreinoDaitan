package br.com.project.crud.services;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import br.com.project.crud.utils.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Cacheable(value = "person",key = "#id")
    public Person getPersonById(long id) throws PersonNotFoundException {

        if (!personRepository.findById(id).isPresent()){
            throw new PersonNotFoundException(id);
        }
        return personRepository.findById(id).get();
    }

    public List<Person> getPeople(){
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);
        return list;
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void createPerson(Person person){
        personRepository.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Caching(
            evict = @CacheEvict(value = "person",key = "#person.id"),
            put = @CachePut(value = "person",key = "#person.id"))
    public void updatePerson(Person person) throws PersonNotFoundException {
        if (!personRepository.findById(person.getId()).isPresent()) {
            throw new PersonNotFoundException(person.getId());
        }
        personRepository.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @CacheEvict(value = "person",key = "#id")
    public void deletePerson(long id) throws PersonNotFoundException {

        if (!personRepository.findById(id).isPresent()){
            throw new PersonNotFoundException(id);
        }
        personRepository.deleteById(id);
    }

    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name);
    }
    public List<Person> getPersonByCountry(String country){
        return personRepository.findByCountry(country);
    }
    public List<Person> getPersonByNameAndCountry(String name, String country){return  personRepository.findByNameAndCountry(name,country);}

    @CacheEvict(value = "person",allEntries = true)
    public void evictCache(){}

}
