import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import {ClinicService} from "./clinic.service";
import {IAPIResponse} from "../payloads/IAPIResponse";

@Injectable({
  providedIn: 'root'
})
export class MyPatientRecordDetailsResolver implements Resolve<Observable<IAPIResponse>> {
  constructor(private clinicService: ClinicService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAPIResponse> {
    const id: string = route.paramMap.get('id') || '';
    return this.clinicService.getPatientRecordDetailsById(id);
  }
}
