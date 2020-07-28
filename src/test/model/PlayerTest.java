package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Player class
public class PlayerTest {

    Player player5;
    Player player6;

    @BeforeEach
    void runBefore() {
        player5 = new Player("Lionel Messi", 33, "RW", 98);
        player6 = new Player("Kai Havertz", 21, "CAM", 87);
    }

    @Test
    void isTrainableTest() {
        assertFalse(player5.isTrainable());
        assertTrue(player6.isTrainable());
    }

    @Test
    void trainPlayerTest() {
        player6.trainPlayer();
        assertEquals(90, player6.getSoccerRating());
        assertEquals("CAM", player6.getPosition());
    }
}


