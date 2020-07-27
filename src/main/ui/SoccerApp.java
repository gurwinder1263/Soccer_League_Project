package ui;

import model.*;
import java.util.Scanner;

// Fantasy Soccer Application
public class SoccerApp {
    private League champLeague;
    private Scanner input;

    // EFFECTS: runs the Soccer application
    public SoccerApp() {
        champLeague = createLeague();
        runSelections(0);
    }

    private League createLeague() {
        Player ronaldo = new Player("Cristiano Ronaldo", 35, 1.05, "LW",94);
        Player dybala = new Player("Paulo Dybala", 26, 0.60, "CAM",89);
        Player ramos = new Player("Sergio Ramos", 34, 0.10, "CB",88);
        Player benzema = new Player("Karim Benzema", 32, 0.73, "ST",86);
        Team juventus = new Team("Juventus FC", ronaldo, dybala);
        Team madrid = new Team("Real Madrid FC", ramos, benzema);
        League league = new League("UEFA Champions League",juventus,madrid);
        return league;
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSelections(int a) {
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
                processCmd(command,a);
                /////////////////////////////////////////////////////////////////////////////////////// fix
            }
        }

        System.out.println("\nCiao");
    }

    private void displayMenu(int a) {
        if (a == 0) {
            displayMainMenu();
        } else if (a == 1) {
            displayTeamMenu();
        } else {
            displayPlayerMenu();
        }
    }



    // EFFECTS: displays menu of options to user
    private void displayMainMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\tm -> manage teams");
        System.out.println("\tv -> view teams");
        System.out.println("\te -> end");
    }

    private void displayTeamMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\tst -> select team to go to players");
        System.out.println("\tat -> add new team to the League");
        System.out.println("\trt -> remove team from the League");
        System.out.println("\te -> end");
    }

    private void displayPlayerMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\tsp -> select player to manage attributes");
        System.out.println("\tap -> add new player to the team");
        System.out.println("\trp -> remove player from the team");
        System.out.println("\te -> end");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCmd(String command, int a) {
        if (a == 0) {
            processMainCommand(command);
        } else if (a == 1) {
            processTeamCmd(command);
        } else {
            processPlayerCmd(command);
        }
    }

    private void processMainCommand(String command) {
        if (command.equals("m")) {
            runTeamSelections();
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

    private void runTeamSelections() {
        runSelections(1);
    }

    private void processTeamCmd(String command) {
        if (command.equals("st")) {
            runPlayerSelections();
        } else if (command.equals("at")) {
            newTeam();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void newTeam() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new team?");
        String command = input.next();

        Team newTeam = new Team(command);
        if (!champLeague.isTeamPresent(newTeam)) {
            champLeague.addNewTeam(newTeam);
        } else {
            System.out.println("This team is already playing in the league.");
        }
    }

    private void runPlayerSelections() {
        runSelections(2);
    }

    private void processPlayerCmd(String command) {
        if (command.equals("c")) {
            // select player();
        } else if (command.equals("ap")) {
            newplayer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void newplayer() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new player?");
        String inpName = input.next();
        System.out.println("Enter the age of" + inpName + "?");
        int inpAge = Integer.parseInt(input.next());
        System.out.println("Enter the position of the player such as ST, CM, CB, LW, CDM?");
        String inpPosition = input.next();
        System.out.println("Enter the average soccer rating of new player?");
        int inpAttribute = Integer.parseInt(input.next());
        System.out.println("Enter the goal per game ration in format such as 0.70?");
        double inpGoalRatio = Double.parseDouble(input.next());

        Player newplayer = new Player(inpName,inpAge,inpGoalRatio,inpPosition,inpAttribute);
        if (!.isTeamPresent(newplayer)) {
            champLeague.addNewTeam(newplayer);
        } else {
            System.out.println("This player is already playing in this team.");
        }
    }
}








