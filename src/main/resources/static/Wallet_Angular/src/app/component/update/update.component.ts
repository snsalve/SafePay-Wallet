import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WalletService } from 'src/app/service/wallet.service';
import { Customer1 } from 'src/app/model/Customer1';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  editProfileForm: FormGroup;
  submitted: boolean = false;
  user = sessionStorage.getItem('username');
  userData = new Customer1("","","",null,"",null);
  constructor(private formBuilder: FormBuilder,
    private router: Router,private walletService: WalletService) { }

  ngOnInit() {
    if(this.user==null)
    this.router.navigate(['login']);
    else{
      this.editProfileForm = this.formBuilder.group({
        name: ['', [Validators.required, Validators.pattern("[A-Za-z _ ]{1,30}")]],
        username: ['', [Validators.required, Validators.pattern("[A-Z a-z 0-9]{8,12}")]],
        mob_number: ['', [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
        mail: ['', [Validators.required, Validators.email]]
      });
      this.walletService.getUser(this.user).subscribe(data => this.userData=data);
    }
  }

  public editUser(){
    this.submitted = true;
    if (this.editProfileForm.invalid) {
      return;
    }

  this.walletService.updateUser(this.editProfileForm.value).subscribe( data => {
    if(data){
      alert(`Data Updated Successfully`);
      this.router.navigate(['accDetails']);
    }
    else{
      alert(`Username cannot be changed`);
      return;
  }
}) 
}
}
