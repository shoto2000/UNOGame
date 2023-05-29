package Game;

import java.util.ArrayList;
import java.util.List;

public class WorkingGame {
    private List<Player> players;
    private Deck deck;
    private List<Card> discarded;
    private int currentPlayerIndex;
    private boolean direction;
    private Card currentCard;

    public WorkingGame(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.discarded = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.direction = false;
    }

    private void distributeInitialCards() {
        for (Player player : players) {
            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(deck.drawCard());
            }
            player.receiveInitialCards(initialCards);
        }
    }

    public void startGame(){
        deck.initialize();
        deck.suffle();
        distributeInitialCards();
        Card initialCard = deck.drawCard();
        discarded.add(initialCard);
        currentCard = initialCard;
        System.out.println("Game have been Started:-");
        System.out.println("Starting the game with: "+ initialCard.getColor()+" "+initialCard.getValue());
    }

    public void turnHandler(Player player){
        System.out.println(player.getName()+"'s turn");

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getDiscarded() {
        return discarded;
    }

    public void setDiscarded(List<Card> discarded) {
        this.discarded = discarded;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
