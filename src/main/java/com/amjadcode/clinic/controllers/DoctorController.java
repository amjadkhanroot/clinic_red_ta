package com.amjadcode.clinic.controllers;

import com.amjadcode.authentication.models.User;
import com.amjadcode.authentication.services.AuthService;
import com.amjadcode.clinic.models.PatientProfile;
import com.amjadcode.clinic.models.PatientRecord;
import com.amjadcode.clinic.payloads.requests.PatientProfileCreation;
import com.amjadcode.clinic.payloads.requests.PatientProfileUpdate;
import com.amjadcode.clinic.payloads.requests.PatientRecordCreation;
import com.amjadcode.clinic.payloads.requests.PatientRecordUpdate;
import com.amjadcode.clinic.services.PatientProfileService;
import com.amjadcode.clinic.services.PatientRecordService;
import com.amjadcode.commons.ApiResponse;
import com.amjadcode.commons.EGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@PreAuthorize("hasRole('DOCTOR')")
@RequestMapping("api/v1/clinic/doctor")
public class DoctorController {

    @Autowired
    AuthService authService;
    @Autowired
    PatientProfileService patientProfileService;
    @Autowired
    PatientRecordService patientRecordService;

    @GetMapping("patient-profiles")
    public HttpEntity<?> profilesList() {
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.getAll()), HttpStatus.OK);
    }

    @GetMapping("patient-profiles/{id}")
    public HttpEntity<?> getProfilesById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.getProfileById(id)), HttpStatus.OK);
    }

    @PostMapping("patient-profiles/create")
    public HttpEntity<?> createProfile(@RequestBody @Valid PatientProfileCreation profileCreation) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        User patient = authService.getUserById(profileCreation.patient());

        if (patient == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid patient!"), HttpStatus.BAD_REQUEST);

        if (patientProfileService.getByPatient(patient.getId()) != null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "Already exists!"), HttpStatus.BAD_REQUEST);

        if (!profileCreation.gender().toUpperCase(Locale.ROOT).equals(EGender.MALE.name()) && !profileCreation.gender().toUpperCase(Locale.ROOT).equals(EGender.FEMALE.name()))
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid gender!"), HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.createProfile(profileCreation, patient, loggedUser)), HttpStatus.OK);

    }

    @PostMapping("patient-profiles/update/{id}")
    public HttpEntity<?> updateProfile(@PathVariable Long id, @RequestBody @Valid PatientProfileUpdate profileUpdate) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        PatientProfile patientProfile = patientProfileService.getProfileById(id);

        if (patientProfile == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid profile!"), HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.updateProfile(patientProfile, profileUpdate, loggedUser)), HttpStatus.OK);
    }

    @GetMapping("records-by-profile/{profileId}")
    public HttpEntity<?> recordsList(@PathVariable Long profileId) {
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.getRecordByProfileId(profileId)), HttpStatus.OK);
    }

    @GetMapping("records/{id}")
    public HttpEntity<?> getRecordsById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.getRecordById(id)), HttpStatus.OK);
    }

    @PostMapping("records/create")
    public HttpEntity<?> createRecord(@RequestBody @Valid PatientRecordCreation patientRecordCreation) throws Exception {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        PatientProfile patientProfile = patientProfileService.getProfileById(patientRecordCreation.patientProfile());

        if (patientProfile == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid patient profile!"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.createRecord(patientProfile, patientRecordCreation, loggedUser)), HttpStatus.OK);
    }

    @PostMapping("records/update/{id}")
    public HttpEntity<?> updateRecord(@PathVariable Long id, @RequestBody @Valid PatientRecordUpdate recordUpdate) throws Exception {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        PatientRecord patientRecord = patientRecordService.getRecordById(id);
        if (patientRecord == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid patient profile!"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.updateRecord(patientRecord, recordUpdate, loggedUser)), HttpStatus.OK);
    }

    // only the creator can delete his own.
    @DeleteMapping("records/delete/{id}")
    public HttpEntity<?> deleteRecord(@PathVariable Long id) throws Exception {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        PatientRecord patientRecord = patientRecordService.getRecordById(id, loggedUser.getId());
        if (patientRecord == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "select a valid patient profile!"), HttpStatus.BAD_REQUEST);

        patientRecordService.deleteRecord(patientRecord, loggedUser);
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", ""), HttpStatus.OK);
    }

    @GetMapping("patient-list")
    public HttpEntity<?> patientList() {
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.getAllPatient()), HttpStatus.OK);
    }

}
