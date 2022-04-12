import { Component, OnInit } from '@angular/core';
import {IPatientRecord} from "../../payloads/IPatientRecord";
import {ActivatedRoute, Router} from "@angular/router";
import {NgxSpinnerService} from "ngx-spinner";
import {ClinicService} from "../../clinic.service";
import {ToastrService} from "ngx-toastr";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IPatientProfile} from "../../payloads/IPatientProfile";

@Component({
  selector: 'app-create-patient-record-page',
  templateUrl: './create-patient-record-page.component.html',
  styleUrls: ['./create-patient-record-page.component.scss']
})
export class CreatePatientRecordPageComponent implements OnInit {

  patientRecordFrom: FormGroup;
  patientProfile: IPatientProfile;
  patientRecord: IPatientRecord;
  isEdit = false;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private spinnerService: NgxSpinnerService,
              private clinicService: ClinicService,
              private toastrService: ToastrService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.patientProfile = this.activatedRoute?.snapshot?.data['profile']?.data;
    this.patientRecord = this.activatedRoute?.snapshot?.data['record']?.data;
    if (this.patientRecord != undefined)
        this.isEdit = true;

    if (!this.isEdit){
      this.patientRecordFrom = this.formBuilder.group(
        {
          patientProfile    : [this.patientProfile.id],
          weight      : [null,Validators.required],
          height    : [null,Validators.required],
          temperature    : [null,Validators.required],
          bloodPressure      : [null,Validators.required],
          sugar      : [null,Validators.required],
          complaints      : [null,Validators.required],
          actionToken      :[null,Validators.required],
          description      : [null,Validators.required],
          diagnosis      : [null,Validators.required],
          recommendation      :[null,Validators.required],
          comment      : [null,Validators.required],
        }
      );
    }else {
      this.patientRecordFrom = this.formBuilder.group(
        {
          patientProfile    : [this.patientRecord.patientProfile],
          weight      : [this.patientRecord.weight,Validators.required],
          height    : [this.patientRecord.height,Validators.required],
          temperature    : [this.patientRecord.temperature,Validators.required],
          bloodPressure      : [this.patientRecord.bloodPressure,Validators.required],
          sugar      : [this.patientRecord.sugar,Validators.required],
          complaints      : [this.patientRecord.complaints,Validators.required],
          actionToken      :[this.patientRecord.actionToken,Validators.required],
          description      : [this.patientRecord.description,Validators.required],
          diagnosis      : [this.patientRecord.diagnosis,Validators.required],
          recommendation      :[this.patientRecord.recommendation,Validators.required],
          comment      : [this.patientRecord.comment,Validators.required],
        }
      );
    }

  }

  submit() {
    if (this.patientRecordFrom.valid){
      if (!this.isEdit){
        this.clinicService.createPatientRecord(this.patientRecordFrom.value).subscribe(res => {
          this.router.navigate(['/clinic/patient-profile-list'])
          this.toastrService.success("successfully created!", "Success");
        }, error => {
          this.toastrService.error(error?.error.message, error?.error.description);
        });
      }else{
        this.clinicService.updatePatientRecord(this.patientRecord.id, this.patientRecordFrom.value).subscribe(res => {
          this.router.navigate(['/clinic/patient-profile-list'])
          this.toastrService.success("successfully updated!", "Success");
        }, error => {
          this.toastrService.error(error?.error.message, error?.error.description);
        });
      }
    }else {
      this.toastrService.error("Check your inputs!", "Failed");
    }

  }


}
