package com.xoslu.tech.bookmanagment.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenRequest {

    private String username;
    private String password;
    private String clientId = "internal";
    private String clientSecret = "internal";
    private String grantType;
    private String refreshToken;

    public AuthTokenRequest(String username, String password, String grantType) {
        this.username = username;
        this.password = password;
        this.grantType = grantType;
    }

    public AuthTokenRequest(String grantType, String refreshToken) {
        this.grantType = grantType;
        this.refreshToken = refreshToken;
    }
}
