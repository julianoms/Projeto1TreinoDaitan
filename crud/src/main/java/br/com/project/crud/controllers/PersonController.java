package br.com.project.crud.controllers;

import br.com.project.crud.models.Person;
import br.com.project.crud.utils.PersonNotFoundException;
import br.com.project.crud.utils.ReturnObj;
import br.com.project.crud.utils.ReturnObjectSingle;
import br.com.project.crud.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<ReturnObj> create(@RequestBody Person person) throws PersonNotFoundException {

        personService.createPerson(person);

        List<ReturnObjectSingle> returnList = ResponseSingleCreator(person);
        ReturnObj returnObj = new ReturnObj("Ok","Person Created",returnList);


        return  ResponseEntity.ok(returnObj);
    }


    @GetMapping
    public ResponseEntity<ReturnObj> read(@Param(value = "name")String name,
                                          @Param (value = "country")String country) throws PersonNotFoundException {

        List<ReturnObjectSingle> returnList = new ArrayList<>();

        if(name != null && country != null){
            List<Person> people = personService.getPersonByNameAndCountry(name,country);
            ResponseListCreator(people, returnList);
            ReturnObj returnObj = new ReturnObj("Ok","List of people named :"+name + " from :"+country,returnList);
            return ResponseEntity.ok(returnObj);
        }
        if(name != null) {
            List<Person> people = personService.getPersonByName(name);
            ResponseListCreator(people, returnList);
            ReturnObj returnObj = new ReturnObj("Ok","List of people named :"+name,returnList);
            return ResponseEntity.ok(returnObj);
        }
        if(country != null){
            List<Person> people = personService.getPersonByCountry(country);
            ResponseListCreator(people, returnList);
            ReturnObj returnObj = new ReturnObj("Ok","List of people from: "+country,returnList);
            return ResponseEntity.ok(returnObj);
        }

        List<Person> people = personService.getPeople();

        ResponseListCreator(people, returnList);
        ReturnObj returnObj = new ReturnObj("Ok","List of all people:",returnList);
        return ResponseEntity.ok(returnObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObj> update (
             @PathVariable(value = "id")long id, @RequestBody Person person) throws PersonNotFoundException {

        person.setId(personService.getPersonById(id).getId());
        personService.updatePerson(person);

        List<ReturnObjectSingle> returnList = ResponseSingleCreator(person);
        ReturnObj returnObj = new ReturnObj("Ok","Person Updated",returnList);


        return  ResponseEntity.ok(returnObj);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObj> delete (@PathVariable(value = "id") long id) throws PersonNotFoundException {

        personService.deletePerson(id);
        ReturnObj object = new ReturnObj("deleted","Person Deleted");
        return ResponseEntity.ok(object);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReturnObj> readById(@PathVariable(value = "id") long id) throws PersonNotFoundException {

        Person person = personService.getPersonById(id);

        List<ReturnObjectSingle> returnList = ResponseSingleCreator(person);
        ReturnObj returnObj = new ReturnObj("Ok","Person retrieved",returnList);
        return  ResponseEntity.ok(returnObj);
    }

    private List<ReturnObjectSingle> ResponseSingleCreator(Person person) throws PersonNotFoundException {
        List<ReturnObjectSingle> returnList = new ArrayList<>();
        ReturnObjectSingle obj = new ReturnObjectSingle(person);
        obj.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        obj.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        returnList.add(obj);
        return returnList;
    }

    private void ResponseListCreator(List<Person> people, List<ReturnObjectSingle> returnList) throws PersonNotFoundException {
        ReturnObjectSingle object;
        for(Person person:people){
            object = new ReturnObjectSingle(person);
            object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
            object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
            returnList.add(object);
        }
    }



}
