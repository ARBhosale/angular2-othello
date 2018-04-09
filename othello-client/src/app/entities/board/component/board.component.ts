import { Component, Input } from "@angular/core";
import { Board } from "../board";
import { GameService } from "../../../services/game/game.service";

@Component({
    selector: 'board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent {

    board: Board;
    constructor(private gameService: GameService) {
        this.board = new Board(this.gameService);
    }



}