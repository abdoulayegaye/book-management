package com.sonatel.sdc.domain;

import lombok.Data;

public class AuthTokenRequest {

    private String username;

    private String password;

    private String client_id = "internal";

    private String client_secret = "internal";

    private String grant_type;

    private String refresh_token;

    public AuthTokenRequest(String username, String password, String grant_type) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
    }

    public AuthTokenRequest(String grant_type, String refresh_token) {
        this.grant_type = grant_type;
        this.refresh_token = refresh_token;
    }

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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
