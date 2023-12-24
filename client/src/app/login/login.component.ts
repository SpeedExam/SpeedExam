import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthServiceService} from "../services/auth-service.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  login_form !: FormGroup;
  token:string='';
  id:number=0;
  registrationSuccess: boolean = false;

  constructor(private service:AuthService, private route: ActivatedRoute, private builder:FormBuilder,private router:Router,private userhservice:AuthServiceService,private toast:NgToastService) {
 }
 ngOnInit() {

   if(this.userhservice.isLogged()){
     this.router.navigate(['/admin/dashboard']);
   }
 //   this.route.queryParams.subscribe((params) => {
 //     this.registrationSuccess = params['registrationSuccess'] === 'true';
 //
 // })

  this.login_form=this.builder.group({
    email:this.builder.control('',Validators.compose([Validators.required,Validators.email])),
    password:this.builder.control('',Validators.required)
  });
 }

  onLoging() {
    console.log(this.login_form.value);
    this.service.login(this.login_form.value).subscribe(
      (response)=> {
        console.log(response);

        this.userhservice.setToken(response.token);
        this.userhservice.setId(response.id);
        if (response.id == 1) {
          this.toast.success({detail: "Success message", summary: "Login is Success", duration: 5000});
          this.router.navigate(['admin/dashboard']);
        } else {
          this.toast.success({detail: "Success message", summary: "Login is Success", duration: 5000});
          this.router.navigate(['user']);
        }
      },
      (error)=>{
        console.error('Login error', error);
        this.toast.error({detail:"Error message",summary:"Login is failed",duration:15000});
      }
    )
      }


  get Password():FormControl{
    return this.login_form.get('password') as FormControl;
  }
  get Email():FormControl{
    return this.login_form.get("email") as FormControl;
  }
}
