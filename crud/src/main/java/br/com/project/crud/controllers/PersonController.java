package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import br.com.project.crud.models.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/create")
    public ReturnObject create(@RequestParam(value = "name", defaultValue = "João")String name, @RequestParam(value = "country", defaultValue = "Brasil") String country){

        Person person =  new Person(name,country);
        personRepository.savePerson(person);

            return new ReturnObject("OK", "Person was included",person);
    }

    @RequestMapping("/read")
    public ReturnObject read(){
        List<Person> people= personRepository.findAll();
        return new ReturnObject("OK","Selection of all person intities",people);
    }



    @RequestMapping("/update/{id}")
    public Person update (@PathVariable(value = "id")long id, @RequestParam(value = "name")String name, @RequestParam(value = "country")String country) {

        Person person = personRepository.findById(id);
        person.setName(name);
        person.setCountry(country);
        return personRepository.merge(person);

    }

    @RequestMapping("/delete/{id}")
    public String delete ( @PathVariable(value = "id") long id) throws Exception {

        personRepository.deleteById(id);
        return "Person deleted ";
    }

    @RequestMapping("/readByName")
    public Iterable<Person> readByName(@RequestParam String name){
        return personRepository.findByName(name );
    }

}
