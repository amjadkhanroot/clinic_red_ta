package com.amjadcode.clinic.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clc_patient_records_history")
public class PatientRecordHistory {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private Long patientProfile;
    private Double weight;
    private Double height;
    private Double temperature;
    private String bloodPressure;
    private Double sugar;
    private String complaints;
    private String actionToken;
    private String description;
    private String diagnosis;
    private String recommendation;
    private String comment;
    private Long createdBy;
    private String refCode;
    private Long modifiedUser;
    private String action;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PatientRecordHistory that = (PatientRecordHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
