import { Player } from "../../entities/player/player";
import { Injectable } from "@angular/core";
import { DiscType } from "../../entities/disc/disc";
import { Http, Response, Headers, RequestOptions, URLSearchParams }
    from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { AppComponent } from "../../app.component";
import { Account } from './account';
import { Router } from "@angular/router";

@Injectable()
export class AccountService {

    loggedAccount: Account = null;

    constructor(private http: Http, private router: Router) {

    }

    public login(value): Promise<any> {

        console.log(value);
        let bodyString = value;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let option = new RequestOptions({ headers: headers });

        console.log("bodystring : " + bodyString);
        return this.http.post(AppComponent.BASE_URL + '/player/account/v1/login', bodyString, option)
            .toPromise()
            .then((response) => {
                console.log(response);
                this.loggedAccount = new Account(response);
                this.router.navigateByUrl('/dashboard');
            })
            .catch(this.handleError);

    }

    public SignUp(value): Promise<any> {
        console.log(value);
        let bodyString= JSON.stringify(value);
        let headers= new Headers({'Content-Type': 'application/json'});
        let option= new RequestOptions({headers: headers});
        return this.http.post(AppComponent.BASE_URL + '/player/account/v1', bodyString, option)
            .toPromise()
            .then((response) => {
                console.log(response);
                this.loggedAccount = new Account(response);
                this.router.navigateByUrl('/dashboard');
            })
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
