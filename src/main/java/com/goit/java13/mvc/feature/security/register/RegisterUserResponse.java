package com.goit.java13.mvc.feature.security.register;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterUserResponse {
    private boolean success;
    private Error error;

    public static RegisterUserResponse success() {
        return RegisterUserResponse.builder().success(true).error(Error.ok).build();
    }

    public static RegisterUserResponse failed(Error error) {
        return RegisterUserResponse.builder().success(false).error(error).build();
    }

    public enum Error {
        ok,
        usernameAlreadyExists,
        invalidUsername,
        invalidPassword
    }
}
