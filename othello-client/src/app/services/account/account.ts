export class Account {
    private id: number;
    private userName: string;
    private fullName: string;
    private wins: number;
    private losses: number;
    private firstName: string;
    private lastName: string;

    constructor(serverAccountResponse: any) {
        let serverAccount = JSON.parse(serverAccountResponse._body);
        this.id = serverAccount.id;
        this.userName = serverAccount.userName;
        this.fullName = serverAccount.fullName;
        this.wins = serverAccount.wins;
        this.losses = serverAccount.losses;
        this.firstName = serverAccount.firstName;
        this.lastName = serverAccount.lastName;
    }
}