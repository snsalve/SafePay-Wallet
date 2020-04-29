import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/model/Transactions';
import { WalletService } from 'src/app/service/wallet.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  transaction = new Transaction(null,"",null,"","",null);
  trans:Transaction[];
  
  user = sessionStorage.getItem('username');
  constructor(private walletService: WalletService, private router:Router) { }

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
      
    this.walletService.getTransactions(this.user).subscribe( data => this.trans=data );
  }
  
}
