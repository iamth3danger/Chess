import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel {

    private static final int ROWS = 8;
    private static final int COLS = 8;
    public final int SQUARE_SIZE = 50;
    private int startCol, startRow, endCol, endRow;
    private Piece selectedPiece;
    private ArrayList moves = new ArrayList<Move>();
    
    Piece[][] board = new Piece[8][8]; 
    private Player currentPlayer;
    private Player player1, player2;
    
    private static int[] lastMove = new int[5]; 
    
    private Visualizer viz;

    
    
    
    
    
    public Board(Player player1, Player player2) {
        // Initialize the board with pieces in their starting positions
    	this.player1 = player1;
    	this.player2 = player2;
    	this.currentPlayer = player1;
    	this.viz = new Visualizer(this);
    	setPreferredSize(new Dimension(COLS * SQUARE_SIZE, ROWS * SQUARE_SIZE));
        
        Piece[] backWhite = backPieces(true);
        Piece[] backBlack = backPieces(false);
        
        for (int i = 0; i < 8; i++) {
            board[0][i] = backBlack[i];
            board[1][i] = new Pawn(false);
            board[6][i] = new Pawn(true);
            board[7][i] = backWhite[i];
        }
       
        this.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mousePressed(MouseEvent e) {
        	    int col = e.getX() / SQUARE_SIZE;
        	    int row = e.getY() / SQUARE_SIZE;
        	    
        	    if (selectedPiece == null) {
        	        // If no piece is selected, select the piece at the clicked square
        	        selectedPiece = board[row][col];
        	        startRow = row;
        	        startCol = col;
        	    } else {
        	        // If a piece is already selected, move it to the clicked square
        	        endRow = row;
        	        endCol = col;
        	        movePiece(startRow, startCol, endRow, endCol);
        	        selectedPiece = null; // Reset the selected piece
        	    }
        	}
        });
    }
 
    public void initBoard() {
    	viz.makeBoard();
    }
    
    private Piece[] backPieces(boolean isWhite) {
        Piece[] back = {new Rook(isWhite), new Knight(isWhite), new Bishop(isWhite),  new Queen(isWhite), new King(isWhite), new Bishop(isWhite), new Knight(isWhite), new Rook(isWhite)};
        return back;
    }
    
    
    
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        // Check if the move is valid
    	
    	int squareSize = 50; // size of each square on the board
    	
        Piece piece = board[startRow][startCol];
        
        
        if (enPassant(startRow, startCol, endRow, endCol)) {
        	System.out.println("En Passant!");
        }
        
        
        if (!piece.isValidMove(startRow, startCol, endRow, endCol, board) && !enPassant(startRow, startCol, endRow, endCol)) {
            System.out.println("Invalid move 2");
            return;
        }
        
        
        if (piece.getColor() != currentPlayer.isWhite()) {
            System.out.println("Invalid move 1");
            return;
        }
        
        if (isInCheck(currentPlayer.isWhite())) {
        	board[endRow][endCol] = piece;
            board[startRow][startCol] = null;
            if (isInCheck(currentPlayer.isWhite())) {
            	board[startRow][startCol] = piece;
            	board[endRow][endCol] = null;
            	System.out.println("You cannot make this move as it puts your king in check");
            	return;
            }
            else {
            	board[startRow][startCol] = piece;
            	board[endRow][endCol] = null;
            }
       
        }
        
        if (piece instanceof Movable) {
            ((Movable) piece).setHasMoved(false);
        }
        
        Visualizer.handleImage(piece, squareSize);

        lastMove[0] = piece.pieceName().charAt(0); // Convert the piece name to an integer
        lastMove[1] = startRow;
        lastMove[2] = startCol;
        lastMove[3] = endRow;
        lastMove[4] = endCol;

        Move move = new Move(startRow, startCol, endRow, endCol, piece.getColor());
        moves.add(move);
        
        // Update the board array
        if (board[startRow][startCol] instanceof King && Math.abs(endCol - startCol) == 2) {
            CastlingPositions castled = castle(startRow, startCol, endRow, endCol);
            if (castled == null) return;
            else {
            	makeMove(castled.getRow(), castled.getKingStartCol(), castled.getRow(), castled.getKingEndCol());
            	makeMove(castled.getRow(), castled.getRookStartCol(), castled.getRow(), castled.getRookEndCol());
            }
        }
        else {
	        makeMove(startRow, startCol, endRow, endCol);
        }
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        
        viz.repaintBoard();
    }
    

    public void makeMove(int startRow, int startCol, int endRow, int endCol) {
    	board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = null;
    }
    
    
    private boolean isInCheck(boolean color) {
        int kingRow = -1, kingCol = -1;
        // Find the king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].pieceName().equals("K") && board[i][j].getColor() == color) {
                    kingRow = i;
                    kingCol = j;
                }
            }
        }
        // If the king is not found, return false
        if (kingRow == -1) {
            return false;
        }
        // Iterate through all the pieces of the opposing color
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != color) {
                    // Check if the piece can make a valid move to the king's position
                    if (board[i][j].isValidMove(i, j, kingRow, kingCol, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
   
   
   
    public static int[] getLastMove() {
		return lastMove;
	}

    public ArrayList<Move> getMoves() { return moves; }
    
    private CastlingPositions castle(int startRow, int startCol, int endRow, int endCol) {
        Piece king = board[startRow][startCol];
        Piece rook;
        int direction;
        
        // Check if the king is moving to the left or right
        if (endCol < startCol) {
            rook = board[startRow][0];
            direction = -1;
        } else {
            rook = board[startRow][7];
            direction = 1;
        }
        
        // Check if the king and rook have not moved
        if (!(king instanceof Movable) || ((Movable) king).getHasMoved()) {
            System.out.println("Invalid move: King has moved");
            return null;
        }
        
        if (((Movable) rook).getHasMoved()) {
            System.out.println("Invalid move: Rook has moved");
            return null;
        }
        
        // Check if there are no pieces between the king and rook
        for (int col = startCol + direction; col != endCol; col += direction) {
            if (board[startRow][col] != null) {
                System.out.println("Invalid move: Pieces between king and rook");
                return null;
            }
        }
        
        // Check if the king would be in check in each square it would pass through
        for (int col = startCol; col <= endCol; col += direction) {
        	System.out.println(col);
            Piece temp = board[startRow][col];
            board[startRow][col] = king;
            board[startRow][startCol] = null;
            if (isInCheck(king.getColor())) {
                System.out.println("Invalid move: King would be in check");
                board[startRow][startCol] = king;
                board[startRow][col] = temp;
                return null;
            }
            board[startRow][startCol] = king;
            board[startRow][col] = temp;
        }
        
        ((Movable) king).setHasMoved(true);
        ((Movable) rook).setHasMoved(true);
        
     // Move the king and rook
        int [] moves = new int [4];
        
        
      
        int initRowPos = 7;
        
        //board[startRow][startCol + 2 * direction] = king;
        //board[startRow][startCol] = null;
        if (direction == -1) {
            //board[startRow][0] = null;
        	initRowPos = 0;
        } 
        
        moves[3] = startCol + 1 * direction;
        CastlingPositions castled = new CastlingPositions(startRow, startCol + 2 * direction, initRowPos, startCol + 1 * direction);
        return castled;
        
        // Update the hasMoved flag
       
        
        
   
    }

    
    /*
    public boolean enPassant(int row, int col) {
        // Check if the last move was a pawn moving two steps forward adjacent to the player's pawn
    	
    	 if (moves.isEmpty()) {
    	        return false;
    	    }
    	 
        Move lastMove = getMoves().get(moves.size() - 1);
        if (lastMove.getStartRow() == row && lastMove.getStartCol() == col && lastMove.getEndRow() == row + 2 && lastMove.getEndCol() == col) {
            // Check if the piece that made the last move is a pawn
            Piece piece = board[lastMove.getStartRow()][lastMove.getStartCol()];
            if (piece instanceof Pawn) {
                // Check if the pawn is adjacent to the player's pawn
                if (piece.getColor() != currentPlayer.isWhite()) {
                    // Check if the player's pawn is on the same file as the pawn that made the last move
                    if (board[row][col] instanceof Pawn && board[row][col].getColor() == currentPlayer.isWhite()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    */
    
    public boolean enPassant(int startRow, int startCol, int endRow, int endCol) {
        // Check if the last move was a pawn moving two steps forward adjacent to the player's pawn
        if (moves.isEmpty()) {
            return false;
        }
        
        Move lastMove = getMoves().get(moves.size() - 1);
        if (lastMove.getEndRow() == startRow && Math.abs(lastMove.getEndRow() - lastMove.getStartRow()) == 2 && Math.abs(lastMove.getEndCol() - startCol) == 1) {
            // Check if the piece that made the last move is a pawn
            Piece piece = board[lastMove.getStartRow()][lastMove.getStartCol()];
            if (piece instanceof Pawn && board[startRow][startCol] instanceof Pawn && piece.getColor() != board[startRow][startCol].getColor()
            		&& Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) {
                        return true;
                    }
                }
        return false;
    }
    
    

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
    
