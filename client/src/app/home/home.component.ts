import {Component, OnInit} from '@angular/core';
import {AuthServiceService} from "../services/auth-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit{
  constructor(private userService:AuthServiceService,private router:Router) {
  }
  ngOnInit() {
    if(this.userService.isLogged()){
      this.router.navigate(['admin/dashboard']);

    }  }
}
