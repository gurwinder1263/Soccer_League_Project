package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testParseTeamsFile1() {
        try {
            ArrayList<Team> teams = Reader.readTeams(new File("./data/testTeamsFile1.txt"));
            Team team1 = teams.get(0);
            assertEquals("Liverpool FC", team1.getName());

            Player player1 = team1.listOfPlayers.get(0);
            assertEquals("Sadio Mane", player1.getName());
            assertEquals(28, player1.age);
            assertEquals(90, player1.getSoccerRating());
            assertEquals("ST", player1.position);

            Player player2 = team1.listOfPlayers.get(1);
            assertEquals("Roberto Firmino", player2.getName());
            assertEquals(28, player2.age);
            assertEquals(87, player2.getSoccerRating());
            assertEquals("CF", player2.position);

            Team team2 = teams.get(1);
            assertEquals("FC Bayern Munich", team2.getName());

            Player player3 = team2.listOfPlayers.get(0);
            assertEquals("Robert Lewandowski", player3.getName());
            assertEquals(31, player3.age);
            assertEquals(92, player3.getSoccerRating());
            assertEquals("ST", player3.position);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseTeamsFile2() {
        try {
            ArrayList<Team> teams = Reader.readTeams(new File("./data/testTeamsFile2.txt"));
            Team team1 = teams.get(0);
            assertEquals("FC Barcelona", team1.getName());

            Player player1 = team1.listOfPlayers.get(0);
            assertEquals("Lionel Messi", player1.getName());
            assertEquals(33, player1.age);
            assertEquals(94, player1.getSoccerRating());
            assertEquals("RW", player1.position);

            Player player2 = team1.listOfPlayers.get(1);
            assertEquals("Gerard Pique", player2.getName());
            assertEquals(34, player2.age);
            assertEquals(86, player2.getSoccerRating());
            assertEquals("CB", player2.position);

            Team team2 = teams.get(1);
            assertEquals("Atletico Madrid FC", team2.getName());

            Player player3 = team2.listOfPlayers.get(0);
            assertEquals("Jan Oblak", player3.getName());
            assertEquals(27, player3.age);
            assertEquals(90, player3.getSoccerRating());
            assertEquals("GK", player3.position);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readTeams(new File("./path/does/not/exist/testTeams.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}

