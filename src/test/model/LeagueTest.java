package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Leaque class
public class LeagueTest {

    League league1;
    League league2;
    Team team1;
    Team team2;
    Team team3;
    Team team4;


    @BeforeEach
    void runBefore() {
        team1 = new Team("ChelseaFC");
        team2 = new Team("ArsenalFC");
        team3 = new Team("Manchester United FC");
        team4 = new Team("LiverpoolFC");
        league1 = new League("La Liga");
        league2 = new League("Premier League", team1, team2);
    }

    @Test
    void addNewTeamTest() {
        league2.addNewTeam(team3);

        assertTrue(league2.listOfTeams.contains(team3));
        assertFalse(league2.listOfTeams.contains(team4));
        assertEquals("Manchester United FC", league2.listOfTeams.get(2).getName());
    }

    @Test
    void isTeamPresentTest() {
        league2.addNewTeam(team3);

        assertTrue(league2.isTeamPresent(team3));
        assertFalse(league2.isTeamPresent(team4));

    }

    @Test
    void teamPrint() {
        league2.addNewTeam(team3);
        for (Team tm : league2.listOfTeams) {
            System.out.println(tm);
        }
    }
}
