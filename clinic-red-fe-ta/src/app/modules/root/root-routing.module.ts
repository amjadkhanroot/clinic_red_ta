import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Error404PageComponent } from './pages/error404-page/error404-page.component';
import {RoutesConfig} from "../../configs/routes.config";


const rootRoutes: Routes = [
  { path: RoutesConfig.homeRoutes.routes.page404, component: Error404PageComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(rootRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class RootRoutingModule {
}
