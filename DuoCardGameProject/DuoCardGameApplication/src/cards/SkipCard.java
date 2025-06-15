package cards;

import app.CardGameMediator;
import app.Player;
import helpers.Color;

/**
 * SkipCard skips the next player who will
 * normally play and continious with the
 * the subsequent player
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class SkipCard extends ActionCard {

    /**
     * constructor that initializes the SkipCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public SkipCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {
        // skip to next player
        CardGameMediator mediator = this.getMediator();
        Player skippedPlayer = mediator.getNextPlayer();
        System.out.println(skippedPlayer.getName() + " skipped");
    }

    @Override
    public int getPoint() {
        return 20;
    }

    /**
     * returns a copy of the SkipCard keeping the mediator field 
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new SkipCard(this.getType(), this.getColorEnum(), this.getMediator());
    }
}
