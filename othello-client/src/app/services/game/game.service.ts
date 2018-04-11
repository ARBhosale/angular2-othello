import { Player } from "../../entities/player/player";
import { Injectable } from "@angular/core";
import { DiscType } from "../../entities/disc/disc";
import { Game } from "./game";

@Injectable()
export class GameService {

    onGoingGames: Array<Game>;

    constructor() {
        
    }

    public startNewGame(): Game {
        let newGame = new Game();
        //// this.onGoingGames.push(newGame);
        return newGame;
    }
}
