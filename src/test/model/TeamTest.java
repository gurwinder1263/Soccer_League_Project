package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Team class
public class TeamTest {

    Team team1;
    Team team2;
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    @BeforeEach
    void runBefore() {
        player1 = new Player("Phillipe Coutinho", 27, "LW", 86);
        player2 = new Player("Paulo Dybala", 26, "CAM", 89);
        player3= new Player("Sergio Ramos", 34, "CB", 88);
        player4 = new Player("Karim Benzema", 32, "ST", 86);
        team1 = new Team("ChelseaFC");
        team2 = new Team("ArsenalFC", player1, player2);
    }

    @Test
    void signNewPlayerTest() {
        team1.signNewPlayer(player3);

        assertTrue(team1.listOfPlayers.contains(player3));
        assertFalse(team2.listOfPlayers.contains(player4));
        assertEquals("Sergio Ramos", team1.listOfPlayers.get(0).getName());
    }

    @Test
    void isPlayerPresentTest() {
        team2.signNewPlayer(player3);

        assertTrue(team2.isPlayerPresent(player3));
        assertFalse(team2.isPlayerPresent(player4));
    }

    @Test
    void isUnitPresentTest() {
        team2.signNewPlayer(player3);

        assertTrue(team2.isUnitPresent(player3));
        assertFalse(team2.isPlayerPresent(player4));
    }

}
