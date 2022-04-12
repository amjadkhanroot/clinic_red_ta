package com.amjadcode.clinic.repositories;

import com.amjadcode.clinic.models.PatientRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordHistoryRepo extends JpaRepository<PatientRecordHistory, Long> {
}

