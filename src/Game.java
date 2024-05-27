
import java.util.*;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Visualizer viz;
    
    public Game() {
        player1 = new Player("player1", true);
        player2 = new Player("player2", false);
        board = new Board(player1, player2);
    }

    
    public void startGame() {
    	board.initBoard();
    }
    
    
    public static void main(String [] args) {
    	Game game = new Game();
    	game.startGame();
    }
}

