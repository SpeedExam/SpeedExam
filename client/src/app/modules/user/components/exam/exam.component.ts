import { Option, Question } from './../../classes/user';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrl: './exam.component.scss'
})
export class ExamComponent {
  timer: any={
    duration: 10,
    minutes: 0,
    seconds: 0
  };
  score=0;
  result=0;
  isStarted: boolean = false;
  currentQuestionIndex = 0;
  selectedOption: Option | undefined;
  selectedOptionIndex: number | undefined;
  answered = false;
  currentQuestion: { id:number, question: string; options: Option[] } = { id:-1,question: '', options: [] };
  questions: { id:number, question: string; options: Option[] }[] = [
    {
      id:1,
      question: 'What is the capital of India?',
      options: [
        { id:1,content: 'New Delhi', isCorrect: true },
        { id:2,content: 'Mumbai', isCorrect: false },
        { id:3,content: 'Kolkata', isCorrect: false },
        { id:4,content: 'Chennai', isCorrect: false }
      ]
    },
    {
      id:2,
      question: 'What is the capital of Australia?',
      options: [
        { id:1,content: 'Sydney', isCorrect: false },
        { id:2,content: 'Melbourne', isCorrect: false },
        { id:3,content: 'Canberra', isCorrect: true },
        { id:4,content: 'Perth', isCorrect: false }
      ]
    },
    {
      id:3,
      question: 'What is the capital of United States?',
      options: [
        { id:1,content: 'New York', isCorrect: false },
        { id:2,content: 'Washington DC', isCorrect: true },
        { id:3,content: 'Los Angeles', isCorrect: false },
        { id:4,content: 'Chicago', isCorrect: false }
      ]
    },
    {
      id:4,
      question: 'What is the capital of United Kingdom?',
      options: [
        { id:1,content: 'London', isCorrect: true },
        { id:2,content: 'Manchester', isCorrect: false },
        { id:3,content: 'Liverpool', isCorrect: false },
        { id:4,content: 'Birmingham', isCorrect: false }
      ]
    },
    {
      id:5,
      question: 'What is the capital of Japan?',
      options: [
        { id:1,content: 'Tokyo', isCorrect: true },
        { id:2,content: 'Kyoto', isCorrect: false },
        { id:3,content: 'Osaka', isCorrect: false },
        { id:4,content: 'Yokohama', isCorrect: false }
      ]
    }
  ];
  responses: { questionid: number; answer: number }[] = [];

  constructor(private router: Router) { }

  start():void {
    this.startTimer();
    this.isStarted = true;
    console.log('Exam started');
    this.showCurrentQuestion();
  }

  correctQuestion():void{
    this.selectedOption=this.currentQuestion.options.find((option) => option.id === this.selectedOptionIndex);
    if(this.selectedOption?.isCorrect)
    {
      this.score++;
    }
  }

  unloadHandler():any{
    clearInterval(this.timer);
    this.router.navigate(['/user']);
  }

  beforeUnloadHandler(event: Event): any {
    const confirmationMessage = 'leaving the page will end the exam Forever';
    (event as any).returnValue = confirmationMessage;
    return confirmationMessage;
}
nextQuestion() {
  this.correctQuestion();
  this.answered = false;
  this.currentQuestionIndex++;
  this.showCurrentQuestion();
}

showCurrentQuestion() {
  this.currentQuestion = this.questions[this.currentQuestionIndex];
}

completeExam() {
  this.result=(this.score/this.questions.length)*100;
  console.log(this.result);
  this.navigateToUserProfile();
}

navigateToUserProfile() {
  this.router.navigate(['/user']);
}

startTimer() {

  this.timer.interval = setInterval(() => {
    this.timer.minutes = Math.floor(this.timer.duration / 60);
    this.timer.seconds = this.timer.duration % 60;

    this.timer.minutes = this.timer.minutes < 10 ? `0${this.timer.minutes}` : this.timer.minutes;
    this.timer.seconds = this.timer.seconds < 10 ? `0${this.timer.seconds}` : this.timer.seconds;

    if (--this.timer.duration < 0) {
      clearInterval(this.timer.interval);
      this.completeExam();
    }
  }, 1000);
}


ngOnDestroy(): void {
  clearInterval(this.timer);
}
}
