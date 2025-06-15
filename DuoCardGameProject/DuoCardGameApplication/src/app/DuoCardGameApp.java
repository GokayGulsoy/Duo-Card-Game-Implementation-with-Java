package app;

/**
 * DuoCardGameApp class acts as the driver class
 * to run the application it creates an instance
 * of CardGameMediator object to run the simulation
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class DuoCardGameApp {
    /**
     * creates an instance 
     * 
     */
    public static void main(String[] args) {
        // run the simulation for "Duo-Card Game"
        CardGameMediator mediator = new CardGameMediator();
        mediator.simulateDuoCardGame();
    }
}
