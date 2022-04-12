import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RoutesConfig} from "./configs/routes.config";
import {AuthGuard} from "./modules/auth/auth.guard";

const appRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'clinic'
  },
  {
    path: RoutesConfig.authRoutes.routeName,
    loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)

  },
  {
    path: RoutesConfig.clinicRoutes.routeName,
    loadChildren: () => import('./modules/clinic/clinic.module').then(m => m.ClinicModule),
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: RoutesConfig.authRoutes.routeName
  }
];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule
  ],
  providers: [
    AuthGuard
  ]
})
export class AppRoutingModule {
}
