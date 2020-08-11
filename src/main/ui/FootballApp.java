package ui;

import model.League;
import model.Player;
import model.Team;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// Fantasy Soccer Application
// created with the use of class TellerApp in Project <AccountNotRobust>.
public class FootballApp extends JFrame {

    private static final String TEAMS_FILE = "./data/Teams.txt";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static League champLeague;
    private Team activeTeam;
    private Player activePlayer;


    // Constructs main window
    // EFFECTS: sets up window in which FootballApp will be displayed
    public FootballApp() {
        super("Fantasy Soccer App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel mp = new MainPanel(this);
        add(mp,BorderLayout.CENTER,0);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    @Override
    public int getWidth() {
        return this.WIDTH;
    }

    @Override
    public int getHeight() {
        return this.HEIGHT;
    }

    //MODIFIES:this
    //EFFECTS: switches the panel view to Team Menu Panel.
    public void switchToTeamsMenuPanel() {
        TeamsMenuPanel tmp = new TeamsMenuPanel(this);
        add(tmp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS: switches the panel view to Teams Panel.
    public void switchToTeamsPanel() {
        TeamsPanel tp = new TeamsPanel(this);
        add(tp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS: switches the panel view to Players Panel.
    public void switchToPlayersPanel(String tmName) {
        PlayersPanel pp = new PlayersPanel(this,tmName);
        add(pp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS: switches the panel view to Exit Panel.
    public void switchToExitPanel() {
        ExitPanel ep = new ExitPanel(this);
        add(ep, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }


    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS: location of frame is set to appear it at center on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    //MODIFIES: this
    //EFFECTS: creates a object of League class from real world data.
    public void createLeague() {
        Player ronaldo = new Player("Cristiano Ronaldo", 35, "LW", 94);
        Player dybala = new Player("Paulo Dybala", 26, "CAM", 89);
        Player ramos = new Player("Sergio Ramos", 34, "CB", 88);
        Player benzema = new Player("Karim Benzema", 32, "ST", 86);
        Team juventus = new Team("Juventus FC", ronaldo, dybala);
        Team madrid = new Team("Real Madrid FC", ramos, benzema);
        champLeague = new League("UEFA Champions League", juventus, madrid);
    }

    // EFFECTS: saves state of all teams to TEAMS_FILE
    public void saveTeams() {
        try {
            Writer writer = new Writer(new File(TEAMS_FILE));
            for (Team team : champLeague.listOfTeams) {
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
    public void loadTeams() {
        try {
            ArrayList<Team> teams = Reader.readTeams(new File(TEAMS_FILE));
            champLeague = new League("Uefa Champions League",teams);
        } catch (IOException e) {
            createLeague();
        }
    }
}

