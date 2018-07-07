package com.bsptechs.beans;

import lombok.Data;

@Data
public class UserFormDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String facebookLogin;
    private boolean enabled;
}
