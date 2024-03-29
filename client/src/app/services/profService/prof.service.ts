import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {Test} from "../../interfaces/Test";

@Injectable({
  providedIn: 'root'
})
export class ProfService {
  URL = "http://localhost:3000"
  constructor(private htpp : HttpClient) { }

  createExam(T : Test){

    return  this.htpp.post<Test>(this.URL.concat("/Test"),T);
  }
  updateExam(T:Test,id : number){
    return this.htpp.put(this.URL.concat("/Test/"+id),T);
  }
  getallExams(){
    return this.htpp.get(this.URL.concat("/Test"));
  }
  deleteSubject(id:number){
    return this.htpp.delete(this.URL.concat("/Test/"+id));
  }
  getExam(id : number){
    return this.htpp.get(this.URL.concat("/Test/"+id));
  }

}
