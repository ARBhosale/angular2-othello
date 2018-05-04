import { Component, Input, OnInit } from "@angular/core";
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { Router } from "@angular/router";


@Component({
    selector: 'player-stats',
    templateUrl: './player.Stats.html',
    styleUrls: ['./player.stats.css']
})
export class PlayerStatsComponent implements OnInit {

    @Input() player: Account;

    constructor(private accountService: AccountService, private router: Router) { }

    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }
        if (!this.player) {
            this.router.navigateByUrl("login");
        }
    }
}
