import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../auth/auth.service";
import {ClinicService} from "../../clinic.service";
import {NgxSpinnerService} from "ngx-spinner";
import {IPatientProfile} from "../../payloads/IPatientProfile";

@Component({
  selector: 'app-patient-profile-page',
  templateUrl: './patient-profile-page.component.html',
  styleUrls: ['./patient-profile-page.component.css']
})
export class PatientProfilePageComponent implements OnInit {

  patientProfiles: IPatientProfile[] = [];

  constructor(
    private clinicService: ClinicService,
    private router: ActivatedRoute,
    private _router: Router,
    private _toster: ToastrService,
    private _authService: AuthService,
    private _spinnerService: NgxSpinnerService
  ) { }

  ngOnInit(): void {
    this.getPatientProfiles();
  }

  getPatientProfiles(): void {
    this._spinnerService.show('getPatientProfiles')
    this.clinicService.getPatientProfiles().subscribe(res => {
        this.patientProfiles = res.data
    }, error => {
      this._toster.error(error?.error.message, error?.error.description);
      this._spinnerService.hide('getPatientProfiles');
    });
  }

  goToNewRecord() {
    this._router.navigate(["/apps/clinic/record/new/" + this.router.snapshot.paramMap.get('id')]);
  }

  goToCreateProfile() {
    this._router.navigate(['/clinic/create-patient-profile'])
  }

  selectedProfile(id: any) {
    this._router.navigate(["/clinic/patient-profile-list/" + id])
  }
}
