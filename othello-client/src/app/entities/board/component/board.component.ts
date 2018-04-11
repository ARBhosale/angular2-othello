import { Component, Input, OnInit } from "@angular/core";
import { Board } from "../board";
import { GameService } from "../../../services/game/game.service";
import { Game } from "../../../services/game/game";
import { DiscType } from "../../disc/disc";
import { BoardState } from "../board.state";

@Component({
    selector: 'board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    static BLACK_TURN = 'It is black playing now';
    static WHITE_TURN = 'It is white playing now';

    boardState: BoardState;

    @Input() game: Game;

    constructor() {
    }

    ngOnInit() {
        if (!this.game) {
            return;
        }
        this.boardState = new BoardState(this.game);
        this.boardState.initialize();
    }

    public playMove(rowNumber: number, colNumber): void {
        this.boardState.setBoardPiece(rowNumber, colNumber);
    }

    public getPlayerTurnMessage(): string {
        if (this.game.currentPlayer.discType === DiscType.Black) {
            return BoardComponent.BLACK_TURN;
        } else {
            return BoardComponent.WHITE_TURN;
        }
    }

    public undoMove(): void {
        this.boardState.goToPreviousState();
    }
}
