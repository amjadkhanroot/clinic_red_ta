package com.amjadcode.authentication.payloads.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public record LoginRequest(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {

}
