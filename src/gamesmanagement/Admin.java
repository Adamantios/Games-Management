package gamesmanagement;

public class Admin extends Person {
    private String phoneNumber;

    //Constructor for the Admin Class
    public Admin(String name, String nickName, String number) {
        super(name, nickName);
        phoneNumber = number;
    }

    //PhoneNumber Getter
    String getPhoneNumber() {
        return phoneNumber;
    }

    //PhoneNumber Setter
    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
