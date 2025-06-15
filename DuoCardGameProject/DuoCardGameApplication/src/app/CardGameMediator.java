package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import cards.ActionCard;
import cards.Card;
import cards.DrawTwoCard;
import cards.NumberCard;
import cards.ReverseCard;
import cards.ShuffleHandsCard;
import cards.SkipCard;
import cards.WildCard;
import cards.WildDrawFourCard;
import helpers.Color;
import helpers.FileIO;

/**
 * CardGameMediator class is responsible for managing Duo Card Game
 * and implements the mediator design pattern to reduce coupling among
 * player objects
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class CardGameMediator {

    private int currentPlayerPosition;
    private int roundNumber;
    private String gameDirection;
    private Color currentCardColor;
    private boolean shouldDrawCard;
    private boolean mustDrawCard;
    private int amountOfCardsToBeDrawn;
    private boolean isInitialRound;
    private boolean isGameFinished;
    private ArrayList<Player> players;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;

    /**
     * default constructor that initializes
     * data fields as follows
     * <ul>
     * <li>currentPlayerPosition as <b>0</b></li>
     * <li>roundNumber as <b>1</b></li>
     * <li>gameDirection as <b>"left"</b></li>
     * <li>currentCardColor as <b>null</b></li>
     * <li>shouldDrawCard as <b>false</b></li>
     * <li>mustDrawCard as <b>false</b></li>
     * <li>amountOfCardsToBeDrawn as <b>0</b></li>
     * <li>isInitialRound as <b>true</b></li>
     * <li>isGameFinished as <b>false</b></li>
     * <li>players as <b>null</b></li>
     * <li>drawPile as <b>null</b></li>
     * <li>discardPile as <b>null</b></li>
     * </ul>
     */
    public CardGameMediator() {
        this.currentPlayerPosition = 0;
        this.roundNumber = 1;
        this.gameDirection = "left";
        this.currentCardColor = null;
        this.shouldDrawCard = false;
        this.mustDrawCard = false;
        this.amountOfCardsToBeDrawn = 0;
        this.isInitialRound = true;
        this.isGameFinished = false;
        this.players = new ArrayList<>();
        this.drawPile = null;
        this.discardPile = null;
    }

    /**
     * returns the value of the currentPlayerPosition field
     * 
     * @return <b>int</b> that represents the position
     *         of the current player who is going to play
     */
    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    /**
     * returns the value of the field roundNumber.
     * 
     * @return <b>int</b> that represents the current round number
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * returns a reference to gameDirection field.
     * 
     * @return <b>String</b> that represents the game direction
     */
    public String getGameDirection() {
        return gameDirection;
    }

    /**
     * returns a reference to currentCardColor field
     * 
     * @return <b>String</b> that represents the color
     *         of the card located at the top of the draw pile
     */
    public String getCurrentCardColor() {
        return currentCardColor.getColor();
    }

    /**
     * returns the value of the shouldDrawCard field
     * 
     * @return <b>boolean</b> that indicates whether
     *         next player has to draw card from draw pile
     */
    public boolean getShouldDrawCard() {
        return shouldDrawCard;
    }

    /**
     * returns the value of the mustDrawCard field
     * 
     * @return <b>boolean</b> that indicates whether
     *         next player must draw card from draw pile
     */
    public boolean getMustDrawCard() {
        return mustDrawCard;
    }

    /**
     * returns the value of amountOfCardsToBeDrawn field
     * 
     * @return <b>int</b> amount of cards that must
     *         be drawn by next player
     * 
     */
    public int getAmountOfCardsToBeDrawn() {
        return amountOfCardsToBeDrawn;
    }

    /**
     * returns the value of isInitialRound field
     * 
     * @return boolean value which is true if
     *         current round is the initial round, false
     *         otherwise
     */
    public boolean getIsInitialRound() {
        return isInitialRound;
    }

    /**
     * returns the value of isGameFinished field
     * 
     * @return boolean value which is true if
     *         game is finished, false otherwise
     */
    public boolean getIsGameFinished() {
        return isGameFinished;
    }

    /**
     * returns a reference to Player object who is
     * currently playing
     * 
     * @return <b>Player</b> reference to player
     *         who is currently playing
     */
    public Player getCurrentlyPlayingPlayer() {
        return players.get(currentPlayerPosition);
    }

    /**
     * returns ArrayList of players for current round.
     * 
     * @return <b>ArrayList</b> which contains
     *         all players in the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * returns the position of the dealer of the current round.
     * 
     * @return <b>int</b> that represents the position
     *         of the dealer
     */
    public int getDealerPosition() {
        int dealerPosition = 0;

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerType().equals("dealer")) {
                dealerPosition = i;
            }
        }

        return dealerPosition;
    }

    /**
     * returns the top card of discardPile above
     * which player has to play card
     * 
     * @return Card object which is a reference
     *         to top card of the discardPile
     */
    public Card getTopCardOfDiscardPile() {
        return discardPile.getLast();
    }

    /**
     * sets the shouldDrawCondition to given boolean.
     * if it is true, player should draw card.
     * if it is false, player doesn't draw card.
     * 
     * @param shouldDrawCondition condition either true or false
     *                            to indicate whether player has to draw card from
     *                            draw pile
     *                            or not which is imposed by previous player's play
     *                            of Draw Two
     *                            or Wild Draw Four card
     */
    public void setShouldDrawCard(boolean shouldDrawCondition) {
        this.shouldDrawCard = shouldDrawCondition;
    }

    /**
     * sets the mustDrawCard field to given boolean.
     * if it is true, player must draw card.
     * if it is false, player does not draw card.
     * 
     * @param mustDrawCard boolean value that indicates
     *                     whether next player must draw card from draw pile
     */
    public void setMustDrawCard(boolean mustDrawCard) {
        this.mustDrawCard = mustDrawCard;
    }

    /**
     * sets the gameDirection to given direction.
     * 
     * @param gameDirection game direction to be set
     *                      which is either "left" or "right"
     */
    public void setGameDirection(String gameDirection) {
        this.gameDirection = gameDirection;
    }

    /**
     * sets the currentCardColor to given Color enum
     * which represents the most recent color of the game
     * 
     * @param currentCardColor color of the top card
     *                         in the discardPile to be set
     */
    public void setCurrentCardColor(Color currentCardColor) {
        this.currentCardColor = currentCardColor;
    }

    /**
     * sets the amountOfCardsToBeDrawn to given integer
     * which indicates amount of cards to be drawn
     * 
     * @param amountOfCardsToBeDrawn int value indicates
     *                               the number of carts that must be drawn by next
     *                               player
     */
    public void setAmountOfCardsToBeDrawn(int amountOfCardsToBeDrawn) {
        this.amountOfCardsToBeDrawn = amountOfCardsToBeDrawn;
    }

    /**
     * sets the isInitialRound to given boolean.
     * if it is true, it indicates that current round is initial round.
     * if it is false, it indicates that current round is not initial round .
     * 
     * @param isInitialRound boolean value to indicate
     *                       whether current round is the initial round
     */
    public void setInitialRound(boolean isInitialRound) {
        this.isInitialRound = isInitialRound;
    }

    /**
     * sets the isGameFinished to given boolean.
     * if it is true, it indicates that game has finished.
     * if it is false, it indicates that game has not yet finished
     * 
     * @param isGameFinished boolean that represents
     *                       whether game is finished or not
     */
    public void setIsGameFinished(boolean isGameFinished) {
        this.isGameFinished = isGameFinished;
    }

    /**
     * set the draw pile as given draw pile
     * 
     * @param drawPile new draw pile to be set
     */
    public void setDrawPile(ArrayList<Card> drawPile) {
        this.drawPile = drawPile;
    }

    /**
     * sets the discard pile as given discard pile
     * 
     * @param discardPile new discard pile to be set
     */
    public void setDiscardPile(ArrayList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    /**
     * set the position for current player to given position
     * 
     * @param position int value that sets the
     *                 position of the current player
     */
    public void setCurrentPlayerPosition(int position) {
        this.currentPlayerPosition = position;
    }

    /**
     * add the played card to discard pile as a top card
     * 
     * @param playedCard Card object played by player
     *                   and added to the top of the discardPile
     */
    public void placeCardToDiscardPile(Card playedCard) {
        discardPile.add(playedCard);
    }

    /**
     * runs the simulation for playing "Duo Card Game"
     */
    public void simulateDuoCardGame() {
        initGame();
        FileIO csvManipulator = new FileIO();
        // write the header to game_status.csv file
        csvManipulator.createCSVFileAndHeader(players);
        while (!isGameFinished) {
            initRound();
            // read scores from game_status.csv file all rounds apart from round 1
            if (roundNumber != 1) {
                csvManipulator.readScoresFromCSVFile(players, roundNumber - 2);
            }

            System.out.println("\nRound " + roundNumber + " started\n");
            playRound(csvManipulator);
        }

        // write the winner of the game to CSV file
        // and display the winner and player's score
        Player winner = getGameWinner();
        csvManipulator.writeWinnerInformation(winner);
        System.out.println("\n" + winner.getName() + " won the game");
        System.out.println("Score: " + winner.getScore());
    }

    /**
     * initialize game by determining number
     * of players and creating player objects
     */
    public void initGame() {
        int numberOfPlayers = determineNumberOfPlayers();
        createPlayers(numberOfPlayers);
    }

    /**
     * called at the beginning of each round to initialize the round
     */
    public void initRound() {
        ArrayList<Card> deck = createDeck();
        determineDealer(deck);

        // dealer deals cards
        Player dealer = getDealer();
        // set currentPlayerPosition to dealers position
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerType() == "dealer") {
                setCurrentPlayerPosition(i);
                break;
            }
        }

        dealer.dealCardsToStartGame(deck);
        // set the current card color to color
        // of the top card of discardPile at the start of the game
        setCurrentCardColor(discardPile.getLast().getColorEnum());

        // if theres is an action card at the top
        // of the discard pile, perform card's action
        if (!(discardPile.getLast().getType().equals("number"))) {
            ActionCard actionCard = (ActionCard) discardPile.getLast();
            actionCard.performAction();
        }

        // at the end of the initGame method
        // currentPlayerPosition is same as the dealer position
        setInitialRound(false);

        System.out.println("Dealer is: " + getDealer().getName() + "\n");
        for (Player player : players) {
            System.out.println(player.toString());
        }

        System.out.println("Starting card of Discard Pile is: " + discardPile.getLast().toString() + "\n");
    }

    /**
     * <p>
     * round is played until one player
     * plays his/her last card
     * </p>
     * 
     * @param csvManipulator FileIO object that is used to
     *                       record end of round information to CSV file
     * 
     */
    public void playRound(FileIO csvManipulator) {
        while (!isRoundFinished() || getShouldDrawCard() || getMustDrawCard()) {
            // TO-DO
            playForCurrentPlayer();
        }

        if (checkIsGameFinished()) {
            setIsGameFinished(true);
        }

        else { // if game is not finished
               // reset score for each player, set their type to "normal"
               // and clear out their hands, also reset the fields of CardGameMediator

            // before resetting players and mediator
            // write the player scores to CSV file preceded by the
            // Round information

            Player roundWinner = getRoundWinner();
            roundWinner.calculateScore(players);
            csvManipulator.writeRoundInformation(players, roundNumber);
            resetPlayers();
            reset();
            System.out.println("\nRound " + roundNumber + " ended\n");
            incrementRoundNumber();
        }

    }

    /**
     * player whose turn it is plays the game
     */
    public void playForCurrentPlayer() {
        // get next player to play
        Player player = getNextPlayer();
        System.out.println("\nGame direction is: " + getGameDirection());
        System.out.println(player.getName() + "\n");
        // check whether player must draw card
        if (getMustDrawCard()) {
            // if previous player played WildDrawFour card
            // current player has to draw four cards
            createNewDrawPileIfNecessary();

            player.drawCards();
            setMustDrawCard(false);
            setShouldDrawCard(false);
            setAmountOfCardsToBeDrawn(0);
        }

        else if (getShouldDrawCard()) {
            ActionCard drawTwoCard = player.checkForDrawTwoCardInHand();

            if (drawTwoCard != null) {
                // player has drawTwoCard in
                // his hand so player doesn't
                // have to draw instead plays that drawTwoCard
                drawTwoCard.performAction();
                placeCardToDiscardPile(drawTwoCard);
                setCurrentCardColor(drawTwoCard.getColorEnum());
            }

            else { // if player doesn't have drawTwoCard
                createNewDrawPileIfNecessary();
                player.drawCards();
                setShouldDrawCard(false);
                setAmountOfCardsToBeDrawn(0);
            }

        }

        else { // logic for playing card
               // if current player is not required to draw card

            // logic for discardPile top card being number card
            if (discardPile.getLast().getType().equals("number")) {
                player.playCard("number", discardPile.getLast().getColor());
            }

            // logic for discardPile top card being action card
            else {
                player.playCard("action", discardPile.getLast().getColor());
            }

        }

    }

    /**
     * if amount of cards to be drawn is greater
     * than or equal to drawPile size then create
     * new drawPile from existing discardPile
     *
     */
    public void createNewDrawPileIfNecessary() {
        if (amountOfCardsToBeDrawn >= drawPile.size()) {
            System.out.println("Cards in draw are depleted !!");
            System.out.println("Creating new draw pile from discard pile");
            formNewDrawPile();
        }
    }

    /**
     * <p>
     * deal 7 card to each player at the start of the game
     * </p>
     * 
     * @param deck ArrayList of Cards to be dealed among players
     * 
     */
    public void dealCardsToStartGame(ArrayList<Card> deck) {
        // shuffle the deck before dealing
        Collections.shuffle(deck);
        int numberOfPlayers = players.size();
        int numberOfCardsToBeDealt = 7 * numberOfPlayers;

        distributeCardsToEachPlayer(deck, numberOfCardsToBeDealt);

        // create and set drawPile
        ArrayList<Card> drawPileCards = new ArrayList<>();
        for (Card card : deck) {
            drawPileCards.add((Card) card.clone());
        }

        setDrawPile(drawPileCards);
        // discard the top entry from draw pile
        Card discardPileTopCard = drawPile.removeLast();
        while (discardPileTopCard.getType().equals("wild draw four")) {
            drawPile.addFirst(discardPileTopCard);
            discardPileTopCard = drawPile.removeLast();
        }

        ArrayList<Card> discardPileCards = new ArrayList<>();
        setDiscardPile(discardPileCards);
        discardPile.add(discardPileTopCard);
    }

    /**
     * deal cards in pile to all players in direction
     * indicated by the dealingDirection
     * 
     * @param pile             pile of cards to be dealt among players
     * @param dealingDirection direction in which cards
     *                         are to be dealt
     */
    public void dealCards(ArrayList<Card> pile, String dealingDirection) {

        // before dealing, temporarily set the
        // dealing direction to given dealingDirection
        String actualGameDirection = getGameDirection();
        setGameDirection(dealingDirection);

        // start dealing with the next player
        int numberOfCardsToBeDealt = pile.size();
        distributeCardsToEachPlayer(pile, numberOfCardsToBeDealt);

        // set the gameDirection to actual game direction again
        setGameDirection(actualGameDirection);
    }

    /**
     * <p>
     * removes and returns the top card of the drawPile
     * </p>
     * 
     * @return Card object which is the
     *         at the top of the drawPile
     */
    public Card removeCardFromDrawPile() {
        return drawPile.removeLast();
    }

    /**
     * @return <b>boolean</b> that is true if round has finished,
     *         otherwise false
     */
    private boolean isRoundFinished() {
        boolean isFinished = false;

        for (Player player : players) {
            if (player.getCardsInHand().size() == 0) {
                isFinished = true;
            }
        }

        return isFinished;
    }

    /**
     * method to check if game has finished
     * 
     * @return <b>boolean</b> value that is true if
     *         game is finished, false otherwise
     */
    private boolean checkIsGameFinished() {
        // if one player has reached to 500 score
        // points that is the winner and game has finished
        for (Player player : players) {
            if (player.getScore() >= 500) {
                return true;
            }
        }

        return false;
    }

    /**
     * reset each player by calling reset method of
     * player object
     * 
     * @see app.Player#reset() reset
     */
    private void resetPlayers() {
        for (Player player : players) {
            player.reset();
        }
    }

    /**
     * advance to next round
     */
    private void incrementRoundNumber() {
        this.roundNumber++;
    }

    /**
     * reset fields apart from players before
     * starting next round
     */
    private void reset() {
        setCurrentPlayerPosition(0);
        setGameDirection("left");
        setCurrentCardColor(null);
        setShouldDrawCard(false);
        setMustDrawCard(false);
        setAmountOfCardsToBeDrawn(0);
        setInitialRound(true);
        setDrawPile(null);
        setDiscardPile(null);
    }

    /**
     * @return <b>Player</b> the winner of the round
     */
    private Player getRoundWinner() {
        Player roundWinner = null;
        for (Player player : players) {
            if (player.getCardsInHand().size() == 0) {
                roundWinner = player;
            }
        }

        return roundWinner;
    }

    /**
     * @return Player the winner of the game
     */
    private Player getGameWinner() {
        Player gameWinner = null;
        for (Player player : players) {
            if (player.getScore() >= 500) {
                gameWinner = player;
            }
        }

        return gameWinner;
    }

    /**
     * @param pile                   pile of cards to be dealt among players
     * @param numberOfCardsToBeDealt number of cards to be dealt
     */
    private void distributeCardsToEachPlayer(ArrayList<Card> pile, int numberOfCardsToBeDealt) {
        int numberOfCardsDealt = 0;

        while (numberOfCardsDealt < numberOfCardsToBeDealt) {
            Player player = getNextPlayer();
            Card dealtCard = pile.removeLast();
            player.takeCardFromDistributer(dealtCard);
            numberOfCardsDealt++;
        }
    }

    /**
     * randomly generates number of players in game
     * 
     * @return <b>int</b> that represents the number of players in game
     */
    private int determineNumberOfPlayers() {
        // creating an instance of Random class
        Random rng = new Random();
        // generating random number in range 2-4 (both inclusive)
        int numOfPlayers = rng.nextInt(2, 5);
        // number of players can be 2, 3, or 4
        return numOfPlayers;
    }

    /**
     * creates the Player objects to be stored in players
     * 
     * @param numberOfPlayers int number of players to be created
     */
    private void createPlayers(int numberOfPlayers) {
        String playerNamePrefix = "Player ";

        for (int i = 1; i <= numberOfPlayers; i++) {
            String playerName = playerNamePrefix + i;
            // until the dealer is determined all players types
            // are set to "normal"
            Player player = new Player(playerName, "normal");
            // add each player to ArrayList<Card> players
            player.setGameMediator(this);
            players.add(player);
        }

    }

    /**
     * determines dealer according to highest card number
     * drawn by players. The player with the highest card
     * number is assigned as the dealer
     * 
     * @param deck ArrayList<Card> containing 109 cards
     */
    private void determineDealer(ArrayList<Card> deck) {
        // for each player, draw a NumberCard
        // (continiously and randomly draw until a NumberCard is drawn)
        int[] cardNumbers = new int[players.size()];
        Random rng = new Random();
        for (int i = 0; i < players.size(); i++) {
            int randomIndex = rng.nextInt(0, deck.size());

            Card anyCard = deck.get(randomIndex);
            deck.remove(randomIndex);

            while (anyCard.getType() != "number") {
                deck.add(anyCard);
                randomIndex = rng.nextInt(0, deck.size());
                anyCard = deck.get(randomIndex);
                deck.remove(randomIndex);
            }

            deck.add(anyCard); // removed card added back to deck

            // at the end of the while loop it
            // is ensured that player has drawn
            // NumberCard
            NumberCard numberCard = (NumberCard) anyCard;
            cardNumbers[i] = numberCard.getNumber();
        }

        // choose dealer according to card numbers
        checkForDue(cardNumbers);
    }

    /**
     * check for due in terms of highest card number. If
     * there is a due, randomly choose the dealer among those
     * highest numbered card holders
     * 
     * @param cardNumbers array containing card
     *                    numbers drawn for determining dealer
     */
    private void checkForDue(int[] cardNumbers) {

        boolean[] highestValues = new boolean[cardNumbers.length];
        // we use boolean array to check if
        // more than one player has drawn same
        // highest value
        int highestNumberIndex = 0;
        int highestNumber = cardNumbers[0];

        // determine the highest value in cardNumbers
        for (int i = 1; i < cardNumbers.length; i++) {
            if (cardNumbers[i] > highestNumber) {
                highestNumber = cardNumbers[i];
                highestNumberIndex = i;
            }
        }

        highestValues[highestNumberIndex] = true;

        // check for same highest values in
        // other indexes and set corresponding
        // entries in highestValues array to true
        for (int i = 0; i < cardNumbers.length; i++) {
            if (highestNumber == cardNumbers[i] && i != highestNumberIndex) {
                highestValues[i] = true;
            }
        }

        ArrayList<Integer> highestPlayerIndexes = new ArrayList<>();
        for (int i = 0; i < highestValues.length; i++) {
            if (highestValues[i]) {
                highestPlayerIndexes.add(i);
            }
        }

        Player player;
        // set the player's type field to "dealer"
        if (highestPlayerIndexes.size() == 1) {
            player = players.get(highestPlayerIndexes.get(0));
        }

        else {
            // if more than one highest valued
            // player exists, chose that randomly
            Random rng = new Random();
            int randomIndex = rng.nextInt(0, highestPlayerIndexes.size());
            player = players.get(randomIndex);
        }

        player.setPlayerType("dealer");
    }

    /**
     * @return <b>Player</b> which is the dealer for current round
     */
    private Player getDealer() {
        Player dealer = null;
        // locate "dealer" in players
        for (Player player : players) {
            if (player.getPlayerType() == "dealer") {
                dealer = player;
            }
        }

        return dealer;
    }

    /**
     * form a new draw pile from discard pile
     * if drawPile run out of cards
     */
    private void formNewDrawPile() {
        // shuffle discard pile
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);

        // remove transferred cards from discard pile
        discardPile.clear();
        // take the top card from drawPile
        // and add it to discardPile
        Card topCardDrawnFromDrawPile = drawPile.removeLast();
        discardPile.add(topCardDrawnFromDrawPile);
        System.out.println(
                "Top card of the discard pile after forming new draw pile is: " + topCardDrawnFromDrawPile.toString());
    }

    /**
     * returns a reference to player who will play next round.
     * 
     * @return <b>Player</b> who will play next
     */
    public Player getNextPlayer() {
        // there are two edge cases for
        // getting the next player
        if (gameDirection.equals("right") && currentPlayerPosition == (players.size() - 1)) {
            currentPlayerPosition = 0;
        }

        else if (gameDirection.equals("left") && (currentPlayerPosition == 0)) {
            currentPlayerPosition = players.size() - 1;
        }

        else { // non-edge cases
            if (gameDirection.equals("left")) {
                currentPlayerPosition--;
            }

            else { // gameDirection.equals("right") case
                currentPlayerPosition++;
            }

        }

        return players.get(currentPlayerPosition);
    }

    /**
     * creates 109 card deck according the number of
     * colors specified in the requirements document
     * <b>CENG431-Spring2025_HW01.pdf</b>
     * 
     * @return <b>ArrayList<Card></b> that contains the 109 cards
     *         comprising initial deck
     */
    private ArrayList<Card> createDeck() {
        // create deck
        ArrayList<Card> deck = new ArrayList<>();
        // creating 4 zero number card, one for each color
        NumberCard blue0 = new NumberCard("number", Color.BLUE, 0, this);
        NumberCard green0 = new NumberCard("number", Color.GREEN, 0, this);
        NumberCard red0 = new NumberCard("number", Color.RED, 0, this);
        NumberCard yellow0 = new NumberCard("number", Color.YELLOW, 0, this);

        // adding each zero card to deck
        deck.add(blue0);
        deck.add(green0);
        deck.add(red0);
        deck.add(yellow0);

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                // creating two of each other number (1-9)
                NumberCard blue = new NumberCard("number", Color.BLUE, i, this);
                NumberCard green = new NumberCard("number", Color.GREEN, i, this);
                NumberCard red = new NumberCard("number", Color.RED, i, this);
                NumberCard yellow = new NumberCard("number", Color.YELLOW, i, this);

                // add each card to deck
                deck.add(blue);
                deck.add(green);
                deck.add(red);
                deck.add(yellow);
            }
        }

        // adding 8 cards for Draw Two, Reverse and Skip
        // Action cards 2 for each color
        for (int i = 0; i < 2; i++) {

            DrawTwoCard drawTwoCardBlue = new DrawTwoCard("draw two", Color.BLUE, this);
            DrawTwoCard drawTwoCardGreen = new DrawTwoCard("draw two", Color.GREEN, this);
            DrawTwoCard drawTwoCardRed = new DrawTwoCard("draw two", Color.RED, this);
            DrawTwoCard drawTwoCardYellow = new DrawTwoCard("draw two", Color.YELLOW, this);

            ReverseCard reverseBlue = new ReverseCard("reverse", Color.BLUE, this);
            ReverseCard reverseGreen = new ReverseCard("reverse", Color.GREEN, this);
            ReverseCard reverseRed = new ReverseCard("reverse", Color.RED, this);
            ReverseCard reverseYellow = new ReverseCard("reverse", Color.YELLOW, this);

            SkipCard skipBlue = new SkipCard("skip", Color.BLUE, this);
            SkipCard skipGreen = new SkipCard("skip", Color.GREEN, this);
            SkipCard skipRed = new SkipCard("skip", Color.RED, this);
            SkipCard skipYellow = new SkipCard("skip", Color.YELLOW, this);

            deck.add(drawTwoCardBlue);
            deck.add(drawTwoCardGreen);
            deck.add(drawTwoCardRed);
            deck.add(drawTwoCardYellow);

            deck.add(reverseBlue);
            deck.add(reverseGreen);
            deck.add(reverseRed);
            deck.add(reverseYellow);

            deck.add(skipBlue);
            deck.add(skipGreen);
            deck.add(skipRed);
            deck.add(skipYellow);
        }

        // adding 4 cards for Wild and Wild Draw Four
        // Action cards 1 for each color
        WildCard wildBlue = new WildCard("wild", Color.BLUE, this);
        WildCard wildGreen = new WildCard("wild", Color.GREEN, this);
        WildCard wildRed = new WildCard("wild", Color.RED, this);
        WildCard wildYellow = new WildCard("wild", Color.YELLOW, this);

        WildDrawFourCard wildDrawFourBlue = new WildDrawFourCard("wild draw four", Color.BLUE, this);
        WildDrawFourCard wildDrawFourGreen = new WildDrawFourCard("wild draw four", Color.GREEN, this);
        WildDrawFourCard wildDrawFourRed = new WildDrawFourCard("wild draw four", Color.RED, this);
        WildDrawFourCard wildDrawFourYellow = new WildDrawFourCard("wild draw four", Color.YELLOW, this);

        deck.add(wildBlue);
        deck.add(wildGreen);
        deck.add(wildRed);
        deck.add(wildYellow);

        deck.add(wildDrawFourBlue);
        deck.add(wildDrawFourGreen);
        deck.add(wildDrawFourRed);
        deck.add(wildDrawFourYellow);

        // adding 1 card for ShuffleHands
        ShuffleHandsCard shuffleCard = new ShuffleHandsCard("shuffle", Color.DEFAULT, this);
        deck.add(shuffleCard);

        return deck;
    }

}