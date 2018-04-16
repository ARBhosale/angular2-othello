import { Component } from '@angular/core';

import {LoginService} from './login.comp.Service';

@Component({
  selector: 'log-in',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[LoginService]
})
export class LoginComponent {
  loginData={};
  test :String;
  posts: post[];
  constructor(private _loginService: LoginService ){}

  ngOnInit(){
    this._loginService.getResp()
                    .subscribe(posts=> console.log(this.posts));
    console.log(this.posts);
  }
  formSubmit(){
      console.log(this.loginData);
      
  }
}

interface post {
  id:number;
  title:string;
  body:string;
}
