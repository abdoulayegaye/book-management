package com.xoslu.tech.bookmanagment.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthInfo {
    @NotNull private String username;
    @NotNull private String password;
    @NotNull private String grantType;
}
