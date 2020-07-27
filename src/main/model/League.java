package model;

import java.util.ArrayList;

public class League {
    public static final int SEASON_YEAR = 2020;
    public static final int MAX_NUM_TEAMS = 10;

    public String name; // League name
    public ArrayList<Team> listOfTeams;

    // EFFECTS: constructs newly created League with a name and empty list of teams.
    public League(String name) {
        this.name = name;
        listOfTeams = new ArrayList<>(MAX_NUM_TEAMS);
    }


    // EFFECTS: constructs newly created League with a name and two teams.
    public League(String name, Team team1, Team team2) {
        this.name = name;
        listOfTeams = new ArrayList<>(MAX_NUM_TEAMS);
        listOfTeams.add(0, team1);
        listOfTeams.add(1, team2);
    }


    // EFFECTS: returns the team selected by the user.
    public String getName() {
        return name;
    }

    // REQUIRES: team is already not present in the league.
// MODIFIES: this
// EFFECTS: adds the new team to the league.
    public void addNewTeam(Team team) {
        listOfTeams.add(team);
    }

    // REQUIRES: team is already present in the league.
// MODIFIES: this
// EFFECTS: removes the existing team from the league.
    public void relegateTeam(Team team) {
        listOfTeams.remove(team);
    }

    // EFFECTS: if list of the teams contains the selected team,
//                     - return true.
//                     - Otherwise, return false.
    public boolean isTeamPresent(Team teamChecked) {
        for (Team team : listOfTeams) {
            if ((team.getName()).equalsIgnoreCase(teamChecked.getName())) {
                return true;
            }
        }
        return false;
    }
}
