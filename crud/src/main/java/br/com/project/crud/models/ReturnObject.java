package br.com.project.crud.models;


import org.springframework.lang.Nullable;

import java.util.List;

public class ReturnObject {

    private String Status;
    private String Message;
    @Nullable
    private Person person;
    @Nullable
    private List<Person> people;

    public ReturnObject(String status, String message) {
        Status = status;
        Message = message;
    }

    public ReturnObject(String status, String message, Person person) {
        Status = status;
        Message = message;
        this.person = person;
//        this.people = new ArrayList<Person>();
//        this.people.add(person);
    }

    public ReturnObject(String status, String message, List<Person> people) {
        Status = status;
        Message = message;
        this.people = people;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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


    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
