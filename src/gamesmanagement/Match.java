package gamesmanagement;

import java.util.ArrayList;

public class Match {
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    private int theWinner;

    //Constructor for Match Class
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        theWinner = 0;
    }

    //Player1 Getter
    Player getPlayer1() {
        return player1;
    }

    //Player1 Setter
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    //Player2 Getter
    Player getPlayer2() {
        return player2;
    }

    //Player2 Setter
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    //Players Getter
    public ArrayList<Player> getPlayers() {
        return players;
    }

    //returns 0 if game has not started , 1 if player1 won and 2 if player2 won
    Player getWinner() {
        return theWinner == 0 ? null : theWinner == 1 ? player1 : player2;
    }

    //sets the winner of match based on the player' s nickName
    public void setWinner(String playerNickName) {
        if (player1.getNickName().equals(playerNickName))
            theWinner = 1;
        else if (player2.getNickName().equals(playerNickName))
            theWinner = 2;
    }

    //sets the winner of match based on the player and returns true.In case of an invalid player the function returns false
    void setWinner(Player player) {
        if (player.equals(player1))
            theWinner = 1;
        if (player.equals(player2))
            theWinner = 2;
    }

    //returns true if the match is over, otherwise it returns false
    boolean matchIsOver() {
        return theWinner > 0;
    }
}
