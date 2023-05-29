package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;


    public Deck() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void initialize(){
        for(Color color:Color.values()){
            for(int i=0;i<=12;i++){
                cards.add(new Card(color,i));
                if(i!=0) cards.add(new Card(color,i));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void replenish(){
        int discardPileSize = cards.size();
        for (int i = discardPileSize - 1; i >= 0; i--) {
            Card discardedCard = cards.remove(i);
            cards.add(discardedCard);
        }
        shuffle();
    }

    public Card drawCard(){
        if(cards.isEmpty()){
            System.out.println("there is no card in Deck, So Replenishing");
            replenish();
        }
        return cards.remove(cards.size()-1);
    }
}
