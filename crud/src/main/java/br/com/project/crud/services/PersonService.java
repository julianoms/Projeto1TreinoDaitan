package br.com.project.crud.services;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import br.com.project.crud.utils.PersonNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person GetPersonById(long id) throws PersonNotFoundExeption {
        if(!personRepository.existsById(id)){
            throw new PersonNotFoundExeption(id);
        }
        return personRepository.findById(id).get();
    }
    public List<Person> GetPeople(){
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);
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
