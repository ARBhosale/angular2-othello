import { DiscType } from "../../entities/disc/disc";

export class Playing {
    private gameId: number;
    private playerBlackId: number;
    private playerWhiteId: number;
    private playerBlackScore: number;
    private playerWhiteScore: number;
    private timeLimitInMinutes: number;
    private currentPlayerHasMoves = true;
    private boardId: number;
    private currentTurn: DiscType;

    constructor(serverPlayingResponse: any) {
        this.gameId = serverPlayingResponse.gameId;
        this.playerBlackId = serverPlayingResponse.playerBlackId;
        this.playerWhiteId = serverPlayingResponse.playerWhiteId;
        this.playerBlackScore = serverPlayingResponse.playerBlackScore;
        this.playerWhiteScore = serverPlayingResponse.playerWhiteScore;
        this.timeLimitInMinutes = serverPlayingResponse.timeLimitInMinutes;
        this.currentPlayerHasMoves = serverPlayingResponse.currentPlayerHasMoves;
        this.boardId = serverPlayingResponse.boardId;
        this.currentTurn = serverPlayingResponse.currentTurn == 'Black' ? DiscType.Black : DiscType.White;
    }
}