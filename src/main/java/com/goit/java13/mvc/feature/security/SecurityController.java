package com.goit.java13.mvc.feature.security;

import com.goit.java13.mvc.feature.security.register.RegisterUserRequest;
import com.goit.java13.mvc.feature.security.register.RegisterUserResponse;
import com.goit.java13.mvc.feature.security.register.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SecurityController {
    private final RegisterUserService registerUserService;

    @PostMapping("/api/v1/user/register")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
        return registerUserService.register(request);
    }
}
