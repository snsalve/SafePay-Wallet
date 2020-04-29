import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer1 } from 'src/app/model/Customer1';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {
  addMoneyForm : FormGroup;

  submitted : boolean= false;
  user = sessionStorage.getItem('username');
  userdata = new Customer1("","","",null,"",null);
  userdata1 = new Customer1("","","",null,"",null);

  constructor(private formBuilder:FormBuilder,private walletService: WalletService, private router:Router) { }
  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
      else{
        this.addMoneyForm= this.formBuilder.group({
          amount: ['', [Validators.required,Validators.max(100000), Validators.pattern("[1-9][0-9]{0,4}")]],
          password:['',Validators.required]
        });
      }
      this.walletService.getUser(this.user).subscribe(data=>this.userdata1=data)
  }
  public addMoney(){
    this.submitted=true;
    if(this.addMoneyForm.invalid)
      return;
    let pass=this.addMoneyForm.controls.password.value;
    if(pass!=this.userdata1.password){
      alert("Password Invalid");
      return;
    }
    this.userdata.username=this.user;
    let amount=this.addMoneyForm.controls.amount.value;
    this.walletService.deposit(this.userdata,amount).subscribe( 
      data =>{
        if(data){
          alert("Money Added Successfully");
        }
        else{
          alert("Money Not Added");
          //this.router.navigate(['deposit']);
        }
      });
    }
 

}
