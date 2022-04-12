import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignUpPageComponent } from './pages/sign-up-page/sign-up-page.component';
import { LogInPageComponent } from './pages/log-in-page/log-in-page.component';
import {RoutesConfig} from "../../configs/routes.config";


const authenticationRoutes: Routes = [
  {
    path: 'register',
    component: SignUpPageComponent
  },
  {
    path: 'login',
    component: LogInPageComponent
  },
  {
    path: '**',
    redirectTo: RoutesConfig.homeRoutes.page404 }
];

@NgModule({
  imports: [
    RouterModule.forChild(authenticationRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class AuthRoutingModule {
}
