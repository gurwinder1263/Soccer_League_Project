package ui;

import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TeamsPanel extends JPanel implements ActionListener, ListSelectionListener {
    public FootballApp frame;
    public JButton newTeam;
    public JButton selTeam;
    public JButton back;
    public JLabel topText;
    public JList<String> teamList;
    public DefaultListModel<String> model;
    private JTextField newTeamText;
    private JTextField selTeamText;


    // EFFECTS: Constructs a main panel with size and background colour of panel,
    //           updates this with the user selection
    public TeamsPanel(FootballApp frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        //setBackground(Color.lightGray);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //this.setLayout(new GridLayout(7,1,5,5));
        this.setLayout(null);
        topText = new JLabel("Teams playing in the Uefa Champions league are:");
        topText.setFont(new Font("Arial", 20, 20));
        topText.setBounds(10,10,450,100);
        this.add(topText);
        model = new DefaultListModel<>();
        for (Team tm: FootballApp.champLeague.listOfTeams) {
            model.addElement(tm.getName());
        }
        teamList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(teamList);
        add(scrollPane);
        teamList.setVisibleRowCount(5);
        teamList.setFixedCellWidth(100);
        teamList.setFixedCellHeight(25);
        teamList.addListSelectionListener(this);
        teamList.setBounds(10,110,100,150);
        add(teamList);
        setVisible(true);
        addComponents();
    }

    public void addComponents() {
        newTeamText = new JTextField(10);
        selTeamText = new JTextField(10);
        newTeam = new JButton("Add New Team");
        newTeam.addActionListener(this);
        newTeam.setActionCommand("add");
        selTeam = new JButton("View Players");
        selTeam.addActionListener(this);
        selTeam.setActionCommand("select");
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setActionCommand("back");
        newTeamText.setBounds(150,110,200,50);
        newTeam.setBounds(150,170,200,50);
        selTeamText.setBounds(10,270,200,50);
        selTeam.setBounds(10,330,200,50);
        back.setBounds(350,350,200,50);
        this.add(newTeamText);
        this.add(newTeam);
        this.add(selTeamText);
        this.add(selTeam);
        this.add(back);
    }

    // MODIFIES: this
    // EFFECTS:  adds new team if team is already not there.
    //          - Otherwise, sends message to the user to inform about team's presence in League.
    private void newTeam(String newTmName) {
        Team newTeam = new Team(newTmName);
        if (!FootballApp.champLeague.isTeamPresent(newTeam)) {
            FootballApp.champLeague.addNewTeam(newTeam);
            model.addElement(newTmName);
        } else {
            System.out.println("This team is already playing in the league.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("add")) {
            String newTmName = newTeamText.getText();
            newTeam(newTmName);
        } else if (action.equals("back")) {
            removeAll();
            frame.switchToTeamsMenuPanel();
        } else if (action.equals("select")) {
            String selTmName = selTeamText.getText();
            System.out.println(model.contains(selTmName));
            if (model.contains(selTmName)) {
                removeAll();
                frame.switchToPlayersPanel(selTmName);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        selTeamText.setText(teamList.getSelectedValue() + "");
    }
}
