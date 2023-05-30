package Game;

import Game.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(name + " have drawn a card: ");
    }

    public void playCard(Card card, WorkingGame game) {
        inHand.remove(card);
        List<Card> discarded = game.getDiscarded();
        discarded.add(card);
        game.setDiscarded(discarded);
        game.setCurrentCard(card);
        System.out.println(name + " played: " + card);
    }

    public boolean hasValidCardToPlay(Card currentCard) {
        for (Card card : inHand) {
            if (card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue() ||
                    card.getColor()==Color.WILD || card.getColor()==Color.WILD4) {
                return true;
            }
        }
        return false;
    }

    public boolean hasValidCardToPlayForWild4(Card currentCard) {
        for (Card card : inHand) {
            if (card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue() ||
                    card.getColor()==Color.WILD) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidColor(String color, Player currentPlayer) {
        String uppercaseColor = color.toUpperCase();

        if (uppercaseColor.equals("RED") ||
                uppercaseColor.equals("BLUE") ||
                uppercaseColor.equals("YELLOW") ||
                uppercaseColor.equals("GREEN")) {

            for (Card card : currentPlayer.getInHand()) {
                if (card.getColor().toString().equals(uppercaseColor)) {
                    return true;
                }
            }
        }

        return false; // Invalid color or player doesn't have a card of that color
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
        else if (card.getColor() == Color.WILD) {
            System.out.println("You played a Wild card. Type a color from these: RED, YELLOW, BLUE, GREEN");
            Scanner sc = new Scanner(System.in);
            String chosenColor;
            boolean isValidColor = false;

            do {
                chosenColor = sc.nextLine();
                isValidColor = isValidColor(chosenColor,game.getPlayers().get(game.getCurrentPlayerIndex()));
                if (!isValidColor) {
                    System.out.println("Invalid color choice. Choose a valid color:");
                }
            } while (!isValidColor);

            chosenColor = chosenColor.toUpperCase();

            if(chosenColor.equals("RED")){
                game.setCurrentCard(new Card(Color.RED,-2));
            }
            else if(chosenColor.equals("YELLOW")){
                game.setCurrentCard(new Card(Color.YELLOW,-2));
            }
            else if(chosenColor.equals("BLUE")){
                game.setCurrentCard(new Card(Color.BLUE,-2));
            }
            else if(chosenColor.equals("GREEN")){
                game.setCurrentCard(new Card(Color.GREEN,-2));
            }

        }
        else if (card.getColor() == Color.WILD4) {
            System.out.println("You played a Wild card +4. Type a color from these: RED, YELLOW, BLUE, GREEN");
            Scanner sc = new Scanner(System.in);
            String chosenColor;
            boolean isValidColor = false;

            do {
                chosenColor = sc.nextLine();
                isValidColor = isValidColor(chosenColor,game.getPlayers().get(game.getCurrentPlayerIndex()));
                if (!isValidColor) {
                    System.out.println("Invalid color choice. Choose a valid color:");
                }
            } while (!isValidColor);

            chosenColor = chosenColor.toUpperCase();

            if(chosenColor.equals("RED")){
                game.setCurrentCard(new Card(Color.RED,-2));
            }
            else if(chosenColor.equals("YELLOW")){
                game.setCurrentCard(new Card(Color.YELLOW,-2));
            }
            else if(chosenColor.equals("BLUE")){
                game.setCurrentCard(new Card(Color.BLUE,-2));
            }
            else if(chosenColor.equals("GREEN")){
                game.setCurrentCard(new Card(Color.GREEN,-2));
            }

            int nextPlayerIndex = game.getNextPlayerIndex();
            Player nextPlayer = game.getPlayers().get(nextPlayerIndex);
            for (int i = 0; i < 4; i++) {
                nextPlayer.drawCard(game.getDeck());
            }
            System.out.println(nextPlayer.getName() + " drew 4 cards from the deck and skipped their turn");
            game.skipNextPlayer();
        }

    }
}
