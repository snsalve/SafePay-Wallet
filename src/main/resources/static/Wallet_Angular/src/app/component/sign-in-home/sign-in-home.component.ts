import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer } from 'src/app/model/Customer';
import { Customer1 } from 'src/app/model/Customer1';

@Component({
  selector: 'app-sign-in-home',
  templateUrl: './sign-in-home.component.html',
  styleUrls: ['./sign-in-home.component.css']
})
export class SignInHomeComponent implements OnInit {
  user = sessionStorage.getItem('username');
  userData = new Customer1("","","",null,"",null);
  message:string;
  message1:string;
  constructor(private walletService: WalletService, private router:Router) { }

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
      
    console.log("username is:" + this.user);
    let resp = this.walletService.getUser(this.user);
    resp.subscribe( (data) => this.userData=data);
    //console.log(this.userData);
  }

}
