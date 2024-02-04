import { Injectable } from '@angular/core';
import {Login, LoginResponse, Register} from "../classes/login";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  basedUrl:String="http://localhost:8090/"
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
    console.log(userData);
    const registerUrl = `${this.basedUrl}signup`;
    return this.http.post<LoginResponse>(registerUrl, userDataWithoutConfirmPassword);
  }
  logout(): void {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });
    this.http.post(this.basedUrl + 'logout', {headers}).subscribe(
      (response) => {
        localStorage.removeItem('token');
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
