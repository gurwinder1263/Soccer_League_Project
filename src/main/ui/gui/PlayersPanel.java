package ui.gui;

import exceptions.NotTrainableException;
import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a players panel displayed to user.
public class PlayersPanel extends JPanel implements ActionListener, ListSelectionListener {
    public FootballApp frame;
    private Team activeTeam;
    private Player activePlayer;
    private JList<String> playerList;
    private DefaultListModel<String> modelPlayer;
    private JTextField newPlayerText;
    private JTextField playerRatingField;
    private JTextField trainingMessage;
    private JLabel trainingImage;
    private JLabel trainingLabel;
    private JButton newPlayer;
    private JButton trainPlayer;
    private JButton back;

    // EFFECTS: Constructs a players panel with size and background colour of panel,
    //           updates this with the user selection
    public PlayersPanel(FootballApp frame,String teamName) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(null);
        if (verifyTeamSelected(teamName)) {
            JLabel topText = new JLabel("Players playing for " + teamName + " are:");
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
            playerList.setFixedCellHeight(50);
            playerList.addListSelectionListener(this);
            scrollPane.setBounds(10,110,200,150);
            add(scrollPane);
            addComponents();
        }
    }

    //EFFECTS: initialize rest of the fields that are not dealt in the constructor.
    //         adds them to the panel.
    public void addComponents() {
        trainingImage = new JLabel();
        trainingLabel = new JLabel("Training Message:");
        newPlayerText = new JTextField(10);
        playerRatingField = new JTextField(10);
        trainingMessage = new JTextField(20);
        newPlayer = new JButton("Add New Player");
        newPlayer.addActionListener(this);
        newPlayer.setActionCommand("add");
        trainPlayer = new JButton("Train Player");
        trainPlayer.addActionListener(this);
        trainPlayer.setActionCommand("train");
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setActionCommand("back");
        setsBounds();
        this.add(newPlayerText);
        this.add(newPlayer);
        this.add(playerRatingField);
        this.add(trainPlayer);
        this.add(back);
        this.add(trainingImage);
        this.add(trainingMessage);
        this.add(trainingLabel);
    }

    //EFFECTS: sets bounds for class fields for display purposes.
    public void setsBounds() {
        trainingImage.setBounds(350,350,150,160);
        trainingLabel.setBounds(230,520,200,40);
        trainingMessage.setBounds(350,520,260,40);
        newPlayerText.setBounds(250,110,200,50);
        newPlayer.setBounds(250,170,200,50);
        playerRatingField.setBounds(10,270,200,50);
        trainPlayer.setBounds(10,330,200,50);
        back.setBounds(600,600,100,30);

    }

    // MODIFIES: this
    // EFFECTS:  adds new player if player is already not there.
    //          - Otherwise, sends message to the user to inform about player's presence in team.
    private void newPlayer(String newPlyName) {
        Player newPlayer = new Player(newPlyName,20,"ST",82);
        if (!activeTeam.isPlayerPresent(newPlayer)) {
            activeTeam.signNewPlayer(newPlayer);
            modelPlayer.addElement(newPlyName);
            newPlayerText.setText("");
        } else {
            newPlayerText.setText("Already plays");
        }
    }

    //MODIFIES: this
    //EFFECTS: returns true if the team name is present in the league.
    //        - otherwise, false.
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

    //MODIFIES: this
    //EFFECTS: returns true if the player name is present in the team.
    //        - otherwise, false.
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

    // MODIFIES: this
    // EFFECTS: Trains player. If active player  is trainable
    //                    - train him and displays new rating and training message.
    //                    - Otherwise, messages to inform user that player has reached his potential.
    public void playerTraining() {
        try {
            activePlayer.trainPlayer();
            ImageIcon image = new ImageIcon("./data/tinyTraining.png");
            trainingImage.setIcon(image);
            trainingMessage.setText(activePlayer.name + " has trained.");
            playerRatingField.setText(activePlayer.soccerRating + "");
        } catch (NotTrainableException e) {
            trainingMessage.setText(activePlayer.name + " has reached his potential.");
        }
    }

    // MODIFIES: this.
    // EFFECTS: adds new player, go back to previous OR trains player in the team when corresponding
    //         ActionListener is involved.
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
            String rating = playerRatingField.getText();
            if (activePlayer.getSoccerRating() == Integer.parseInt(rating)) {
                playerTraining();
            }
        }
    }

    // MODIFIES: this.
    // EFFECTS: adds Soccer rating of the selected player from the list, to JTextField.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        String name = playerList.getSelectedValue() + "";
        if (verifyPlayerSelected(name)) {
            playerRatingField.setText(activePlayer.soccerRating + "");
        }
    }
}
