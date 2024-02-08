import { SidenavComponent } from './components/side-nav/side-nav.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import {AdminModule} from "../admin/admin.module";
import { ExamsComponent } from './components/exams/exams.component';
import { ExamComponent } from './components/exam/exam.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCommonModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { SettingsComponent } from './components/settings/settings.component';
import { ResultsComponent } from './components/results/results.component';
import { SublevelMenuComponent } from './components/side-nav/sublevel.component';
import { MatCardModule } from '@angular/material/card';


@NgModule({
  declarations: [
    UserDashboardComponent,
    SideBarComponent,
    ExamsComponent,
    ExamComponent,
    ResultsComponent,
    SettingsComponent,
    SidenavComponent,
    SublevelMenuComponent,

  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    AdminModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatRadioModule,
    MatButtonModule,
    MatCommonModule,
    MatCardModule
  ]
})
export class UserModule { }
