package model;

import persistence.*;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents  a team with name, and list of players playing for it.
public class Team extends SoccerUnit implements Saveable {
    public static final int MAX_PLAYERS_IN_SQUAD = 25;

    public String name;
    public ArrayList<Player> listOfPlayers;

    // EFFECTS: constructs newly created Team with a name and empty list of players.
    public Team(String name) {
        super(name);
        this.name = name;
        listOfPlayers = new ArrayList<>(MAX_PLAYERS_IN_SQUAD);
    }

    // EFFECTS: constructs newly created Team with a name and list of players.
    public Team(String name, ArrayList<Player> players) {
        super(name);
        this.name = name;
        listOfPlayers = players;
    }

    // EFFECTS: constructs newly created Team with a name and two players.
    public Team(String name, Player player, Player nextPlayer) {
        super(name);
        this.name = name;
        listOfPlayers = new ArrayList<>(MAX_PLAYERS_IN_SQUAD);
        listOfPlayers.add(0, player);
        listOfPlayers.add(1, nextPlayer);
    }

    // EFFECTS: returns the player's name.
    public String getName() {
        return name;
    }

    // REQUIRES: player is already not present in the team.
    // MODIFIES: this
    // EFFECTS: adds the new player to the team.
    public void signNewPlayer(Player newPlayer) {
        listOfPlayers.add(newPlayer);

    }

    // EFFECTS: if the team contains the player,
    //                     - return true.
    //                     - Otherwise, return false.
    public boolean isPlayerPresent(Player playerChecked) {
        for (Player player : listOfPlayers) {
            if ((player.getName()).equalsIgnoreCase(playerChecked.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: saves team name and list of players in it using delimiters.
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER_1);
        for (Player player : listOfPlayers) {
            printWriter.print(player.getName());
            printWriter.print(Reader.DELIMITER_2);
            printWriter.print(player.age);
            printWriter.print(Reader.DELIMITER_2);
            printWriter.print(player.position);
            printWriter.print(Reader.DELIMITER_2);
            printWriter.print(player.getSoccerRating());
            printWriter.print(Reader.DELIMITER_1);
        }
        printWriter.println();
    }

    // EFFECTS: if the team contains the player,
    //                     - return true.
    //                     - Otherwise, return false.
    @Override
    public boolean isUnitPresent(SoccerUnit sc) {
        for (Player player : listOfPlayers) {
            if ((player.getName()).equalsIgnoreCase(sc.name)) {
                return true;
            }
        }
        return false;
    }
}
