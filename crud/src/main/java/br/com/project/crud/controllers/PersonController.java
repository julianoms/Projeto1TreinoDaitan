package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
