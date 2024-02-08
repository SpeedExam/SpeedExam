import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {authGuardGuard} from "./Guards/auth-guard.guard";
import {SubjectsComponent} from "./modules/admin/prof/components/subjects/subjects.component";
import {ExamComponent} from "./modules/admin/prof/components/exam/exam.component";

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'home',component:HomeComponent},
  {path:'admin',loadChildren:()=>import("./modules/admin/admin.module").then((m)=>m.AdminModule),canActivate:[authGuardGuard],data: { role: 'professor' }},
  {path:'user',loadChildren:()=>import("./modules/user/user.module").then((m)=>m.UserModule),canActivate:[authGuardGuard],data: { role: 'student' }},
  {path:'',redirectTo:'home',pathMatch:"full"},
  {
    path : "prof",
    loadChildren : () =>
      import("./modules/admin/prof/prof.module.module").then((m)=>m.ProfModuleModule)
  },
  {path:'Tests',component:SubjectsComponent},
  {path:'exam/:id',component: ExamComponent},
  {path:'**',component:NotFoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
