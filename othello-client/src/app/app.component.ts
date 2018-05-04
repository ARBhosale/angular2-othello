import { Component, OnInit } from '@angular/core';
import { GameService } from './services/game/game.service';
import { Game } from './services/game/game';
import { AccountService } from './services/account/account.service';





@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';
  game: Game;
  public static BASE_URL: String = 'http://localhost:8080';

  constructor(private accountService: AccountService, private gameService: GameService) {

  }

  ngOnInit() {
    this.game = this.gameService.startNewGame();
  }

  public isAUserLoggedIn(): boolean {
    return null !== this.accountService.loggedAccount
  }
}
