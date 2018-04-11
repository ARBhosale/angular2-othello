import { Player } from "../../entities/player/player";
import { DiscType } from "../../entities/disc/disc";
import { Board } from "../../entities/board/board";

export class Game {
    public currentPlayer: Player;
    public nextPlayer: Player;

    constructor() {
        this.currentPlayer = new Player(DiscType.Black);
        this.nextPlayer = new Player(DiscType.White);
    }

    public updateScores(boardBeingPlayed: Board): void {
        this.currentPlayer.currentScore = 0;
        this.nextPlayer.currentScore = 0;
        for (let i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            for (let j = 0; j < Board.NUMBER_OF_COLUMNS; j++) {
                let disc = boardBeingPlayed.values[i][j];
                if (!disc) {
                    continue;
                } else {
                    if (this.currentPlayer.discType === disc.discValue) {
                        this.currentPlayer.currentScore++;
                    } else {
                        this.nextPlayer.currentScore++;
                    }
                }
            }
        }
        // this.currentPlayer.currentScore += numberOfDiscsFlipped + 1;
        // this.nextPlayer.currentScore -= numberOfDiscsFlipped;
    }

    public updatePlayerTurns(): void {
        let temp = this.currentPlayer;
        this.currentPlayer = this.nextPlayer;
        this.nextPlayer = temp;
    }

    public playMove(rowNumber: number, colNumber): Promise<Game> {
        // call to play move which sends currentPlayer, moveRow, moveCol and return updated Game(scores) only
        return Promise.resolve(this);
    }

    public undoMove(): Promise<Game> {
        // call to undo move and return updated Game(scores) only
        return Promise.resolve(this);
    }

}