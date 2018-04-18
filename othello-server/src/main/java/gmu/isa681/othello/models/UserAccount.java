package gmu.isa681.othello.models;



import javax.persistence.Entity;

@Entity
public class UserAccount {

    private String userName;
    private String password;

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
