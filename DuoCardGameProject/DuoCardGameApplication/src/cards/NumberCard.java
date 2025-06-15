package cards;

import app.CardGameMediator;
import helpers.Color;

/**
 * NumberCard class does not impose any special
 * effect and each instance is represented with
 * a specific number
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class NumberCard extends Card {

    private int number;

    /**
     * constructor that initializes the NumberCard object with provided
     * type, color, number, and mediator parameters.
     * 
     * @param type     String that represents the type of card
     * @param color    Enum that represents the color of card
     * @param number   int that represents the number of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public NumberCard(String type, Color color, int number, CardGameMediator mediator) {
        super(type, color, mediator);
        this.number = number;
    }

    /**
     * returns the value of the number field.
     * 
     * @return int value that represents
     *         the number of card
     */
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof NumberCard)) {
            return false;
        }

        NumberCard otherNumberCard = (NumberCard) otherObject;
        if (otherNumberCard.getType().equals(getType()) && otherNumberCard.getColor().equals(getColor())
                && otherNumberCard.getNumber() == getNumber()) {
            return true;
        }

        return false;
    }

    @Override
    public int getPoint() {
        return number;
    }

    /**
     * returns a String representation of NumberCard object
     */
    @Override
    public String toString() {
        return this.getType() + " " + this.getNumber() + " of color: " + this.getColor();
    }

    /**
     * returns a copy of the NumberCard keeping the mediator field 
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new NumberCard(this.getType(), this.getColorEnum(), this.getNumber(), this.getMediator());
    }

}
