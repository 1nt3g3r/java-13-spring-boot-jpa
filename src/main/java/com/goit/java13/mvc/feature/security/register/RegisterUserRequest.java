package com.goit.java13.mvc.feature.security.register;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
