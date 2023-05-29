package Game;

import Game.Card;

import java.util.List;

public class Player {
    private int id;
    private String name;
    private List<Card> inHand;

    public Player(){

    }

    public Player(int id, String name, List<Card> inHand) {
        this.id = id;
        this.name = name;
        this.inHand = inHand;
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
}
