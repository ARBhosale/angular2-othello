export enum DiscType {
    Black, White
}

export class Disc {

    constructor(public discValue: DiscType, public rowPosition: number, public colPosition: number) { }

    public getCopy(): Disc {
        return new Disc(this.discValue, this.rowPosition, this.colPosition);
    }
}