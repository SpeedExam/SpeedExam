import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProfService} from "../../../../../services/profService/prof.service";

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.scss']
})
export class ExamComponent implements OnInit {
id : any;
Exam : any ;
 // quest:any;
  constructor(private route:ActivatedRoute,private srv:ProfService) {
    this.id=route.snapshot.paramMap.get("id");
    this.getExam();

  }

  ngOnInit(): void {
  }
  getExam(){
    this.srv.getExam(this.id).subscribe(
      (res:any)=>{
        this.Exam=res;

      }
    )
    //this.quest=this.Exam.Quest;
  }

}
