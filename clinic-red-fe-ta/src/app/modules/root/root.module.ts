import { NgModule } from '@angular/core';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { Error404PageComponent } from './pages/error404-page/error404-page.component';
import { RootRoutingModule } from './root-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    RootRoutingModule
  ],
  declarations: [
    Error404PageComponent,
    HeaderComponent,
    FooterComponent
  ],
  exports: [
    Error404PageComponent,
    HeaderComponent,
    FooterComponent
  ]
})

export class RootModule {
}
