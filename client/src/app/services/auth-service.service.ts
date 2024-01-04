import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  setToken(token: string): void {
    localStorage.setItem("token",token)
  }
  setId(Id:number){
    localStorage.setItem("id",Id.toLocaleString());
  }

  getId(): String | null{
    return localStorage.getItem("id") ;
  }
  getToken(): string | null{
    return localStorage.getItem("token");
  }
  isLogged():boolean{
    if(this.getToken()!=null && this.getId()!=null){
      return true;
    }
    else
      return false;
  }
  logout(){
    localStorage.clear()
  }

}
