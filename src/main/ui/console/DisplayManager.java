package ui.console;

import model.*;

//Represents class that deals with menu display.
public class DisplayManager {

    // EFFECTS: displays load teams or use default teams, options to user.
    public void displayLoadOrDefault() {
        System.out.println("\nThere are saved teams from last time.");
        System.out.println("\tEnter     y          -> to load saved teams.");
        System.out.println("\tEnter any other key  -> to use default teams.");
    }

    // EFFECTS: displays main menu of options to user.
    public void displayMainMenu() {
        System.out.println("\n MAIN MENU. Choose from:");
        System.out.println("\tm -> manage teams");
        System.out.println("\tv -> view teams");
        System.out.println("\te -> save and quit");
    }

    // EFFECTS: displays team menu after main menu to user
    public void displayTeamMenu() {
        System.out.println("\n TEAM MENU. Choose from:");
        System.out.println("\tst -> to select team to view players or go to players");
        System.out.println("\tat -> to add new team to the League");
        System.out.println("\te -> back to previous");
    }

    // EFFECTS: displays all teams to select from after team menu.
    public void displayTeamSelect(League league) {
        System.out.println("\n" + league.getName() + " has " + league.listOfTeams.size() + " teams:");
        for (Team team : league.listOfTeams) {
            System.out.println("\t" + (team.name.substring(0, 3)).toLowerCase() + " -> " + team.name);
        }
        System.out.println("\te -> back to previous");
    }

    // EFFECTS: displays player menu for selected team after teamSelect menu.
    public void displayPlayerMenu(Team team) {
        System.out.println("\nChoose options for team " + team.name);
        System.out.println("\tsp -> to select any player of team " + team.name + " for training");
        System.out.println("\tap -> to add new player to the team " + team.name);
        System.out.println("\te -> back to previous");
    }

    // EFFECTS: displays all players of selected team to select one.
    public void displayPlayerSelect(Team team) {
        System.out.println("\n" + team.name + " has " + team.listOfPlayers.size() + " players:");
        for (Player player : team.listOfPlayers) {
            System.out.println("\t" + (player.name.substring(0, 3)).toLowerCase() + " -> " + player.name);
        }
        System.out.println("\te -> back to previous");
    }

    // EFFECTS: displays player training menu for selected player.
    public void displayPlayerTrainingMenu(Player player) {
        System.out.println("\nFor player " + player.name + ":");
        System.out.println("\tEnter y -> to train");
        System.out.println("\tEnter e -> to go back to previous");
    }

    // EFFECTS: prints all the teams in the League.
    public void displayTeamNames(League league) {
        System.out.println(league.getName() + " has " + league.listOfTeams.size() + " teams:");
        for (Team team : league.listOfTeams) {
            System.out.println(team.name);
        }
    }

    //EFFECTS: prints invalid input statement.
    public void printInvalidInput() {
        System.out.println("INVALID input. Please only select from the given options.");
    }
}
