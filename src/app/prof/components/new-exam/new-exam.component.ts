import {Component, ViewChild} from '@angular/core';

import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

import {NgToastService} from "ng-angular-popup";
import {Question} from "../../../interfaces/question";

import {ProfService} from "../../../services/profService/prof.service";
import {Test} from "../../../interfaces/Test";
import {
  AppearanceAnimation,
  ConfirmBoxInitializer,
  DialogLayoutDisplay,
  DisappearanceAnimation
} from "@costlydeveloper/ngx-awesome-popup";




@Component({
  selector: 'app-new-exam',
  templateUrl: './new-exam.component.html',
  styleUrl: './new-exam.component.scss'
})
export class NewExamComponent {



  TestName = new FormControl("");




  questionForm!:FormGroup;
  quest: Array<Question> = [];
  id : any;

  step1 = false;
  step2 = false;


  answers :Array<string>  =[];
  correctID :any;

  stepperIndex: number=0;
  btt="Create";
  btt2="Finish";
  tmp=0;
  tmp1=0;


  constructor(private fb:FormBuilder,  private toast: NgToastService ,private serv : ProfService,) {
    this.createForm();



  }
  createForm(){
    this.questionForm = this.fb.group(
      {
        question:['',[Validators.required]],
        answer1:['',[Validators.required]],
        answer2:['',[Validators.required]],
        answer3:["",],
        answer4 : ["",],
        timing:['',[Validators.required]],

      }
    )
  }
  start(){
    if(this.tmp1==0){
      if(this.TestName.value=="" ){
        this.toast.error({detail:"ERROR",summary:'Enter the exam inforamtions',sticky:true});
      }
      else{

        this.step1=true;

      }
      if(this.step1){
        this.toast.success({detail:"SUCCESS",summary:"Test "+this.TestName.value+" is created ,click Next to add the questions ",duration:6000});
        this.stepperIndex=1;
        this.btt="Next"
      }
    }


  }



  delete() {
    this.questionForm.reset();

  }



  addQuestion() {


      if(this.questionForm.invalid){
        this.toast.error({detail:"ERROR",summary:'Enter the timing and at least one question and 2 answers',sticky:true});

      }
      else if(!this.correctID){
        this.toast.error({detail:"ERROR",summary:'Select the correct answer',sticky:true});
      }

      else{
        this.answers.push(this.questionForm.value.answer1);
        this.answers.push(this.questionForm.value.answer2);
        if(this.questionForm.value.answer3){
          this.answers.push(this.questionForm.value.answer3);
        }
       if(this.questionForm.value.answer4) {
          this.answers.push(this.questionForm.value.answer4);
        }
        const ques: Question =
        {
          correctAns: this.correctID,
          question: this.questionForm.value.question,
          answers: this.answers,
          timing : this.questionForm.value.timing
        };
        this.toast.success({detail:"SUCCESS",summary:'Question added susscefully',duration:2000});


        this.quest.push(ques);
        //console.log(this.quest);
        this.answers  = [];
        this.questionForm.reset();
      }

  }

  getCorrect(event: any) {

     this.correctID = event.value ;
  }

  cancel() {
    this.questionForm.reset();
    this.answers=[];
    this.quest=[];
   this.TestName.reset();
   this.step1=false;


  }

  send() {

    if(this.tmp==0){
      if(this.quest.length==0){
        this.toast.error({detail:"ERROR",summary:'Can not save the exam , Enter at least one question ',sticky:true});

      }
      else {


        const sampleTest: Test = {

          TestName: this.TestName.value,
          Quest: this.quest
        };


        this.serv.createExam(sampleTest).subscribe(
          {
            next: value =>{this.toast.info({detail:"Info",summary:'Questions added to '+this.TestName.value+' succefully',duration:3000})
            this.id=value.id;

            },
            error: ()=>this.toast.error({detail:"ERROR",summary:'ERROR while saving the exam ',sticky:true})


          }
        );


        this.btt2="Display the exam";
        this.step2=true ;
        this.tmp=1;
      }


    }


  }


  del(index: number) {
      this.quest.splice(index,1);
    const sampleTest: Test = {

      TestName: this.TestName.value,
      Quest: this.quest
    };
    //
    this.serv.updateExam(sampleTest,this.id).subscribe(
      res=>{
        this.toast.warning({detail:"Deletion",summary:'THE QUESTION IS DELETED susscefully',sticky:true});
      }
    )


  }
}
