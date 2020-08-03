package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testTeamsFile3.txt";
    private Writer testWriter;
    private Team team1;
    private Team team2;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        Player player1 = new Player( "Cristiano Ronaldo", 35, "LW", 94 );
        Player player2 = new Player( "Paulo Dybala", 26, "CAM", 89 );
        Player player3 = new Player( "Sergio Ramos", 34, "CB", 88 );
        Player player4 = new Player( "Karim Benzema", 32, "ST", 86 );
        team1 = new Team("Juventus FC", player1, player2 );
        team2 = new Team("Real Madrid FC", player3, player4 );
    }

    @Test
    void testWriteTeams() {
        // save team1 and team2 teams to file
        testWriter.write(team1);
        testWriter.write(team2);
        testWriter.close();

        // now read them back in and verify that the teams have the expected values
        try {
            ArrayList<Team> teams = Reader.readTeams(new File(TEST_FILE));
            Team team1 = teams.get(0);
            assertEquals("Juventus FC", team1.getName());

            Player player1 = team1.listOfPlayers.get(0);
            assertEquals("Cristiano Ronaldo", player1.getName());
            assertEquals(35, player1.age);
            assertEquals(94, player1.getSoccerRating());
            assertEquals("LW", player1.position);


            Team team2 = teams.get(1);
            assertEquals("Real Madrid FC", team2.getName());

            Player player3 = team2.listOfPlayers.get(1);
            assertEquals("Karim Benzema", player3.getName());
            assertEquals(32, player3.age);
            assertEquals(86, player3.getSoccerRating());
            assertEquals("ST", player3.position);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}

