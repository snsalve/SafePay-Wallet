import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer1 } from 'src/app/model/Customer1';
import { Router } from '@angular/router';

@Component({
  selector: 'app-acc-details',
  templateUrl: './acc-details.component.html',
  styleUrls: ['./acc-details.component.css']
})
export class AccDetailsComponent implements OnInit {
  user = sessionStorage.getItem('username');
  userData = new Customer1("","","",null,"",null);
  constructor(private walletService: WalletService, private router:Router) { }

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
      console.log(this.user);
    this.walletService.getUser(this.user).subscribe( data => this.userData = data);
  }

}


