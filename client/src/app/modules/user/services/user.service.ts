import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // basedUrl:String="http://localhost:8090/auth/"
  constructor(private http:HttpClient) { }

  // getExams(){
  //   return this.http.get(this.basedUrl+"exams");
  // }



}
