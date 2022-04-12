import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientProfilePageComponent } from './pages/patient-profile-page/patient-profile-page.component';
import { PatientProfileDetailsPageComponent } from './pages/patient-profile-details-page/patient-profile-details-page.component';
import { PatientRecordDetailsPageComponent } from './pages/patient-record-details-page/patient-record-details-page.component';
import {ClinicRoutingModule} from "./clinic-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";
import {MatSelectModule} from "@angular/material/select";
import { CreatePatientProfilePageComponent } from './pages/create-patient-profile-page/create-patient-profile-page.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from "@angular/material/table";
import {MatDatepickerModule} from "@angular/material/datepicker";
import { CreatePatientRecordPageComponent } from './pages/create-patient-record-page/create-patient-record-page.component';
import { PatientDetailsPageComponent } from './pages/patient-details-page/patient-details-page.component';



@NgModule({
  declarations: [
    PatientProfilePageComponent,
    PatientProfileDetailsPageComponent,
    PatientRecordDetailsPageComponent,
    CreatePatientProfilePageComponent,
    CreatePatientRecordPageComponent,
    PatientDetailsPageComponent
  ],
  imports: [
    CommonModule,
    ClinicRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    MatSelectModule,
    MatCheckboxModule,
    MatTableModule,
    MatDatepickerModule,
  ]
})
export class ClinicModule { }
