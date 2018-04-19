package br.com.project.crud.models;


import org.springframework.hateoas.ResourceSupport;

public class ReturnObjectSingle extends ResourceSupport {

    private String Status;
    private String Message;
    private Person person;


    public ReturnObjectSingle(String status, String message) {
        Status = status;
        Message = message;
    }

    public ReturnObjectSingle(String status, String message, Person person) {
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
