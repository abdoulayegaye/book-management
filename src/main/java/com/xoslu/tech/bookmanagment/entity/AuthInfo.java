package com.sonatel.sdc.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.ToString;


public class AuthInfo {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String grant_type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", grant_type='" + grant_type + '\'' +
            '}';
    }
}
