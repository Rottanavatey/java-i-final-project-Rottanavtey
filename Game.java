import java.util.Random;

public class Game {
    private int numDice, trials;
    private Random rand;
    private int[][] playerRolls;
    private int[][] computerRolls;
    private int[][] playerResults;
    private int[][] computerResults;

    public Game(int numDice, int trials) {
        this.numDice = numDice;
        this.trials = trials;
        rand = new Random();
        playerRolls = new int[trials][numDice];
        computerRolls = new int[trials][numDice];
        playerResults = new int[trials][2];
        computerResults = new int[trials][2];
    }

    public void play(String who, int playerTarget, int computerTarget) {
        for (int i = 0; i < trials; i++) {
            int playerPoints = 0;
            int computerPoints = 0;
            int playerTotalPoints = 0;
            int computerTotalPoints = 0;

            for (int j = 0; j < numDice; j++) {
                playerRolls[i][j] = rand.nextInt(6) + 1;
                computerRolls[i][j] = rand.nextInt(6) + 1;

                if (playerRolls[i][j] == playerTarget) {
                    playerPoints++;
                }

                if (computerRolls[i][j] == computerTarget) {
                    computerPoints++;
                }
            }

            playerTotalPoints = playerPoints;
            computerTotalPoints = computerPoints;

            playerResults[i][0] = playerPoints;
            playerResults[i][1] = playerTotalPoints;

            computerResults[i][0] = computerPoints;
            computerResults[i][1] = computerTotalPoints;
        }
    }

    public int[][] getPlayerRolls() {
        return playerRolls;
    }

    public int[][] getComputerRolls() {
        return computerRolls;
    }

    public int[][] getPlayerResults() {
        return playerResults;
    }

    public int[][] getComputerResults() {
        return computerResults;
    }
}
