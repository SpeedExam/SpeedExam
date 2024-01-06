import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrl: './exam.component.scss'
})
export class ExamComponent {
  timer: any;
  isStarted: boolean = false;
  currentQuestionIndex = 0;
  selectedOptionIndex: number = 0;
  answered = false;
  currentQuestion: { id:number, question: string; options: string[] } = { id:-1,question: '', options: [] };
  questions: { id:number, question: string; options: string[] }[] = [
    { id:1, question: 'What is the capital of France?', options: ['Paris', 'Berlin', 'London', 'Madrid'] },
    { id:2, question: 'What is the largest planet in our solar system?', options: ['Earth', 'Jupiter', 'Mars', 'Venus'] },
  ];
  responses: { questionid: number; answer: number }[] = [];

  constructor(private router: Router) { }


  start():void {
    this.isStarted = true;
    console.log('Exam started');
    this.showCurrentQuestion();
    this.startTimer(60);
  }

  unloadHandler():any{
    this.router.navigate(['/user']);
  }

  beforeUnloadHandler(event: Event): any {
    const confirmationMessage = 'leaving the page will end the exam Forever';
    (event as any).returnValue = confirmationMessage;
    return confirmationMessage;
}
nextQuestion() {
  this.answered = false;
  this.responses.push({ questionid: this.currentQuestion.id, answer: this.selectedOptionIndex });
  
  this.currentQuestionIndex++;
  this.showCurrentQuestion();
}

showCurrentQuestion() {
  this.currentQuestion = this.questions[this.currentQuestionIndex];
}

completeExam() {
  this.responses.push({ questionid: this.currentQuestion.id, answer: this.selectedOptionIndex });
  this.navigateToUserProfile();
}

navigateToUserProfile() {
  this.router.navigate(['/user']);
}

startTimer(duration: number) {
  let timer = duration, minutes, seconds;
  this.timer = setInterval(() => {
    minutes = parseInt((timer / 60).toString(), 10);
    seconds = parseInt((timer % 60).toString(), 10);

    minutes = minutes < 10 ? minutes : minutes;
    seconds = seconds < 10 ? 0 + seconds : seconds;

    if (--timer < 0) {
      timer = duration;
    }
    if (minutes == 0 && seconds == 0) {
      clearInterval(this.timer);
      this.completeExam();
    }
  }, 1000)}
}
