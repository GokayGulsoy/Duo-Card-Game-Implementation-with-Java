package cards;

import app.CardGameMediator;
import helpers.Color;

/**
 * DrawTwoCard class triggers next player to 
 * draw two cards from deck when played
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class DrawTwoCard extends ActionCard {

    /**
     * 
     * constructor that initializes the DrawTwoCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public DrawTwoCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {
        // set hasToDrawCard condition on
        // mediator object to true to indicate that next player has to draw two cards
        CardGameMediator mediator = this.getMediator();
        if (!mediator.getShouldDrawCard()) {
            mediator.setShouldDrawCard(true);
        }

        int amountOfCardsToBeDrawn = mediator.getAmountOfCardsToBeDrawn();
        amountOfCardsToBeDrawn += 2;
        mediator.setAmountOfCardsToBeDrawn(amountOfCardsToBeDrawn);
        
        System.out.println(
                "Next player has to draw: " + amountOfCardsToBeDrawn + " cards after playing " + getType() + " card");
    }

    @Override
    public int getPoint() {
        return 20;
    }

    /**
     * returns a copy of the DrawTwoCard keeping the mediator field 
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new DrawTwoCard(this.getType(), this.getColorEnum(), this.getMediator());
    }
}
