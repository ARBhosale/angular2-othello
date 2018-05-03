import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { CreateAccountComponent } from '../services/createPlayerAccount/createAccount.component';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
//import { Router } from '@angular/router';
//import {UserService} from '../user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 constructor(private http: Http){}
  //constructor(private router:Router, private user:UserService) { }

 public submitted: boolean;
    accountGroup: FormGroup;
    private baseUrl: String = 'http://localhost:8080';

 // ngOnInit() {
 //   console.log('hit');
  //}

  ngOnInit(){
      this.accountGroup= new FormGroup({
        userName1: new FormControl(''),
        password1: new FormControl('')
      });
  }
 
  onSubmit({value,valid}: {value: loginAccount, valid: boolean} ){
        console.log(value);
        let bodyString= JSON.stringify(value);
        let headers= new Headers({'Content-Type': 'application/json'});
        let option= new RequestOptions({headers: headers});

        console.log("bodystring : "+bodyString);
        this.http.post(this.baseUrl+'/player/account/v1/login', bodyString, option)
                .subscribe(res => console.log(res));
    }
  

}

export interface loginAccount{
  userName1:String;
  password1:String;
  
}