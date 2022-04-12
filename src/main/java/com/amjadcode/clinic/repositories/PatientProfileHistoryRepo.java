package com.amjadcode.clinic.repositories;

import com.amjadcode.clinic.models.PatientProfileHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileHistoryRepo extends JpaRepository<PatientProfileHistory, Long> {

}
