import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../model/Customer';
import { Observable } from 'rxjs';
import { Customer1 } from '../model/Customer1';
import { Transaction } from '../model/Transactions';

@Injectable({
  providedIn: 'root'
})
export class WalletService {
  
  constructor( 
     private httpClient :  HttpClient) { }

     public createUser(cust:Customer1): Observable<any>{
       return this.httpClient.post<any>("http://localhost:8080/wallet/create", cust );
     }

     public updateUser(cust:Customer1): Observable<any>{
      return this.httpClient.post<any>("http://localhost:8080/wallet/update", cust );
    }

     public getUser(id):Observable<any>{
       return this.httpClient.get<Customer1>("http://localhost:8080/wallet/getUser/" + id);
     } 

     public getAllUser():Observable<any>{
      return this.httpClient.get<Customer1>("http://localhost:8080/wallet/getAll");
    } 

     public userLogin(mobile,pass):Observable<any>{
      return this.httpClient.get<Customer>("http://localhost:8080/wallet/doLogin/" + mobile + "/" + pass );
    }

    public deposit(user,amount):Observable<any>{
      return this.httpClient.post<any>(`http://localhost:8080/wallet/doDeposit/${amount}`, user);
    }

    public withdraw(user,amount):Observable<any>{
      return this.httpClient.post<any>(`http://localhost:8080/wallet/doWithdraw/${amount}`, user);
    }

    public transfer(mobile,amount,pass, user):Observable<any>{
      return this.httpClient.get<any>(`http://localhost:8080/wallet/doTransfer/${mobile}/${amount}/${pass}/${user}`);
    }

    public getTransactions(id):Observable<any>{
      return this.httpClient.get<Transaction>(`http://localhost:8080/wallet/getTransactions/${id}`);
    } 
}
