package gamesmanagement;

public class Person {
    private String name;
    private String nickName;

    //Constructor for Person Class
    public Person(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    //Name Getter
    public String getName() {
        return name;
    }

    //Name Setter
    public void setName(String name) {
        this.name = name;
    }

    //NickName Getter
    String getNickName() {
        return nickName;
    }

    //NickName Setter
    void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
