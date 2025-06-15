package cards;

import app.CardGameMediator;
import helpers.Color;

/**
 * ActionCard class represents the class of cards
 * which have special effect after played
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public abstract class ActionCard extends Card {

    /**
     * constructor that initializes the data members with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public ActionCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof ActionCard)) {
            return false;
        }

        Card otherCard = (Card) otherObject;

        if (otherCard.getType().equals(getType()) && otherCard.getColor().equals(getColor())) {
            return true;
        }

        return false;
    }

    /**
     * returns a String representation of ActionCard object
     */
    @Override
    public String toString() {
        return this.getType() + " of color: " + this.getColor();
    }

    /**
     * each subclass of ActionCard class
     * overrides this method and implement
     * specific action determined by action cards type
     */
    public abstract void performAction();

}
