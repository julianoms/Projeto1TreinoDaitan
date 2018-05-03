package br.com.project.crud.utils;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;


public class ReturnObj  {
    private String status;
    private String message;
    private List<ReturnObjectSingle> people;

    public ReturnObj(){}

    public ReturnObj(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReturnObj(String status, String message, List<ReturnObjectSingle> people) {
        this.status = status;
        this.message = message;
        this.people = people;
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

    public List<ReturnObjectSingle> getPeople() {
        return people;
    }

    public void setPeople(List<ReturnObjectSingle> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "ReturnObj{" +
                "status='" + this.status + '\'' +
                ", message='" + this.message + '\'' +
                ", people=" + this.people +
                '}';
    }
}
