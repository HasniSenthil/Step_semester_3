package oop.class_problems;
import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};
        int rounds = 5;
        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];
        int wins = 0;
        int losses = 0;
        int draws = 0;
        for (int i = 0; i < rounds; i++) {
            System.out.print("Round " + (i + 1) + " - Player: ");
            playerMoves[i] = sc.next();
            computerMoves[i] = moves[random.nextInt(3)];
            results[i] = playRound(playerMoves[i],computerMoves[i]);
            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
            System.out.println("Computer: " + computerMoves[i]);
            System.out.println("Result: " + results[i]);
            System.out.println();
        }
        double winPercentage = (wins * 100.0) / rounds;
        System.out.println("Wins: " + wins + " | " +  "Losses: " + losses + " | " + "Draws: " + draws +" | "+ "Win % = " + winPercentage + "%");
        sc.close();
    }
}
