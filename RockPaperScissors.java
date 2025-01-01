import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Choices and their visual representation
        String[] choices = {"Rock 🪨", "Paper 📜", "Scissors ✂️", "Lizard 🦎", "Spock 🖖"};
        String[] choicesKey = {"rock", "paper", "scissors", "lizard", "spock"};

        // Game variables
        int player1Score = 0;
        int computerScore = 0;
        int highScore = 0;

        // Load leaderboard
        try {
            File file = new File("leaderboard.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNextInt()) {
                    highScore = fileScanner.nextInt();
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard file.");
        }

        System.out.println("Welcome to Rock, Paper, Scissors, Lizard, Spock!");
        System.out.println("The current high score is: " + highScore);
        System.out.println("Rules: Rock crushes Scissors and Lizard, Paper covers Rock and disproves Spock, Scissors cuts Paper and decapitates Lizard, Lizard eats Paper and poisons Spock, Spock vaporizes Rock and smashes Scissors.");

        while (true) {
            // Player's choice
            System.out.println("\nEnter your choice (Rock, Paper, Scissors, Lizard, Spock): ");
            String playerChoice = scanner.nextLine().trim().toLowerCase();

            // Validate player input
            boolean validChoice = false;
            for (String key : choicesKey) {
                if (playerChoice.equals(key)) {
                    validChoice = true;
                    break;
                }
            }
            if (!validChoice) {
                System.out.println("Invalid choice. Please choose Rock, Paper, Scissors, Lizard, or Spock.");
                continue;
            }

            // Computer's choice
            int computerIndex = random.nextInt(choices.length);
            String computerChoice = choicesKey[computerIndex];
            System.out.println("Computer chose: " + choices[computerIndex]);

            // Determine winner
            if (playerChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                (playerChoice.equals("rock") && (computerChoice.equals("scissors") || computerChoice.equals("lizard"))) ||
                (playerChoice.equals("paper") && (computerChoice.equals("rock") || computerChoice.equals("spock"))) ||
                (playerChoice.equals("scissors") && (computerChoice.equals("paper") || computerChoice.equals("lizard"))) ||
                (playerChoice.equals("lizard") && (computerChoice.equals("paper") || computerChoice.equals("spock"))) ||
                (playerChoice.equals("spock") && (computerChoice.equals("rock") || computerChoice.equals("scissors")))
            ) {
                System.out.println("You win this round!");
                player1Score++;
            } else {
                System.out.println("Computer wins this round!");
                computerScore++;
            }

            // Update high score
            if (player1Score > highScore) {
                highScore = player1Score;
            }

            // Display scores
            System.out.println("\nCurrent Scores:");
            System.out.println("You: " + player1Score);
            System.out.println("Computer: " + computerScore);
            System.out.println("High Score: " + highScore);

            // Ask to play again
            System.out.println("\nDo you want to play again? (yes/no): ");
            String playAgainInput = scanner.nextLine().trim().toLowerCase();

            if (playAgainInput.equals("no")) {
                break; // Exit the loop to stop the game
            }
        }

        // Save leaderboard
        try {
            FileWriter writer = new FileWriter("leaderboard.txt");
            writer.write(String.valueOf(highScore));
            writer.close();
            System.out.println("Leaderboard updated!");
        } catch (IOException e) {
            System.out.println("Error writing to leaderboard file.");
        }

        // Final Scores and Goodbye Message
        System.out.println("\nFinal Scores:");
        System.out.println("You: " + player1Score);
        System.out.println("Computer: " + computerScore);

        if (player1Score > computerScore) {
            System.out.println("Congratulations! You are the overall winner!");
        } else if (player1Score < computerScore) {
            System.out.println("Computer wins overall! Better luck next time!");
        } else {
            System.out.println("It's a tie overall!");
        }

        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
    
}
