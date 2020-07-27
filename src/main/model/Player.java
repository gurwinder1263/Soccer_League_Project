package model;

// Represents a player with name, age, position, team and other soccer attributes.
public class Player {
    public static final String SEX = "M";
    public static final int MAX_ATTRIBUTE = 100; // Attributes are rated out of 100.
    public static final int ATTRIBUTE_INCREASE_PER_TRAINING = 3; // increase in attributes per training session.

    public String name;
    public int age;
    public double goalAverage; // expected goals scoring rate or goals per game and it is in range 0 >= avg_g <=2
    public String position;
    public int soccerAttributes;// an average of player's shooting, passing, dribbling and defending skills.

    public Player(String name, int age, double goalAverage, String position, int soccerAttributes) {
        this.name = name;
        this.age = age;
        this.goalAverage = goalAverage;
        this.position = position;
        this.soccerAttributes = soccerAttributes;
    }

    public String getPosition() {
        return position;
    }

    public void changePosition(String newPosition) {
        position = newPosition;
    }

    public boolean isTrainable() {
        return soccerAttributes <= (MAX_ATTRIBUTE - ATTRIBUTE_INCREASE_PER_TRAINING);
    }

    public void trainPlayer() {
        soccerAttributes = soccerAttributes + ATTRIBUTE_INCREASE_PER_TRAINING;
    }
}
