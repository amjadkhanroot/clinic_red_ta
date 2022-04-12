import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import {StorageService} from "../../shared/services/storage.service";
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService,
              private router: Router,
              private storageService: StorageService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    return new Promise(resolve => {
      if (this.authService.isLoggedIn()) {
        resolve(true);
      } else {
        this.storageService.removeCookie('accessToken');
        this.router.navigate(['auth/login?loggedOut=true']);
        resolve(false);

      }
    });
  }
}
