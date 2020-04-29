import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer1 } from 'src/app/model/Customer1';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-balance',
  templateUrl: './show-balance.component.html',
  styleUrls: ['./show-balance.component.css']
})
export class ShowBalanceComponent implements OnInit {
  user = sessionStorage.getItem('username');
  userData = new Customer1("","","",null,"",null);
  constructor(private walletService: WalletService, private router:Router) { }

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
    this.walletService.getUser(this.user).subscribe( data => this.userData = data);
  }

}
