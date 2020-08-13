package ui.console;

import exceptions.NotTrainableException;
import model.League;
import model.Player;
import model.Team;

import java.util.Scanner;

// Fantasy Soccer Application
// created with the use of class TellerApp in Project <AccountNotRobust>.
public class SoccerApp {
    private static final String TEAMS_FILE = "./data/Teams.txt";

    private static League champLeague;
    private Team activeTeam;
    private Player activePlayer;
    private Scanner input;
    private DisplayManager display;
    private DataManager data;

    // EFFECTS: runs the Soccer application
    public SoccerApp() {
        System.out.println("\nWelcome to My Fantasy Soccer League");
        display = new DisplayManager();
        data = new DataManager();
        promptUserToLoadOrDefault();
        controlFlow(0);
        data.saveTeams(champLeague);
        System.out.println("\nCiao");
    }

    // MODIFIES: this
    // EFFECTS: asks user to load saved teams or start with default ones.
    private void promptUserToLoadOrDefault() {
        input = new Scanner(System.in);
        display.displayLoadOrDefault();
        String userInput = input.nextLine();
        userInput = userInput.toLowerCase();

        if (userInput.equals("y")) {
            champLeague = data.loadTeams();
        } else {
            champLeague = data.createLeague();
        }
    }


    // REQUIRES: parameter a to one of {0,1,2,3,4,5}.
    // MODIFIES: this
    // EFFECTS: processes user input based on parameter a.
    // 0 is passed in it when using this method for first time; 1, 2, 3, 4 and 5 are passed for subsequent uses.
    private void controlFlow(int a) {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu(a);
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCmd(command, a);
            }
        }
    }

    // REQUIRES: parameter a to one of {0,1,2,3,4,5}.
    // EFFECTS: displays the type of menu depending on the value of a parameter.
    private void displayMenu(int a) {
        if (a == 0) {
            display.displayMainMenu();
        } else if (a == 1) {
            display.displayTeamMenu();
        } else if (a == 2) {
            display.displayTeamSelect(champLeague);
        } else if (a == 3) {
            display.displayPlayerMenu(activeTeam);
        } else if (a == 4) {
            display.displayPlayerSelect(activeTeam);
        } else {
            display.displayPlayerTrainingMenu(activePlayer);
        }
    }


    // REQUIRES: parameter a to one of {0,1,2,3,4,5}.
    // EFFECTS: processes user command based on parameter a.
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

    // EFFECTS: processes user command for Main Menu.
    private void processMainCommand(String command) {
        if (command.equals("m")) {
            runTeamMenu();
        } else if (command.equals("v")) {
            display.displayTeamNames(champLeague);
        } else {
            display.printInvalidInput();
        }
    }

    // EFFECTS: runs Team Menu after user selects m to manage teams.
    private void runTeamMenu() {
        controlFlow(1);
    }

    // EFFECTS: processes user command for Team Menu.
    private void processTeamCmd(String command) {
        if (command.equals("st")) {
            runTeamSelect();
        } else if (command.equals("at")) {
            newTeam();
        } else {
            display.printInvalidInput();
        }
    }


    // EFFECTS: runs Team Select Menu after user selects st to select one of the teams.
    private void runTeamSelect() {
        controlFlow(2);
    }

    // MODIFIES: this
    // EFFECTS: After user selects at to add new team to the League, adds new team if team is already not there.
    //                     - Otherwise, sends message to the user to inform about team's presence in League.
    private void newTeam() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new team? Please enter at least 3 characters.");
        String command = input.nextLine();

        Team newTeam = new Team(command);
        if (!champLeague.isTeamPresent(newTeam)) {
            champLeague.addNewTeam(newTeam);
        } else {
            System.out.println("This team is already playing in the league.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for Team Select Menu to make team selected by user to be active team.
    private void processTeamSelectCmd(String command) {
        int a = 0;
        for (Team team : champLeague.listOfTeams) {
            if (command.equals((team.name.substring(0, 3)).toLowerCase())) {
                activeTeam = team;
                a = 1;
                break;
            }
        }
        if (a == 1) {
            runPlayerMenu();
        } else {
            display.printInvalidInput();
        }
    }

    // EFFECTS: runs Player Menu for selected team.
    public void runPlayerMenu() {
        controlFlow(3);
    }

    // EFFECTS: processes user command for Player Menu.
    private void processPlayerCmd(String command) {
        if (command.equals("sp")) {
            runPlayerSelect();
        } else if (command.equals("ap")) {
            newPlayer();
        } else {
            display.printInvalidInput();
        }
    }

    // MODIFIES: this
    // EFFECTS: After user selects ap to add new player, adds new player if player is already not there.
    //                     - Otherwise, sends message to the user to inform about player's presence in the team.
    private void newPlayer() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of new player?");
        String inpName = input.nextLine();
        System.out.println("Enter the age of " + inpName + "?");
        int inpAge = Integer.parseInt(input.nextLine());
        System.out.println("Enter the position of the player such as ST, CM, CB, LW, CDM?");
        String inpPosition = input.nextLine();
        System.out.println("Enter the average soccer rating of " + inpName + "?");
        int inpAttribute = Integer.parseInt(input.nextLine());

        Player newPlayer = new Player(inpName, inpAge, inpPosition, inpAttribute);
        if (!activeTeam.isPlayerPresent(newPlayer)) {
            activeTeam.signNewPlayer(newPlayer);
        } else {
            System.out.println("This player is already playing in this team.");
        }
    }

    // EFFECTS: runs Player Select Menu after user selects sp to select one of the players.
    public void runPlayerSelect() {
        controlFlow(4);
    }

    // MODIFIES: this
    // EFFECTS: processes user command for Player Select Menu to make player selected by user to be active player.
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
            display.printInvalidInput();
        }
    }

    // EFFECTS: runs Player Training Menu for selected player.
    public void runPlayerTrainingMenu() {
        controlFlow(5);
    }

    // EFFECTS: processes user command for Player Training Menu
    private void processPlayerTrainingCmd(String command) {
        if (command.equals("y")) {
            playerTraining();
        } else {
            display.printInvalidInput();
        }
    }

    // MODIFIES: this
    // EFFECTS: Trains player. If active player  is trainable
    //                    - train him and prints new rating.
    //                    - Otherwise, prints message to inform user that player has reached his potential.
    public void playerTraining() {
        try {
            activePlayer.trainPlayer();
            System.out.println(activePlayer.name + "'s new soccer rating is " + activePlayer.getSoccerRating() + ".");

        } catch (NotTrainableException e) {
            System.out.println("The player " + activePlayer.name + " has reached his potential.");
            System.out.println("He can not be trained anymore.");
        }
    }
}