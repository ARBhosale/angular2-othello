import { Player } from "../../entities/player/player";
import { Injectable } from "@angular/core";
import { DiscType } from "../../entities/disc/disc";
import { Game } from "./game";
import { Http, Response, Headers, RequestOptions, URLSearchParams }
    from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class GameService {

    static PLAY_MOVE_URL = "http://localhost:8080/game/playing/v1/play";
    onGoingGames: Array<Game>;

    constructor(private http: Http) {

    }

    public startNewGame(): Game {
        let newGame = new Game(this);
        //// this.onGoingGames.push(newGame);
        return newGame;
    }

    public createNewGame(): void { }
    public joinGame(): void { }


    public makeMove(row: number, col: number): Promise<any> {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        let body = {
            "gameId": 5,
            "playerId": 2,
            "insertAtRow": row,
            "insertAtColumn": col
        };
        let options = new RequestOptions({ headers: headers });
        return this.http
            .post(GameService.PLAY_MOVE_URL, body, options)
            .toPromise()
            .catch(error => {
                console.log(error);
                throw error;
            });
    }
}
