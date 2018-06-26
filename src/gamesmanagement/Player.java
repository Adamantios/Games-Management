package gamesmanagement;

public class Player extends Person {
    private String email;

    //Constructor for Class Player
    public Player(String name, String nickName, String email) {
        super(name, nickName);
        this.email = email;
    }

    //Email Getter
    String getEmail() {
        return email;
    }

    //Email Setter
    void setEmail(String email) {
        this.email = email;
    }
}
