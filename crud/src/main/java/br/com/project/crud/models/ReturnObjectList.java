package br.com.project.crud.models;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class ReturnObjectRead extends ResourceSupport {

    private String Status;
    private String Message;
    private Collection<Person> people;

    public ReturnObjectRead(String status, String message, Collection<Person> people) {
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

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}
