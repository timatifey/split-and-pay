import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from "./login.component";
import {SignInRoutingModule} from "./sign-in-routing.module";

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SignInRoutingModule
  ]
})
export class LoginModule {

}
