import { Disc, DiscType } from "../disc/disc";
import { GameService } from "../../services/game/game.service";
import { Game } from "../../services/game/game";

export class Board {

    static NUMBER_OF_ROWS = 8;
    static NUMBER_OF_COLUMNS = 8;
    static EMPTY_LOCATION_RULE = 'You must select empty location on board';
    static OUTFLANK_RULE = 'You must select an empty location on board which outflanks and flips an opponent disc';

    public values: Array<Array<Disc>>;

    private game: Game;

    actionResultMessage: string = null;

    constructor(game: Game) {
        this.game = game;
        this.initializeValues();
    }

    public setBoardPiece(rowNum: number, colNum: number): void {
        // if the board position is empty
        if (this.values[rowNum][colNum] !== null) {
            this.actionResultMessage = Board.EMPTY_LOCATION_RULE;
            return;
        }
        this.values[rowNum][colNum] = undefined;
        // if the move can outflank an opponent disc
        let outFlankedDiscs = this.getOutflankedDiscs(rowNum, colNum);
        if (outFlankedDiscs.length === 0) {
            this.actionResultMessage = Board.OUTFLANK_RULE;
            return;
        }
        // setting board piece
        console.log('Game before: ');
        console.log(this.game);
        console.log('Player ' + this.game.currentPlayer.discType + ' played: Row = ' + rowNum + ', Col = ' + colNum);
        this.game.playMove(rowNum, colNum)
            .then((updatedGame) => {
                // this.game = updatedGame;

                this.values[rowNum][colNum] = new Disc(this.game.currentPlayer.discType, rowNum, colNum);
                this.flipDiscs(outFlankedDiscs, this.game.currentPlayer.discType);
                this.game.updateScores(outFlankedDiscs.length);
                this.game.updatePlayerTurns();
                console.log('Game after: ');
                console.log(this.game);
            })
            .catch((error) => {
                this.actionResultMessage = error;
            });
    }

    private flipDiscs(discs: Array<Disc>, discType: DiscType): void {
        for (let i = 0; i < discs.length; i++) {
            discs[i].discValue = discType;
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
        this.initializeStartingPositions();
    }

    private initializeStartingPositions(): void {
        this.values[3][3] = new Disc(DiscType.White, 3, 3);
        this.values[4][4] = new Disc(DiscType.White, 4, 4);
        this.values[4][3] = new Disc(DiscType.Black, 4, 3);
        this.values[3][4] = new Disc(DiscType.Black, 3, 4);
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
            // if (listOfDiscs[i].rowPosition === userMoveRow && listOfDiscs[i].colPosition === userMovCol) {
            //     return i;
            // }
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

    private getOutflankedDiscs(moveRow: number, moveCol: number): Array<Disc> {
        let outFlankedDisks = [];
        let currentUserDiscType = this.game.currentPlayer.discType;

        // horizontal outflanked disks
        let horizontalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getRowOfDiscs(moveRow));
        // vertical outflanked disks
        let verticalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getColumnOfDiscs(moveCol));
        // leftRight diagonal outflanked disks
        let leftRightDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getLeftRightDiagonalDiscs(moveRow, moveCol));
        // rightLeft diagonal outflanked disks
        let rightLeftDiagonalDiscs = this.getOutFlankedDiscsOutOfList(moveRow, moveCol, currentUserDiscType, this.getRightLeftDiagonalDiscs(moveRow, moveCol))

        outFlankedDisks = outFlankedDisks.concat(horizontalDiscs, verticalDiscs, leftRightDiagonalDiscs, rightLeftDiagonalDiscs);

        return outFlankedDisks;
    }



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
