import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ProfService} from "../../../../services/profService/prof.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.scss'
})
export class OverviewComponent implements OnInit{
  constructor(private route:Router,private srv:ProfService,private toast: NgToastService ) {
  }
onClick(){
    this.route.navigate(["/prof"]);
}
  subjects:any[]=[];
  empty:boolean =false;

  ngOnInit(): void {
    this.getsSubjects();
  }
  getsSubjects(){
    this.srv.getallExams().subscribe(
      (res:any)=>{
        this.subjects=res;
        this.empty = this.subjects.length != 0;
        console.log(this.empty);
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
