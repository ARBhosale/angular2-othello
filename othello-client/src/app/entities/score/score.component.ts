import { Component, Input, OnInit } from "@angular/core";
import { Player } from "../player/player";
import { Game } from "../../services/game/game";
import { DiscType } from "../disc/disc";

@Component({
    selector: 'score',
    styleUrls: ['./score.component.css'],
    templateUrl: './score.component.html'
})
export class ScoreComponent implements OnInit {

    @Input() game: Game;

    player1: Player;
    player2: Player;

    ngOnInit() {
        this.player1 = this.game.currentPlayer;
        this.player2 = this.game.nextPlayer;
    }

    public getScoreForBlack(): number {
        return this.getScore(DiscType.Black);
    }

    public getScoreForWhite(): number {
        return this.getScore(DiscType.White);
    }

    private getScore(discType: DiscType): number {
        if (this.player1.discType === discType) {
            return this.player1.currentScore;
        } else {
            return this.player2.currentScore;
        }
    }

}