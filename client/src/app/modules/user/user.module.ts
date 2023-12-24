import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import {AdminModule} from "../admin/admin.module";


@NgModule({
  declarations: [
    UserDashboardComponent,
    SideBarComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    AdminModule
  ]
})
export class UserModule { }
