import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  URL = "http://localhost:3000"

  constructor(private http : HttpClient) {

  }
  public getall(){

    return this.http.get( this.URL.concat("/students"));

  }
  public create (user : User){
     return this.http.post<User>(this.URL.concat("/students"),user)
  }

}
