import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceGameGUI extends JFrame {
    private JPanel homePanel;
    private JTextField targetField;
    private JTextArea resultArea;
    private int numDice = 3;
    private int trials = 5;
    private JButton rollButton;
    private Game game;
    private int currentTrial = 0;

    public DiceGameGUI() {
        setTitle("3 Dice Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame before entering full-screen mode
        setSize(400, 300);

        setLayout(new CardLayout());

        // Set the background color for the main JFrame
        getContentPane().setBackground(new Color(216, 191, 216)); // Light purple

        createHomePanel();
        add(homePanel, "Home");

        targetField = new JTextField(5);
        rollButton = new JButton("Roll Dice");

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        createGamePanel();
        setVisible(true);
    }

    private void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(4, 1));

        // Set the background color for the homePanel
        homePanel.setBackground(new Color(216, 191, 216)); // Light purple

        // Use a larger font for the welcome label
        Font welcomeFont = new Font("Arial", Font.BOLD, 18);
        JLabel welcomeLabel = new JLabel("Welcome to the 3 Dice Game!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(welcomeFont);
        homePanel.add(welcomeLabel);

        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHowToPlay();
            }
        });

        JButton playGameButton = new JButton("Play Game");
        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGamePanel();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        homePanel.add(howToPlayButton);
        homePanel.add(playGameButton);
        homePanel.add(exitButton);
    }

    private void createGamePanel() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 1));

        // Set the background color for the gamePanel
        gamePanel.setBackground(new Color(216, 191, 216)); // Light purple

        JPanel targetPanel = new JPanel();
        targetPanel.setLayout(new FlowLayout());
        targetPanel.setBackground(new Color(216, 191, 216)); // Light purple

        // Use a larger font for the "Enter Target Number" label
        Font targetLabelFont = new Font("Arial", Font.BOLD, 18);
        JLabel targetLabel = new JLabel("Enter Target Number:");
        targetLabel.setFont(targetLabelFont);
        targetPanel.add(targetLabel);

        // Use a larger font for the targetField
        Font targetFieldFont = new Font("Arial", Font.PLAIN, 16);
        targetField.setFont(targetFieldFont);
        targetPanel.add(targetField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmTarget();
            }
        });

        targetPanel.add(confirmButton);
        gamePanel.add(targetPanel);

        rollButton.setEnabled(false);

        // Use a larger font for the rollButton
        Font rollButtonFont = new Font("Arial", Font.PLAIN, 16);
        rollButton.setFont(rollButtonFont);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        gamePanel.add(rollButton);

        gamePanel.add(new JScrollPane(resultArea));

        add(gamePanel, "Game");
    }

    private void showHowToPlay() {
        JOptionPane.showMessageDialog(this,
                "Rules:\n" +
                        "1. Enter a target number between 1 and 6.\n" +
                        "2. Click 'Confirm' to start the game.\n" +
                        "3. Click 'Roll Dice' to roll the dice.\n" +
                        "4. For each trial, earn points if the rolled dice match the target number.\n" +
                        "5. View the results for each trial.\n" +
                        "6. After all trials, the player with the most points wins!\n" +
                        "7. Choose to play again or exit.",
                "How to Play - Game Rules", // Added title
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showGamePanel() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Game");
    }
    
    private void confirmTarget() {
        try {
            int playerTarget = Integer.parseInt(targetField.getText());

            if (playerTarget < 1 || playerTarget > 6) {
                throw new NumberFormatException();
            }

            int computerTarget = 1; // Assuming computer target is 1
            game = new Game(numDice, trials);
            game.play("Player", playerTarget, computerTarget);
            currentTrial = 0;
            rollButton.setEnabled(true);

            JOptionPane.showMessageDialog(this,
                    "Target number confirmed!\nPlayer Target: " + playerTarget + "\nComputer Target: " + computerTarget,
                    "Confirmation", // Added title
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            rollButton.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                    "Invalid target number. Please enter a valid integer between 1 and 6.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rollDice() {
        if (currentTrial < trials) {
            int playerTarget = Integer.parseInt(targetField.getText());
            int computerTarget = 1; // Assuming computer target is 1
            game.play("Player", playerTarget, computerTarget);

            int[][] playerResults = game.getPlayerResults();
            int[][] computerResults = game.getComputerResults();
            int[][] playerRolls = game.getPlayerRolls();
            int[][] computerRolls = game.getComputerRolls();

            displayResults(playerResults, computerResults, playerRolls, computerRolls);

            currentTrial++;

            if (currentTrial == trials) {
                rollButton.setEnabled(false);
                determineWinner();
            }
        }
    }

    private void displayResults(int[][] playerResults, int[][] computerResults, int[][] playerRolls, int[][] computerRolls) {
        StringBuilder resultText = new StringBuilder("Results for Trial " + (currentTrial + 1) + ":\n");

        resultText.append("Player: ");
        for (int j = 0; j < numDice; j++) {
            resultText.append(playerRolls[currentTrial][j]).append(" ");
        }
        resultText.append("Points: ").append(playerResults[currentTrial][1]).append("\n");

        resultText.append("Computer: ");
        for (int j = 0; j < numDice; j++) {
            resultText.append(computerRolls[currentTrial][j]).append(" ");
        }
        resultText.append("Points: ").append(computerResults[currentTrial][1]).append("\n");

        resultArea.append(resultText.toString() + "\n");
    }

    private void determineWinner() {
        int playerTotalPoints = 0;
        int computerTotalPoints = 0;

        for (int i = 0; i < trials; i++) {
            playerTotalPoints += game.getPlayerResults()[i][1];
            computerTotalPoints += game.getComputerResults()[i][1];
        }

        String winner = "It's a tie!";
        if (playerTotalPoints > computerTotalPoints) {
            winner = "Player wins!";
        } else if (playerTotalPoints < computerTotalPoints) {
            winner = "Computer wins!";
        }

        int option = JOptionPane.showOptionDialog(this,
                "Final Results:\nPlayer Total Points: " + playerTotalPoints +
                        "\nComputer Total Points: " + computerTotalPoints + "\n" + winner,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Try Again", "Exit"}, // Button options
                "Try Again");

        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        targetField.setText("");
        resultArea.setText("");
        rollButton.setEnabled(false);

        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Home");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiceGameGUI());
    }
}
