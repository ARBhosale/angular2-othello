import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { GameService } from "../services/game/game.service";

@Component({
    selector: 'showgames',
    templateUrl: './showgames.component.html',
    styleUrls: ['./showgames.component.css']
})
export class ShowGamesComponent implements OnInit {

    @Input() player: Account;

    id: Number;
    showg = false;
    allGames = [];

    constructor(private accountService: AccountService, private http: Http, private gameService: GameService) { }

    createGameGroup: FormGroup;
    private baseUrl: String = 'http://localhost:8080';

    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }

        this.gameService.showAvailableGames()
                .then(result => {
                    console.log("Hi "+result)
                    let games = JSON.parse(result).content;
                    console.log("Games "+games)
                    });

              //  .catch(error) => console.log(error));

        this.id=this.player['id'];
    }

    
    onSubmit({ value, valid }: { value: joinGame, valid: boolean}) {





}



   
}



export interface joinGame {
  isBlack: boolean;
  gameTime: Number;
}