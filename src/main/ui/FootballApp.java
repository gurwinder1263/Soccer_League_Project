package ui;

import model.League;
import model.Player;
import model.Team;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class FootballApp extends JFrame {

    private static final String TEAMS_FILE = "./data/Teams.txt";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static League champLeague;
    private Team activeTeam;
    private Player activePlayer;
    private Scanner input;
    private MainPanel mp;
    private TeamsMenuPanel tmp;
    private TeamsPanel tp;


    // Constructs main window
    // EFFECTS: sets up window in which FootballApp will be displayed
    public FootballApp() {
        super("Fantasy Soccer App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp = new MainPanel(this);
        add(mp,BorderLayout.CENTER,0);
        addKeyListener(new KeyHandler());
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

    public void switchToTeamsMenuPanel() {
        tmp = new TeamsMenuPanel(this);
        add(tmp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    public void switchToTeamsPanel() {
        tp = new TeamsPanel(this);
        add(tp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    public void switchToPlayersPanel(String tmname) {
        PlayersPanel pp = new PlayersPanel(this,tmname);
        add(pp, BorderLayout.CENTER);
        pack();
        centreOnScreen();
        setVisible(true);
    }

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



    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
           // game.keyPressed(e.getKeyCode());
        }
    }
}

