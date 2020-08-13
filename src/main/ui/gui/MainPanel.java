package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel displayed when user starts the app.
public class MainPanel extends JPanel implements ActionListener {
    public FootballApp frame;
    public JButton loadTeams;
    public JButton defaultTeams;
    public JLabel welcome;


    // EFFECTS: Constructs a main panel with size and background colour of panel,
    //           updates this with the user selection
    public MainPanel(FootballApp frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setBackground(Color.white);
        //this.game = g;
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(null);
        welcome = new JLabel("Welcome to UEFA Champions League");
        welcome.setFont(new Font("Arial", 20, 20));
        welcome.setBounds(150,0,500,70);
        loadTeams = new JButton("Load Saved Teams");
        loadTeams.addActionListener(this);
        loadTeams.setActionCommand("load");
        loadTeams.setBounds(230,90,150,50);
        defaultTeams = new JButton("Use Default Teams");
        defaultTeams.addActionListener(this);
        defaultTeams.setActionCommand("default");
        defaultTeams.setBounds(230,230,150,50);
        this.add(welcome);
        this.add(loadTeams);
        this.add(defaultTeams);

    }

    // MODIFIES: this.
    // EFFECTS: loads saved teams or uses default teams when corresponding
    //         ActionListener is involved.
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("load")) {
            FootballApp.champLeague = FootballApp.data.loadTeams();
        } else if (action.equals("default")) {
            FootballApp.champLeague = FootballApp.data.createLeague();
        }
        removeAll();
        frame.switchToTeamsMenuPanel();
    }
}
