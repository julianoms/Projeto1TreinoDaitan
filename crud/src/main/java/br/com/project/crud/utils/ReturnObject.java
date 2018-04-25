package br.com.project.crud.utils;

import org.springframework.hateoas.ResourceSupport;

public class ReturnObject extends ResourceSupport {

    private String Status;
    private String Message;

    public ReturnObject (String status, String message) {
        Status = status;
        Message = message;
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
