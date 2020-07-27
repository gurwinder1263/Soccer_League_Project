package ui;

import model.*;

import java.util.Scanner;

// Fantasy Soccer Application
public class SoccerApp {
    private static League champLeague;
    private Team activeTeam;
    private Player activePlayer;
    private Scanner input;

    // EFFECTS: runs the Soccer application
    public SoccerApp() {
        champLeague = createLeague();
        System.out.println("\nWelcome to My Fantasy Soccer League");
        controlFlow(0);
        System.out.println("\nCiao");

    }

    private League createLeague() {
        Player ronaldo = new Player("Cristiano Ronaldo", 35, "LW", 94);
        Player dybala = new Player("Paulo Dybala", 26, "CAM", 89);
        Player ramos = new Player("Sergio Ramos", 34, "CB", 88);
        Player benzema = new Player("Karim Benzema", 32, "ST", 86);
        Team juventus = new Team("Juventus FC", ronaldo, dybala);
        Team madrid = new Team("Real Madrid FC", ramos, benzema);
        League league = new League("UEFA Champions League", juventus, madrid);
        return league;
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void controlFlow(int a) {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu(a);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCmd(command, a);
            }
        }

    }

    private void displayMenu(int a) {
        if (a == 0) {
            displayMainMenu();
        } else if (a == 1) {
            displayTeamMenu();
        } else if (a == 2) {
            displayTeamSelect();
        } else if (a == 3) {
            displayPlayerMenu();
        } else if (a == 4) {
            displayPlayerSelect();
        } else {
            displayPlayerTrainingMenu();
        }
    }


    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\n MAIN MENU. Choose from:");
        System.out.println("\tm -> manage teams");
        System.out.println("\tv -> view teams");
        System.out.println("\te -> quit");
    }

    // EFFECTS: displays team menu after main menu to user
    private void displayTeamMenu() {
        System.out.println("\n TEAM MENU. Choose from:");
        System.out.println("\tst -> to select team to view players or go to players");
        System.out.println("\tat -> to add new team to the League");
        System.out.println("\te -> back");
    }

    // EFFECTS: displays all teams to select one after team menu to user
    private void displayTeamSelect() {
        System.out.println("\n" + champLeague.name + " has " + champLeague.listOfTeams.size() + " teams:");
        for (Team team : champLeague.listOfTeams) {
            System.out.println("\t" + (team.name.substring(0,3)).toLowerCase() + " -> " + team.name);
        }
        System.out.println("\te -> back");
    }

    private void displayPlayerMenu() {
        System.out.println("\nChoose options for team " + activeTeam.name);
        System.out.println("\tsp -> to select any player of team " + activeTeam.name + " for training");
        System.out.println("\tap -> to add new player to the team " + activeTeam.name);
        System.out.println("\te -> back");
    }

    private void displayPlayerSelect() {
        System.out.println("\n" + activeTeam.name + " has " + activeTeam.listOfPlayers.size() + " players:");
        for (Player player : activeTeam.listOfPlayers) {
            System.out.println("\t" + (player.name.substring(0, 3)).toLowerCase() + " -> " + player.name);
        }
        System.out.println("\te -> back");
        //System.out.println("\tmadrid -> Real Marid FC");
    }


    private void displayPlayerTrainingMenu() {
        System.out.println("\nFor player " + activePlayer.name + ":");
        System.out.println("\tEnter y to train");
        System.out.println("\tEnter e to go back to previous");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCmd(String command, int a) {
        if (a == 0) {
            processMainCommand(command);
        } else if (a == 1) {
            processTeamCmd(command);
        } else if (a == 2) {
            processTeamSelectCmd(command);
        } else if (a == 3) {
            processPlayerCmd(command);
        } else if (a == 4) {
            processPlayerSelectCmd(command);
        } else {
            processPlayerTrainingCmd(command);
        }
    }

    private void processMainCommand(String command) {
        if (command.equals("m")) {
            runTeamMenu();
        } else if (command.equals("v")) {
            displayTeamNames();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayTeamNames() {
        System.out.println(champLeague.name + " has " + champLeague.listOfTeams.size() + " teams:");
        for (Team team : champLeague.listOfTeams) {
            System.out.println(team.name);
        }
    }

    private void runTeamMenu() {
        controlFlow(1);
    }

    private void processTeamCmd(String command) {
        if (command.equals("st")) {
            runTeamSelect();
        } else if (command.equals("at")) {
            newTeam();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void runTeamSelect() {
        controlFlow(2);
    }


    private void newTeam() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new team? Please enter at least 3 characters.");
        String command = input.next();

        Team newTeam = new Team(command);
        if (!champLeague.isTeamPresent(newTeam)) {
            champLeague.addNewTeam(newTeam);
        } else {
            System.out.println("This team is already playing in the league.");
        }
    }

    private void processTeamSelectCmd(String command) {
        int a = 0;
        for (Team team : champLeague.listOfTeams) {
            if (command.equals((team.name.substring(0,3)).toLowerCase())) {
                activeTeam = team;
                a = 1;
                break;
            }
        }
        if (a == 1) {
            runPlayerMenu();
        } else {
            System.out.println("INVALID input. Please only select from the given options.");
            controlFlow(2);
        }
    }


    public void runPlayerMenu() {
        controlFlow(3);
    }


    private void processPlayerCmd(String command) {
        if (command.equals("sp")) {
            runPlayerSelect();
        } else if (command.equals("ap")) {
            newPlayer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void newPlayer() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new player?");
        String inpName = input.next();
        System.out.println("Enter the age of " + inpName + "?");
        int inpAge = Integer.parseInt(input.next());
        System.out.println("Enter the position of the player such as ST, CM, CB, LW, CDM?");
        String inpPosition = input.next();
        System.out.println("Enter the average soccer rating of " + inpName + "?");
        int inpAttribute = Integer.parseInt(input.next());

        Player newplayer = new Player(inpName, inpAge, inpPosition, inpAttribute);
        if (!activeTeam.isPlayerPresent(newplayer)) {
            activeTeam.signNewPlayer(newplayer);
        } else {
            System.out.println("This player is already playing in this team.");
        }
    }

    public void runPlayerSelect() {
        controlFlow(4);
    }

    public void processPlayerSelectCmd(String command) {
        int a = 0;
        for (Player player : activeTeam.listOfPlayers) {
            if (command.equals((player.name.substring(0, 3)).toLowerCase())) {
                activePlayer = player;
                a = 1;
                break;
            }
        }
        if (a == 1) {
            runPlayerTrainingMenu();

        } else {
            System.out.println("INVALID input. Please only select from the given options.");
            controlFlow(4);
        }
    }

    public void runPlayerTrainingMenu() {
        controlFlow(5);
    }


    public void processPlayerTrainingCmd(String command) {
        if (activePlayer.isTrainable()) {
            activePlayer.trainPlayer();
            System.out.println("The player " + activePlayer.name + " has new soccer rating of " +
                    activePlayer.getSoccerRating() + ".");
        } else {
            System.out.println("The player " + activePlayer.name + " has reached his potential. " +
                    "He can not be trained anymore.");
        }
    }

}








