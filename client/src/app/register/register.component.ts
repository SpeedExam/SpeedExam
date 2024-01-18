import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {AuthServiceService} from "../services/auth-service.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit{
  errorMessages: { [key: string]: string } = {};

  constructor(private service: AuthService,private router:Router,private builder:FormBuilder,private toasters:ToastrService,private userhservice:AuthServiceService,private toast:NgToastService) {
  }

  public register_form !: FormGroup;

  ngOnInit() {
    if(this.userhservice.isLogged()){
      this.router.navigate(['./admin/dashboard']);
    }
  this.register_form=this.builder.group({
    firstname:this.builder.control('',Validators.required),
    lastname:this.builder.control('',Validators.required),
    email:this.builder.control('',Validators.compose([Validators.required,Validators.email])),
    role:this.builder.control('',Validators.required),
    password:this.builder.control('',Validators.compose([Validators.required,Validators.minLength(8)])),
    confirm_password:this.builder.control('',Validators.compose([Validators.required,Validators.minLength(8)]))
  });
  }

  onRegister() {
    console.log(this.register_form.value);
    this.errorMessages = {}; // Reset error messages
    // Object.keys(this.register_form.controls).forEach(field => {
    //   const control = this.register_form.get(field);
    //   if (control && (control.value.trim() === '' || (control.touched && control.invalid))) {
    //     this.errorMessages[field] = `${field} is required`;
    //   }
    // });
    if (this.register_form.get('password')?.value?.trim() !== this.register_form.get('confirm_password')?.value?.trim() && (this.register_form.get('password')?.value?.length>0 &&  this.register_form.get('confirm_password')?.value?.length>0)) {
      this.errorMessages['equal'] = 'Passwords do not match';
    }
    if (this.register_form.valid) {
      this.service
        .register(this.register_form.value)
        .subscribe(
          (response) => {
            console.log(response);

            this.toast.success({detail: "Success message", summary: "Register is Success", duration: 5000});
            this.router.navigate(['/login'],{ queryParams: { registrationSuccess: true } });
          },
          (error)=>{
            console.error(error);
            this.toast.error({detail:"Error message",summary:"Register is failed",duration:15000});
          }
        );

    }
    else
      this.toast.error({detail:"Error message",summary:"Register is failed",duration:15000});

  }
  get FirstName():FormControl{
    return this.register_form.get('firstname') as FormControl;
  }
  get LastName():FormControl{
    return this.register_form.get('lastname') as FormControl;
  }
  get Email():FormControl{
    return this.register_form.get('email') as FormControl;
  }
  get Password():FormControl{
    return this.register_form.get('password') as FormControl;
  }
  get Confirm_Password():FormControl{
    return this.register_form.get('confirm_password') as FormControl;
  }
  get Role():FormControl{
    return this.register_form.get('role') as FormControl;
  }


}





