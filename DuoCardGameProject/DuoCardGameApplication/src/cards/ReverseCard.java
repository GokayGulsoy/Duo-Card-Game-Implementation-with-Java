package cards;

import app.CardGameMediator;
import helpers.Color;

/**
 * ReverseCard changes direction of game from either
 * "left" to "right" or "right" to "left"
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class ReverseCard extends ActionCard {

    /**
     * 
     * constructor that initializes the ReverseCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public ReverseCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {
        // reverse the direction of game
        CardGameMediator mediator = this.getMediator();

        System.out.println("Game direction before reversal is: " + mediator.getGameDirection());
        if (mediator.getIsInitialRound()) {
            // at the start of the game if revealed
            // first dealer plays and then game continious
            // in reverse direction

            // get player to left of the dealer
            mediator.getNextPlayer();
            // set the game direction to left instead of right
            mediator.setGameDirection("right");
        }

        else if (mediator.getGameDirection().equals("left")) {
            mediator.setGameDirection("right");
        }

        else { // game direction is right set it to left
            mediator.setGameDirection("left");
        }

        System.out.println("Game direction after reversal is: " + mediator.getGameDirection());
    }

    @Override
    public int getPoint() {
        return 20;
    }

    /**
     * returns a copy of the ReverseCard keeping the mediator field 
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new ReverseCard(this.getType(), this.getColorEnum(), this.getMediator());
    }

}
