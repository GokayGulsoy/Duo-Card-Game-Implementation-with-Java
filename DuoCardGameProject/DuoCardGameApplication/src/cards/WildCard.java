package cards;

import app.CardGameMediator;
import app.Player;
import helpers.Color;

/**
 * WildCard changes color of game according
 * to color of the card which occurs most frequent.
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class WildCard extends ActionCard {

    /**
     * constructor that initializes the WildCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public WildCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {

        CardGameMediator mediator = this.getMediator();
        System.out.println("Before wild card is played color is: " + mediator.getCurrentCardColor());
        if (mediator.getIsInitialRound()) {
            // at the beginning of the game, player
            // to the left of the dealer chooses the next color
            Player player = mediator.getNextPlayer();
            player.determineNextColor();
            mediator.setCurrentPlayerPosition(mediator.getDealerPosition());
        }

        else { // apart from the initial round color is determined by current player
            mediator = this.getMediator();
            Player player = mediator.getCurrentlyPlayingPlayer();
            player.determineNextColor();
        }

        System.out.println("After wild card is played color is: " + mediator.getCurrentCardColor());
    }

    @Override
    public int getPoint() {
        return 50;
    }

    /**
     * returns a copy of the WildDrawFourCard keeping the mediator field
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new WildCard(this.getType(), this.getColorEnum(), this.getMediator());
    }
}
