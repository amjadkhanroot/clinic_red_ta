package com.amjadcode.clinic.models;

import com.amjadcode.authentication.models.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clc_patient_records")
public class PatientRecord {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OneToOne
    private PatientProfile patientProfile;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @NotNull
    private Double temperature;
    @NotNull
    private String bloodPressure;
    @NotNull
    private Double sugar;
    @NotNull
    private String complaints;
    @NotNull
    private String actionToken;
    private String description;
    private String diagnosis;
    private String recommendation;
    private String comment;
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
        PatientRecord that = (PatientRecord) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
