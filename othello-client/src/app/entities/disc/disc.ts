export enum DiscType {
    Black, White
}

export class Disc {

    constructor(public discValue: DiscType, public rowPosition: number, public colPosition: number) { }


}