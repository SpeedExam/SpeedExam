import { Component, OnInit } from '@angular/core';
import {ProfService} from "../../../../../services/profService/prof.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {

  constructor(private srv:ProfService,private toast: NgToastService ) {
  }
  subjects:any[]=[];

  ngOnInit(): void {
    this.getsSubjects();
  }
  getsSubjects(){
    this.srv.getallExams().subscribe(
      (res:any)=>{
        this.subjects=res;
      }
    )
  }

  delete(index: number) {

    let id = this.subjects[index].id;
    this.subjects.splice(index,1);
    this.srv.deleteSubject(id).subscribe(
      res=>{
        this.toast.warning({detail:"Deletion",summary:'The Exam is deleted',sticky:true});
      }
    )


  }
}
