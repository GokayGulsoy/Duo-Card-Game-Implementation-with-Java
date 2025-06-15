package cards;

import app.CardGameMediator;
import helpers.Color;

/**
 * Card class is the superclass of all cards in Duo Card Game
 * and acts as an abstract base class for NumberCard and ActionCard
 * classes
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public abstract class Card implements Cloneable {

    private String type;
    private Color color;
    private CardGameMediator mediator;

    /**
     * constructor that initializes the the data members with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public Card(String type, Color color, CardGameMediator mediator) {
        this.type = type;
        this.color = color;
        this.mediator = mediator;
    }

    /**
     * returns a reference to type field
     * 
     * @return String representation for type of card
     */
    public String getType() {
        return type;
    }

    /**
     * returns a reference to color field
     * 
     * @return Color enum that represents card color
     */
    public Color getColorEnum() {
        return color;
    }

    /**
     * returns String representation of color field
     * 
     * @return <b>String</b> representation of color
     */
    public String getColor() {
        return color.getColor();
    }

    /**
     * returns a reference to mediator field
     * 
     * @return <b>CardGameMediator</b> reference to mediator object
     */
    public CardGameMediator getMediator() {
        return mediator;
    }

    /**
     * If card is NumberCard point is the
     * face value, for ActionCard's point depends on the
     * action card type
     * 
     * @return <b>int</b> point that is specific to card type
     */
    public abstract int getPoint();

    /**
     * each subclass of ActionCard class overrides this method
     * to enforce equality semantics
     * 
     * @return boolean which is true if otherObject is equals to
     *         ActionCard object, false otherwise
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * each subclass of Card class must create a override
     * this method for creating a copy of Card object
     */
    @Override
    public abstract Object clone();

}
