import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import {HttpClient, HttpClientModule, withFetch} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './register/register.component';
import { HeroComponent } from './hero/hero.component';
import { FeatureComponent } from './feature/feature.component';
import { TeamComponent } from './team/team.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import {NgToastModule} from "ng-angular-popup";
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from "@angular/material/input";
import {MatRadioModule} from "@angular/material/radio";
import {NewExamComponent} from "./modules/admin/prof/components/new-exam/new-exam.component";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    HeroComponent,
    FeatureComponent,
    TeamComponent,
    NotFoundComponent,
    HomeComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // Add this line
    ToastrModule.forRoot(),
    NgToastModule,
    BrowserAnimationsModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatRadioModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,

  ],
  providers: [
    provideClientHydration(),
    HttpClient
  ],
  exports: [


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
