import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Game } from "./game";
import { Playing } from "./playing";
import {AppComponent} from "../../app.component";

@Injectable()
export class GameService {

    static PLAY_MOVE_URL = "http://localhost:8080/game/playing/v1/play";
    onGoingGames: Array<Game>;

    playing: Playing;

    constructor(private http: Http) {

    }

    public startNewGame(): Game {
        let newGame = new Game(this);
        //// this.onGoingGames.push(newGame);
        return newGame;
    }

    public createNewGame(value) { 

        let bodyString = value;
        let headers= new Headers({'Content-Type': 'application/json'});
        let option= new RequestOptions({headers: headers});

      return  this.http.post(AppComponent.BASE_URL+'/game/playing/v1', bodyString, option)
                .subscribe(res => console.log(res));
    }

    public showAvailableGames(): Promise<any> {

        return this.http.get(AppComponent.BASE_URL + '/game/playing/v1')
            .toPromise()
            .then(this.extractData)
            .catch(error => {
                console.log(error);
                throw error;
            });
    }   

    private extractData(res: any) {
        let body = res.json();
        return res._body;
    }


    public joinGame(): void { }


    public makeMove(row: number, col: number): Promise<any> {
        return Promise.resolve();
        // let headers = new Headers({
        //     'Content-Type': 'application/json'
        // });
        // let body = {
        //     "gameId": 5,
        //     "playerId": 2,
        //     "insertAtRow": row,
        //     "insertAtColumn": col
        // };
        // let options = new RequestOptions({ headers: headers });
        // return this.http
        //     .post(GameService.PLAY_MOVE_URL, body, options)
        //     .toPromise()
        //     .catch(error => {
        //         console.log(error);
        //         throw error;
        //     });
    }
}
