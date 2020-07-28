package model;

import java.util.ArrayList;

// Represents  a team with name, and list of players playing for it.
public class Team {
    public static final int MAX_PLAYERS_IN_SQUAD = 25;

    public String name;
    public ArrayList<Player> listOfPlayers;

    public Team(String name) {
        this.name = name;
        listOfPlayers = new ArrayList<Player>(MAX_PLAYERS_IN_SQUAD);
    }

    public Team(String name, Player player, Player nextPlayer) {
        this.name = name;
        listOfPlayers = new ArrayList<Player>(MAX_PLAYERS_IN_SQUAD);
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
}
