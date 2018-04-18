package br.com.project.crud.models;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;

public class ReturnObject extends ResourceSupport {

    private String Status;
    private String Message;
    @Nullable
    private Person person;


    public ReturnObject(String status, String message) {
        Status = status;
        Message = message;
    }

    public ReturnObject(String status, String message, Person person) {
        Status = status;
        Message = message;
        this.person = person;
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


}
