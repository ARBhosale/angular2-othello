import { Player } from "../../entities/player/player";
import { Injectable } from "@angular/core";

@Injectable()
export class GameService {

    public players: Array<Player>;
    public currentPlayer: Player;

    constructor() { }

}