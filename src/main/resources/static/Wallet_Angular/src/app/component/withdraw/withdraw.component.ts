import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer1 } from 'src/app/model/Customer1';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
  withdrawForm : FormGroup;
  submitted : boolean= false;

  constructor(private formBuilder: FormBuilder, private walletService: WalletService, private router:Router) { }

  userdata= new Customer1("","","",null,"",null);
  userdata1 = new Customer1("","","",null,"",null);
  user = sessionStorage.getItem('username');
  message: string;

  public removeMoney(){

    this.submitted=true;
    if(this.withdrawForm.invalid)
      return;
      let pass=this.withdrawForm.controls.password.value;
      if(pass!=this.userdata1.password){
        alert("Password Invalid");
        return;
      }
    let amount = this.withdrawForm.controls.amount.value;
    this.userdata.username=this.user;

    this.walletService.withdraw(this.userdata,amount).subscribe( 
      data =>{
        if(data){
          alert("Money Withdrawn Successfully");
        }
        else{
          alert("Cannot Withdraw. Insufficient funds in wallet.");
        }
      });
    }
  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
    else{
        this.withdrawForm= this.formBuilder.group({
          amount: ['', [Validators.required,Validators.max(100000), Validators.pattern("[1-9][0-9]{0,4}")]],
          password:['',Validators.required]
        });
      }
      this.walletService.getUser(this.user).subscribe(data=>this.userdata1=data)

  }

}
