import { Component, OnInit } from '@angular/core';
import {IUser} from "../../payloads/IUser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {NgxSpinnerService} from "ngx-spinner";
import {ToastrService} from "ngx-toastr";
import {ClinicService} from "../../clinic.service";
import {IPatientProfile} from "../../payloads/IPatientProfile";

@Component({
  selector: 'app-create-patient-profile-page',
  templateUrl: './create-patient-profile-page.component.html',
  styleUrls: ['./create-patient-profile-page.component.scss']
})
export class CreatePatientProfilePageComponent implements OnInit {
  patientProfileFrom: FormGroup;
  patientList: IUser[] = [];
  patientProfile: IPatientProfile;
  isEdit = false;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private spinnerService: NgxSpinnerService,
              private clinicService: ClinicService,
              private toastrService: ToastrService,
              private formBuilder: FormBuilder) {


  }

  ngOnInit(): void {
    this.patientProfile = this.activatedRoute?.snapshot?.data['profile']?.data;

    if (this.patientProfile != undefined)
      this.isEdit = true;

    this.getAllPatient();

    if (!this.isEdit){
      this.patientProfileFrom = this.formBuilder.group(
        {
          patient: [null, Validators.required],
          fullName: [null, Validators.required],
          gender: [null, Validators.required],
          smoking: [false, Validators.required],
          dateOfBirth: [null, Validators.required],
          diabetes: [false, Validators.required],
        }
      );
    }else {
      this.patientProfileFrom = this.formBuilder.group(
        {
          patient: [this.patientProfile.patient],
          fullName: [this.patientProfile.fullName],
          gender: [this.patientProfile.gender],
          smoking: [this.patientProfile.smoking, Validators.required],
          dateOfBirth: [this.patientProfile.dateOfBirth, Validators.required],
          diabetes: [this.patientProfile.diabetes],
        }
      );
    }

  }

  getAllPatient() {
    this.clinicService.getAllPatient().subscribe(res => {
      this.patientList = res.data;
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }

  submit() {
    if (this.patientProfileFrom.valid){
      if (!this.isEdit){
          this.clinicService.createPatientProfile(this.patientProfileFrom.value).subscribe(res => {
            this.router.navigate(['/clinic/patient-profile-list'])
            this.toastrService.success("successfully created!", "Success");
            }, error => {
              this.toastrService.error(error.error.message, error.error.description);
            });
      }else{
            this.clinicService.updatePatientProfile(this.patientProfile.id, this.patientProfileFrom.value).subscribe(res => {
              this.router.navigate(['/clinic/patient-profile-list'])
              this.toastrService.success("successfully updated!", "Success");
            }, error => {
              this.toastrService.error(error.error.message, error.error.description);
            });
          }
    }else {
      this.toastrService.error("Check your inputs!", "Failed");
    }

  }

}
