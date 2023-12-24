import { Injectable } from '@angular/core';
import {Login, LoginResponse, Register} from "../classes/login";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  basedUrl:String="http://localhost:8090/api/auth/"
  constructor(private http:HttpClient) {
  }
  login(userData:any):Observable<LoginResponse>{
    const loginUrl= `${this.basedUrl}login`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json', // Add this line
    });
    return this.http.post<LoginResponse>(loginUrl,userData,{headers});
  }

  register(userData: any): Observable<LoginResponse> {
    // Remove the confirm_password field if it exists
    const {confirm_password, ...userDataWithoutConfirmPassword} = userData;

    const registerUrl = `${this.basedUrl}register`;
    return this.http.post<LoginResponse>(registerUrl, userDataWithoutConfirmPassword);
  }
}
