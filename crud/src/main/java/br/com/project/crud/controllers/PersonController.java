package br.com.project.crud.controllers;

import br.com.project.crud.daos.PersonRepository;
import br.com.project.crud.models.Person;
import br.com.project.crud.models.ReturnObject;
import br.com.project.crud.models.ReturnObjectList;
import br.com.project.crud.models.ReturnObjectSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/create")
    public ResponseEntity<ReturnObjectSingle> create(
            @RequestParam(value = "name") String name, @RequestParam(value = "country") String country)  {

        Person person =  new Person(name,country);
        personRepository.savePerson(person);

        ReturnObjectSingle object = new ReturnObjectSingle("created","Person created",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel());
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @RequestMapping("/read")
    public ResponseEntity<ReturnObjectList> read(){
        List<Person> people= personRepository.findAll();
        ReturnObjectList object = new ReturnObjectList("Ok","List of all included people", people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @RequestMapping("/update/{id}")
    public ResponseEntity<ReturnObjectSingle> update (@PathVariable(value = "id")long id, @RequestParam(value = "name")String name, @RequestParam(value = "country")String country) {

        Person person = personRepository.findById(id);
        person.setName(name);
        person.setCountry(country);
        personRepository.merge(person);

        ReturnObjectSingle object = new ReturnObjectSingle("Ok","Person Updated",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel());
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete"));
        return  ResponseEntity.ok(object);

    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<ReturnObject> delete (@PathVariable(value = "id") long id)  {

        personRepository.deleteById(id);
        ReturnObject object = new ReturnObject("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }

    @RequestMapping("/readByName")
    public Iterable<Person> readByName(@RequestParam String name){
        return personRepository.findByName(name );
    }

    @GetMapping("/read/{id}")
    public Person readById(@PathVariable(value = "id") long id){
        return personRepository.findById(id);
    }

}
