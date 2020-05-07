import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer1 } from 'src/app/model/Customer1';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {
  transferForm : FormGroup;
  submitted : boolean= false;
  customer: Customer1[];
  userdata1 = new Customer1("","","",null,"",null);
  user = sessionStorage.getItem('username');
  constructor(private formBuilder:FormBuilder, private walletService: WalletService, private router:Router) { }

    message:string;  pass:any;  mobNumber:any;
  
  public sendMoney(){
    this.submitted=true
    if(this.transferForm.invalid)
      return;

    let password=this.transferForm.controls.password.value;
    if(password!=this.userdata1.password){
      alert("Password Invalid");
      return;
    }

    let mobile = this.transferForm.controls.mob_number.value;
    if(mobile==this.userdata1.mob_number){
      alert("Self transfer Not Allowed");
      return;
    }
    
    let amount = this.transferForm.controls.amount.value;
    console.log(this.mob);
    this.walletService.transfer(mobile, amount, password, this.user).subscribe(data=>{
      if(data)
        alert("Money Transfer Successful");
      else
        alert("Money Transfer Failed. Insufficient funds in wallet.");
    })
  }

  mob:number;
  public onClick(num){
    this.mob=num;
    this.transferForm= this.formBuilder.group({
      mob_number: [this.mob, [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
      amount: ['', [Validators.required,Validators.max(10000), Validators.pattern("[1-9][0-9]{0,4}")]],
      password:['',Validators.required]
    });  }

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
    else{
      this.transferForm= this.formBuilder.group({
        mob_number: [this.mob, [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
        amount: ['', [Validators.required,Validators.max(10000), Validators.pattern("[1-9][0-9]{0,4}")]],
        password:['',Validators.required]
      });
    }
    this.walletService.getUser(this.user).subscribe(data=>this.userdata1=data)
    this.walletService.getAllUser().subscribe( data=> this.customer=data);
  }

}
