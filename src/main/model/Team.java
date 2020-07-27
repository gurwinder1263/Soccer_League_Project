package model;


import java.util.ArrayList;

public class Team implements Common {
    public static final int MAX_PLAYERS_IN_SQUAD = 25;

    public String name;
    public ArrayList<Player> listOfPlayers;

    public Team(String name) {
        this.name = name;
        listOfPlayers = new ArrayList<Player>(MAX_PLAYERS_IN_SQUAD);
    }

    public Team(String name,Player player,Player nextPlayer) {
        this.name = name;
        listOfPlayers = new ArrayList<Player>(MAX_PLAYERS_IN_SQUAD);
        listOfPlayers.add(0,player);
        listOfPlayers.add(1,nextPlayer);
    }

    // EFFECTS: returns the player selected by the user from the team.
    public Player selectPlayer() {
        Player player
        return player;
    }

    // REQUIRES: player is already not present in the team.
    // MODIFIES: this
    // EFFECTS: adds the new player to the team.
    public void signNewPlayer(Player newPlayer) {
        listOfPlayers.add(newPlayer);

    }

    // REQUIRES: player is already present in the team.
    // MODIFIES: this
    // EFFECTS: sells the existing player out of the team.
    public void sellPlayer(Player existingPlayer) {
        listOfPlayers.remove(existingPlayer);

    }

    // EFFECTS: if the team contains the player,
    //                     - return true.
    //                     - Otherwise, return false.
    public boolean isPlayerPresent(Player player) {
        return listOfPlayers.contains(player);
    }

    // EFFECTS: displays all the existing players in some format.
    public void displayAll() {
        // stub
    }

}
