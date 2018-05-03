package br.com.project.crud.utils;

import br.com.project.crud.models.Person;
import org.springframework.hateoas.ResourceSupport;

public class ReturnObjectSingle extends ResourceSupport {


    private Person person;

    public ReturnObjectSingle(){}

    public ReturnObjectSingle(Person person){
        this.person = person;
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
                ", person=" + person +
                '}';
    }
}
