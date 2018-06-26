package gamesmanagement;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class Tournament {
    private String name;
    private Date startDate;
    private Date endDate;
    private String place;
    private ArrayList<Game> games;
    private ArrayList<Person> persons;

    //Constructor for Tournament Class
    public Tournament(String name, Date startDate, Date endDate, String place) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        games = new ArrayList<>();
        persons = new ArrayList<>();
    }

    //Name Getter
    public String getName() {
        return name;
    }

    //Name Setter
    public void setName(String name) {
        this.name = name;
    }

    //StartDate Getter
    Date getStartDate() {
        return startDate;
    }

    //StartDate Setter
    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //EndDate Getter
    Date getEndDate() {
        return endDate;
    }

    //EndDate Setter
    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //Place Getter
    String getPlace() {
        return place;
    }

    //Place Setter
    void setPlace(String place) {
        this.place = place;
    }

    //returns the number of games
    int numberOfGames() {
        return games.size();
    }

    //creates a new time game
    void createTimeGame(String name, int maxPlayers, Date date, Date time) {
        games.add(new TimeGame(name, maxPlayers, date, time));
    }

    //creates a new round game
    void createRoundGame(String name, int maxPlayers, Date date, int rounds) {
        games.add(new RoundGame(name, maxPlayers, date, rounds));
    }

    //find a game by game name
    Game findGame(String gameName) {
        for (Game g : games)
            if (g.getName().equals(gameName))
                return g;
        return null;
    }

    //deletes a game
    void deleteGame(Game g) {
        Iterator<Game> it = games.iterator();

        if (!g.gameHasStarted())
            while (it.hasNext()) {
                Game game = it.next();
                if (game.equals(g)) {
                    it.remove();
                    break;
                }
            }
    }

    // Edits a game
    public void editGame(String name, Game newInfo) {
        for (Game g : games)
            if (g.getName().equals(name) && !g.gameHasStarted()) {
                g.setName(newInfo.getName());
                g.setMaxPlayers(newInfo.getMaxPlayers());
                g.setDate(newInfo.getDate());
                break;
            }
    }

    ArrayList<Game> getGames() {
        return games;
    }

    void addPerson(Person p) {
        persons.add(p);
    }

    ArrayList<Person> getPersons() {
        return persons;
    }

    Person getPerson(String nickName) {
        for (Person p : persons)
            if (p.getNickName().equals(nickName))
                return p;
        return null;
    }

    void deletePerson(String nickName) {
        for (Person p : persons)
            if (p.getNickName().equals(nickName)) {
                persons.remove(p);
                return;
            }
    }
}
