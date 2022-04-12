package com.amjadcode.authentication.payloads.requests;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public record RegisterRequest(@NotBlank
                              @Size(min = 3, max = 20)
                              String username,
                              @NotBlank
                              @Size(max = 50)
                              @Email
                              String email,
                              @NotBlank
                              @Size(min = 6, max = 40)
                              String password,
                              String role) {
}
