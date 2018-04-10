import { Component } from '@angular/core';

import {LoginService} from './login.comp.Service';

@Component({
  selector: 'log-in',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData={};
  test :String;
  constructor(private _loginService: LoginService ){}

  ngOnInit(){
    this._loginService.getResp()
                    .subscribe((HelloData)=> console.log(HelloData));
   
  }
  formSubmit(){
      console.log(this.loginData);
      
  }
}
