package cards;

import java.util.ArrayList;

import app.CardGameMediator;
import app.Player;
import helpers.Color;

/**
 * ShuffleHandsCard class causes all players hands
 * to be shuffled when played
 * 
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class ShuffleHandsCard extends ActionCard {

    /**
     * constructor that initializes the ShuffleHandsCard object with provided
     * type, color, and mediator parameters.
     * 
     * @param type     String that represents the card type
     * @param color    Color enum value that represents color of card
     * @param mediator CardGameMediator object that represents the mediator of the
     *                 game
     */
    public ShuffleHandsCard(String type, Color color, CardGameMediator mediator) {
        super(type, color, mediator);
    }

    @Override
    public void performAction() {
        CardGameMediator mediator = this.getMediator();

        System.out.println("Before shuffle hands card is played color is: " + mediator.getCurrentCardColor());
        mediator.setCurrentCardColor(this.getColorEnum());

        if (mediator.getIsInitialRound()) {
            // player to the left of the dealer
            // chooses the starting color in initial round
            Player player = mediator.getNextPlayer();
            player.determineNextColor();
            int dealerPosition = mediator.getDealerPosition();
            mediator.setCurrentPlayerPosition(dealerPosition);
        }

        else { // if current round is not the initial round
            ArrayList<Card> shufflePile = new ArrayList<>();
            // get each player from mediator and add their
            // hands to shufflePile
            ArrayList<Player> players = mediator.getPlayers();
            for (Player player : players) {
                shufflePile.addAll(player.getCardsInHand());
            }

            // after adding all cards to shufflePile
            // clear each player's hand
            for (Player player : players) {
                player.clearHand();
            }

            // get the player who played the shuffleHandsCard
            Player player = mediator.getCurrentlyPlayingPlayer();
            // player shuffle the cards in shufflePile
            shufflePile = player.shuffleCards(shufflePile);
            // player of the ShuffleHands card distributes
            // the shuffled cards to each player
            int currentPlayerPosition = mediator.getCurrentPlayerPosition();
            player.dealCards(shufflePile, "left");
            // after distributing cards, game continious in its current order
            mediator.setCurrentPlayerPosition(currentPlayerPosition);
            // player of the card should determine the next color
            player.determineNextColor();
        }

        System.out.println("Hands of players are shuffled");
        System.out.println("After shuffle hands card is played color is: " + mediator.getCurrentCardColor());
    }

    @Override
    public int getPoint() {
        return 40;
    }

    /**
     * returns a copy of the ShuffleHandsCard keeping the mediator field 
     * reference same.
     * 
     */
    @Override
    public Object clone() {

        return new ShuffleHandsCard(this.getType(), this.getColorEnum(), this.getMediator());
    }
}
