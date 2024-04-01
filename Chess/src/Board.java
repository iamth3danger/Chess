import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel{

    private static final int ROWS = 8;
    private static final int COLS = 8;
    public final int SQUARE_SIZE = 50;
    private PieceMover pieceMover;
    
    private Spot[][] boxes = new Spot[8][8];
    
    private ChessBoard chessBoard;
    Piece[][] board = new Piece[8][8]; 
    

    public Board() {
        // Initialize the board with pieces in their starting positions
    	 chessBoard = new ChessBoard();
    	 
    	Piece[] backWhite = backPieces(true);
     	Piece[] backBlack = backPieces(false);
     	
     	for (int i = 0; i < 8; i++) {
     		board[0][i] = backBlack[i];
         	board[1][i] = new Pawn(false);
         	board[6][i] = new Pawn(true);
         	board[7][i] = backWhite[i];
     	}
     	pieceMover = new PieceMover(this);
        this.add(pieceMover); 
    }
 
    
    public Piece[] backPieces(boolean isWhite) {
    	Piece[] back = {new Rook(isWhite), new Knight(isWhite), new Bishop(isWhite),  new Queen(isWhite), new King(isWhite), new Bishop(isWhite), new Knight(isWhite), new Rook(isWhite)};
    	return back;
    	
    }
    public Spot getSpot(int x, int y) {
        return boxes[x][y];
    }
  
    public void printBoard() {
        // ... (column and row labels)
        for (int row = 0; row < 8; row++) {
            // ... (row labels)
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    System.out.print("[" + board[row][col].toString() + "]"); // Print piece symbol
                } else {
                	  System.out.print("[ ] ");  // Print empty square with a space
                }
            }
            System.out.println(" " + (8 - row));
            // ...(row labels)
        }
        System.out.println(" a  b  c  d  e  f  g  h");
        
        
        
    }
    
    
    public void movePiece(int oldX, int oldY, int newX, int newY) {
        // Get the image of the piece to be moved
        String pieceImageAddress = board[oldX][oldY].getImage();
        BufferedImage pieceImage = null;
        try {
            pieceImage = ImageIO.read(new File(pieceImageAddress));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scale the piece image to fit the square on the board
        int squareSize = 50; // size of each square on the board
        BufferedImage scaledPieceImage = new BufferedImage(squareSize, squareSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) scaledPieceImage.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(pieceImage, 0, 0, squareSize, squareSize, null);
        g2d.dispose();



        // Update the board array
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
        for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		if (board[i][j] != null)
        		System.out.println(board[i][j].pieceName());
        	}
        }
    }
    
    
    

    public boolean isInCheck(boolean color) {
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
   
	   

    public void makeBoard() {
        // Create the chess board image
        chessBoard.createBoard();

        // Draw the pieces on the board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    // Get the image address of the piece
                    String pieceImageAddress = board[row][col].getImage();

                    // Convert the image address to a BufferedImage
                    BufferedImage pieceImage = null;
                    try {
                        pieceImage = ImageIO.read(new File(pieceImageAddress));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Draw the piece on the board
                    if (pieceImage != null) {
                        chessBoard.drawPiece(pieceImage, col, row);
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the board and pieces
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Draw the square
                g.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                // Draw the piece
                if (board[row][col] != null) {
                    // Get the image address of the piece
                    String pieceImageAddress = board[row][col].getImage();
                    // Convert the image address to a BufferedImage
                    BufferedImage pieceImage = null;
                    try {
                        pieceImage = ImageIO.read(new File(pieceImageAddress));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Draw the piece on the board
                    if (pieceImage != null) {
                        g.drawImage(pieceImage, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
                    }
                }
            }
        }
    }
    
    


	    public static void main(String[] args) {
	    	Board bird = new Board();
	        bird.makeBoard();
	        bird.add(bird.pieceMover); // Add the pieceMover to the Board panel
	        bird.setVisible(true); 
	      
	    }
	}
    
