import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {IPatientRecord} from "../../payloads/IPatientRecord";
import {ActivatedRoute, Router} from "@angular/router";
import {NgxSpinnerService} from "ngx-spinner";
import {ToastrService} from "ngx-toastr";
import {ClinicService} from "../../clinic.service";
import {IPatientProfile} from "../../payloads/IPatientProfile";

@Component({
  selector: 'app-patient-details-page',
  templateUrl: './patient-details-page.component.html',
  styleUrls: ['./patient-details-page.component.scss']
})
export class PatientDetailsPageComponent implements OnInit {

  patientProfile: IPatientProfile;
  patientRecords: IPatientRecord[] = [];
  selectedRecords: IPatientRecord;
  displayedColumns: string[] = ['id', 'complaints', 'diagnosis', 'actionToken', 'refCode'];

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private spinnerService: NgxSpinnerService,
              private clinicService: ClinicService,
              private toastrService: ToastrService) {

  }

  ngOnInit(): void {
    this.getMyProfile();
    this.getMyRecords();

  }

  getMyProfile(){
    this.clinicService.getMyProfile().subscribe(res => {
      this.patientProfile = res.data;
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }

  getMyRecords(): void {
    this.clinicService.getMyRecord().subscribe(res => {
      this.patientRecords = res.data;
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }

  selectedRecord(id: any) {
    this.clinicService.getMyRecordById(id).subscribe(res => {
      this.selectedRecords = res.data;
    }, error => {
      this.toastrService.error(error.error.message, error.error.description);
    });
  }

}
