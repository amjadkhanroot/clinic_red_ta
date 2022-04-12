import { Injectable } from '@angular/core';
import {IAPIResponse} from "../payloads/IAPIResponse";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {RoutesConfig} from "../../configs/routes.config";
import {IPatientProfileRequest} from "./payloads/IPatientProfileRequest";
import {IPatientRecordRequest} from "./payloads/IPatientRecordRequest";

export const endpoints = RoutesConfig.clinicRoutes.endpoints;

@Injectable({
  providedIn: 'root'
})
export class ClinicService {

  constructor(private http: HttpClient) { }

  getPatientProfileDetailsById(id: any) : Observable<IAPIResponse> {
    return this.http.get<IAPIResponse>(endpoints.DoctorRoute.profilesId + id);
  }

  getPatientRecordDetailsById(id: any): Observable<IAPIResponse> {
    return this.http.get<IAPIResponse>(endpoints.DoctorRoute.recordById + id);
  }

  recordsByProfile(id: any): Observable<IAPIResponse> {
    return this.http.get<IAPIResponse>(endpoints.DoctorRoute.recordsByProfile + id);
  }

  getPatientProfiles() {
    return this.http.get<IAPIResponse>(endpoints.DoctorRoute.profiles);
  }

  getAllPatient(){
    return this.http.get<IAPIResponse>(endpoints.DoctorRoute.getAllPatient);
  }

  createPatientProfile(profileRequest: IPatientProfileRequest) {
    return this.http.post<IAPIResponse>(endpoints.DoctorRoute.createProfile, profileRequest);
  }

  updatePatientProfile(id: any, profileRequest: IPatientProfileRequest) {
    return this.http.post<IAPIResponse>(endpoints.DoctorRoute.updateProfile + id, profileRequest);
  }

  deletePatientRecord(id: any) {
    return this.http.delete<IAPIResponse>(endpoints.DoctorRoute.deleteRecord + id);
  }

  createPatientRecord(recordRequest: IPatientRecordRequest) {
    return this.http.post<IAPIResponse>(endpoints.DoctorRoute.createRecord, recordRequest);
  }

  updatePatientRecord(id: any, recordRequest: IPatientRecordRequest) {
    return this.http.post<IAPIResponse>(endpoints.DoctorRoute.updateRecord + id, recordRequest);
  }

  getMyProfile() {
    return this.http.get<IAPIResponse>(endpoints.PatientRoute.getProfile);
  }

  getMyRecord() {
    return this.http.get<IAPIResponse>(endpoints.PatientRoute.getRecords);
  }

  getMyRecordById(id: any) {
    return this.http.get<IAPIResponse>(endpoints.PatientRoute.getRecordById + id);
  }
}
