package br.com.project.crud.models;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_CLIENT;

    @JsonValue
    public String getAuthority() {
        return name();
    }

}