import { Component } from '@angular/core';
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.scss'
})
export class SettingsComponent {
currentPassword:string='';
newPassword:string='';
confirmationPassword:string='';
constructor(private auth:AuthService) {
}
changePassword(){
  const userData={
    currentPassword:this.currentPassword,
    newPassword:this.newPassword,
    confirmationPassword:this.confirmationPassword,
  }
  console.log(userData);
  this.auth.changePassword(userData).subscribe(
    (response)=> {
      console.log(response)
    },
    (error)=>{
      console.log(error)
    });
}
}
