package com.amjadcode.authentication.payloads.responses;

public record RegisterResponse(
        Long id,
                              String username,
                              String email) {
}
