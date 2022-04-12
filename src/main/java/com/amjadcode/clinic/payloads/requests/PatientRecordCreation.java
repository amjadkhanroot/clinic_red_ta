package com.amjadcode.clinic.payloads.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PatientRecordCreation(
        @NotNull
        Long patientProfile,
        @NotNull
        Double weight,
        @NotNull
        Double height,
        @NotNull
        Double temperature,
        @NotNull @NotBlank
        String bloodPressure,
        @NotNull
        Double sugar,
        @NotNull @NotBlank
        String complaints,
        @NotNull @NotBlank
        String actionToken,
        String description,
        @NotNull @NotBlank
        String diagnosis,
        String recommendation,
        String comment
) {

}
