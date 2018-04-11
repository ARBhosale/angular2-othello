import { Component, OnInit } from '@angular/core';
import { GameService } from './services/game/game.service';
import { Game } from './services/game/game';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';
  game: Game;

  constructor(private gameService: GameService) {

  }

  ngOnInit() {
    this.game = this.gameService.startNewGame();
  }
}
