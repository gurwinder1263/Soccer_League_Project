package ui.gui;

import model.League;
import ui.console.DataManager;

import javax.swing.*;
import java.awt.*;

// Fantasy Soccer Application
// created with the use of class TellerApp in Project <AccountNotRobust>.
public class FootballApp extends JFrame {

    private static final String TEAMS_FILE = "./data/Teams.txt";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static League champLeague;
    public static DataManager data = new DataManager();


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
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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
}

