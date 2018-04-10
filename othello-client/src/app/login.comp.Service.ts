import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {
    constructor(private _http: Http){  }

    getResp() : Observable<String> {
        return this._http.get("http://localhost:8080/api/hello")
                    .map((response: Response ) => <String>response.json());
    }
}
