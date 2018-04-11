import { DiscType } from "../disc/disc";
import { User } from "../user/user";

export class Player {
    user: User;
    discType: DiscType;
    currentScore = 2;

    constructor(discType: DiscType) {
        this.discType = discType;
    }

    public initializePlayerFromUserJSON(jsonData: any): void {

    }
}