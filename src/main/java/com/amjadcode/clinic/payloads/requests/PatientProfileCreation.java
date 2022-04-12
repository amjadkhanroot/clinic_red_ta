package com.amjadcode.clinic.payloads.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record PatientProfileCreation(
        @NotNull
        Long patient,
        @NotNull
        @Size(max = 255) String fullName,
        @NotNull
        @Size(max = 6)
        String gender,
        @NotNull
        Boolean smoking,
        @NotNull
        LocalDate dateOfBirth,
        @NotNull
        Boolean diabetes
) {

}
