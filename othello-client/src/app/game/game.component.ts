import { Component, OnInit } from '@angular/core';
import { AccountService } from '../services/account/account.service';
import { GameService } from '../services/game/game.service';
import { Game } from '../services/game/game';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  game: Game;

  constructor(private accountService: AccountService, private gameService: GameService) { }

  ngOnInit() {
    this.game = this.gameService.startNewGame();
  }

}
