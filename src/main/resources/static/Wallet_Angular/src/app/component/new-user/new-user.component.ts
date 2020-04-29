import { Component, OnInit } from '@angular/core';
import { WalletService } from 'src/app/service/wallet.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  addForm: FormGroup;
  submitted: boolean = false;
  
  constructor(private formBuilder: FormBuilder,private httpClientService: WalletService, private router:Router) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern("[A-Za-z _ ]{1,30}")]],
      username: ['', [Validators.required, Validators.pattern("[A-Z a-z 0-9]{8,12}")]],
      mob_number: ['', [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
      mail: ['', [Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{7,14}")]]

    });
}
  public newUser(){
    this.submitted = true;
    if (this.addForm.invalid) {
      return;
    }

    this.httpClientService.createUser(this.addForm.value).subscribe( data => {
      alert(`${this.addForm.controls.name.value} Registered Successfully`);
      this.router.navigate(['homepage']);
    }, err => {
      console.log(err.stack);
  }) 
    }

    

}
