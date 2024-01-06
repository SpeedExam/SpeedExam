import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserDashboardComponent} from "./components/user-dashboard/user-dashboard.component";
import { ExamsComponent } from './components/exams/exams.component';
import { ExamComponent } from './components/exam/exam.component';
import { ResultsComponent } from './components/results/results.component';
import { SettingsComponent } from './components/settings/settings.component';

const routes: Routes = [
  {
    path:'',
    component:UserDashboardComponent,
    children:[
      {
        path:'',
        component:ExamsComponent,
      },
      {
        path:'results',
        component:ResultsComponent,
      },
      {
        path:'settings',
        component:SettingsComponent
      }
    ],

},
{
  path:'exam/:id',
  component:ExamComponent,
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
