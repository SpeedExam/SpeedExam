import { Component } from '@angular/core';
import { Exam } from '../../classes/user';
import { OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthServiceService } from '../../../../services/auth-service.service';

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrl: './exams.component.scss'
})
export class ExamsComponent implements OnInit{
  public exms: any;
  constructor(private userService:UserService,private auth:AuthServiceService) { }

  ngOnInit(): void {
    this.userService.getNpExams(this.auth.getId()).subscribe((data: any) => {
      this.exms = data;
      console.log(this.exms);
    });
}

  // exams:Array<Exam> = [
  //   {
  //     id: 1,
  //     name: 'Scrum',
  //     type: 'QCM',
  //     duration: 60,
  //     prof: 'M. Badir',
  //     questions: [
  //       {
  //         id: 1,
  //         name: 'What is the capital of France?',
  //         options: [
  //           { id: 1, content: 'Paris', isCorrect: true },
  //           { id: 2, content: 'Berlin', isCorrect: false },
  //           { id: 3, content: 'London', isCorrect: false },
  //           { id: 4, content: 'Madrid', isCorrect: false },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: 'What is the largest planet in our solar system?',
  //         options: [
  //           { id: 1, content: 'Earth', isCorrect: false },
  //           { id: 2, content: 'Jupiter', isCorrect: true },
  //           { id: 3, content: 'Mars', isCorrect: false },
  //           { id: 4, content: 'Venus', isCorrect: false },
  //         ],
  //       },
  //     ],
  //   },
  //   {
  //     id: 2,
  //     name: 'Angular',
  //     type: 'QCM',
  //     duration: 60,
  //     prof: 'M. Ghailani',
  //     questions: [
  //       {
  //         id: 1,
  //         name: 'What is the capital of France?',
  //         options: [
  //           { id: 1, content: 'Paris', isCorrect: true },
  //           { id: 2, content: 'Berlin', isCorrect: false },
  //           { id: 3, content: 'London', isCorrect: false },
  //           { id: 4, content: 'Madrid', isCorrect: false },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: 'What is the largest planet in our solar system?',
  //         options: [
  //           { id: 1, content: 'Earth', isCorrect: false },
  //           { id: 2, content: 'Jupiter', isCorrect: true },
  //           { id: 3, content: 'Mars', isCorrect: false },
  //           { id: 4, content: 'Venus', isCorrect: false },
  //         ],
  //       },
  //     ],
  //   },
  //   {
  //     id: 3,
  //     name: 'NodeJS',
  //     type: 'QCM',
  //     duration: 60,
  //     prof: 'M. Haddad',
  //     questions: [
  //       {
  //         id: 1,
  //         name: 'What is the capital of France?',
  //         options: [
  //           { id: 1, content: 'Paris', isCorrect: true },
  //           { id: 2, content: 'Berlin', isCorrect: false },
  //           { id: 3, content: 'London', isCorrect: false },
  //           { id: 4, content: 'Madrid', isCorrect: false },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: 'What is the largest planet in our solar system?',
  //         options: [
  //           { id: 1, content: 'Earth', isCorrect: false },
  //           { id: 2, content: 'Jupiter', isCorrect: true },
  //           { id: 3, content: 'Mars', isCorrect: false },
  //           { id: 4, content: 'Venus', isCorrect: false },
  //         ],
  //       },
  //     ],
  //   },
  // ];

}
