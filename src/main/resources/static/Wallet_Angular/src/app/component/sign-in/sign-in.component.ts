import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  invalidLogin : boolean=false;
  loginForm: FormGroup;
  submitted: boolean=false;
  success: string;

  constructor(private formBuilder: FormBuilder, private walletService: WalletService ,private router : Router) { }
  
  ngOnInit() {
    if (this.user==null){
      this.loginForm= this.formBuilder.group({
        mob_number: ['', [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
        password:['',Validators.required]
      });
    }
    else{
      this.router.navigate(['/loginHome']);
    }
    
  }
  
  user = sessionStorage.getItem('username');

  public logincheck(){
    this.submitted=true;
    if(this.loginForm.invalid)
      return;
    
    let mobile =this.loginForm.controls.mob_number.value;
    let password =this.loginForm.controls.password.value;

    this.walletService.userLogin(mobile,password).subscribe( data =>{
       console.log(data);
       if(data!=null){
                sessionStorage.setItem('username', data.username);
                this.router.navigate(['loginHome']);
              }
        else
              this.invalidLogin=true;
      }, err=>{
        console.log(err.stack);
      }); 
  } 
}