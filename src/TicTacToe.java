import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean vsComputer;
    private boolean gameOver;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize game board
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> makeMove(row, col));
                boardPanel.add(buttons[i][j]);
            }
        }

        // Create status label
        statusLabel = new JLabel("Select game mode");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create mode selection buttons
        JPanel modePanel = new JPanel();
        JButton pvpButton = new JButton("Player vs Player");
        JButton pvcButton = new JButton("Player vs Computer");

        pvpButton.addActionListener(e -> startNewGame(false));
        pvcButton.addActionListener(e -> startNewGame(true));

        modePanel.add(pvpButton);
        modePanel.add(pvcButton);

        // Add components to frame
        add(modePanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setSize(400, 500);
        setLocationRelativeTo(null);
    }

    private void startNewGame(boolean computerOpponent) {
        vsComputer = computerOpponent;
        currentPlayer = 'X';
        gameOver = false;

        // Reset all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }

        updateStatus();
    }

    private void makeMove(int row, int col) {
        if (gameOver || !buttons[row][col].getText().isEmpty()) {
            return;
        }

        // Make player move
        buttons[row][col].setText(String.valueOf(currentPlayer));

        if (checkWin(currentPlayer)) {
            gameOver = true;
            statusLabel.setText("Player " + currentPlayer + " wins!");
            return;
        }

        if (isBoardFull()) {
            gameOver = true;
            statusLabel.setText("It's a draw!");
            return;
        }

        // Switch players
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        updateStatus();

        // Make computer move if enabled
        if (vsComputer && currentPlayer == 'O' && !gameOver) {
            makeComputerMove();
        }
    }

    private void makeComputerMove() {
        // Simple computer AI: randomly choose an empty cell
        while (true) {
            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if (buttons[row][col].getText().isEmpty()) {
                makeMove(row, col);
                break;
            }
        }
    }

    private boolean checkWin(char player) {
        String symbol = String.valueOf(player);

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol) &&
                    buttons[i][1].getText().equals(symbol) &&
                    buttons[i][2].getText().equals(symbol)) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(symbol) &&
                    buttons[1][j].getText().equals(symbol) &&
                    buttons[2][j].getText().equals(symbol)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][2].getText().equals(symbol)) {
            return true;
        }

        if (buttons[0][2].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][0].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateStatus() {
        if (!gameOver) {
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
