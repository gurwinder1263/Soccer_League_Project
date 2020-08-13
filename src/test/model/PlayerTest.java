package model;

import exceptions.NotTrainableException;
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

    @Test // Exception is thrown
    void trainPlayer5Test() {

        try {
            assertEquals(98, player5.getSoccerRating());
            assertFalse(player5.isTrainable());
            player5.trainPlayer();
            fail("I was not expecting to reach this line of code.");
        } catch(NotTrainableException e){
            System.out.println("Exception is expected.");
        }
    }

    @Test // Exception is not thrown
    void trainPlayer6Test() {
        try {
            assertEquals(87, player6.getSoccerRating());
            assertTrue(player6.isTrainable());
            player6.trainPlayer();
            assertEquals(90, player6.getSoccerRating());
        } catch(NotTrainableException e){
            fail("Exception should not have been thrown.");
        }
    }


}


