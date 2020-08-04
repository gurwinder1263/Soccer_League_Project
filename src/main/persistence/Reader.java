package persistence;

import model.Player;
import model.Team;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read team data from a file.
public class Reader {
    public static final String DELIMITER_1 = ";";
    public static final String DELIMITER_2 = ",";


    // EFFECTS: returns a list of Teams parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static ArrayList<Team> readTeams(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of Teams parsed from list of strings
    // where each string contains data for one team
    private static ArrayList<Team> parseContent(List<String> fileContent) {
        ArrayList<Team> teams = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line,DELIMITER_1);
            teams.add(parseTeam(lineComponents));
        }
        return teams;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line, String delimiter) {
        String[] splits = line.split(delimiter);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 1 where element 0 represents the
    // name of the team to be constructed, rest of the elements
    // collectively represents list of players such element 1 represents
    // first player, element 2 represents second player and so on.
    // EFFECTS: returns team constructed from components
    private static Team parseTeam(ArrayList<String> components) {
        String name = components.get(0);
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i < components.size(); i++) {
            ArrayList<String> playerComponents = splitString(components.get(i),DELIMITER_2);
            players.add(parsePlayer(playerComponents));
        }
        return new Team(name,players);
    }

    // REQUIRES: playerStats has size 4 corresponding to player characteristics
    // where element 0 represents the player name, element 1 represents
    // player's age, element 2 represents position and element 3 represents
    // player's average rating.
    // EFFECTS: returns player constructed from player's components
    private static Player parsePlayer(ArrayList<String> playerStats) {
        String name = playerStats.get(0);
        int age = Integer.parseInt(playerStats.get(1));
        String position = playerStats.get(2);
        int avgRating = Integer.parseInt(playerStats.get(3));
        return new Player(name,age,position,avgRating);
    }



}
