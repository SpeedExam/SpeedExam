export interface Option{
  id:number,
  content:string,
  isCorrect:boolean,
}

export interface Question{
  id:number,
  options:Option[],
  name:string,
}

export interface result{
  id:number,
  exam:string,
  type:string,
  prof:string,
  score:number
}

export interface Exam{
  id:number,
  name:string,
  type:string,
  prof:string,
  questions:Question[],
  duration:number,
}
