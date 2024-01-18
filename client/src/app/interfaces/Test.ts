import {Question} from "./question";

export interface Test {
  id?:number
  TestName : any,
  Quest:Array<Question>
}
