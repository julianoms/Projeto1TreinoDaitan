package br.com.project.crud.service;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person GetPersonById(long id){
        Person obj = personRepository.findById(id).get();
        return obj;
    }
    public List<Person> GetPeople(){
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
    public void CreatePerson(Person person){
        personRepository.save(person);
    }

    public void updatePerson(Person person){
        personRepository.save(person);
    }
    public void deletePerson(long id){
        personRepository.deleteById(id);
    }
    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name);
    }

}
