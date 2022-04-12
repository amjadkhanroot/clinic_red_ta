import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {IPatientProfile} from "../../payloads/IPatientProfile";
import {NgxSpinnerService} from "ngx-spinner";
import {IPatientRecord} from "../../payloads/IPatientRecord";
import {ClinicService} from "../../clinic.service";
import {ToastrService} from "ngx-toastr";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {transition, trigger, useAnimation} from "@angular/animations";
import {fadeIn} from "ng-animate";


@Component({
  selector: 'app-patient-profile-details-page',
  templateUrl: './patient-profile-details-page.component.html',
  styleUrls: ['./patient-profile-details-page.component.scss']
})
export class PatientProfileDetailsPageComponent implements OnInit {

  patientProfile: IPatientProfile;
  patientRecords: IPatientRecord[] = [];
  patientProfileFrom: FormGroup;
  displayedColumns: string[] = ['id', 'complaints', 'diagnosis', 'actionToken', 'refCode'];

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private spinnerService: NgxSpinnerService,
              private clinicService: ClinicService,
              private toastrService: ToastrService,
              private formBuilder: FormBuilder) {

    this.patientProfileFrom = this.formBuilder.group(
      {
        fullName: [null, Validators.required],
        gender: [null, Validators.required],
        smoking: [false, Validators.required],
        dateOfBirth: [null, Validators.required],
        diabetes: [false, Validators.required],
      }
    );
  }

  ngOnInit(): void {
    this.patientProfile = this.activatedRoute?.snapshot?.data['profile']?.data;
    if (this.patientProfile != undefined)
        this.getRecordsByProfile(this.patientProfile.id);

  }

  getRecordsByProfile(id: any): void {
    this.clinicService.recordsByProfile(id).subscribe(res => {
      this.patientRecords = res.data;
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }




  goToCreateProfile() {
    this.router.navigate(['/clinic/create-patient-profile'])
  }
  goToUpdateProfile() {
    this.router.navigate(['/clinic/update-patient-profile/' + this.patientProfile.id])
  }
  selectedRecord(id: any) {
    this.router.navigate(['/clinic/patient-record-details/'+ id])
  }

  createRecord() {
    this.router.navigate(['/clinic/create-patient-record/'+this.patientProfile.id])
  }
}
