import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import {PatientProfilePageComponent} from "./pages/patient-profile-page/patient-profile-page.component";
import {DoctorGuard} from "./doctor.guard";
import {
  PatientProfileDetailsPageComponent
} from "./pages/patient-profile-details-page/patient-profile-details-page.component";
import {MyPatientProfileDetailsResolver} from "./my-patient-profile-details.resolver";
import {MyPatientRecordDetailsResolver} from "./my-patient-record-details.resolver";
import {
  PatientRecordDetailsPageComponent
} from "./pages/patient-record-details-page/patient-record-details-page.component";
import {
  CreatePatientProfilePageComponent
} from "./pages/create-patient-profile-page/create-patient-profile-page.component";
import {CreatePatientRecordPageComponent} from "./pages/create-patient-record-page/create-patient-record-page.component";
import {PatientDetailsPageComponent} from "./pages/patient-details-page/patient-details-page.component";



const clinicRoutes: Routes = [
  {
    path: "",
    pathMatch: "full",
    redirectTo: 'patient-profile-list'
  },
  {
    path: "create-patient-profile",
    component: CreatePatientProfilePageComponent,
    canActivate: [DoctorGuard]
  },
  {
    path: "update-patient-profile/:id",
    component: CreatePatientProfilePageComponent,
    canActivate: [DoctorGuard],
    resolve: { profile: MyPatientProfileDetailsResolver }
  },
  {
    path: "patient-profile-list",
    component: PatientProfilePageComponent,
    canActivate: [DoctorGuard]
  },
  {
    path: "patient-profile-list/:id",
    component: PatientProfileDetailsPageComponent,
    canActivate: [DoctorGuard],
    resolve: { profile: MyPatientProfileDetailsResolver }
  },
  {
    path: "patient-record-details/:id",
    component: PatientRecordDetailsPageComponent,
    canActivate: [DoctorGuard],
    resolve: { record: MyPatientRecordDetailsResolver }
  },
  {
    path: "create-patient-record/:profileId",
    component: CreatePatientRecordPageComponent,
    canActivate: [DoctorGuard],
    resolve: { profile: MyPatientProfileDetailsResolver }
  },
  {
    path: "update-patient-record/:id",
    component: CreatePatientRecordPageComponent,
    canActivate: [DoctorGuard],
    resolve: { record: MyPatientRecordDetailsResolver }
  },
  {
    path: "patient-details",
    component: PatientDetailsPageComponent
  },

  {
    path: "**",
    redirectTo: "patient-profile-list"
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(clinicRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: [
    MyPatientProfileDetailsResolver,
    MyPatientRecordDetailsResolver,
    DoctorGuard
  ]
})

export class ClinicRoutingModule {
}
