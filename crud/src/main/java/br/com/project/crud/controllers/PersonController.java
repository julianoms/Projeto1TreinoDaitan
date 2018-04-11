package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/create")
    public String create(@RequestParam(value = "name", defaultValue = "João")String name, @RequestParam(value = "country", defaultValue = "Brasil") String country){

        Person person =  new Person(name,country);
        personRepository.save(person);
        return person.toString() + "Cadastrado com sucesso ";
    }

    @RequestMapping("/read")
    public Iterable<Person> read(){
        return personRepository.findAll();
    }

    @RequestMapping("/update/{id}")
    public Person update (@PathVariable(value = "id")long id, @RequestParam(value = "name", defaultValue = "teste")String name, @RequestParam(value = "country",defaultValue = "teste")String country){

        Optional<Person> optional = personRepository.findById(id);
        Person person = optional.get();
        person.setName(name);
        person.setCountry(country);

        Person updatedperson = personRepository.save(person);
        return updatedperson;
    }


}
