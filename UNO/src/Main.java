import Game.Player;
import Game.WorkingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfPlayers;
        do {
            System.out.println("Type the number of players playing the game between 2 to 10:");
            System.out.print("Number of players: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input from the scanner
            }
            numOfPlayers = sc.nextInt();
        } while (numOfPlayers < 2 || numOfPlayers > 10);

        List<Player> players = new ArrayList<>();
        for(int i=0;i<numOfPlayers;i++){
            int playerId = i+1;
            System.out.print("Enter the name of player "+playerId+": ");
            String playerName = sc.next();
            players.add(new Player(playerId,playerName));
        }

        WorkingGame game = new WorkingGame(players);
        game.startGame();

        while (true) {
            Player currentPlayer = game.getPlayers().get(game.getCurrentPlayerIndex());
            game.turnHandler(currentPlayer);
        }
    }
}