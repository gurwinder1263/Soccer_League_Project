package exceptions;

//Represents exception used when a player is not trainable or reached max. Soccer rating.
public class NotTrainableException extends Exception {

    //EFFECTS: constructs the class object with error message.
    public NotTrainableException(String msg) {
        super(msg);
    }
}
