import { Component } from '@angular/core';

@Component({
  selector: 'log-in',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData={};
  formSubmit(){
      console.log(this.loginData);
  }
}
