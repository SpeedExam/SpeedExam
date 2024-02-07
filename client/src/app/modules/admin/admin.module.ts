import { SublevelMenuComponent1 } from './side-nav/sublevel.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardsComponent } from './components/admin-dashboards/admin-dashboards.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CardsComponent } from './components/cards/cards.component';
import { RecentUsersComponent } from './components/recent-transactions/recent-users.component';
import { OverviewComponent } from './components/overview/overview.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { FooterDashboardComponent } from './components/footer-dashboard/footer-dashboard.component';
import { Sidenav1Component } from './side-nav/side-nav.component';
import { MatIconModule } from '@angular/material/icon';
// import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { SettingsComponent } from './components/settings/settings.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AdminDashboardsComponent,
    SidebarComponent,
    CardsComponent,
    RecentUsersComponent,
    OverviewComponent,
    UserTableComponent,
    FooterDashboardComponent,
    Sidenav1Component,
    SublevelMenuComponent1,
    SettingsComponent
  ],
  exports: [
    SidebarComponent
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        MatIconModule,
        FormsModule
    ]
})
export class AdminModule { }
