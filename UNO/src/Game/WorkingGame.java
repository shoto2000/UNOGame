package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkingGame {
    private List<Player> players;
    private Deck deck;
    private List<Card> discarded;
    private int currentPlayerIndex;
    private int direction;
    private Card currentCard;

    public WorkingGame(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.discarded = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.direction = 1;
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
        deck.shuffle();
        distributeInitialCards();
        Card initialCard = deck.drawCard();
        discarded.add(initialCard);
        currentCard = initialCard;
        System.out.println("Game have been Started:-");
        System.out.println("Starting the game with: "+ initialCard);
    }

    private boolean isValidCardPlay(Card card) {
        return card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue()
                || card.getColor()==Color.WILD || card.getColor()==Color.WILD4;
    }

    private boolean isValidCardPlayWild4(Card card) {
        return card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue()
                || card.getColor()==Color.WILD;
    }

    public void turnHandler(Player player) {
        System.out.println(player.getName() + player.getId()+"'s turn");
        System.out.println("\n* * * * * * * * * * * * * * * * * * * *");
        if(currentCard.getValue()==-2){
            System.out.println("Current Card: " + currentCard.getColor());
        }
        else{
            System.out.println("Current Card: " + currentCard);
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * *\n");
        System.out.println(player.getName() + player.getId()+"'s Hand:");
        System.out.println("* * * * * * * * * * * * * * * * * * * *");

        for (int i = 0; i < player.getInHand().size(); i++) {
            String printing = "index " + i + " = " + player.getInHand().get(i);
            String formattedText = String.format("%-" + 36 + "s", printing);
            System.out.println("* " + formattedText + "*");
        }

        System.out.println("* * * * * * * * * * * * * * * * * * * *\n");

        if (!player.hasValidCardToPlay(currentCard)) {
            System.out.println("Not a valid card to play, Drawing a Card");
            player.drawCard(deck);

            System.out.println("\nAfter Drawing Card " + player.getName() + player.getId()+ "'s Hand:");
            System.out.println("* * * * * * * * * * * * * * * * * * * *");

            for (int i = 0; i < player.getInHand().size(); i++) {
                String printing = "index " + i + " = " + player.getInHand().get(i);
                String formattedText = String.format("%-" + 36 + "s", printing);
                System.out.println("* " + formattedText + "*");
            }

            System.out.println("* * * * * * * * * * * * * * * * * * * *\n");

            if (!player.hasValidCardToPlay(currentCard)) {
                System.out.println("Still no valid card to play, turn skipped");
                nextPlayer();
                return;
            }
        }

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Enter the index of the card to play between {0-" + (player.getInHand().size() - 1) + "}" + " or -1 to draw a card");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();

                if (choice == -1) {
                    player.drawCard(deck);
                    return;
                }

                if (choice >= 0 && choice < player.getInHand().size()) {
                    Card selectedCard = player.getInHand().get(choice);

                    if (selectedCard.getColor() == Color.WILD4) {
                        boolean hasValidCardToPlay = false;

                        for (Card card : player.getInHand()) {
                            if (isValidCardPlayWild4(card)) {
                                hasValidCardToPlay = true;
                                break;
                            }
                        }

                        if (hasValidCardToPlay) {
                            System.out.println("You cannot use Wild +4 if you have another valid card to play. Try again.");
                            continue;
                        }
                    }

                    if (isValidCardPlay(selectedCard)) {
                        player.playCard(selectedCard, this);

                        if (selectedCard.isSpecialActionCard()) {
                            player.applySpecialCardEffect(selectedCard, this);
                        }

                        if (player.getInHand().isEmpty()) {
                            System.out.println(player.getName() + " wins the game");
                            System.exit(0);
                        }

                        nextPlayer();
                        return;
                    } else {
                        System.out.println("Invalid card selection. Try again.");
                    }
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                String invalidInput = sc.next();
                System.out.println("Invalid input: " + invalidInput + ". Try again.");
            }
        }
    }



    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction) % players.size();
        if (currentPlayerIndex < 0) {
            currentPlayerIndex += players.size();
        }
    }

    public void skipNextPlayer() {
        nextPlayer();
        System.out.println("Next player's turn skipped.");
    }

    public void reverseDirection() {
        direction *= -1;
        System.out.println("Direction of play reversed.");
    }

    public int getNextPlayerIndex() {
        int nextPlayerIndex = (currentPlayerIndex + direction) % players.size();
        if (nextPlayerIndex < 0) {
            nextPlayerIndex += players.size();
        }
        return nextPlayerIndex;
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

    public int isDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
