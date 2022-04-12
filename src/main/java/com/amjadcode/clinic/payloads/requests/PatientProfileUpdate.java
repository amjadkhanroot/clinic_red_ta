package com.amjadcode.clinic.payloads.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record PatientProfileUpdate(
        @NotNull
        Boolean smoking,
        @NotNull
        Boolean diabetes
) {

}
