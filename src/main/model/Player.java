package model;

// Represents a player with name, age, position,and overall soccer rating.
public class Player {
    public static final String SEX = "M";
    public static final int MAX_RATING = 100; // Attributes are rated out of 100.
    public static final int RATING_INCREASE_PER_TRAINING = 3; // increase in overall rating per training session.

    public String name;
    public int age;
    public String position;
    public int soccerRating;// an average of player's shooting, passing, dribbling and defending skills.

    public Player(String name, int age, String position, int soccerRating) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.soccerRating = soccerRating;
    }

    public String getName() {
        return name;
    }

    public int getSoccerRating() {
        return soccerRating;
    }

    // EFFECTS: if the player can be trained ( if player has not reached his maximum rating of 98, 99 or, 100)
    //                     - return true.
    //                     - Otherwise, return false.
    public boolean isTrainable() {
        return soccerRating <= (MAX_RATING - RATING_INCREASE_PER_TRAINING);
    }

    // MODIFIES: this
    // EFFECTS: increases overall rating based on increase in one training session.
    public void trainPlayer() {
        soccerRating = soccerRating + RATING_INCREASE_PER_TRAINING;
    }
}
