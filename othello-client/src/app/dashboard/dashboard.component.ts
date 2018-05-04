import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from '@angular/forms';
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    @Input() player: Account;
    showCreateGameForm = false;
    showJoinGameForm = false;

    constructor(private accountService: AccountService) { }

    createGameGroup: FormGroup;

    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }
    }

    showCreateGame() {
        this.showCreateGameForm = true;
        this.showJoinGameForm = false;
    }

    showJoinGame() {
        this.showCreateGameForm = false;
        this.showJoinGameForm = true;
    }
}
