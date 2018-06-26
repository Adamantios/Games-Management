package gamesmanagement;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class GamesManagement {

    private static Tournament tournament;
    private static final Scanner scanIn = new Scanner(System.in);
    private static menus Menu;

    //general functions
    public static void main(String[] args) {
        Menu = new menus();
        MenuChoice choice = Menu.StartTournament();

        if (choice == MenuChoice.createTournament) {
            createTournament();
            manageTournament();
        }
        System.out.println();
        System.out.println("Σας ευχαριστούμε που χρησιμοποιήσατε το λογισμικό μας!");
    }

    /**
     * Reads a string from the user, boolean param
     *
     * @param message        a message to tell the user what to insert.
     * @param emptyPermitted true if empty string is allowed or false if not
     * @return the string which has been read.
     */
    private static String readString(String message, Boolean emptyPermitted) {
        String s = "";
        while (s.isEmpty()) {
            System.out.print(message);
            s = scanIn.nextLine().trim();
            if (s.isEmpty() && emptyPermitted) {
                return "";
            }
        }
        return s;
    }

    /**
     * Reads an integer  from the user
     *
     * @param message        a message to tell the user what to insert.
     * @param emptyPermitted true if enter is allowed or false if not.
     * @return -1 if the user pressed enter, otherwise the integer he inserted.
     */
    private static int readInt(String message, Boolean emptyPermitted) {
        boolean retry = true;
        int i = 0;
        while (retry) {
            System.out.print(message);
            String s = scanIn.nextLine().trim();
            if (s.isEmpty() && emptyPermitted) {
                return -1;
            }
            try {
                i = Integer.parseInt(s);
                retry = false;
            } catch (Exception ex) {
                System.out.println("Παρακαλώ πληκτρολογείστε έναν έγκυρο αριθμό");
            }
        }
        return i;
    }

    /**
     * Reads a date from the user
     *
     * @param message        a message to tell the user what to insert.
     * @param formatString   the format in which the user has to insert the date.
     * @param emptyPermitted true if enter is allowed or false if not.
     * @return new Date() if the user pressed enter, otherwise the date he inserted.
     */
    private static Date readDate(String message, String formatString, Boolean emptyPermitted) {
        Date inDate = new Date();
        boolean retry = true;
        while (retry) {
            System.out.print(message);
            String s = scanIn.nextLine().trim();
            if (s.isEmpty() && emptyPermitted) {
                return null;
            }
            try {
                inDate = new SimpleDateFormat(formatString).parse(s);
                retry = false;
            } catch (Exception ex) {
                System.out.println("Παρακαλώ πληκτρολογείστε μια έγκυρη τιμή με τη μορφή " + formatString);
            }
        }
        return inDate;
    }

    //checks if max number of players is power of two, so that a game can be created
    static boolean isExpOfTwo(int i) {
        return (i != 0) && ((i & (i - 1)) == 0);
    }

    //tournament management
    private static void createTournament() {
        System.out.println("Παρακαλώ εισάγετε τα στοιχεία του τουρνουά:");
        System.out.println("------------------------------------------:");
        String name = readString("Τίτλος: ", false);
        Date startDate = readDate("Ημερομηνία έναρξης (dd-MM-yyyy): ", "dd-MM-yyyy", false);
        Date endDate;
        while (true) {
            endDate = readDate("Ημερομηνία λήξης (dd-MM-yyyy): ", "dd-MM-yyyy", false);
            if (endDate.before(startDate)) {
                System.out.println("Η ημερομηνία λήξης πρέπει να είναι μεταγενέστερη της έναρξης");
            } else {
                break;
            }
        }
        String place = readString("Τόπος διεξαγωγής: ", false);
        tournament = new Tournament(name, startDate, endDate, place);
    }

    //prints tournament information.
    private static void printTournamentInfo() {
        System.out.println("+----------------------------------------+");
        System.out.printf("%-41s|\n", "| Στοιχεία Τουρνουά");
        System.out.printf("%-41s|\n", ("| " + tournament.getName()));
        System.out.printf("%-41s|\n", ("| Έναρξη " + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getStartDate())));
        System.out.printf("%-41s|\n", ("| Λήξη   " + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getEndDate())));
        System.out.printf("%-41s|\n", ("| Τόπος διεξαγωγής: " + tournament.getPlace()));
    }

    //Displays the tournament menu and processes the users choice
    private static void manageTournament() {
        boolean loop = true;
        while (loop) {
            printTournamentInfo();
            MenuChoice choice = Menu.manageTournament();
            switch (choice) {
                case editTournament:
                    editTournament();
                    break;
                case viewGames:
                    viewGames();
                    break;
                case addGame:
                    addGame();
                    break;
                case editGame:
                    editGame();
                    break;
                case deleteGame:
                    deleteGame();
                    break;
                case manageGame:
                    manageGame();
                    break;
                case viewPersons:
                    viewAllPersons(false);
                    break;
                case addPerson:
                    addPerson();
                    break;
                case editPerson:
                    editPerson();
                    break;
                case deletePerson:
                    deletePerson();
                    break;
                case viewAllWinners:
                    viewAllWinners();
                    break;
                case exit:
                    loop = false;
                    break;
            }
        }
    }

    private static void editTournament() {
        printTournamentInfo();
        System.out.println("+----------------------------------------+\n");
        System.out.println("Παρακαλώ εισάγετε τα νέα στοιχεία του τουρνουά:");
        System.out.println("Πατήστε enter στα στοιχεία που θέλετε να παραμείνουν αμετάβλητα\n");

        String name = readString("Τίτλος: ", true);
        if (!name.isEmpty()) {
            tournament.setName(name);
        }

        Date startDate = readDate("Ημερομηνία έναρξης (dd-MM-yyyy): ", "dd-MM-yyyy", true);
        if (startDate != null) {
            tournament.setStartDate(startDate);
        } else {
            startDate = tournament.getStartDate();
        }

        Date endDate;
        while (true) {

            endDate = readDate("Ημερομηνία λήξης (dd-MM-yyyy): ", "dd-MM-yyyy", true);
            if (endDate == null) {
                endDate = tournament.getEndDate();
            }

            if (endDate.before(startDate)) {
                System.out.println("Η ημερομηνία λήξης πρέπει να είναι μεταγενέστερη της έναρξης");
            } else {
                break;
            }
        }
        tournament.setEndDate(endDate);
        String place = readString("Τόπος διεξαγωγής (ή enter για να παραμείνει ίδιο): ", true);
        if (!place.isEmpty()) {
            tournament.setPlace(place);
        }
    }

    private static void viewAllPersons(boolean playersOnly) {
        for (Person p : tournament.getPersons()) {
            if (playersOnly && (p instanceof Admin)) {
                continue;
            }
            System.out.println("Ψευδώνυμο     : " + p.getNickName());
            System.out.println("Ονοματεπώνυμο : " + p.getName());
            if (p instanceof Player) {
                System.out.println("Ιδιότητα      : Παίκτης");
                System.out.println("Email         : " + ((Player) p).getEmail());
            } else if (p instanceof Admin) {
                System.out.println("Ιδιότητα      : Διαχειριστής");
                System.out.println("Τηλέφωνο      : " + ((Admin) p).getPhoneNumber());
            }
            System.out.println("--------------------------------------------------------:");
        }
    }

    private static void addPerson() {
        System.out.println("Παρακαλώ εισάγετε τα στοιχεία του συμμετέχοντα:");
        System.out.println("----------------------------------------------:");
        String name = readString("Ονοματεπώνυμο: ", false);
        String nickName = readString("Ψευδώνυμο: ", false);
        while (tournament.getPerson(nickName) != null) {
            nickName = readString("Το ψευδώνυμο υπάρχει ήδη! Δώστε ένα διαφορετικό: ", false);
        }
        int role = -1;
        while (role < 1 || role > 2) {
            role = readInt("Ιδιότητα (1 = Παίχτης,  2 = Διαχειριστής) :", false);
        }
        Person p;
        if (role == 1) {
            String eMail = readString("Email: ", false);
            p = new Player(name, nickName, eMail);
            tournament.addPerson(p);
        } else {
            String phone = readString("Τηλέφωνο: ", false);
            p = new Admin(name, nickName, phone);
            tournament.addPerson(p);
        }
        System.out.println("Θέλετε να τον προσθέσετε σε κάποιο παιχνίδι;");
        String gm = readString("Δώστε τον τίτλο του παιχνιδιού ή enter για να το κάνετε αργότερα: ", true);
        String msg = "Ο συμμετέχοντας προστέθηκε στη λίστα. Μπορείτε να τον εντάξετε σε κάποιο παιχνίδι από τις αντίστοιχες επιλογές";
        if (!gm.isEmpty()) {
            Game g = tournament.findGame(gm);
            if (g == null) {
                System.out.println("Το παιχνίδι δε βρέθηκε. " + msg);
            } else if (p instanceof Player) {
                if (g.gameHasStarted()) {
                    System.out.println(
                            "Το παιχνίδι έχει ήδη αρχίσει και δε μπορείτε να προσθέσετε παίκτες!\n" + msg);
                    return;
                }
                if (g.getAllPlayers().size() == g.getMaxPlayers()) {
                    System.out.println("Έχει συμπληρωθεί ο μέγιστος αριθμός παικτών!\n" + msg);
                    return;
                }
                tournament.findGame(gm).addPlayer((Player) p);
            } else {
                tournament.findGame(gm).setAdministrator((Admin) p);
            }
        } else {
            System.out.println(msg);
        }
    }

    private static void editPerson() {
        String nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους συμμετέχοντες:", false);
        while (nickName.equals("?")) {
            viewAllPersons(false);
            nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους συμμετέχοντες:", false);
        }
        Person p = tournament.getPerson(nickName);
        if (p == null) {
            System.out.println("Ο χρήστης δε βρέθηκε\n");
            return;
        }
        System.out.println("Παρακαλώ εισάγετε τα στοιχεία του συμμετέχοντα:");
        System.out.println("Πατήστε enter στα στοιχεία που θέλετε να παραμείνουν αμετάβλητα\n");
        System.out.println("----------------------------------------------:");
        String name = readString("Ονοματεπώνυμο: ", true);
        nickName = readString("Ψευδώνυμο: ", true);
        if (p instanceof Player) {
            String eMail = readString("Email: ", true);
            if (!name.isEmpty()) {
                p.setName(name);
            }
            if (!nickName.isEmpty()) {
                p.setNickName(nickName);
            }
            if (!eMail.isEmpty()) {
                ((Player) p).setEmail(eMail);
            }
        } else {
            String phone = readString("Τηλέφωνο: ", true);
            if (!name.isEmpty()) {
                p.setName(name);
            }
            if (!nickName.isEmpty()) {
                p.setNickName(nickName);
            }
            if (!phone.isEmpty()) {
                ((Admin) p).setPhoneNumber(phone);
            }
        }
    }

    private static void deletePerson() {
        String nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους συμμετέχοντες:", false);
        while (nickName.equals("?")) {
            for (Person p : tournament.getPersons()) {
                System.out.print("Ψευδώνυμο     : " + p.getNickName());
                System.out.print("Ονοματεπώνυμο : " + p.getName());
                System.out.print("Ιδιότητα      : "
                        + (p instanceof Player ? ((Player) p).getEmail() : ((Admin) p).getPhoneNumber()));
            }
            System.out.println("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους συμμετέχοντες:");
            nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους συμμετέχοντες:", false);
        }
        Person p = tournament.getPerson(nickName);
        if (p == null) {
            System.out.println("Ο χρήστης δε βρέθηκε\n");
            return;
        }
        for (Game g : tournament.getGames()) {
            if (g.gameHasStarted()) {
                for (Player p1 : g.getAllPlayers()) {
                    if (p1.getNickName().equals(nickName)) {
                        System.out.println("Ο χρήστης δε μπορεί να διαγραφεί γιατί το παιχνίδι έχει ήδη αρχίσει!\n");
                        return;
                    }
                }
            }
        }
        tournament.deletePerson(nickName);
    }

    private static void viewAllWinners() {
        System.out.println("+----------------------------------------+");
        System.out.printf("%-41s|\n", "| Αποτελέσματα Παιχνιδιών:");
        System.out.println("+----------------------------------------+");
        for (Game g : tournament.getGames()) {
            System.out.printf("%-41s|\n", "| Παιχίδι: " + g.getName());
            System.out.printf("%-41s|\n", "| Νικητής: " + (!(g.gameIsOver()) ? "Δεν τελείωσε ακόμη"
                    : (g.getWinnerOfGame().getNickName() + " - " + g.getName())));
            System.out.println("+----------------------------------------+");
        }
        System.out.println("Πατήστε enter για συνέχεια...");
        scanIn.nextLine();
    }

    private static void viewGames() {
        if (tournament.numberOfGames() == 0) {
            System.out.println("Δεν έχουν καταχωρηθεί παιχνίδια.");
        }
        for (Game g : tournament.getGames()) {
            System.out.println("Τίτλος: " + g.getName());
            System.out.println("Ημερομηνία έναρξης: "
                    + new SimpleDateFormat("dd-MM-yyyy").format(g.getDate()));
            System.out.println("Ώρα έναρξης: " + new SimpleDateFormat("HH:mm:ss").format(g.getDate()));
            System.out.println("Μέγιστος αριθμός παιχτών: " + g.getMaxPlayers());
            System.out.println(g instanceof TimeGame
                    ? ("Διάρκεια παιχνιδιού: " + new SimpleDateFormat("HH:mm:ss").format(((TimeGame) g).getTime()))
                    : ("Αριθμός γύρων: " + ((RoundGame) g).getRounds()));
            Admin adm = g.getAdministrator();
            System.out.println("Διαχειριστής: " + (adm == null ? "Δεν έχει οριστεί"
                    : adm.getNickName() + " - " + adm.getName()));
            System.out.println();
        }
        System.out.println("Πατήστε enter για συνέχεια...");
        scanIn.nextLine();
    }

    private static void addGame() {
        System.out.println("Παρακαλώ εισάγετε τα στοιχεία του Παιχνιδιού:");
        System.out.println("--------------------------------------------:");
        String name = readString("Τίτλος: ", false);
        Date date;
        while (true) {
            date = readDate("Ημερομηνία και ώρα έναρξης (dd-MM-yyyy HH:mm:ss): ", "dd-MM-yyyy HH:mm:ss", false);
            if (date.before(tournament.getStartDate()) || date.after(tournament.getEndDate())) {
                System.out.println("Οι ημερομηνίες διεξαγωγής του τουρνουά είναι από "
                        + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getStartDate())
                        + " έως "
                        + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getEndDate()));
                System.out.println("Παρακαλώ δώστε μια ημερομηνία εντός των παραπάνω ορίων");
            } else {
                break;
            }
        }
        int maxPlayers;
        do {
            maxPlayers = readInt("Μέγιστος αριθμός παιχτών (δύναμη του 2): ", false);
        } while (!isExpOfTwo(maxPlayers));
        int gtype = 0;
        while (gtype != 1 && gtype != 2) {
            gtype = readInt("Τύπος παιχνιδιού (1 = time game,   2 = round game):", false);
            if (gtype == 1) {
                Date time = readDate("Διάρκεια παιχνιδιού (HH:mm:ss): ", "HH:mm:ss", false);
                tournament.createTimeGame(name, maxPlayers, date, time);
            } else if (gtype == 2) {
                int rounds = readInt("Αριθμός γύρων: ", false);
                while (rounds % 2 == 0)
                    rounds = readInt("Αριθμός γύρων (Πρέπει να είναι περιττός αριθμός): ", false);
                tournament.createRoundGame(name, maxPlayers, date, rounds);
            }
        }
    }

    private static void editGame() {
        String gameName = readString("Παρακαλώ δώστε τον τίτλο του παιχνιδιού ή ? για να δείτε όλα τα παιχνίδια: ", false);
        while (gameName.equals("?")) {
            viewGames();
            gameName = readString("Παρακαλώ δώστε τον τίτλο του παιχνιδιού ή ? για να δείτε όλα τα παιχνίδια: ", false);
        }
        Game g = tournament.findGame(gameName);
        if (g == null) {
            System.out.println("Το παιχνίδι δε βρέθηκε\n");
            return;
        }
        System.out.println("Παρακαλώ εισάγετε τα στοιχεία του παιχιδιού:");
        System.out.println("Πατήστε enter στα στοιχεία που θέλετε να παραμείνουν αμετάβλητα\n");
        System.out.println("----------------------------------------------:");
        String newName = readString("Τίτλος: ", true);
        if (!newName.isEmpty()) {
            g.setName(newName);
        }
        Date date;
        while (true) {
            date = readDate("Ημερομηνία και ώρα έναρξης (dd-MM-yyyy HH:mm:ss): ", "dd-MM-yyyy HH:mm:ss", true);
            if (date == null) {
                date = g.getDate();
            }
            if (date.before(tournament.getStartDate()) || date.after(tournament.getEndDate())) {
                System.out.println("Οι ημερομηνίες διεξαγωγής του τουρνουά είναι από "
                        + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getStartDate())
                        + " έως "
                        + new SimpleDateFormat("dd-MM-yyyy").format(tournament.getEndDate()));
                System.out.println("Παρακαλώ δώστε μια ημερομηνία εντός των παραπάνω ορίων");
            } else {
                break;
            }
        }
        g.setDate(date);
        int maxPlayers;
        do {
            maxPlayers = readInt("Μέγιστος αριθμός παιχτών (δύναμη του 2): ", true);
            if (maxPlayers == -1) {
                maxPlayers = g.getMaxPlayers();
            }
        } while (!isExpOfTwo(maxPlayers));
        g.setMaxPlayers(maxPlayers);
        int gtype = 0;
        while (gtype != 1 && gtype != 2) {
            gtype = readInt("Τύπος παιχνιδιού (1 = time game,   2 = round game):", true);

            if (gtype == -1) {
                gtype = (g instanceof TimeGame) ? 1 : 2;
            }

            if (g instanceof TimeGame) {
                Date time = readDate("Διάρκεια παιχνιδιού (HH:mm:ss): ", "HH:mm:ss", true);

                if (time == null) {
                    time = ((TimeGame) g).getTime();
                }

                ((TimeGame) g).setTime(time);
            } else if (g instanceof RoundGame) {
                int rounds = readInt("Αριθμός γύρων: ", true);

                if (rounds == -1) {
                    rounds = ((RoundGame) g).getRounds();
                }

                ((RoundGame) g).setRounds(rounds);
            }
        }
    }

    private static void deleteGame() {
        String gameName = readString("Παρακαλώ δώστε το όνομα του παιχνιδιού που θέλετε να διαγράψετε: ", false);
        Game g = tournament.findGame(gameName);
        if (g == null) {
            System.out.println("Το παιχνίδι δε βρέθηκε!");
        } else if (g.gameHasStarted()) {
            System.out.println("Το παιχνίδι έχει ήδη αρχίσει και δε μπορεί να διαγραφεί!");
        } else {
            tournament.deleteGame(g);
            System.out.println("Το παιχνίδι διαγράφτηκε! "
                    + "Οι συμμετέχοντες παραμένουν στη λίστα του τουρνουά και "
                    + "μπορούν να ενταχτούν σε κάποιο άλλο παιχνίδι.");
        }
    }

    //game management
    private static void manageGame() {
        String gameName = readString(
                "Παρακαλώ δώστε το όνομα του παιχνιδιού που θέλετε να διαχειριστείτε (ή enter για επιστροφή): ",
                true);
        if (gameName.isEmpty()) {
            System.out.println("Το παιχνίδι δε βρέθηκε!");
            return;
        }
        Game theGame = tournament.findGame(gameName);

        boolean loop = true;
        while (loop) {
            printGameInfo(theGame);
            MenuChoice choice = Menu.manageGame();
            switch (choice) {
                case viewPlayers:
                    viewPlayers(theGame);
                    break;
                case addPlayer:
                    addPlayer(theGame);
                    break;
                case removePlayer:
                    removePlayer(theGame);
                    break;
                case viewMatchesOfTheCurrentRound:
                    viewMatchesOfTheCurrentRound(theGame);
                    break;
                case setWinner:
                    setWinner(theGame);
                    break;
                case createRound:
                    createRound(theGame);
                    break;
                case viewWinnersOfTheRound:
                    viewWinnersOfTheRound(theGame);
                    break;
                case viewWinnersOfTheGame:
                    viewWinnersOfTheGame(theGame);
                    break;
                case exit:
                    loop = false;
                    break;
            }
        }
    }

    private static void printGameInfo(Game g) {
        System.out.println("+-------------------------------------------+");
        System.out.printf("%-44s|\n", "| Στοιχεία Παιχνιδιού");
        System.out.printf("%-44s|\n", ("| " + g.getName()));
        System.out.printf("%-44s|\n", ("| Τύπος: "
                + (g instanceof TimeGame ? "time game" : "round game")));
        System.out.printf("%-44s|\n", ("| Έναρξη:          " + new SimpleDateFormat("dd-MM-yyyy").format(g.getDate())));
        System.out.printf("%-44s|\n", ("| Μέγ. αρ. πακτών: " + g.getMaxPlayers()));
        if (g instanceof TimeGame) {
            System.out.printf("%-44s|\n", ("| Διάρκεια:        "
                    + new SimpleDateFormat("hh:mm:ss").format(((TimeGame) g).getTime())));
        } else {
            System.out.printf("%-44s|\n", ("| Αριθμός γύρων    " + ((RoundGame) g).getRounds()));
        }
        if (g.gameIsOver()) {
            System.out.printf("%-44s|\n", "| Νικητής του παιχνιδιού:");
            System.out.printf("%-44s|\n", "| " + g.getWinnerOfGame().getNickName());
        }
    }

    private static void viewPlayers(Game g) {
        ArrayList<Player> players = g.getAllPlayers();
        for (Player p : players) {
            System.out.println("Ψευδώνυμο     : " + p.getNickName());
            System.out.println("Ονοματεπώνυμο : " + p.getName());
            System.out.println("----------------------------------------------:");
        }
    }

    private static void addPlayer(Game g) {
        if (g.gameHasStarted()) {
            System.out.println("Το παιχνίδι έχει ήδη αρχίσει και δε μπορείτε να προσθέσετε παίκτες!\n");
            return;
        }
        if (g.getAllPlayers().size() == g.getMaxPlayers()) {
            System.out.println("Έχει συμπληρωθεί ο μέγιστος αριθμός παικτών!\n");
            return;
        }
        String nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του παίκτη ή ? για να δείτε όλους τους εγγεγραμμένους παίκτες: ", false);
        while (nickName.equals("?")) {
            viewAllPersons(true);
            nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους εγγεγραμμένους παίκτες: ", false);
        }
        Person p = tournament.getPerson(nickName);
        if (p == null) {
            System.out.println("Ο παίκτης δε βρέθηκε!\n");
        } else if (g.playerExists(nickName)) {
            System.out.println("Ο παίκτης συμμετέχει ήδη στο παιχνίδι!\n");
        } else {
            g.addPlayer((Player) p);
        }
    }

    private static void removePlayer(Game g) {
        if (g.gameHasStarted()) {
            System.out.println("Το παχνίδι έχει ήδη αρχίσει και δε μπορείτε να διαγράψετε παίκτες!\n");
            return;
        }
        String nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του παίκτη ή ? για να δείτε όλους τους εγγεγραμμένους παίκτες: ", false);
        while (nickName.equals("?")) {
            viewAllPersons(true);
            nickName = readString("Παρακαλώ δώστε το ψευδώνυμο του συμμετέχοντα ή ? για να δείτε όλους τους εγγεγραμμένους παίκτες: ", false);
        }
        Person p = tournament.getPerson(nickName);
        if (p == null) {
            System.out.println("Ο παίκτης δε βρέθηκε!\n");
        } else {
            g.deletePlayer((Player) p);
        }
    }

    private static void viewMatchesOfTheCurrentRound(Game g) {
        if (g.getRoundsArray().isEmpty()) {
            System.out.println("Το παιχνίδι δεν έχει αρχίσει ακόμη");
            return;
        }
        ArrayList<Round> rounds = g.getRoundsArray();
        Round r = rounds.get(rounds.size() - 1);
        int matchCounter = 1;
        int roundCounter = 1;
        for (Match m : r.getMatches()) {
            System.out.println(matchCounter++ + "ος αγώνας "
                    + roundCounter++ + "ου γύρου");
            System.out.println(m.getPlayer1().getNickName()
                    + " εναντίον " + m.getPlayer2().getNickName());
            if (m.matchIsOver()) {
                System.out.println("Νικητής: " + m.getWinner().getNickName());
            } else {
                System.out.println("Ο αγώνας είναι σε εξέλιξη");
            }
            System.out.println("---------------------------------------------\n");
        }
    }

    private static void setWinner(Game g) {
        viewMatchesOfTheCurrentRound(g);
        if (!g.gameIsOver()) {
            String nickName = readString("Δώστε το όνομα του Παίκτη που θέλετε να ορίσετε ως νικητή\n"
                    + "ή πατήστε enter για να επιστρέψετε στο μενού: ", true);
            if (nickName.isEmpty()) {
                return;
            }
            if (g.gameHasStarted()) {
                ArrayList<Round> rounds = g.getRoundsArray();
                Round round = rounds.get(rounds.size() - 1);
                boolean playerFound = false;
                for (Match m : round.getMatches()) {
                    if (m.getPlayer1().getNickName().equals(nickName)) {
                        m.setWinner(m.getPlayer1());
                        playerFound = true;
                        break;
                    } else if (m.getPlayer2().getNickName().equals(nickName)) {
                        m.setWinner(m.getPlayer2());
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound) {
                    System.out.println("Ο παίκτης που δώσατε δε συμμετέχει στον τρέχοντα γύρο του παιχνιδιού!");
                }
            }
        }
    }

    private static void createRound(Game g) {
        if (g.gameIsOver()) {
            System.out.println("Το παιχνίδι τελείωσε!");
        } else if (!g.roundIsOver()) {
            System.out.println("Ο τελευταίος γύρος του παιχνιδιού δεν έχει τελειώσει ακόμη!\n");
        } else if (g.getAllPlayers().isEmpty() || !isExpOfTwo(g.getAllPlayers().size())) {
            System.out.println("Δεν έχει συμπληρωθεί ο απαιτούμενος αριθμός πακτών!\n");
        } else {
            g.startNewRound();
        }
    }

    private static void viewWinnersOfTheRound(Game g) {
        if (g.getRoundsArray().isEmpty()) {
            System.out.println("Το παιχνίδι δεν έχει αρχίσει ακόμη");
            return;
        }
        ArrayList<Round> rounds = g.getRoundsArray();
        ArrayList<Player> players = g.getWinnersOfRound((rounds.size() - 1));
        int i = 1;
        for (Player player : players) {
            System.out.println("Ο νικητής του " + i + "ου ματς ήταν ο " + player.getNickName() + "\n");
            i++;
        }
    }

    private static void viewWinnersOfTheGame(Game g) {
        if (g.getRoundsArray().isEmpty()) {
            System.out.println("Το παιχνίδι δεν έχει αρχίσει ακόμη");
            return;
        }
        ArrayList<Round> rounds = g.getRoundsArray();
        ArrayList<Player> winners;
        for (int i = 1; i <= (rounds.size()); i++) {
            System.out.println("Στον " + i + "o γύρο");
            System.out.println("---------------------------------------------------------------");
            winners = g.getWinnersOfRound(i - 1);
            int j = 1;
            for (Player winner : winners) {
                System.out.println("    Ο νικητής του " + j + "ου ματς ήταν ο " + winner.getNickName() + "\n");
                j++;
            }
            System.out.println("---------------------------------------------------------------");
        }
    }

    private static void viewWinnerOfTheGame(Game g) {
        Player p = g.getWinnerOfGame();
        System.out.println("Ο νικητής του παιχνιδιού είναι ο "
                + p.getNickName() + " - " + p.getName());
    }
}
