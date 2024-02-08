

export interface Login {
  username:string,
  password:string,
}
export interface Register{
  firstName:string,
  lastName:string,
  phoneNumber:string,
  localDate:Date,
  id_Card:number,
  email:string,
  password:string
}
export interface LoginResponse{
  ACCESS_TOKEN:string ;
  role:string,
  userID:number
}
