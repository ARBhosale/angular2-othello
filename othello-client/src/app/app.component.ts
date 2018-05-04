import { Component, OnInit } from '@angular/core';
import { GameService } from './services/game/game.service';
import { Game } from './services/game/game';
import { AccountService } from './services/account/account.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';
  public static BASE_URL: String = 'http://localhost:8080';

  constructor(private accountService: AccountService, private gameService: GameService, private router: Router) {

  }

  ngOnInit() {
  }

  public isAUserLoggedIn(): boolean {
    return null !== this.accountService.loggedAccount
  }

  public getJumbotronCssClass(): string {
    let cssClass = "jumbotron jumbotron-fluid";
    if (this.router.url.indexOf('dashboard') >= 0) {
      return cssClass += " jumbotron-dashboard";
    }
    if (this.router.url.indexOf('playing') >= 0) {
      return cssClass += " jumbotron-game";
    }
    return cssClass;
  }
}
