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
            if(color==Color.WILD || color ==Color.WILD4) continue;
            for(int i=0;i<=12;i++){
                cards.add(new Card(color,i));
                if(i!=0) cards.add(new Card(color,i));
            }
        }

        for(int i=0;i<4;i++){
            cards.add(new Card(Color.WILD,-1));
            cards.add(new Card(Color.WILD4,-1));
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void replenish(){
        initialize();
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
