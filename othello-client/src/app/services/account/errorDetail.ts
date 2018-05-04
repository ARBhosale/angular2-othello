export class ErrorDetail {
    private message: string;

    constructor(serverAccountResponse: any) {
        let serverAccount = JSON.parse(serverAccountResponse._body);
        this.message = serverAccount.messgae;
      
    }
}