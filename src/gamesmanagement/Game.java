package gamesmanagement;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    private String name;
    private int maxPlayers;
    private Date date; // date and time game starts
    private ArrayList<Player> players;
    private ArrayList<Round> rounds;
    private Admin administrator;

    //Constructor for the Game Class
    public Game(String name, int maxPlayers, Date date) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.date = date;
        players = new ArrayList<>();
        rounds = new ArrayList<>();
    }

    //Name Getter
    public String getName() {
        return name;
    }

    //MaxPlayers Getter
    int getMaxPlayers() {
        return maxPlayers;
    }

    ArrayList<Player> getAllPlayers() {
        return players;
    }

    //Date Getter
    Date getDate() {
        return date;
    }

    //Name Setter
    public void setName(String name) {
        this.name = name;
    }

    //MaxPlayers Setter
    void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    //Date Setter
    void setDate(Date date) {
        this.date = date;
    }

    //Administrator Getter
    Admin getAdministrator() {
        return administrator;
    }

    //Administrator Setter
    void setAdministrator(Admin administrator) {
        this.administrator = administrator;
    }

    ArrayList<Round> getRoundsArray() {
        return rounds;
    }

    //returns the number of players
    public int numberOfPlayers() {
        return players.size();
    }

    //returns the number of rounds
    private int numberOfRounds() {
        return rounds.size();
    }

    //checks if a game has started
    boolean gameHasStarted() {
        return numberOfRounds() > 0;
    }

    //returns the winners of the round
    ArrayList<Player> getWinnersOfRound(int roundNumber) {
        return new ArrayList<>(rounds.get(roundNumber).getWinners());
    }

    //returns the winner of the game
    Player getWinnerOfGame() {
        if (gameIsOver()) {
            return getWinnersOfRound(rounds.size() - 1).get(0);
        } else {
            return null;
        }
    }

    //check if current (last) round is over
    boolean roundIsOver() {
        if (rounds.size() > 0) {
            return rounds.get((rounds.size()) - 1).roundIsOver();
        } else return true;
    }

    //starts a new round
    void startNewRound() {
        if (gameHasStarted()) {
            Round r = rounds.get(rounds.size() - 1);
            if (r.roundIsOver())
                rounds.add(new Round(r.getWinners()));
        } else if (!players.isEmpty() && GamesManagement.isExpOfTwo(players.size()))
            rounds.add(new Round(players));
    }

    boolean gameIsOver() {
        if (rounds.isEmpty()) {
            return false;
        }
        return rounds.get(rounds.size() - 1).getWinners().size() == 1;
    }

    //adds a new player
    void addPlayer(Player p) {
        if (!(gameHasStarted() && (players.size() < maxPlayers)))
            players.add(p);
    }

    boolean playerExists(String nickName) {
        for (Player p : players)
            if (p.getNickName().equals(nickName))
                return true;
        return false;
    }

    //deletes a player
    void deletePlayer(Player p) {
        if (!gameHasStarted()) {
            for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
                if (it.next().equals(p)) {
                    it.remove();
                    break;
                }
            }
        }
    }
}
