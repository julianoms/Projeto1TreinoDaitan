package br.com.project.crud.utils;

public class ReturnObjUser {
    private String status;
    private String message;
    private String jwt;

    public ReturnObjUser(String status, String message, String jwt) {
        this.status = status;
        this.message = message;
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
