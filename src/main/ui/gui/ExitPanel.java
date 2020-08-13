package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel displayed when user quits.
public class ExitPanel extends JPanel implements ActionListener {
    public FootballApp frame;
    public JTextArea endMessage;


    // EFFECTS: Constructs a exit panel with size and background colour of panel,
    //           updates this with the user selection
    public ExitPanel(FootballApp frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setBackground(Color.white);
        //this.game = g;
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(null);
        endMessage = new JTextArea("All of the teams have been saved properly");
        endMessage.setFont(new Font("Arial", 20, 20));
        endMessage.setBounds(10,10,600,50);
        JButton quit = new JButton("Quit");
        quit.setBounds(250,150,100,50);
        quit.addActionListener(this);
        add(endMessage);
        add(quit);
    }

    // EFFECTS: exits the app when quit button is clicked.
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
}
