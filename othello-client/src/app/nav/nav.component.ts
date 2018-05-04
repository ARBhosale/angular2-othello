import { Component, OnInit } from '@angular/core';
import { AccountService } from '../services/account/account.service';
import { GameService } from '../services/game/game.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private accountService: AccountService, private gameService: GameService) { }

  ngOnInit() {
  }

}
