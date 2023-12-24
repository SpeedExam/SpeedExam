import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {authGuardGuard} from "./Guards/auth-guard.guard";

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'home',component:HomeComponent},
  {path:'admin',loadChildren:()=>import("./modules/admin/admin.module").then((m)=>m.AdminModule),canActivate:[authGuardGuard]},
  {path:'user',loadChildren:()=>import("./modules/user/user.module").then((m)=>m.UserModule),canActivate:[authGuardGuard]},
  {path:'',redirectTo:'home',pathMatch:"full"},
  {path:'**',component:NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
