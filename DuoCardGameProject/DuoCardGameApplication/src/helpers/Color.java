package helpers;

/**
 * Color enum that represents the color of
 * cards in the Duo Card Game which are blue,
 * green, red, and yellow. Default represents
 * the shuffle card's color as it doesn't have
 * specific color
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public enum Color {

    /**represents the "Blue" color */
    BLUE("Blue"),
    /**represents the "Green" color */
    GREEN("Green"),
    /**represents the "Red" color */
    RED("Red"),
    /**represents the "Yellow" color */
    YELLOW("Yellow"),
    /**represents the color of ShuffleHandsCard 
     * as it does not have any color
     */
    DEFAULT("Default");

    private String color;

    /**
     * constructor that initializes the color field with given parameter.
     * 
     * @param color <b>String</b> value representing the color of the card
     */
    private Color(String color) {
        this.color = color;
    }

    /**
     * returns String representation of the color
     * 
     * @return <b>String</b> value that represents color of card
     */
    public String getColor() {
        return color;
    }

}
