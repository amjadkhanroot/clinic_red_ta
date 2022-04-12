package com.amjadcode.clinic.payloads.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PatientRecordUpdate(
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
