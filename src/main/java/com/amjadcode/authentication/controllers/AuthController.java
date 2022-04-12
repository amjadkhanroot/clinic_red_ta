package com.amjadcode.authentication.controllers;

import com.amjadcode.authentication.payloads.requests.LoginRequest;
import com.amjadcode.authentication.services.AuthService;
import com.amjadcode.commons.ERoleType;
import com.amjadcode.authentication.payloads.requests.RegisterRequest;
import com.amjadcode.authentication.models.Role;
import com.amjadcode.commons.ApiResponse;
import com.amjadcode.commons.ResponseErrorCode;
import com.amjadcode.security.services.JwtUtils;
import com.amjadcode.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public HttpEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "Logged In!", userDetails, jwt), HttpStatus.OK);
    }


    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (!AuthService.isStringOnlyAlphabet(registerRequest.username()))
            return new ResponseEntity<>(new ApiResponse(false, "400", "Bad request!", "username should only contain Alphabet!"), HttpStatus.BAD_REQUEST);

        if (authService.userExists(registerRequest)) {
            return new ResponseEntity<>(new ApiResponse(false, "400", "validation errors!", "Username/Email is already taken!"), HttpStatus.BAD_REQUEST);
        }

        // empty_role: ROLE_PATIENT, doctor: ROLE_DOCTOR
        String strRole = registerRequest.role();

        // if it is something rather than doctor return false
        if (strRole != null && !strRole.isBlank() && !strRole.toLowerCase(Locale.ROOT).equals("doctor"))
            return new ResponseEntity<>(new ApiResponse(false, "400", "Bad request!", "Contact Support, code: " + ResponseErrorCode.ROLE_IS_NOT_FOUND.code()), HttpStatus.BAD_REQUEST);

        Set<Role> roles = new HashSet<>();

        if (strRole == null || strRole.isBlank()) {
            Role patientRole = authService.roleExists(ERoleType.ROLE_PATIENT);
            if (patientRole == null)
                return new ResponseEntity<>(new ApiResponse(false, "400", "Bad request!", "Contact Support, code: " + ResponseErrorCode.ROLE_IS_NOT_FOUND.code()), HttpStatus.BAD_REQUEST);

            roles.add(patientRole);
        } else {
            // it should no one can create a doctor, only an admin or the system but for demo only!
            Role doctorRole = authService.roleExists(ERoleType.ROLE_DOCTOR);
            if (doctorRole == null)
                return new ResponseEntity<>(new ApiResponse(false, "400", "Bad request!", "Contact Support, code: " + ResponseErrorCode.ROLE_IS_NOT_FOUND.code()), HttpStatus.BAD_REQUEST);

            roles.add(doctorRole);
        }

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "User registered successfully!", authService.registerUser(registerRequest, roles)), HttpStatus.OK);
    }

}
