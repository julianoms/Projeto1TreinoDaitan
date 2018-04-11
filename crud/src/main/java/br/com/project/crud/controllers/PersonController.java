package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/create")
    public Person create(@RequestParam(value = "name", defaultValue = "teste")String name){
        
        Person person =  new Person(name,"bra");
        return person;
    }
}
