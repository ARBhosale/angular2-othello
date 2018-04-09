import { Disc, DiscType } from "../disc/disc";
import { GameService } from "../../services/game/game.service";

export class Board {

    static NUMBER_OF_ROWS = 8;
    static NUMBER_OF_COLUMNS = 8;
    static EMPTY_LOCATION_RULE = 'You must select empty location on board';
    static ADJACENT_LOCATION_RULE = 'You must select an empty location on board which outflanks and flips an opponent disc';

    private values: Array<Array<Disc>>;

    actionResultMessage = '';

    constructor(private gameService: GameService) { }

    public setBoardPiece(rowNum: number, colNum: number): void {
        if (this.values[rowNum][colNum] !== null) {
            this.actionResultMessage = Board.EMPTY_LOCATION_RULE;
        }
    }

    private initializeValues(): void {
        for (let row = 0; row < Board.NUMBER_OF_ROWS; row++) {
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
}
