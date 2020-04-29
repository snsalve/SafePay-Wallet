import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private router:Router) { }
  user = sessionStorage.getItem('username');

  ngOnInit() {
    if(this.user==null)
      this.router.navigate(['login']);
    
    sessionStorage.removeItem('username');
    alert("Logged Out Successfully");
    this.router.navigate(['homepage']);
  }

}
