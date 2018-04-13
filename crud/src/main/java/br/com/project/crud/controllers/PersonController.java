package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import br.com.project.crud.models.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam(value = "name", defaultValue = "João")String name, @RequestParam(value = "country", defaultValue = "Brasil") String country){

        Person person =  new Person(name,country);
        personRepository.savePerson(person);

        ReturnObject object = new ReturnObject(HttpStatus.CREATED.toString(),"Person was included",person);
        return new ResponseEntity(object,HttpStatus.CREATED);
    }

    @RequestMapping("/read")
    public ResponseEntity read(){
        List<Person> people= personRepository.findAll();
        ReturnObject object = new ReturnObject(HttpStatus.OK.toString(),"List of all included people",people);

     //     return new ReturnObject(HttpStatus,"Selection of all person entities",people);
        return new ResponseEntity(object,HttpStatus.OK);
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
