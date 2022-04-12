package com.amjadcode.clinic.repositories;

import com.amjadcode.authentication.models.Role;
import com.amjadcode.authentication.models.User;
import com.amjadcode.authentication.models.UserInfo;
import com.amjadcode.clinic.models.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PatientProfileRepo extends JpaRepository<PatientProfile, Long> {

    PatientProfile findByPatient_Id(Long id);

    @Query(nativeQuery = true, value = "select u.* from users u left join users_roles ur on u.id = ur.user_id where ur.roles_id = 2 and u.id not in (select patient_id from clc_patient_profiles)")
    List<UserInfo> getAllPatient();

    @Query("select COALESCE(max(p.id), 0) from PatientProfile p")
    Long getMaxId();
}

