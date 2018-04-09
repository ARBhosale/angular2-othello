import { Disc, DiscType } from "../disc/disc";
import { GameService } from "../../services/game/game.service";

export class Board {

    static NUMBER_OF_ROWS = 8;
    static NUMBER_OF_COLUMNS = 8;
    static EMPTY_LOCATION_RULE = 'You must select empty location on board';
    static ADJACENT_LOCATION_RULE = 'You must select an empty location on board which outflanks and flips an opponent disc';

    public values: Array<Array<Disc>>;

    actionResultMessage = '';

    constructor(private gameService: GameService) {
        this.initializeValues();
    }

    public setBoardPiece(rowNum: number, colNum: number): void {
        if (this.values[rowNum][colNum] !== null) {
            this.actionResultMessage = Board.EMPTY_LOCATION_RULE;
        }
    }

    private initializeValues(): void {
        this.values = [];
        for (let row = 0; row < Board.NUMBER_OF_ROWS; row++) {
            this.values[row] = [];
            for (let col = 0; col < Board.NUMBER_OF_COLUMNS; col++) {
                this.values[row][col] = null;
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

    // private getOutflankedDiscs(moveRow: number, moveCol: number): Array<Disc> {
    //     // let currentUserDiscType = this.gameService.currentPlayer.discType;
    //     // let horizontalOutflanks = this.get
    // }

    // private getHorizontalOutflanks(moveRow: number, moveCol: number, discType: DiscType): Array<Disc> {
    //     let leftOutFlanks = this.getLeftHorizontalOutflanks(moveRow, moveCol, discType);
    // }

    // private getLeftOutflanks(moveRow: number, moveCol: number, discType: DiscType, discs: Array<Disc>): Array<Disc> {
    //     let opponentsOutFlankedDisks = [];
    //     if (moveCol > 0 && moveCol < Board.NUMBER_OF_COLUMNS) {
    //         if (this.values[moveRow][moveCol - 1] === null) {
    //             return null;
    //         }
    //         if (this.values[moveRow][moveCol - 1].discValue === discType) {
    //             return null;
    //         }
    //         if (this.values[moveRow][moveCol - 1].discValue !== discType) {
    //             let outFlankedDisk = this.values[moveRow][moveCol - 1];
    //             opponentsOutFlankedDisks.push(outFlankedDisk);
    //             let outFlankedDisks = this.getHorizontalOutflanks(moveRow, moveCol - 1, discType);
    //             if (outFlankedDisks !== null) {
    //                 opponentsOutFlankedDisks = opponentsOutFlankedDisks.concat(outFlankedDisks);
    //             }
    //             return opponentsOutFlankedDisks;
    //         }
    //     }
    //     return null;
    // }

    // private getLeftHorizontalOutflanks(moveRow: number, moveCol: number, discType: DiscType): Array<Disc> {
    //     let opponentsOutFlankedDisks = [];
    //     if (moveCol > 0 && moveCol < Board.NUMBER_OF_COLUMNS) {
    //         if (this.values[moveRow][moveCol - 1] === null) {
    //             return null;
    //         }
    //         if (this.values[moveRow][moveCol - 1].discValue === discType) {
    //             return null;
    //         }
    //         if (this.values[moveRow][moveCol - 1].discValue !== discType) {
    //             let outFlankedDisk = this.values[moveRow][moveCol - 1];
    //             opponentsOutFlankedDisks.push(outFlankedDisk);
    //             let outFlankedDisks = this.getHorizontalOutflanks(moveRow, moveCol - 1, discType);
    //             if (outFlankedDisks !== null) {
    //                 opponentsOutFlankedDisks = opponentsOutFlankedDisks.concat(outFlankedDisks);
    //             }
    //             return opponentsOutFlankedDisks;
    //         }
    //     }
    //     return null;
    // }
}
