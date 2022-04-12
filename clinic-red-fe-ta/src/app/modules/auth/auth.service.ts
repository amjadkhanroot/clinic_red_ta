import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import jwt_decode from 'jwt-decode';

import {IAPIResponse} from "../payloads/IAPIResponse";
import {ILoginRequest} from "./payloads/ILoginRequest";
import {HttpClient} from "@angular/common/http";
import {IRegisterRequest} from "./payloads/IRegisterRequest";
import {StorageService} from "../../shared/services/storage.service";
import {RoutesConfig} from "../../configs/routes.config";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private storageService: StorageService,
              private http: HttpClient) {
  }

  isLoggedIn(): boolean {
    try {
      const token = this.storageService.getCookie('accessToken');
      if (token) {
        return !!jwt_decode(token);
      }
      return false;
    } catch (Error) {
      return false;
    }
  }

  register(registerReq: IRegisterRequest): Observable<IAPIResponse> {
    return this.http.post<IAPIResponse>(RoutesConfig.authRoutes.endpoints.register, registerReq);
  }

  public login(loginReq: ILoginRequest): Observable<IAPIResponse> {
    return this.http.post<IAPIResponse>(RoutesConfig.authRoutes.endpoints.login, loginReq);
  }

}
