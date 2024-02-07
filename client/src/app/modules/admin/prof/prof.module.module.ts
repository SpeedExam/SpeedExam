import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfModuleRoutingModule } from './prof.module-routing.module';
import { NewExamComponent } from './components/new-exam/new-exam.component';

import {MatStepperModule} from "@angular/material/stepper";
import {MatInputModule} from "@angular/material/input";
import {MatRadioModule} from "@angular/material/radio";
import {MatButtonModule} from '@angular/material/button';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastrModule, ToastrService} from 'ngx-toastr';
import {NgToastModule} from "ng-angular-popup";





@NgModule({
  declarations: [
    NewExamComponent,

  ],
  imports: [
    CommonModule,
    ProfModuleRoutingModule,
    MatStepperModule,
    MatInputModule,
    MatRadioModule,
    MatButtonModule,
    ReactiveFormsModule,
    NgToastModule,
    FormsModule,




],
  providers: [
    ToastrService,

  ],

})
export class ProfModuleModule { }
