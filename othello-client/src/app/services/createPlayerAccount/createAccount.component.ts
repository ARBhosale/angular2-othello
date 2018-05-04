import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Component({
  selector: 'Register-User',
  templateUrl: './createAccount.html',
  styleUrls: ['./createAccount.css']
})
export class CreateAccountComponent implements OnInit{

    constructor(private http: Http){}
    
    public submitted: boolean;
    accountGroup: FormGroup;
    private baseUrl: String = 'http://localhost:8080';
  

    ngOnInit(){
      this.accountGroup= new FormGroup({
        userName: new FormControl(''),
        firstName: new FormControl(''),
        lastName: new FormControl(''),
        password: new FormControl('')
      });

    }

    onSubmit({value,valid}: {value: createAccount, valid: boolean} ){
        console.log(value);
        let bodyString= JSON.stringify(value);
        let headers= new Headers({'Content-Type': 'application/json'});
        let option= new RequestOptions({headers: headers});

        console.log("bodystring : "+bodyString);
        this.http.post(this.baseUrl+'/player/account/v1', bodyString, option)
                .subscribe(res => console.log(res));
    }

  
}

export interface createAccount{
  userName:String;
  firstName:String;
  lastName:String;
  password:String;
  

}



