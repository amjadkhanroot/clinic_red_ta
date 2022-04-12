import { Component } from '@angular/core';
import { transition, trigger, useAnimation } from '@angular/animations';
import { fadeIn } from 'ng-animate';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth.service';
import { UtilsService } from '../../../../shared/services/utils.service';
import { Router } from '@angular/router';
import {IAPIResponse} from "../../../payloads/IAPIResponse";
import {RoutesConfig} from "../../../../configs/routes.config";

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss'],
  animations: [
    trigger('fadeIn', [transition('* => *', useAnimation(fadeIn, {
      params: { timing: 1, delay: 0 }
    }))])
  ]
})

export class SignUpPageComponent {
  signUpForm: FormGroup;
  username = new FormControl('', [Validators.required, Validators.maxLength(100)]);
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$')]);
  hide = true;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private utilsService: UtilsService
  ) {
    this.signUpForm = this.formBuilder.group({
      username: this.username,
      role: "DOCTOR",
      email: this.email,
      password: this.password
    });
  }

  getErrorMessage(field: any): string | void {
    // @ts-ignore
    const classField: any = this[field];
    if (classField?.hasError('required')) {
      return 'You must enter a value';
    } else if (classField?.hasError('username')) {
      return 'Not a valid username';
    } else if (classField?.hasError('pattern')) {
      return 'Not a valid password';
    }
  }

  onUserTypeChange(event: any){
    this.signUpForm.patchValue({
      role: event.value
    })
  }

  sendForm() {
    if (this.signUpForm.valid) {
      this.authService.register(this.signUpForm.value)
        .subscribe((response: IAPIResponse) => {
          if (response.success) {
            this.utilsService.showToastr(response.message, 'Great! Now try to log in!', 'info');
            this.router.navigate(["/auth/login"]);

          }
        }, errors => {
          this.utilsService.showToastr(errors.error.message, JSON.stringify(errors?.error?.data), 'error');
        });
    }
  }

}
