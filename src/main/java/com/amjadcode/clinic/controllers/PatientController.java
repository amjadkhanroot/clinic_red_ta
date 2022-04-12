package com.amjadcode.clinic.controllers;

import com.amjadcode.authentication.models.User;
import com.amjadcode.authentication.services.AuthService;
import com.amjadcode.clinic.models.PatientProfile;
import com.amjadcode.clinic.services.PatientProfileService;
import com.amjadcode.clinic.services.PatientRecordService;
import com.amjadcode.commons.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@PreAuthorize("hasRole('PATIENT')")
@RequestMapping("api/v1/clinic/patient")
public class PatientController {

    @Autowired
    AuthService authService;
    @Autowired
    PatientProfileService patientProfileService;
    @Autowired
    PatientRecordService patientRecordService;

    @GetMapping("profile")
    public HttpEntity<?> getProfile(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientProfileService.getByPatient(loggedUser.getId())), HttpStatus.OK);
    }

    @GetMapping("records")
    public HttpEntity<?> getRecords(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        PatientProfile profile = patientProfileService.getByPatient(loggedUser.getId());
        if (profile == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "Contact your doctor to create a profile for you first!"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.getRecordByProfileId(profile.getId())), HttpStatus.OK);
    }

    @GetMapping("records/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        PatientProfile profile = patientProfileService.getByPatient(loggedUser.getId());
        if (profile == null)
            return new ResponseEntity<>(new ApiResponse(false, "400", "failed", "Contact your doctor to create a profile for you first!"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(true, "200", "success", "", patientRecordService.getRecordById(id, profile)), HttpStatus.OK);
    }
}
