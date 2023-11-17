import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RoomsComponent} from "./rooms.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RoomsRoutingModule} from "./rooms-routing.module";


@NgModule({
  declarations: [
    RoomsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RoomsRoutingModule
  ]
})
export class RoomsModule { }
