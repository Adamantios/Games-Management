package gamesmanagement;

import java.util.Scanner;

class menus {

    private static final Scanner scanIn = new Scanner(System.in);

    private int readChoice() {
        int i;
        String s = scanIn.nextLine().trim();
        try {
            i = Integer.parseInt(s);
        } catch (Exception ex) {
            i = -1;
        }
        return i;
    }

    MenuChoice StartTournament() {
        while (true) {
            System.out.println("+--------------------------+");
            System.out.println("|  1. Δημιουργία Τουρνουά  |");
            System.out.println("|  0. Έξοδος               |");
            System.out.println("+--------------------------+");
            System.out.println();
            System.out.print("Παρακαλώ επιλέξτε: ");

            int choice = readChoice();

            if (choice == 0) {
                return MenuChoice.exit;
            }
            if (choice == 1) {
                return MenuChoice.createTournament;
            }
        }
    }

    MenuChoice manageGame() {
        MenuChoice[] choices = {
                MenuChoice.exit,
                MenuChoice.viewPlayers,
                MenuChoice.addPlayer,
                MenuChoice.removePlayer,
                MenuChoice.viewMatchesOfTheCurrentRound,
                MenuChoice.setWinner,
                MenuChoice.createRound,
                MenuChoice.viewWinnersOfTheRound,
                MenuChoice.viewWinnersOfTheGame
        };
        int choice = -1;
        while (choice < 0 || choice > 11) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|  1. Προβολή παικτών                       |");
            System.out.println("|  2. Προσθήκη παίκτη                       |");
            System.out.println("|  3. Αφαίρεση παίκτη                       |");
            System.out.println("+-------------------------------------------+");
            System.out.println("|  4. Προβολή όλων των μάτς τρέχοντος γύρου |");
            System.out.println("|  5. Εισαγωγή νικητή μάτς                  |");
            System.out.println("|  6. Δημιουργία νέου γύρου                 |");
            System.out.println("+-------------------------------------------+");
            System.out.println("|  7. Προβολή αποτελεσμάτων γύρου           |");
            System.out.println("|  8. Προβολή αποτελεσμάτων παιχνιδιού      |");
            System.out.println("+-------------------------------------------+");
            System.out.println("|  0. Επιστροφή στο κεντρικό μενού          |");
            System.out.println("+-------------------------------------------+");
            System.out.println();
            System.out.print("Παρακαλώ επιλέξτε: ");
            choice = readChoice();
        }
        return choices[choice];
    }

    MenuChoice manageTournament() {

        MenuChoice[] choices = {
                MenuChoice.exit,
                MenuChoice.editTournament,
                MenuChoice.viewGames,
                MenuChoice.addGame,
                MenuChoice.editGame,
                MenuChoice.deleteGame,
                MenuChoice.manageGame,
                MenuChoice.viewPersons,
                MenuChoice.addPerson,
                MenuChoice.editPerson,
                MenuChoice.deletePerson,
                MenuChoice.viewAllWinners
        };

        int choice = -1;
        while (choice < 0 || choice > 11) {
            System.out.println("+----------------------------------------+");
            System.out.println("|  1. Διόρθωση στοιχείων Τουρνουά        |");
            System.out.println("+----------------------------------------+");
            System.out.println("|  2. Προβολή Παιχνιδιών                 |");
            System.out.println("|  3. Δημιουργία Παιχνιδιού              |");
            System.out.println("|  4. Διόρθωση στοιχείων Παιχνιδιού      |");
            System.out.println("|  5. Διαγραφή Παιχνιδιού                |");
            System.out.println("+----------------------------------------+");
            System.out.println("|  6. Διαχείριση Παιχνιδιού              |");
            System.out.println("+----------------------------------------+");
            System.out.println("|  7. Προβολή Συμμετεχόντων              |");
            System.out.println("|  8. Καταχώρηση Συμμετέχοντα            |");
            System.out.println("|  9. Διόρθωση στοιχείων Συμμετέχοντα    |");
            System.out.println("| 10. Διαγραφή Συμμετέχοντα              |");
            System.out.println("+----------------------------------------+");
            System.out.println("| 11. Προβολή αποτελεσμάτων              |");
            System.out.println("+----------------------------------------+");
            System.out.println("|  0. Έξοδος                             |");
            System.out.println("+----------------------------------------+");
            System.out.println();
            System.out.print("Παρακαλώ επιλέξτε: ");
            choice = readChoice();

        }
        return choices[choice];
    }
}
