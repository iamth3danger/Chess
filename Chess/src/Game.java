
import java.util.Scanner;
import java.util.*;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

   
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.printBoard(); // Print the board before prompting the user
            System.out.println("Player " + (currentPlayer.getColor() ? "white" : "black") + ", enter your move:");
            String move = scanner.nextLine();
            if (makeMove(move)) {
                switchPlayer();
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
 
    }

   
    private boolean makeMove(String move) {
        Move newMove = new Move(board, move, currentPlayer.getColor());
        return newMove.makeMove();
    }

    
    
    
    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.makeBoard();
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        Game game = new Game(board, player1, player2);
        game.startGame();
    }
}

