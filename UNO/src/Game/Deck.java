package Game;

import Game.Card;

import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){

    }

    public Deck(List<Card> cards) {
        this.cards = cards;
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
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(null, -1));
            cards.add(new Card(null, -2));
        }
    }

    public void suffle(){
        Collections.shuffle(cards);
    }

//    public void replenish(){
//
//    }
//
    public Card drawCard(){
        if(cards.isEmpty()){

        }
        return cards.remove(cards.size()-1);
    }
}
