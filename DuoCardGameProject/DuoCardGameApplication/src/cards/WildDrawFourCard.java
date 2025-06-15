package cards;

import app.CardGameMediator;
import app.Player;
import helpers.Color;

/**
 * WildDrawFourCard changes the color of the game
 * according to color of the card that occurs most frequently
 * and forces next player to draw four cards from draw pile
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class WildDrawFourCard extends ActionCard {

    /**
     * constructor that initializes the WildDrawFourCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public WildDrawFourCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {
        // set hasToDrawCard condition on
        // mediator object to true to indicate that next player has to draw four cards
        CardGameMediator mediator = this.getMediator();

        System.out.println("Before wild draw four card is played color is: " + mediator.getCurrentCardColor());
        mediator.setMustDrawCard(true);

        int amountOfCardsToBeDrawn = mediator.getAmountOfCardsToBeDrawn();
        amountOfCardsToBeDrawn += 4;
        mediator.setAmountOfCardsToBeDrawn(amountOfCardsToBeDrawn);

        Player player = mediator.getCurrentlyPlayingPlayer();
        player.determineNextColor();

        System.out.println(
                "Next player has to draw: " + amountOfCardsToBeDrawn + " cards after playing " + getType() + " card");

        System.out.println("After wild draw four card is played color is: " + mediator.getCurrentCardColor());
    }

    @Override
    public int getPoint() {
        return 50;
    }

    /**
     * returns a copy of the WildDrawFourCard keeping the mediator field
     * reference same.
     */
    @Override
    public Object clone() {

        return new WildDrawFourCard(this.getType(), this.getColorEnum(), this.getMediator());
    }

}
