import { Component, ViewChild } from '@angular/core';
import { transition, trigger, useAnimation } from '@angular/animations';
import { fadeIn } from 'ng-animate';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { UtilsService } from '../../../../shared/services/utils.service';
import {IAPIResponse} from "../../../payloads/IAPIResponse";
import {RoutesConfig} from "../../../../configs/routes.config";
import {StorageService} from "../../../../shared/services/storage.service";
import {ILoggedIn, ROLES} from "../../../clinic/payloads/ILoggedIn";

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.scss'],
  animations: [
    trigger('fadeIn', [transition('* => *', useAnimation(fadeIn, {
      params: { timing: 1, delay: 0 }
    }))])
  ]
})

export class LogInPageComponent {
  @ViewChild('loginForm') loginForm: any;

  logInForm: FormGroup;
  username = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);
  hide = true;

  constructor(
    private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private utilsService: UtilsService,
              private storageService: StorageService
  ) {
    this.logInForm = this.formBuilder.group({
      username: this.username,
      password: this.password
    });
  }

  getErrorMessage(field: string): string | void {
    // @ts-ignore
    const classField = this[field];
    if (classField?.hasError('required')) {
      return 'You must enter a value';
    } else if (classField?.hasError('username')) {
      return 'Not a valid username';
    }
  }

  sendForm() {
    if (this.logInForm.valid) {
      this.authService.login(this.logInForm.value)
        .subscribe((response) => {
          if (response.success) {
            this.utilsService.showToastr(response.message, 'Welcome Back!', 'info');
            const loginData : ILoggedIn = response.data;
            this.storageService.setCookie('accessToken', response?.accessToken);
            this.storageService.setCookie('loginData', JSON.stringify(loginData));
            this.storageService.setCookie('role', loginData.authorities.filter(f => f.authority == ROLES.DOCTOR).length != 0? ROLES.DOCTOR : ROLES.PATIENT);

            if(loginData.authorities.filter(f => f.authority == ROLES.DOCTOR).length != 0){
              this.router.navigate(['clinic/patient-profile-list']);
            }else{
              this.router.navigate(['clinic/patient-details']);
            }
          }
        }, errors => {
          this.utilsService.showToastr(errors?.error?.message, JSON.stringify(errors?.error), 'error');
        });
    }
  }

}
