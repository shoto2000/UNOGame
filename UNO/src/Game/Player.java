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

    
}
