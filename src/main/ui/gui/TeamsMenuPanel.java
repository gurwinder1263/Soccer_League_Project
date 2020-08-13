package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a teams menu panel displayed to user.
public class TeamsMenuPanel extends JPanel implements ActionListener {
    public FootballApp frame;
    public JButton manageTeams;
    public JButton saveQuit;
    public JLabel topText;

    // EFFECTS: Constructs a teams menu panel with size and background colour of panel,
    //           updates this with the user selection
    public TeamsMenuPanel(FootballApp frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setBackground(Color.gray);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(null);
        topText = new JLabel("Click on buttons to");
        topText.setFont(new Font("Arial", 20, 20));
        topText.setBounds(226,0,500,70);
        manageTeams = new JButton("Manage Teams");
        manageTeams.addActionListener(this);
        manageTeams.setActionCommand("manage");
        manageTeams.setBounds(230,90,150,50);
        saveQuit = new JButton("Save and Quit");
        saveQuit.addActionListener(this);
        saveQuit.setActionCommand("save");
        saveQuit.setBounds(230,230,150,50);
        this.add(topText);
        this.add(manageTeams);
        this.add(saveQuit);
    }

    // MODIFIES: this.
    // EFFECTS: manage teams OR save and quit when corresponding
    //         ActionListener is involved.
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        removeAll();
        if (action.equals("manage")) {
            frame.switchToTeamsPanel();
        } else if (action.equals("save")) {
            FootballApp.data.saveTeams(FootballApp.champLeague);
            frame.switchToExitPanel();
        }

    }

}
