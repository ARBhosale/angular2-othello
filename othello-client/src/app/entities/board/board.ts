import { Disc, DiscType } from "../disc/disc";
import { GameService } from "../../services/game/game.service";
import { Game } from "../../services/game/game";

export class Board {

    static NUMBER_OF_ROWS = 8;
    static NUMBER_OF_COLUMNS = 8;
    static EMPTY_LOCATION_RULE = 'You must select empty location on board';
    static OUTFLANK_RULE = 'You must select an empty location on board which outflanks and flips an opponent disc';

    public values: Array<Array<Disc>>;

    public previousValues: Array<Array<Disc>>;

    private game: Game;

    actionResultMessage: string = null;

    constructor(game: Game, board?: Board) {
        this.game = game;
        this.initializeValues(board);
    }

    public initializeStartingPositions(): void {
        this.values[3][3] = new Disc(DiscType.White, 3, 3);
        this.values[4][4] = new Disc(DiscType.White, 4, 4);
        this.values[4][3] = new Disc(DiscType.Black, 4, 3);
        this.values[3][4] = new Disc(DiscType.Black, 3, 4);
    }

    public getCopy(): Board {
        let board = new Board(this.game, this);
        return board;
    }

    public flipDiscs(discs: Array<Disc>, discType: DiscType): void {
        for (let i = 0; i < discs.length; i++) {
            discs[i].discValue = discType;
        }
    }

    public getSameDiscs(discsToGet: Array<Disc>): Array<Disc> {
        let discs = [];
        for (let i = 0; i < discsToGet.length; i++) {
            let discToGet = discsToGet[i];
            if (!discsToGet) {
                discs.push(null);
            }
            discs.push(this.values[discToGet.rowPosition][discToGet.colPosition]);
        }
        return discs;
    }

    private initializeValues(boardToInitializeFrom?: Board): void {
        this.values = [];
        for (let row = 0; row < Board.NUMBER_OF_ROWS; row++) {
            this.values[row] = [];
            for (let col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
                if (boardToInitializeFrom && boardToInitializeFrom.values[row][col]) {
                    this.values[row][col] = boardToInitializeFrom.values[row][col].getCopy();
                } else {
                    this.values[row][col] = null;
                }
            }
        }
    }

    private getRowOfDiscs(rowNumber: number): Array<Disc> {
        if (rowNumber >= 0 && rowNumber < Board.NUMBER_OF_ROWS) {
            return this.values[rowNumber];
        } else {
            return null;
        }
    }

    private getColumnOfDiscs(colNumber: number): Array<Disc> {
        if (colNumber >= 0 && colNumber < Board.NUMBER_OF_COLUMNS) {
            let discs = [];
            for (let i = 0; i < Board.NUMBER_OF_ROWS; i++) {
                discs.push(this.values[i][colNumber]);
            }
            return discs;
        } else {
            return null;
        }
    }

    private getRightLeftDiagonalDiscs(rowNumber: number, colNumber: number): Array<Disc> {
        let diagonalDiscs = [];
        let maxNumberOfDiagonalPositions = 8;
        // Northeast including current disc
        for (let i = 0; i < maxNumberOfDiagonalPositions; i++) {
            let discRow = rowNumber - i;
            let discCol = colNumber + i;
            if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
                diagonalDiscs.push(this.values[discRow][discCol]);
            }
        }
        diagonalDiscs = diagonalDiscs.reverse();
        // Southwest
        for (let i = 1; i < maxNumberOfDiagonalPositions; i++) {
            let discRow = rowNumber + i;
            let discCol = colNumber - i;
            if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
                diagonalDiscs.push(this.values[discRow][discCol]);
            }
        }

        return diagonalDiscs;
    }

    private getLeftRightDiagonalDiscs(rowNumber: number, colNumber: number): Array<Disc> {
        let diagonalDiscs = [];
        let maxNumberOfDiagonalPositions = 8;
        // Northwest including current disc
        for (let i = 0; i < maxNumberOfDiagonalPositions; i++) {
            let discRow = rowNumber - i;
            let discCol = colNumber - i;
            if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
                diagonalDiscs.push(this.values[discRow][discCol]);
            }
        }
        diagonalDiscs = diagonalDiscs.reverse();
        // Southeast
        for (let i = 1; i < maxNumberOfDiagonalPositions; i++) {
            let discRow = rowNumber + i;
            let discCol = colNumber + i;
            if (discRow >= 0 && discRow < Board.NUMBER_OF_ROWS && discCol >= 0 && discCol < Board.NUMBER_OF_COLUMNS) {
                diagonalDiscs.push(this.values[discRow][discCol]);
            }
        }
        return diagonalDiscs;
    }

    private getIndexOfMoveDisc(userMoveRow: number, userMovCol: number, userDiscType: DiscType, listOfDiscs: Array<Disc>): number {
        for (let i = 0; i < listOfDiscs.length; i++) {
            if (listOfDiscs[i] === null) {
                continue;
            }
            if (listOfDiscs[i] === undefined) {
                return i;
            }
        }
        return -1;
    }

    private getOutFlankedDiscsOutOfList(userMoveRow: number, userMovCol: number, userDiscType: DiscType, listOfDiscs: Array<Disc>): Array<Disc> {
        if (listOfDiscs.length === 0) {
            return [];
        }
        let index = this.getIndexOfMoveDisc(userMoveRow, userMovCol, userDiscType, listOfDiscs);
        if (index < 0) {
            return [];
        } else {
            let leftApparentOutflankedDiscs = [];
            // left of current move
            for (let i = index - 1; i >= 0; i--) {
                let boardDisc = listOfDiscs[i];
                if (!boardDisc && i === 0) {
                    leftApparentOutflankedDiscs = [];
                }
                if (!boardDisc) {
                    continue;
                }
                if (boardDisc.discValue !== userDiscType) {
                    if (i === 0) {
                        leftApparentOutflankedDiscs = [];
                    } else {
                        leftApparentOutflankedDiscs.push(boardDisc);
                    }
                } else if (boardDisc.discValue === userDiscType) {
                    break;
                }
            }
            // right of current move
            let rightApparentOutflankedDiscs = [];
            for (let i = index + 1; i < listOfDiscs.length; i++) {
                let boardDisc = listOfDiscs[i];
                if (!boardDisc && i === listOfDiscs.length - 1) {
                    rightApparentOutflankedDiscs = [];
                    continue;
                }
                if (!boardDisc) {
                    continue;
                }
                if (boardDisc.discValue !== userDiscType) {
                    if (i === listOfDiscs.length - 1) {
                        rightApparentOutflankedDiscs = [];
                    } else {
                        rightApparentOutflankedDiscs.push(boardDisc);
                    }
                } else if (boardDisc.discValue === userDiscType) {
                    break;
                }
            }

            let outFlankedDiscsInList = leftApparentOutflankedDiscs.concat(rightApparentOutflankedDiscs);

            return outFlankedDiscsInList;
        }
    }

    public getOutflankedDiscs(moveRow: number, moveCol: number): Array<Disc> {
        let outFlankedDisks = [];
        let currentUserDiscType = this.game.currentPlayer.discType;

        this.values[moveRow][moveCol] = undefined;

        // horizontal outflanked disks
        let horizontalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getRowOfDiscs(moveRow));
        // vertical outflanked disks
        let verticalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getColumnOfDiscs(moveCol));
        // leftRight diagonal outflanked disks
        let leftRightDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getLeftRightDiagonalDiscs(moveRow, moveCol));
        // rightLeft diagonal outflanked disks
        let rightLeftDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getRightLeftDiagonalDiscs(moveRow, moveCol))

        this.values[moveRow][moveCol] = null;

        outFlankedDisks = outFlankedDisks.concat(horizontalDiscs, verticalDiscs, leftRightDiagonalDiscs, rightLeftDiagonalDiscs);

        return outFlankedDisks;
    }
}
