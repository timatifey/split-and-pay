import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RoomComponent} from "./room.component";
import {RoomRoutingModule} from "./room-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    RoomComponent
  ],
  imports: [
    CommonModule,
    RoomRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class RoomModule { }
