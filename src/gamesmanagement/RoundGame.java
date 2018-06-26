package gamesmanagement;

import java.util.Date;

public class RoundGame extends Game {
    private int rounds;

    //Constructor for RoundGame Class
    public RoundGame(String name, int maxPlayers, Date date, int rounds) {
        super(name, maxPlayers, date);
        this.rounds = rounds;
    }

    //Rounds Getter
    int getRounds() {
        return rounds;
    }

    //Rounds Setter
    void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
