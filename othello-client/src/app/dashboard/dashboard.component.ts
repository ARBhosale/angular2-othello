import { Component, Input, OnInit } from "@angular/core";
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { Router } from "@angular/router";


@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

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
