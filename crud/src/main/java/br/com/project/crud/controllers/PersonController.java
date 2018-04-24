package br.com.project.crud.controllers;

import br.com.project.crud.models.Person;
import br.com.project.crud.utils.PersonNotFoundExeption;
import br.com.project.crud.utils.ReturnObject;
import br.com.project.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
    private PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> create(@RequestBody Person person) throws PersonNotFoundExeption {

        personService.CreatePerson(person);

        ReturnObject object = new ReturnObject("created","Person created");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @GetMapping("/read")
    public ResponseEntity<ReturnObject> read(){
        List<Person> people = personService.GetPeople();
        ReturnObject object = new ReturnObject("Ok","List of all included people", people);
        object.add(linkTo(PersonController.class).withRel("index"));
        return ResponseEntity.ok(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObject> update (
             @PathVariable(value = "id")long id, @RequestBody Person person) throws PersonNotFoundExeption {

        person.setId(personService.GetPersonById(id).getId());
        personService.updatePerson(person);

        ReturnObject object = new ReturnObject("Ok","Person Updated");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return  ResponseEntity.ok(object);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObject> delete (@PathVariable(value = "id") long id) throws PersonNotFoundExeption {

        personService.deletePerson(id);
        ReturnObject object = new ReturnObject("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }

    @RequestMapping("/readByName")
    public ResponseEntity<ReturnObject> readByName(@RequestBody String name){
        List<Person> people = personService.getPersonByName(name);
        ReturnObject object = new ReturnObject("Ok","List of people named :"+name,people);
        object.add(linkTo(PersonController.class).withSelfRel());
        return ResponseEntity.ok(object);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnObject> readById(@PathVariable(value = "id") Long id) throws PersonNotFoundExeption {
        Person person = personService.GetPersonById(id);
        ReturnObject object = new ReturnObject("Ok","Person retrieved",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return ResponseEntity.ok(object);
    }

    @RequestMapping("")
    public ResponseEntity<ReturnObject> index (){
        ReturnObject object = new ReturnObject("Ok","Person index");
        object.add(new Link("http://localhost:8080/person","self"));
        object.add(new Link("http://localhost:8080/person/read","people"));
        return ResponseEntity.ok(object);
    }

}
