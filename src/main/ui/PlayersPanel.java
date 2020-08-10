package ui;

import model.*;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersPanel extends JPanel implements ActionListener, ListSelectionListener {
    public FootballApp frame;
    private Team activeTeam;
    private Player activePlayer;
    private JButton newPlayer;
    private JButton trainPlayer;
    private JButton back;
    private JLabel topText;
    private JList<String> playerList;
    private DefaultListModel<String> modelPlayer;
    private JTextField newPlayerText;
    private JTextField playerRatingField;
    private JTextField trainingMessage;

    // EFFECTS: Constructs a main panel with size and background colour of panel,
    //           updates this with the user selection
    public PlayersPanel(FootballApp frame,String teamName) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //this.setLayout(new GridLayout(7,1,5,5));
        this.setLayout(null);
        if (verifyTeamSelected(teamName)) {
            topText = new JLabel("Players playing for " + teamName + " are:");
            topText.setFont(new Font("Arial", 20, 20));
            topText.setBounds(10,10,450,100);
            this.add(topText);
            modelPlayer = new DefaultListModel<>();
            for (Player pl: activeTeam.listOfPlayers) {
                modelPlayer.addElement(pl.getName());
            }
            playerList = new JList<>(modelPlayer);
            JScrollPane scrollPane = new JScrollPane(playerList);
            playerList.setVisibleRowCount(5);
            playerList.setFixedCellWidth(100);
            playerList.setFixedCellHeight(25);
            playerList.addListSelectionListener(this);
            playerList.setBounds(10,110,100,150);
            add(scrollPane);
            add(playerList);
            setVisible(true);
            addComponents();

        }

    }

    public void addComponents() {
        newPlayerText = new JTextField(10);
        playerRatingField = new JTextField(10);
        newPlayer = new JButton("Add New Player");
        newPlayer.addActionListener(this);
        newPlayer.setActionCommand("add");
        trainPlayer = new JButton("Train Player");
        trainPlayer.addActionListener(this);
        trainPlayer.setActionCommand("train");
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setActionCommand("back");
        newPlayerText.setBounds(150,110,200,50);
        newPlayer.setBounds(150,170,200,50);
        playerRatingField.setBounds(10,270,200,50);
        trainPlayer.setBounds(10,330,200,50);
        back.setBounds(350,350,200,50);
        this.add(newPlayerText);
        this.add(newPlayer);
        this.add(playerRatingField);
        this.add(trainPlayer);
        this.add(back);
    }

    // MODIFIES: this
    // EFFECTS:  adds new team if team is already not there.
    //          - Otherwise, sends message to the user to inform about team's presence in League.
    private void newPlayer(String newPlyName) {
        Player newPlayer = new Player(newPlyName,20,"ST",82);
        if (!activeTeam.isPlayerPresent(newPlayer)) {
            activeTeam.signNewPlayer(newPlayer);
            modelPlayer.addElement(newPlyName);
        } else {
            System.out.println("This player is already playing in this team.");
        }
    }

    private boolean verifyTeamSelected(String teamName) {
        boolean blValue = false;
        for (Team tm: FootballApp.champLeague.listOfTeams) {
            if (teamName.equals(tm.getName())) {
                activeTeam = tm;
                blValue = true;
                break;
            }
        }
        return blValue;
    }

    private boolean verifyPlayerSelected(String playerName) {
        boolean blValue = false;
        for (Player pl: activeTeam.listOfPlayers) {
            if (playerName.equals(pl.getName())) {
                activePlayer = pl;
                blValue = true;
                break;
            }
        }
        return blValue;
    }

    public void playerTraining() {
        trainingMessage = new JTextField(20);
        if (activePlayer.isTrainable()) {
            activePlayer.trainPlayer();
            trainingMessage.setText(activePlayer.name + "'s new soccer rating is " + activePlayer.getSoccerRating());
            playerRatingField.setText(activePlayer.soccerRating + "");
        } else {
            trainingMessage.setText(activePlayer.name + " has reached his potential.");
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("add")) {
            String newPlyName = newPlayerText.getText();
            newPlayer(newPlyName);
        } else if (action.equals("back")) {
            removeAll();
            frame.switchToTeamsPanel();
        } else if (action.equals("train")) {
            JLabel imageTraining = new JLabel(new ImageIcon("footballTraining.png"));
            imageTraining.setBounds(350,10,200,100);
            this.add(imageTraining);

            //String selPlyName = playerRatingField.getText();
            //System.out.println(modelPlayer.contains(selPlyName));
            //if (modelPlayer.contains(selPlyName)) {
            playerTraining();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String name = playerList.getSelectedValue() + "";
        if (verifyPlayerSelected(name)) {
            playerRatingField.setText(activePlayer.soccerRating + "");
        }
    }
}
