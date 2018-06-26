package gamesmanagement;

import java.util.Date;

public class TimeGame extends Game {
    private Date time;

    //Constructor for TimeGame Class
    public TimeGame(String name, int maxPlayers, Date date, Date time) {
        super(name, maxPlayers, date);
        this.time = time;
    }

    //Time Getter
    Date getTime() {
        return time;
    }

    //Time Setter
    void setTime(Date time) {
        this.time = time;
    }
}
