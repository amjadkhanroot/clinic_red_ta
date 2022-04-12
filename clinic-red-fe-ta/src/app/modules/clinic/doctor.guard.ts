import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {StorageService} from "../../shared/services/storage.service";
import {ILoggedIn, ROLES} from "./payloads/ILoggedIn";

@Injectable({
  providedIn: 'root'
})
export class DoctorGuard implements CanActivate {


  constructor(private storageService: StorageService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const userStr = this.storageService.getCookie('loginData');

    if (userStr == undefined){
      return false;
    }

    const userOb:  ILoggedIn = JSON.parse(userStr);

    return !!userOb.authorities.filter(f => f.authority == ROLES.DOCTOR);
  }

}
