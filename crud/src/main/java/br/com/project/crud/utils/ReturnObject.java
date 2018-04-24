package br.com.project.crud.utils;

import br.com.project.crud.models.Person;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class ReturnObject extends ResourceSupport {

    private String Status;
    private String Message;
    private Person person;
    private Collection<Person> people;

    public ReturnObject (String status, String message) {
        Status = status;
        Message = message;
    }

    public ReturnObject(String status, String message, Person person) {
        Status = status;
        Message = message;
        this.person = person;
    }

    public ReturnObject(String status, String message, Collection<Person> people) {
        Status = status;
        Message = message;
        this.people = people;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}
