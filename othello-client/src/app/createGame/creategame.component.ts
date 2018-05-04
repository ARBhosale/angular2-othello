import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { GameService } from "../services/game/game.service";

@Component({
    selector: 'creategame',
    templateUrl: './creategame.component.html',
    styleUrls: ['./creategame.component.css']
})
export class CreateGameComponent implements OnInit {

    @Input() player: Account;
    
    id: Number;

    constructor(private accountService: AccountService, private http: Http, private gameService: GameService) { }

    createGameGroup: FormGroup;
    private baseUrl: String = 'http://localhost:8080';

    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }
        this.id=this.player['id'];

        this.createGameGroup = new FormGroup({
            isBlack: new FormControl(),
            gameTime: new FormControl(),
        })
    }

    onSubmit({ value, valid }: { value: createGame, valid: boolean}) {
        console.log(value);
        let isBlack=value.isBlack;
        let gameTime=value.gameTime;
        let id=this.id;

        let bodyString=JSON.stringify({requestSenderPlayerId:id,isBlack:isBlack,gameTimeLimitInMinutes:gameTime})
        
        this.gameService.createNewGame(bodyString);
        console.log(isBlack,gameTime,id);
        console.log("bodystringNew : "+bodyString);
    }


}

export interface createGame {
  isBlack: boolean;
  gameTime: Number;
}

export interface joinGame {
  isBlack: boolean;
  gameTime: Number;
}