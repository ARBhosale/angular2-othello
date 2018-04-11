import { Player } from "../../entities/player/player";
import { DiscType } from "../../entities/disc/disc";

export class Game {
    public currentPlayer: Player;
    public nextPlayer: Player;

    constructor() {
        this.currentPlayer = new Player(DiscType.Black);
        this.nextPlayer = new Player(DiscType.White);
    }

    public updateScores(numberOfDiscsFlipped: number): void {
        this.currentPlayer.currentScore += numberOfDiscsFlipped + 1;
        this.nextPlayer.currentScore -= numberOfDiscsFlipped;
    }

    public updatePlayerTurns(): void {
        let temp = this.currentPlayer;
        this.currentPlayer = this.nextPlayer;
        this.nextPlayer = temp;
    }

    public playMove(rowNumber: number, colNumber): Promise<Game> {
        return Promise.resolve(this);
    }

}