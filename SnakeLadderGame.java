import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeLadderGame {
    static final int WIN_POSITION = 100;
    static Map<Integer, Integer> snakes = new HashMap<>();
    static Map<Integer, Integer> ladders = new HashMap<>();

    static {
        // Define snakes (Head -> Tail)
        snakes.put(98, 40);
        snakes.put(95, 60);
        snakes.put(92, 20);
        snakes.put(83, 19);
        snakes.put(73, 53);
        snakes.put(69, 33);
        snakes.put(64, 36);
        snakes.put(59, 17);
        snakes.put(55, 7);
        snakes.put(52, 11);
        snakes.put(48, 26);
        snakes.put(46, 5);

        // Define ladders (Bottom -> Top)
        ladders.put(2, 38);
        ladders.put(7, 14);
        ladders.put(8, 31);
        ladders.put(15, 26);
        ladders.put(21, 82);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(78, 98);
        ladders.put(87, 94);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random dice = new Random();
        int player1 = 0, player2 = 0;
        boolean turn = true; // True -> Player1, False -> Player2

        System.out.println("Welcome to Snake and Ladder Game!");
        System.out.println("First player to reach 100 wins!\n");

        while (player1 < WIN_POSITION && player2 < WIN_POSITION) {
            System.out.println((turn ? "Player 1" : "Player 2") + "'s turn. Press Enter to roll the dice...");
            scanner.nextLine(); // Wait for user input

            int roll = dice.nextInt(6) + 1; // Rolling dice (1-6)
            System.out.println("Rolled: " + roll);

            if (turn) {
                player1 = movePlayer(player1, roll);
                System.out.println("Player 1 moves to: " + player1);
                if (player1 == WIN_POSITION) {
                    System.out.println("Player 1 wins!");
                    break;
                }
            } else {
                player2 = movePlayer(player2, roll);
                System.out.println("Player 2 moves to: " + player2);
                if (player2 == WIN_POSITION) {
                    System.out.println("Player 2 wins!");
                    break;
                }
            }

            turn = !turn; // Switch turn
        }
        scanner.close();
    }

    private static int movePlayer(int currentPosition, int roll) {
        int newPosition = currentPosition + roll;

        if (newPosition > WIN_POSITION) {
            return currentPosition; // Player must land exactly on 100
        }

        if (snakes.containsKey(newPosition)) {
            System.out.println("Oops! Bitten by a snake at " + newPosition);
            return snakes.get(newPosition);
        }

        if (ladders.containsKey(newPosition)) {
            System.out.println("Yay! Climbed a ladder at " + newPosition);
            return ladders.get(newPosition);
        }

        return newPosition;
    }
}