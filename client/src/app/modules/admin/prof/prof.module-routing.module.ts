import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NewExamComponent} from "./components/new-exam/new-exam.component";

const routes: Routes = [
  {
    path:"",
    component:NewExamComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfModuleRoutingModule { }
