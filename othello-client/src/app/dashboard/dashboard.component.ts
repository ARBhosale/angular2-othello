import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { GameService } from "../services/game/game.service";
import { ShowGamesComponent } from "../showGames/showgames.component";
import { Router } from "@angular/router";


@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    @Input() player: Account;

    constructor(private accountService: AccountService, private router: Router, private http: Http, private gameService: GameService) { }

    createGameGroup: FormGroup; 
    private baseUrl: String = 'http://localhost:8080';
    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }
        if (!this.player) {
            this.router.navigateByUrl("login");
        }
    }
}

