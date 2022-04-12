package com.amjadcode.clinic.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clc_patient_profiles_history")
public class PatientProfileHistory {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private Long patient;
    private String fullName;
    @Column(length = 6)
    private String gender;
    @Column(columnDefinition = "BIT default 0")
    private Boolean smoking;
    @Column(columnDefinition = "date")
    private LocalDate dateOfBirth;
    private Boolean diabetes;
    private Long createdBy;
    private String refCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long modifiedUser;
    private String action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PatientProfileHistory that = (PatientProfileHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
