package br.com.project.crud.utils;

import br.com.project.crud.models.Person;
import org.springframework.hateoas.ResourceSupport;

public class ReturnObjectSingle extends ResourceSupport {

    private String status;
    private String message;
    private Person person;

    public ReturnObjectSingle(){}

    public ReturnObjectSingle(Person person){
        this.person = person;
    }
    public ReturnObjectSingle(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReturnObjectSingle(String status, String message, Person person) {
        this.status = status;
        this.message = message;
        this.person = person;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "ReturnObjectSingle{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", person=" + person +
                '}';
    }
}
