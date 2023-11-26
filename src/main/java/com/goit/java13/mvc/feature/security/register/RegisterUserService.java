package com.goit.java13.mvc.feature.security.register;

import com.goit.java13.mvc.feature.security.user.User;
import com.goit.java13.mvc.feature.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class RegisterUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterUserResponse register(RegisterUserRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()) {
            return RegisterUserResponse.failed(RegisterUserResponse.Error.invalidUsername);
        }

        if (userRepository.findByUsername(request.getUsername()) != null) {
            return RegisterUserResponse.failed(RegisterUserResponse.Error.usernameAlreadyExists);
        }

        if (request.getPassword() == null || request.getPassword().strip().length() < 5) {
            return RegisterUserResponse.failed(RegisterUserResponse.Error.invalidPassword);
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setRole("ROLE_USER");
        newUser.setEnabled((short) 1);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);

        return RegisterUserResponse.success();
    }
}
