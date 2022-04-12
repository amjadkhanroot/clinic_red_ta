package com.amjadcode.clinic.services;

import com.amjadcode.authentication.models.Role;
import com.amjadcode.authentication.models.User;
import com.amjadcode.authentication.models.UserInfo;
import com.amjadcode.authentication.repositories.RoleRepository;
import com.amjadcode.authentication.repositories.UserRepository;
import com.amjadcode.clinic.models.PatientProfile;
import com.amjadcode.clinic.models.PatientProfileHistory;
import com.amjadcode.clinic.payloads.requests.PatientProfileCreation;
import com.amjadcode.clinic.payloads.requests.PatientProfileUpdate;
import com.amjadcode.clinic.repositories.PatientProfileHistoryRepo;
import com.amjadcode.clinic.repositories.PatientProfileRepo;
import com.amjadcode.commons.EAction;
import com.amjadcode.commons.EGender;
import com.amjadcode.commons.ERoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PatientProfileService {

    @Autowired
    PatientProfileRepo profileRepo;
    @Autowired
    PatientProfileHistoryRepo profileHistoryRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    // no delete action for a doctor, only by admin

    public PatientProfile createProfile(PatientProfileCreation profileCreation, User patient, User loggedUser) {

        PatientProfile patientProfile = new PatientProfile();

        patientProfile.setId(0L);
        patientProfile.setRefCode(getProfileNextRefCode(ClinicConstant.CLC.name(), (profileRepo.getMaxId()+1)));
        patientProfile.setPatient(patient);
        patientProfile.setFullName(profileCreation.fullName());
        patientProfile.setGender(EGender.valueOf(profileCreation.gender().toUpperCase(Locale.ROOT)));
        patientProfile.setDateOfBirth(profileCreation.dateOfBirth());
        patientProfile.setDiabetes(profileCreation.diabetes());
        patientProfile.setSmoking(profileCreation.smoking());
        patientProfile.setCreatedBy(loggedUser);

        return profileRepo.saveAndFlush(patientProfile);
    }

    public PatientProfile updateProfile(PatientProfile patientProfile, PatientProfileUpdate profileUpdate, User loggedUser) {
        saveProfileHistory(patientProfile, EAction.MODIFY.name(), loggedUser);

        patientProfile.setDiabetes(profileUpdate.diabetes());
        patientProfile.setSmoking(profileUpdate.smoking());

        return profileRepo.saveAndFlush(patientProfile);
    }

    private void saveProfileHistory(PatientProfile patientProfile, String action, User loggedUser) {
        PatientProfileHistory profileHistory = new PatientProfileHistory();
        profileHistory.setId(0L);
        profileHistory.setRefId(patientProfile.getId());
        profileHistory.setPatient(patientProfile.getPatient().getId());
        profileHistory.setFullName(patientProfile.getFullName());
        profileHistory.setGender(patientProfile.getGender().name());
        profileHistory.setDateOfBirth(patientProfile.getDateOfBirth());
        profileHistory.setSmoking(patientProfile.getSmoking());
        profileHistory.setDiabetes(patientProfile.getDiabetes());
        profileHistory.setRefCode(patientProfile.getRefCode());
        profileHistory.setCreatedBy(patientProfile.getCreatedBy().getId());
        profileHistory.setAction(action);
        profileHistory.setModifiedUser(loggedUser.getId());
        profileHistoryRepo.save(profileHistory);
    }

    private String getProfileNextRefCode(String moduleCode, long serialId) {
        return moduleCode + "P" + DateTimeFormatter.ofPattern("yyyyMM").format(LocalDateTime.now()) + serialId;
    }

    public PatientProfile getProfileById(Long id) {
        return profileRepo.findById(id).orElse(null);
    }

    //all doctors can get all patient profile
    public List<PatientProfile> getAll() {
        return profileRepo.findAll();
    }

    public PatientProfile getByPatient(Long patientId) {
        return profileRepo.findByPatient_Id(patientId);
    }

    public List<UserInfo> getAllPatient() {
        return profileRepo.getAllPatient();
    }
}
