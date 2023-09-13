import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageSignInComponent } from './components/page-sign-in/page-sign-in.component';
import { PageSignUpComponent } from './components/page-sign-up/page-sign-up.component';

@NgModule({
  declarations: [
    AppComponent,
    PageSignInComponent,
    PageSignUpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
