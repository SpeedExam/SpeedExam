import { Component } from '@angular/core';
import {AuthServiceService} from "../../../../services/auth-service.service";
import {Router, Routes} from "@angular/router";

@Component({
  selector: 'app-admin-dashboards',
  templateUrl: './admin-dashboards.component.html',
  styleUrl: './admin-dashboards.component.scss'
})
export class AdminDashboardsComponent {
  constructor(private auth:AuthServiceService,private router:Router) {
  }
  onLogOut() {
    this.auth.logout();
    this.router.navigate(['home']);
  }
}
