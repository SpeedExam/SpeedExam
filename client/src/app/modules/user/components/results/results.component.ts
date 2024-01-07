import { Component } from '@angular/core';
import { result } from '../../classes/user';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrl: './results.component.scss'
})
export class ResultsComponent {

  results:Array<result>=[
    {id:1,exam:'exam1',type:'type1',prof:'prof1',score:10},
    {id:2,exam:'exam2',type:'type2',prof:'prof2',score:20},
    {id:3,exam:'exam3',type:'type3',prof:'prof3',score:30},
    {id:4,exam:'exam4',type:'type4',prof:'prof4',score:40},
    {id:5,exam:'exam5',type:'type5',prof:'prof5',score:50},
    {id:6,exam:'exam6',type:'type6',prof:'prof6',score:60},
    {id:7,exam:'exam7',type:'type7',prof:'prof7',score:70},
    {id:8,exam:'exam8',type:'type8',prof:'prof8',score:80},
    {id:9,exam:'exam9',type:'type9',prof:'prof9',score:90},
    {id:10,exam:'exam10',type:'type10',prof:'prof10',score:100},
  ]

}
