package com.amjadcode.clinic.repositories;

import com.amjadcode.authentication.models.User;
import com.amjadcode.clinic.models.PatientProfile;
import com.amjadcode.clinic.models.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRecordRepo extends JpaRepository<PatientRecord, Long> {

    List<PatientRecord> findAllByPatientProfile_Id(Long id);

    PatientRecord findByIdAndCreatedBy_Id(Long id, Long createdBy);

    PatientRecord findByIdAndPatientProfile(Long id, PatientProfile patient);

    @Query("select COALESCE(max(p.id), 0) from PatientRecord p")
    Long getMaxId();

}

