import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  setToken(token: string): void {
    localStorage.setItem("token",token)
  }
  setRole(role:string){
    localStorage.setItem("role",role.toLocaleString());
  }

  getRole(): String | null{
    return localStorage.getItem("role") ;
  }
  getToken(): string | null{
    return localStorage.getItem("token");
  }
  isLogged():boolean{
    if(this.getToken()!=null && this.getRole()!=null){
      return true;
    }
    else
      return false;
  }
  logout(){
    localStorage.clear()
  }

}
