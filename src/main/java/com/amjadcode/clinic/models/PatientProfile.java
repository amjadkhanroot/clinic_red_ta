package com.amjadcode.clinic.models;

import com.amjadcode.authentication.models.User;
import com.amjadcode.commons.EGender;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clc_patient_profiles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id"})
        })
public class PatientProfile {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private User patient;
    @NotNull
    @Size(max = 255)
    private String fullName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private EGender gender;
    @NotNull
    @Column(columnDefinition = "BIT default 0")
    private Boolean smoking;
    @NotNull
    @Column(columnDefinition = "date")
    private LocalDate dateOfBirth;
    @NotNull
    @Column(columnDefinition = "BIT default 0")
    private Boolean diabetes;
    @OneToOne
    private User createdBy;
    private String refCode;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PatientProfile that = (PatientProfile) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
