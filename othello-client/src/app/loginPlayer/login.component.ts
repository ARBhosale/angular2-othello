import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { CreateAccountComponent } from '../services/createPlayerAccount/createAccount.component';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { AccountService } from '../services/account/account.service';
//import { Router } from '@angular/router';
//import {UserService} from '../user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 constructor(private http: Http, private accountService: AccountService){}
  //constructor(private router:Router, private user:UserService) { }

 public submitted: boolean;
    accountGroup: FormGroup;

 // ngOnInit() {
 //   console.log('hit');
  //}

  ngOnInit(){
      this.accountGroup= new FormGroup({
        userName: new FormControl(''),
        password: new FormControl('')
      });
  }
 
  onSubmit({value,valid}: {value: loginAccount, valid: boolean} ){
        this.accountService.login(value);
    }
  

}

export interface loginAccount{
  userName:String;
  password:String;
  
}