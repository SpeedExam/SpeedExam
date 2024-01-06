import { Component } from '@angular/core';

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrl: './exams.component.scss'
})
export class ExamsComponent {
  exams:Array<any> = [
    {
      id: 1,
      name: 'Scrum',
      type: 'QCM',
      duration: 60,
      prof: 'M. Badir',
      date: '2020-12-12',
    },
    {
      id: 2,
      name: 'Angular',
      type: 'QCM',
      duration: 60,
      prof: 'M. Ghailani',
      date: '2020-12-12',
    },
    {
      id: 3,
      name: 'NodeJS',
      type: 'QCM',
      duration: 60,
      prof: 'M. Haddad',
      date: '2020-12-12',
    },
  ];

}
