package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import cards.ActionCard;
import cards.Card;
import cards.NumberCard;
import helpers.Color;

/**
 * Player class which represents the player
 * with specific name, score and cards he/she playes
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class Player {

    private final String playerName;
    private int score;
    private String playerType;
    private ArrayList<Card> cardsInHand;
    private CardGameMediator mediator;

    /**
     * constructor that initializes the data fields name,
     * and type with the given parameters
     * 
     * @param playerName String name of the player
     * @param playerType String type of the player
     *                   either "normal" or "dealer"
     */
    public Player(String playerName, String playerType) {
        this.playerName = playerName;
        this.score = 0;
        this.playerType = playerType;
        this.cardsInHand = new ArrayList<>();
        this.mediator = null;
    }

    /**
     * returns a reference to name field.
     * 
     * @return <b>String</b> that represents player's name
     */
    public String getName() {
        return playerName;
    }

    /**
     * returns the value of the score field
     * 
     * @return <b>int</b> that represents the score of player
     */
    public int getScore() {
        return score;
    }

    /**
     * returns a reference to playerType field
     * 
     * @return <b>String</b> that represents player's type
     */
    public String getPlayerType() {
        return playerType;
    }

    /**
     * returns a reference to cardsInHand field.
     * 
     * @return <b>ArrayList</b> of Cards which represents
     *         the cards in hand
     */
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * sets the score to given integer.
     * 
     * @param score int that represents the score to be set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * sets the playerType to given player type.
     * 
     * @param playerType player type to be set
     *                   which can be either "normal" or "dealer"
     */
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    /**
     * sets the mediator to given mediator object.
     * 
     * @param mediator CardGameMediator to be set
     */
    public void setGameMediator(CardGameMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * deal cards to all players at the start of the game
     * 
     * @param deck ArrayList of Cards to be dealed by
     *             dealer to all players
     * 
     *             <p>
     *             delegates dealing to CardGameMediator's dealCardsToStartGame
     *             </p>
     * 
     * @see app.CardGameMediator#dealCardsToStartGame(ArrayList)
     *      dealCardsToStartGame
     */
    public void dealCardsToStartGame(ArrayList<Card> deck) {
        // only "dealer" player is
        // allowed to deal the cards
        if (playerType == "dealer") {
            mediator.dealCardsToStartGame(deck);
        }
    }

    /**
     * take card from distributer and add it to cardsInHand.
     * 
     * @param card Card taken from dealer
     *             to be added to hand
     */
    public void takeCardFromDistributer(Card card) {
        cardsInHand.add(card);
    }

    /**
     * remove all cards in hand
     */
    public void clearHand() {
        cardsInHand.clear();
    }

    /**
     * reset score and playerType data fields
     * before starting new round
     */
    public void reset() {
        setScore(0);
        setPlayerType("normal");
        clearHand();
    }

    /**
     * shuffle the cards passed as parameter and return
     * shuffled cards
     * 
     * @param cards cards to be shuffled
     * @return <b>ArrayList</b> that represents shuffled cards
     */
    public ArrayList<Card> shuffleCards(ArrayList<Card> cards) {
        Collections.shuffle(cards);

        return cards;
    }

    /**
     * deal cards given as the parameter pile to all players
     * in given dealingDirection
     * 
     * @param pile             pile of cards to be dealt among players
     * @param dealingDirection direction in which cards are to be dealt
     */
    public void dealCards(ArrayList<Card> pile, String dealingDirection) {
        mediator.dealCards(pile, dealingDirection);
    }

    /**
     * next color is determined according to
     * color of the card that player has most cards of
     */
    public void determineNextColor() {
        HashMap<String, Integer> colorCountMap = new HashMap<>();
        // add each color to hashmap
        colorCountMap.put("Blue", 0);
        colorCountMap.put("Green", 0);
        colorCountMap.put("Red", 0);
        colorCountMap.put("Yellow", 0);

        // count how many times each color exists in player's hand
        for (String color : colorCountMap.keySet()) {
            for (Card card : cardsInHand) {
                if ((card.getColor()).equals(color)) {
                    int colorCount = colorCountMap.get(color);
                    colorCount++;
                    colorCountMap.put(color, colorCount);
                }
            }
        }

        // after setting number of occurences for each
        // color, find the most commonly occurring color
        ArrayList<String> mostCommonlyOccuringColors = new ArrayList<>();
        String mostCommonlyOccuringColor = "Blue";
        int highestColorCount = colorCountMap.get("Blue");

        for (String color : colorCountMap.keySet()) {
            if (colorCountMap.get(color) > highestColorCount) {
                mostCommonlyOccuringColor = color;
                highestColorCount = colorCountMap.get(color);
            }
        }

        mostCommonlyOccuringColors.add(mostCommonlyOccuringColor);
        /*
         * after determining most commonly occurring
         * color, check for other colors which may
         * occur same number of times and add them to
         * mostCommonlyOccuringColors list
         */
        colorCountMap.remove(mostCommonlyOccuringColor);

        for (String color : colorCountMap.keySet()) {
            if (colorCountMap.get(color) == highestColorCount) {
                mostCommonlyOccuringColors.add(color);
            }
        }

        // checking for due
        if (mostCommonlyOccuringColors.size() == 1) {
            // if there is only one most commonly occuring color
            // set it as the current card color directly
            setGameColor(mostCommonlyOccuringColors, 0);
        }

        else { // if more than one most commonly occuring color exists
            Random rng = new Random();
            int randomIndex = rng.nextInt(0, mostCommonlyOccuringColors.size());
            setGameColor(mostCommonlyOccuringColors, randomIndex);
        }
    }

    /**
     * @param colors     ArrayList used to determine next color
     * @param colorIndex int used to locate which color to choose from colors
     *                   ArrayList
     */
    private void setGameColor(ArrayList<String> colors, int colorIndex) {
        String currentColor = colors.get(colorIndex);

        switch (currentColor) {
            case "Blue":
                mediator.setCurrentCardColor(Color.BLUE);
                break;
            case "Green":
                mediator.setCurrentCardColor(Color.GREEN);
                break;
            case "Red":
                mediator.setCurrentCardColor(Color.RED);
                break;
            case "Yellow":
                mediator.setCurrentCardColor(Color.YELLOW);
        }
    }

    /**
     * @param cardToBeRemoved Card that is to be removed
     *                        after it is played
     */
    private void removePlayedCard(Card cardToBeRemoved) {
        int indexToRemove = 0;
        for (int i = 0; i < cardsInHand.size(); i++) {
            if (cardToBeRemoved.equals(cardsInHand.get(i))) {
                indexToRemove = i;
                break;
            }
        }

        cardsInHand.remove(indexToRemove);
    }

    /**
     * draw cards from drawPile which is
     * determined by the amountOfCards to be drawn data field of CardGameMediator
     */
    public void drawCards() {
        int numberOfCardsDrawn = 0;
        int amountOfCardsToBeDrawn = mediator.getAmountOfCardsToBeDrawn();

        while (numberOfCardsDrawn < amountOfCardsToBeDrawn) {
            Card drawnCard = mediator.removeCardFromDrawPile();
            cardsInHand.add(drawnCard);
            numberOfCardsDrawn++;
        }

        System.out.println("Draw " + mediator.getAmountOfCardsToBeDrawn() + " cards");
    }

    /**
     * returns a reference to DrawTwoCard object found in cardsInHand.
     * 
     * @return ActionCard object which is a reference to
     *         DrawTwoCard found in cardsInHand
     */
    public ActionCard checkForDrawTwoCardInHand() {
        ArrayList<ActionCard> drawTwoCards = new ArrayList<>();

        for (Card card : cardsInHand) {
            if (card.getType().equals("draw two")) {
                ActionCard drawTwoCard = (ActionCard) card;
                drawTwoCards.add(drawTwoCard);
            }
        }

        // if drawTwoCards is empty then return null
        if (drawTwoCards.isEmpty()) {
            return null;
        }

        else { // there are 1 or more drawTwoCars in cardsInHand
            Random rng = new Random();
            int randomIndex = rng.nextInt(0, drawTwoCards.size());
            ActionCard drawTwoCard = drawTwoCards.get(randomIndex);

            int removeIndex = 0;
            // locate the drawTwoCard in cardsInHand then remove it
            for (int i = 0; i < cardsInHand.size(); i++) {
                Card card = cardsInHand.get(i);

                if (card.equals(drawTwoCard)) {
                    removeIndex = i;
                }
            }

            cardsInHand.remove(removeIndex);
            return drawTwoCard;
        }

    }

    /**
     * play the card according the top card of the discard pile
     * 
     * 
     * @param discardPileTopCardType String type of the top card in discardPile
     * @param topCardColor           String color of the top card in discardPile
     */
    public void playCard(String discardPileTopCardType, String topCardColor) {
        Card topCard = mediator.getTopCardOfDiscardPile();
        // create an ArrayList for possible cards to be played
        ArrayList<Card> possibleCardsToPlay = new ArrayList<>();

        if (discardPileTopCardType.equals("number")) {

            // firstly we have to find any number cards that have the
            // same number or color
            for (Card card : cardsInHand) {
                if (card.getType().equals("number")) {
                    NumberCard numberCard = (NumberCard) card;
                    NumberCard topNumberCard = (NumberCard) topCard;

                    if ((numberCard.getNumber() == topNumberCard.getNumber())
                            || (numberCard.getColor().equals(topCardColor))) {
                        possibleCardsToPlay.add(numberCard);
                    }
                }
            }

            // secondly we check for action cards that can be played
            for (Card card : cardsInHand) {
                // if action card is any action card apart from
                // WildDrawFour, add it to possibleCardsToPlay
                // iff color matches to number card the top of the
                // discardPile (apart from shuffle card)
                if ((card.getType().equals("draw two") || card.getType().equals("reverse")
                        || card.getType().equals("skip") || card.getType().equals("wild"))
                        && (card.getColor().equals(topCardColor))) {

                    possibleCardsToPlay.add(card);
                }

                // player can play ShuffleHandsCard
                // even if there are other playable cars in hand
                else if (card.getType().equals("shuffle")) {
                    possibleCardsToPlay.add(card);
                }
            }

            // after checking for all playable cards
            // if there are no other playable cards
            // then player can play WildDrawFourCard
            if (possibleCardsToPlay.size() == 0) {
                for (Card card : cardsInHand) {
                    if (card.getType().equals("wild draw four") && card.getColor().equals(topCardColor)) {
                        possibleCardsToPlay.add(card);
                    }
                }
            }

            playFromPossibleCardsOrDrawCard(possibleCardsToPlay);
        }

        // logic for discardPile top card being action card
        else {

            // if the top card is draw two, reverse, or skip
            if (topCard.getType().equals("draw two") || topCard.getType().equals("reverse")
                    || topCard.getType().equals("skip")) {

                // any number card with matching color can be played
                for (Card card : cardsInHand) {
                    if (card.getType().equals("number") && card.getColor().equals(topCardColor)) {
                        possibleCardsToPlay.add(card);
                    }

                    else if ((card.getType().equals(topCard.getType())) || (card.getType().equals("shuffle"))) {
                        possibleCardsToPlay.add(card);
                    }

                    else if (card.getType().equals("wild") && card.getColor().equals(topCardColor)) {
                        possibleCardsToPlay.add(card);
                    }
                }

                // after checking possibleCardsToPlay apart from
                // WildDrawFour, if no cards to exist to be played
                // add WildDrawFour card to possibleCardsToPlay if exists
                if (possibleCardsToPlay.size() == 0) {
                    for (Card card : cardsInHand) {
                        if (card.getType().equals("wild draw four") && card.getColor().equals(topCardColor)) {
                            possibleCardsToPlay.add(card);
                        }
                    }
                }

                playFromPossibleCardsOrDrawCard(possibleCardsToPlay);

            }

            // if the top card is Wild, WildDrawFour, Shuffle
            else {
                String currentCardColor = mediator.getCurrentCardColor();

                for (Card card : cardsInHand) {
                    // any number card matching with the
                    // currentCardColor can be played
                    if (card.getType().equals("number") && card.getColor().equals(currentCardColor)) {
                        possibleCardsToPlay.add(card);
                    }

                    // if card is draw two, reverse, skip, or wild
                    // card it can be played if it's color matches the
                    // currentCardColor
                    else if ((card.getType().equals("draw two") || card.getType().equals("reverse")
                            || card.getType().equals("skip") || card.getType().equals("wild"))
                            && card.getColor().equals(currentCardColor)) {
                        possibleCardsToPlay.add(card);
                    }

                    // shuffle card can be played above any color
                    else if (card.getType().equals("shuffle")) {
                        possibleCardsToPlay.add(card);
                    }
                }

                // after checking all cards that can be played
                // apart from WildDrawFour, if there are no cards
                // exist to be played add WildDrawFour card to
                // possibleCardsToPlay if exists
                if (possibleCardsToPlay.size() == 0) {
                    for (Card card : cardsInHand) {
                        if (card.getType().equals("wild draw four") && card.getColor().equals(currentCardColor)) {
                            possibleCardsToPlay.add(card);
                        }
                    }
                }

                playFromPossibleCardsOrDrawCard(possibleCardsToPlay);
            }

        }

    }

    /**
     * @param possibleCardsToPlay ArrayList of cards that can be played
     *                            above the card on the top of the discardPile
     */
    private void playFromPossibleCardsOrDrawCard(ArrayList<Card> possibleCardsToPlay) {
        // after accumulating all these possible cards
        // to be played, randomly play one of them if there exists any
        if (possibleCardsToPlay.size() != 0) {
            Random rng = new Random();
            int randomIndex = rng.nextInt(0, possibleCardsToPlay.size());

            Card card = possibleCardsToPlay.get(randomIndex);

            playCard(card);
            // after playing card remove it from players hand
            removePlayedCard(card);
        }

        else { // if there are no cards to be played
               // draw card from drawPile
            drawCard();
        }
    }

    /**
     * if player has no cards to play,
     * 
     * <p>
     * player should draw card from drawPile and plays
     * it immediately otherwise turn moves to next player
     * </p>
     */
    public void drawCard() {
        mediator.setAmountOfCardsToBeDrawn(1);
        // before drawing card ensure if there are
        // sufficient amount of cards available to draw from drawPile
        mediator.createNewDrawPileIfNecessary();
        mediator.setAmountOfCardsToBeDrawn(0);

        Card card = mediator.removeCardFromDrawPile();
        // if drawn card is a number card then
        // it can be played if it is matching with
        // the color of top number card or number of the top card
        Card topCard = mediator.getTopCardOfDiscardPile();
        if (card.getType().equals("number") && topCard.getType().equals("number")) {
            // card can be played if numbers
            // are matching or colors are same
            NumberCard drawnNumberCard = (NumberCard) card;
            NumberCard topNumberCard = (NumberCard) topCard;

            if ((drawnNumberCard.getNumber() == topNumberCard.getNumber())
                    || (drawnNumberCard.getColor().equals(topNumberCard.getColor()))) {
                playCard(card);
            }

            else { // if drawn number card can't be played
                   // as it's color and number doesn't match
                   // with the top number card add it to hand
                addCardToHandIfCantPlayed(card);
                return;
            }

        }

        // if drawn card is number card and top
        // card of discardPile is action card
        // specificially if it is draw two, reverse, or skip card
        else if (card.getType().equals("number") && (!topCard.getType().equals("number"))) {
            if (topCard.getType().equals("draw two") || topCard.getType().equals("reverse")
                    || topCard.getType().equals("skip")) {
                if (card.getColor().equals(topCard.getColor())) {
                    playCard(card);
                }

                else { // if the top card is number card
                    addCardToHandIfCantPlayed(card);
                    return;
                }

            }

            // if top card is Wild, WildDrawFour, or ShuffleHands card
            // number card drawn must have the same color as the
            // currentCardColor to be played
            else if (topCard.getType().equals("wild") || topCard.getType().equals("wild draw four")
                    || topCard.getType().equals("shuffle")) {
                if (card.getColor().equals(mediator.getCurrentCardColor())) {
                    playCard(card);
                }

                else { // if drawn number card can't be played
                       // as it's color does not match with the
                       // currentCardColor add it to hand
                    addCardToHandIfCantPlayed(card);
                    return;
                }

            }

            else { // if drawn number card can't be played
                   // as it's top card is not wild, wild draw four
                   // or, shuffle card add it to hand
                addCardToHandIfCantPlayed(card);
                return;
            }

        }

        // if drawn card is action card of type draw two, reverse,
        // or skip card
        else if (card.getType().equals("draw two") || card.getType().equals("reverse")
                || card.getType().equals("skip")) {
            if (topCard.getType().equals("number")) {
                if (card.getColor().equals(topCard.getColor())) {
                    playCard(card);
                }

                else { // if player can't play action card add
                       // as it's color doesn't match with the
                       // top number card add it to hand
                    addCardToHandIfCantPlayed(card);
                    return;
                }
            }

            else if (topCard.getType().equals("draw two") || topCard.getType().equals("reverse")
                    || topCard.getType().equals("skip")) {
                if (card.getType().equals(topCard.getType()) || card.getColor().equals(topCard.getColor())) {
                    playCard(card);
                }

                else { // if player can't play action card
                       // as color or type of the drawn card
                       // does not match with the top card add it to hand
                    addCardToHandIfCantPlayed(card);
                    return;
                }
            }

            else { // if the top card is Wild, WildDrawFour, or ShuffleHands card
                if (card.getColor().equals(mediator.getCurrentCardColor())) {
                    playCard(card);
                }

                else { // if player can't play action card as
                       // drawn card's color does not match with the
                       // currentCardColor add it to hand
                    addCardToHandIfCantPlayed(card);
                    return;
                }

            }

        }

        // if drawn card is action card of type Wild, WildDrawFour,
        // or ShuffleHands
        else {
            if (card.getType().equals("wild") || card.getType().equals("wild draw four")) {
                if (topCard.getType().equals("draw two") || topCard.getType().equals("reverse")
                        || topCard.getType().equals("skip") || topCard.getType().equals("number")) {
                    if (card.getColor().equals(topCard.getColor())) {
                        playCard(card);
                    }

                    else { // if player can't play action card
                        addCardToHandIfCantPlayed(card);
                        return;
                    }
                }

                else if (topCard.getType().equals("wild") || topCard.getType().equals("wild draw four")
                        || topCard.getType().equals("shuffle")) {
                    if (card.getColor().equals(mediator.getCurrentCardColor())) {
                        playCard(card);
                    }

                    else { // if player can't play action card
                        addCardToHandIfCantPlayed(card);
                        return;
                    }
                }

            }

            else { // drawn card is shuffle card
                   // shuffle hands card can be played on all colors
                playCard(card);
            }

        }

    }

    /**
     * @param card Card that is to be added to hand
     *             of player as it can not be played
     */
    private void addCardToHandIfCantPlayed(Card card) {
        cardsInHand.add(card);
        System.out.println("Draw card " + card.toString());
    }

    /**
     * @param card Card to be played, if it is
     *             NumberCard it's color is set as the currentCardColor
     * 
     *             <p>
     *             and placed to discarPile, otherwise perform the action
     *             specific to ActionCard
     *             </p>
     */
    private void playCard(Card card) {

        mediator.placeCardToDiscardPile(card);
        System.out.println(card.toString() + " played");

        if (card instanceof ActionCard) {
            ActionCard actionCard = (ActionCard) card;

            // for shuffle card, display previous color
            // first as shuffle card has no color which is
            // indicated by Color.DEFAULT enum
            if (card.getType().equals("shuffle")) {
                actionCard.performAction();
            }

            else {
                mediator.setCurrentCardColor(card.getColorEnum());
                actionCard.performAction();
            }
        }

        else {
            mediator.setCurrentCardColor(card.getColorEnum());
        }

    }

    /**
     * returns the total point of all cards in hand
     * 
     * @return <b>int</b> that represents the total
     *         point of cards in hand
     */
    public int calculateTotalCardPoint() {
        int totalCardPoint = 0;
        for (Card card : cardsInHand) {
            totalCardPoint += card.getPoint();
        }

        return totalCardPoint;
    }

    /**
     * <p>
     * calculates the score based on the other players hands
     * </p>
     * 
     * @param players ArrayList of players
     * 
     */
    public void calculateScore(ArrayList<Player> players) {
        int score = getScore();
        for (Player player : players) {
            if (!(player.getName().equals(getName()))) {
                score += player.calculateTotalCardPoint();
            }
        }

        setScore(score);
    }

    /**
     * returns String representation of the Player object
     */
    @Override
    public String toString() {
        String playerInfo = this.playerName + "\n\n" + "Cards In Hand\n";

        for (Card card : cardsInHand) {
            playerInfo += card.toString() + "\n";
        }

        return playerInfo;
    }

}