package com.amjadcode.clinic.services;

import com.amjadcode.authentication.models.User;
import com.amjadcode.clinic.models.PatientProfile;
import com.amjadcode.clinic.models.PatientRecord;
import com.amjadcode.clinic.models.PatientRecordHistory;
import com.amjadcode.clinic.payloads.requests.PatientRecordCreation;
import com.amjadcode.clinic.payloads.requests.PatientRecordUpdate;
import com.amjadcode.clinic.repositories.PatientRecordHistoryRepo;
import com.amjadcode.clinic.repositories.PatientRecordRepo;
import com.amjadcode.commons.EAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PatientRecordService {

    @Autowired
    PatientRecordRepo recordRepo;
    @Autowired
    PatientRecordHistoryRepo recordHistoryRepo;

    public PatientRecord createRecord(PatientProfile patientProfile, PatientRecordCreation recordCreation, User loggedUser) {

        return recordRepo.saveAndFlush(new PatientRecord(
                0L,
                patientProfile,
                recordCreation.weight(),
                recordCreation.height(),
                recordCreation.temperature(),
                recordCreation.bloodPressure(),
                recordCreation.sugar(),
                recordCreation.complaints(),
                recordCreation.actionToken(),
                recordCreation.description(),
                recordCreation.diagnosis(),
                recordCreation.recommendation(),
                recordCreation.comment(),
                loggedUser,
                getRecordNextRefCode(ClinicConstant.CLC.name(), (recordRepo.getMaxId()+1)),
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
    }

    public PatientRecord updateRecord(PatientRecord patientRecord, PatientRecordUpdate recordUpdate, User loggedUser) {
        saveRecordHistory(patientRecord, EAction.MODIFY.name(), loggedUser.getId());

        patientRecord.setComment(recordUpdate.comment());
        patientRecord.setActionToken(recordUpdate.actionToken());
        patientRecord.setDescription(recordUpdate.description());
        patientRecord.setDiagnosis(recordUpdate.diagnosis());
        patientRecord.setRecommendation(recordUpdate.recommendation());
        patientRecord.setComplaints(recordUpdate.complaints());

        return recordRepo.saveAndFlush(patientRecord);
    }

    public void saveRecordHistory(PatientRecord patientRecord, String action, Long modifiedUser){
        PatientRecordHistory patientRecordHistory = new PatientRecordHistory();
        patientRecordHistory.setId(0L);
        patientRecordHistory.setRefCode(patientRecord.getRefCode());
        patientRecordHistory.setRefId(patientRecord.getId());
        patientRecordHistory.setPatientProfile(patientRecord.getPatientProfile().getId());
        patientRecordHistory.setWeight(patientRecord.getWeight());
        patientRecordHistory.setHeight(patientRecord.getHeight());
        patientRecordHistory.setTemperature(patientRecord.getTemperature());
        patientRecordHistory.setBloodPressure(patientRecord.getBloodPressure());
        patientRecordHistory.setSugar(patientRecord.getSugar());
        patientRecordHistory.setDescription(patientRecord.getDescription());
        patientRecordHistory.setActionToken(patientRecord.getActionToken());
        patientRecordHistory.setComplaints(patientRecord.getComplaints());
        patientRecordHistory.setDiagnosis(patientRecord.getDiagnosis());
        patientRecordHistory.setRecommendation(patientRecord.getRecommendation());
        patientRecordHistory.setComment(patientRecord.getComment());
        patientRecordHistory.setCreatedBy(patientRecord.getCreatedBy().getId());

        patientRecordHistory.setAction(action);
        patientRecordHistory.setModifiedUser(modifiedUser);
        recordHistoryRepo.save(patientRecordHistory);

    }

    private String getRecordNextRefCode(String moduleCode, long serialId) {
        return moduleCode + "R" + DateTimeFormatter.ofPattern("yyyyMM").format(LocalDateTime.now()) + serialId;
    }

    public PatientRecord getRecordById(Long id) {
        return recordRepo.findById(id).orElse(null);
    }

    public PatientRecord getRecordById(Long id, Long createdId) {
        return recordRepo.findByIdAndCreatedBy_Id(id, createdId);
    }

    public PatientRecord getRecordById(Long id, PatientProfile patient) {
        return recordRepo.findByIdAndPatientProfile(id,patient);
    }

    public List<PatientRecord> getAll() {
        return recordRepo.findAll();
    }

    public void deleteRecord(PatientRecord patientRecord, User loggedUser) {
        saveRecordHistory(patientRecord, EAction.MODIFY.name(), loggedUser.getId());
        recordRepo.delete(patientRecord);
    }

    public List<PatientRecord> getRecordByProfileId(Long profileId) {
        return recordRepo.findAllByPatientProfile_Id(profileId);
    }
}
