package com.amjadcode.authentication.services;

import com.amjadcode.authentication.models.Role;
import com.amjadcode.authentication.models.User;
import com.amjadcode.authentication.payloads.requests.RegisterRequest;
import com.amjadcode.authentication.payloads.responses.RegisterResponse;
import com.amjadcode.authentication.repositories.RoleRepository;
import com.amjadcode.authentication.repositories.UserRepository;
import com.amjadcode.commons.ERoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public RegisterResponse registerUser(RegisterRequest registerRequest, Set<Role> roles) {
        User user = userRepository.saveAndFlush(new User(0L,registerRequest.username(),
                registerRequest.email(),
                encoder.encode(registerRequest.password()), true, roles));

        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public boolean userExists(RegisterRequest registerRequest) {
        return userRepository.existsAllByEmailOrUsername(registerRequest.email()
                ,registerRequest.username());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Role roleExists(ERoleType role) {
        return roleRepository.findByName(role).orElse(null);
    }

    public static boolean isStringOnlyAlphabet(String str)
    {
        return !str.equals("") && str.matches("^[a-zA-Z]*$");
    }
}
