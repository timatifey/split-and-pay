import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HeaderInterceptor} from "./helpers/header-interceptor.interceptor";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
