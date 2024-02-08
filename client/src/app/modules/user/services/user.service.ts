import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  basedUrl:String="http://localhost:8090/cp/exams/"
  baseUrl2:String="http://localhost:8090/api/exam/"
  constructor(private http:HttpClient) { }

  public getNpExams(id:string):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get(this.basedUrl+id+"/nonpassed",{headers});
   }

   public getExam(id:string):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });
    console.log(this.basedUrl+id);
    return this.http.get(this.baseUrl2+id,{headers});
   }



}
