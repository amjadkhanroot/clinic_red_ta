import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {IPatientRecord} from "../../payloads/IPatientRecord";
import {ActivatedRoute, Router} from "@angular/router";
import {NgxSpinnerService} from "ngx-spinner";
import {ToastrService} from "ngx-toastr";
import {ClinicService} from "../../clinic.service";

@Component({
  selector: 'app-patient-record-details-page',
  templateUrl: './patient-record-details-page.component.html',
  styleUrls: ['./patient-record-details-page.component.scss']
})
export class PatientRecordDetailsPageComponent implements OnInit {

  patientRecord: IPatientRecord;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private spinnerService: NgxSpinnerService,
              private clinicService: ClinicService,
              private toastrService: ToastrService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.patientRecord = this.activatedRoute?.snapshot?.data['record']?.data;
  }

  delete() {
    this.clinicService.deletePatientRecord(this.patientRecord.id).subscribe(res => {
      this.router.navigate(['/clinic/patient-profile-list', this.patientRecord.patientProfile.id])
      this.toastrService.success("successfully created!", "Success");
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }

  update() {
    this.router.navigate(['/clinic/update-patient-record', this.patientRecord.id])
  }
}
