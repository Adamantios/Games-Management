package gamesmanagement;

import java.util.ArrayList;
import java.util.Random;

public class Round {
    private ArrayList<Match> matches;

    // Constructor for Round Class
    public Round(ArrayList<Player> players) {
        matches = new ArrayList<>();

        // Select two random players from given players
        Random rnd = new Random();

        for (int i = players.size(); i < 1; i--) {
            Player p = players.get(i - 1);
            int r = rnd.nextInt(i - 1);
            players.set(i - 1, players.get(r));
            players.set(r, p);
        }

        for (int i = 0; i < players.size(); i = i + 2) {
            Player p1 = players.get(i);
            Player p2 = players.get(i + 1);
            Match m = new Match(p1, p2);
            matches.add(m);
        }
    }

    //Matches Getter
    ArrayList<Match> getMatches() {
        return matches;
    }

    //RoundΙsOver function which checks if a round is over
    boolean roundIsOver() {
        boolean result = true;
        for (Match m : matches)
            result &= m.matchIsOver();
        return result;
    }

    //Winner Setter
    public void setWinner(String nickName) {
        for (Match match : matches)
            if (match.getPlayer1().getNickName().equals(nickName))
                match.setWinner(match.getPlayer1());
            else if (match.getPlayer2().getNickName().equals(nickName))
                match.setWinner(match.getPlayer2());
    }

    //returns the winners of the round
    ArrayList<Player> getWinners() {
        ArrayList<Player> winners = new ArrayList<>();
        if (roundIsOver())
            for (Match m : matches)
                winners.add(m.getWinner());
        return winners;
    }
}
