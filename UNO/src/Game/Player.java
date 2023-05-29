package Game;

import Game.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private List<Card> inHand;

    public Player(){

    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.inHand = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getInHand() {
        return inHand;
    }

    public void setInHand(List<Card> inHand) {
        this.inHand = inHand;
    }

    public void receiveInitialCards(List<Card> cards) {
        inHand.addAll(cards);
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        inHand.add(card);
        System.out.println(name + " have drawn a card: " + card.getColor() + " " + card.getValue());
    }

    public void playCard(Card card, WorkingGame game) {
        inHand.remove(card);
        game.setCurrentCard(card);
        System.out.println(name + " played: " + card.getColor() + " " + card.getValue());
    }

    public boolean hasValidCardToPlay(Card currentCard) {
        for (Card card : inHand) {
            if (card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue()) {
                return true;
            }
        }
        return false;
    }

    public void applySpecialCardEffect(Card card, WorkingGame game){
        if(card.getValue()==10){
            System.out.println("Reversing the direction of play.");
            game.reverseDirection();
        }
        else if(card.getValue()==11){
            System.out.println("Next player turn have been skipped");
            game.skipNextPlayer();
        }
        else if(card.getValue()==12){
            int nextPlayerIndex = game.getNextPlayerIndex();
            System.out.println("check-------------- " + nextPlayerIndex);
            Player nextPlayer = game.getPlayers().get(nextPlayerIndex);
            nextPlayer.drawCard(game.getDeck());
            nextPlayer.drawCard(game.getDeck());

            System.out.println(nextPlayer.getName()+" drew 2 cards from deck and skipped their turn");
            game.skipNextPlayer();
        }
    }
}
