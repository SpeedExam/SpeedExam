import { Component, OnInit } from '@angular/core';
import {AuthServiceService} from "../../../../services/auth-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.scss'
})
export class UserDashboardComponent{
  constructor(private auth:AuthServiceService,private router:Router) {
  }
  onLogOut() {
    this.auth.logout();
    this.router.navigate(['home']);
  }
}
