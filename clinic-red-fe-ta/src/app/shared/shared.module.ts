import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {MaterialModule} from "./services/modules/material.module";
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MaterialModule,
    FlexLayoutModule,
  ],
  declarations: [

  ],
  exports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
  ]
})

export class SharedModule {
}
