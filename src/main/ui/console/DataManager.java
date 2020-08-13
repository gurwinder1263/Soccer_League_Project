package ui.console;

import model.League;
import model.Player;
import model.Team;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

//Represents a class that deals with saving and loading data to/from file.
public class DataManager {
    private static final String TEAMS_FILE = "./data/Teams.txt";

    //EFFECTS: returns a object of League class from real world data.
    public League createLeague() {
        Player ronaldo = new Player("Cristiano Ronaldo", 35, "LW", 94);
        Player dybala = new Player("Paulo Dybala", 26, "CAM", 89);
        Player ramos = new Player("Sergio Ramos", 34, "CB", 88);
        Player benzema = new Player("Karim Benzema", 32, "ST", 86);
        Team juventus = new Team("Juventus FC", ronaldo, dybala);
        Team madrid = new Team("Real Madrid FC", ramos, benzema);
        return new League("UEFA Champions League", juventus, madrid);
    }

    // EFFECTS: saves state of all teams to TEAMS_FILE
    public void saveTeams(League league) {
        try {
            Writer writer = new Writer(new File(TEAMS_FILE));
            for (Team team : league.listOfTeams) {
                writer.write(team);
            }
            writer.close();
            System.out.println("All of the teams have been saved properly.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save teams to destination file.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads teams data from TEAMS_FILE, if that file exists;
    // otherwise initializes the league with default teams.
    public League loadTeams() {
        try {
            ArrayList<Team> teams = Reader.readTeams(new File(TEAMS_FILE));
            return new League("Uefa Champions League",teams);
        } catch (IOException e) {
            return createLeague();
        }
    }
}
