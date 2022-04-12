import { Component, Inject, OnInit } from '@angular/core';
import { APP_CONFIG } from '../../../../configs/app.config';
import { NavigationEnd, Router } from '@angular/router';
import {ROUTES_CONFIG, RoutesConfig} from '../../../../configs/routes.config';
import { AuthService } from '../../../auth/auth.service';
import { StorageService } from '../../../../shared/services/storage.service';
import {ILoggedIn, ROLES} from "../../../clinic/payloads/ILoggedIn";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent implements OnInit {

  currentUrl: string;
  isLoggedIn: boolean;

  constructor(@Inject(APP_CONFIG) public appConfig: any,
              @Inject(ROUTES_CONFIG) public routesConfig: any,
              private storageService: StorageService,
              private authService: AuthService,
              private router: Router) {
    this.currentUrl = '';
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.url;
        this.isLoggedIn = this.authService.isLoggedIn();
      }
    });
  }

  role(): boolean{
    return  this.storageService.getCookie('role') == ROLES.DOCTOR;
  }

  logOut(): void {
    this.storageService.removeCookie('accessToken');
    this.storageService.removeCookie('loginData');
    this.storageService.removeCookie('role');
    this.isLoggedIn = this.authService.isLoggedIn();
    this.router.navigate([RoutesConfig.authRoutes.routes.login]);
  }
}
