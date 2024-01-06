import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminDashboardsComponent} from "./components/admin-dashboards/admin-dashboards.component";
import {CardsComponent} from "./components/cards/cards.component";
import {authGuardGuard} from "../../Guards/auth-guard.guard";
import {UserTableComponent} from "./components/user-table/user-table.component";
import {OverviewComponent} from "./components/overview/overview.component";

const routes: Routes = [
  {path:'',component:AdminDashboardsComponent,children:[
      {path:'dashboard',component:OverviewComponent},
      {path:'users',component:UserTableComponent}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
