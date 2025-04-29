import java.util.Scanner;

public class ConnectFour {
    private final char[][] board;
    private final int ROWS = 6;
    private final int COLS = 7;
    private final char EMPTY_SLOT = '.';

    public ConnectFour() {
        board = new char[ROWS][COLS];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = EMPTY_SLOT;
            }
        }
    }

    public void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    public boolean dropDisc(int col, char disc) {
        if (col < 0 || col >= COLS) {
            System.out.println("Invalid column!");
            return false;
        }

        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == EMPTY_SLOT) {
                board[r][col] = disc;
                return true;
            }
        }

        System.out.println("Column full!");
        return false;
    }

    public boolean checkWin(char disc) {
        // Check horizontal
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == disc && board[r][c+1] == disc &&
                    board[r][c+2] == disc && board[r][c+3] == disc) {
                    return true;
                }
            }
        }
        
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS - 3; r++) {
                if (board[r][c] == disc && board[r+1][c] == disc &&
                    board[r+2][c] == disc && board[r+3][c] == disc) {
                    return true;
                }
            }
        }

        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == disc && board[r+1][c+1] == disc &&
                    board[r+2][c+2] == disc && board[r+3][c+3] == disc) {
                    return true;
                }
            }
        }

        for (int r = 3; r < ROWS; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == disc && board[r-1][c+1] == disc &&
                    board[r-2][c+2] == disc && board[r-3][c+3] == disc) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        Scanner scanner = new Scanner(System.in);

        char currentPlayer = 'X';
        boolean gameEnded = false;

        System.out.println("Welcome to Connect Four!");
        game.displayBoard();

        while (!gameEnded) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter column (0-6): ");
            int col = scanner.nextInt();

            if (game.dropDisc(col, currentPlayer)) {
                game.displayBoard();

                if (game.checkWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }

        scanner.close();
    }
}
