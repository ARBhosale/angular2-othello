import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { CreateAccountComponent } from '../services/createPlayerAccount/createAccount.component';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { AccountService } from '../services/account/account.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: Http, private accountService: AccountService) { }

  public submitted: boolean;
  accountGroup: FormGroup;

  ngOnInit() {
    this.accountGroup = new FormGroup({
      userName: new FormControl(''),
      password: new FormControl('')
    });
  }

  onSubmit({ value, valid }: { value: loginAccount, valid: boolean }) {
    this.accountService.login(value)
        .then(result => console.log(result))
        .catch(error => console.log(error));
  }
}

export interface loginAccount {
  userName: String;
  password: String;

}