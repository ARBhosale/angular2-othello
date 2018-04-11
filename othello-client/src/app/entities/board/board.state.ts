import { Board } from "./board";
import { Game } from "../../services/game/game";
import { Disc, DiscType } from "../disc/disc";

export class BoardState {

    game: Game;
    currentStateIndex = -1;
    states: Array<Board>;

    constructor(game: Game) {
        this.game = game;
    }

    public initialize(): void {
        let newBoard = new Board(this.game);
        newBoard.initializeStartingPositions();
        this.states = [];
        this.pushNewState(newBoard);
    }

    public pushNewState(board: Board): void {
        if (this.currentStateIndex === this.states.length - 1) {
            this.states.push(board);
            this.currentStateIndex++;
        } else if (this.currentStateIndex < this.states.length - 1 && this.currentStateIndex >= 0) {
            this.states = this.states.slice(0, this.currentStateIndex + 1);
            this.states.push(board);
            this.currentStateIndex++;
        }
    }

    public getCurrentState(): Board {
        if (this.currentStateIndex < this.states.length && this.currentStateIndex >= 0) {
            return this.states[this.currentStateIndex];
        } else {
            return null;
        }
    }

    public goToPreviousState(): void {
        let board = this.getCurrentState();
        this.game.undoMove()
            .then((updatedGame) => {
                let board = this.getPreviousState();
                this.game.updateScores(board);
                this.game.updatePlayerTurns();
                this.game = updatedGame;
            })
            .catch((error) => {
                board.actionResultMessage = error;
            });
    }

    public setBoardPiece(rowNum: number, colNum: number): void {
        // if the board position is empty
        let board = this.getCurrentState();
        if (board.values[rowNum][colNum] !== null) {
            board.actionResultMessage = Board.EMPTY_LOCATION_RULE;
            return;
        }

        // if the move can outflank an opponent disc
        let outFlankedDiscs = board.getOutflankedDiscs(rowNum, colNum);
        if (outFlankedDiscs.length === 0) {
            board.actionResultMessage = Board.OUTFLANK_RULE;

            return;
        }
        // setting board piece
        console.log('Board before: ');
        console.log(board.values);
        console.log('Player ' + this.game.currentPlayer.discType + ' played: Row = ' + rowNum + ', Col = ' + colNum);
        this.game.playMove(rowNum, colNum)
            .then((updatedGame) => {
                let newBoard = board.getCopy();
                newBoard.values[rowNum][colNum] = new Disc(this.game.currentPlayer.discType, rowNum, colNum);
                newBoard.flipDiscs(newBoard.getSameDiscs(outFlankedDiscs), this.game.currentPlayer.discType);
                this.game.updateScores(newBoard);
                this.game.updatePlayerTurns();
                console.log('Board after: ');
                console.log(newBoard.values);
                this.pushNewState(newBoard);
                this.game = updatedGame;
                this.performNoMoveActions();
            })
            .catch((error) => {
                board.actionResultMessage = error;
            });
    }

    private performNoMoveActions(): void {
        if (!this.getCurrentState().isAnyMovePossible()) {
            let player = this.game.currentPlayer.discType === DiscType.Black ? 'Black' : 'White';
            if (window.confirm('There are no moves possible for Player ' + player + '. Press OK to continue or Cancel to undo.')) {
                this.game.updatePlayerTurns();
            } else {
                this.goToPreviousState();
            }
        }
    }



    private getPreviousState(): Board {
        if (this.currentStateIndex === 0) {
            return this.getCurrentState();
        }
        if (this.currentStateIndex > 0) {
            this.currentStateIndex--;
            return this.getCurrentState();
        } else {
            return null;
        }
    }
}