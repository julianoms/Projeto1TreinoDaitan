package br.com.project.crud.controllers;

import br.com.project.crud.models.Person;
import br.com.project.crud.utils.PersonNotFoundExeption;
import br.com.project.crud.utils.ReturnObj;
import br.com.project.crud.utils.ReturnObjectSingle;
import br.com.project.crud.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<ReturnObjectSingle> create(@RequestBody Person person) throws PersonNotFoundExeption {

        personService.CreatePerson(person);

        ReturnObjectSingle object = new ReturnObjectSingle("created","Person created");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @GetMapping
    public ResponseEntity<ReturnObj> read(@Param(value = "name")String name,
                                          @Param (value = "country")String country) throws PersonNotFoundExeption {

        List<ReturnObjectSingle> returnList = new ArrayList<>();

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

        List<Person> people = personService.GetPeople();

        ResponseListCreator(people, returnList);
        ReturnObj returnObj = new ReturnObj("Ok","List of all people:",returnList);
        System.out.println(returnObj.toString());

//        class Teste2 extends ResourceSupport{
//            public String teste2;
//
//            public Teste2(String name){
//                this.teste2 = name;
//            }
//        }
//
//        class Teste  {
//            public String teste;
//            public List<Teste2> list;
//
//            public Teste(){
//                this.teste = "BLAASSSS";
//                this.list = new ArrayList<Teste2>(){{
//                    add(new Teste2("OLA"));
//                    add(new Teste2("HELLO"));
//                }};
//            }
//        }
//
//        Teste teste = new Teste();

        return ResponseEntity.ok(returnObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnObjectSingle> update (
             @PathVariable(value = "id")long id, @RequestBody Person person) throws PersonNotFoundExeption {

        person.setId(personService.GetPersonById(id).getId());
        personService.updatePerson(person);

        ReturnObjectSingle object = new ReturnObjectSingle("Ok","Person Updated");
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return  ResponseEntity.ok(object);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnObjectSingle> delete (@PathVariable(value = "id") long id) throws PersonNotFoundExeption {

        personService.deletePerson(id);
        ReturnObjectSingle object = new ReturnObjectSingle("deleted","Person Deleted");
        object.add(linkTo(PersonController.class).withRel("index"));
        return ResponseEntity.ok(object);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReturnObjectSingle> readById(@PathVariable(value = "id") long id) throws PersonNotFoundExeption {
        Person person = personService.GetPersonById(id);
        ReturnObjectSingle object = new ReturnObjectSingle("Ok","Person retrieved",person);
        object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
        object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
        return ResponseEntity.ok(object);
    }

    private void ResponseListCreator(List<Person> people, List<ReturnObjectSingle> returnList) throws PersonNotFoundExeption {
        ReturnObjectSingle object;
        for(Person person:people){
            object = new ReturnObjectSingle(person);
            object.add(linkTo(methodOn(PersonController.class).readById(person.getId())).withSelfRel().withType("GET"));
            object.add(linkTo(methodOn(PersonController.class).delete(person.getId())).withRel("Delete").withType("DELETE"));
            returnList.add(object);
        }
    }



}
