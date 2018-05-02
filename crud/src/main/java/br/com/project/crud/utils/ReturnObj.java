package br.com.project.crud.utils;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;


public class ReturnObj extends ResourceSupport {
    private String status;
    private String message;
    private List<ReturnObjectSingle> obj;

    public ReturnObj(){}

    public ReturnObj(String status, String message, List<ReturnObjectSingle> obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
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

    public List<ReturnObjectSingle> getObj() {
        return obj;
    }

    public void setObj(List<ReturnObjectSingle> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ReturnObj{" +
                "status='" + this.status + '\'' +
                ", message='" + this.message + '\'' +
                ", obj=" + this.obj +
                '}';
    }
}
