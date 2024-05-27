import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Visualizer extends JPanel{

	private static final int ROWS = 8;
    private static final int COLS = 8;
    public final int SQUARE_SIZE = 50;
    private int startCol, startRow, endCol, endRow;
    Piece[][] pieces;
    Board board;
    private Piece selectedPiece;
    private boolean review = false;
   // private Graphics g = getGraphics();
    
    public Visualizer(Board board) {
    	setPreferredSize(new Dimension(COLS * SQUARE_SIZE, ROWS * SQUARE_SIZE));
    	this.board = board;
    	this.pieces = board.board;
    	this.setFocusable(true);
    	this.requestFocusInWindow();
    	this.addMouseListener(new MouseAdapter() {
        	
    		 @Override
             public void mousePressed(MouseEvent e) {
                 int col = e.getX() / SQUARE_SIZE;
                 int row = e.getY() / SQUARE_SIZE;
                 if(review) return;
                 if (selectedPiece == null) {
                     // If no piece is selected, select the piece at the clicked square
                     selectedPiece = board.board[row][col];
                     startRow = row;
                     startCol = col;
                 } else {
                     // If a piece is already selected, move it to the clicked square
                     endRow = row;
                     endCol = col;
                     board.movePiece(startRow, startCol, endRow, endCol);
                     selectedPiece = null; // Reset the selected piece
                 }
             }
        });
    	
    	


    	
    	this.addKeyListener(new KeyAdapter() {
    	    int moveIndex = board.getMoves().size() - 1; // initialize moveIndex to the last move
    	    int count = 0;
    	    @Override
    	    public void keyPressed(KeyEvent e) {
    	        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    	            // Go to the previous move
    	            moveIndex = board.getMoves().size();
    	            System.out.println(moveIndex);
    	            if (moveIndex > 0 && moveIndex > count) {
    	                review = true;
    	                count++;
    	                Move previousMove = board.getMoves().get(moveIndex - count);
    	                System.out.println(previousMove.toString());
    	                board.makeMove(previousMove.getEndRow(), previousMove.getEndCol(), previousMove.getStartRow(), previousMove.getStartCol());
    	                repaintBoard();
    	                // Change the currentPlayer
    	                board.setCurrentPlayer(board.getCurrentPlayer() == board.getPlayer1() ? board.getPlayer2() : board.getPlayer1());
    	                moveIndex--;
    	            }
    	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    	            // Go to the next move
    	            if (count > 0) {
    	                review = true;
    	                count--;
    	                Move nextMove = board.getMoves().get(moveIndex - count);
    	                board.makeMove(nextMove.getStartRow(), nextMove.getStartCol(), nextMove.getEndRow(), nextMove.getEndCol());
    	                repaintBoard();
    	                // Change the currentPlayer
    	                board.setCurrentPlayer(board.getCurrentPlayer() == board.getPlayer1() ? board.getPlayer2() : board.getPlayer1());
    	            }
    	            if (count == 0) {
    	                review = false;
    	            }
    	        }
    	    }
    	});
    	
    	
    }
    
	 private void drawPiece(BufferedImage pieceImage, int col, int row) {
	        Graphics g = getGraphics();
	        g.drawImage(pieceImage, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
	    }   

	    public void createBoard() {
	        JFrame frame = new JFrame("Chess Board");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(this);
	        frame.pack();
	        frame.setVisible(true);
	    }
	    
	    public void makeBoard() {
	        // Create the chess board image
	        createBoard();

	        // Draw the pieces on the board
	        for (int row = 0; row < 8; row++) {
	            for (int col = 0; col < 8; col++) {
	                if (pieces[row][col] != null) {
	                    // Get the image address of the piece
	                    String pieceImageAddress = pieces[row][col].getImage();

	                    // Convert the image address to a BufferedImage
	                    BufferedImage pieceImage = null;
	                    try {
	                        pieceImage = ImageIO.read(new File(pieceImageAddress));
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }

	                    // Draw the piece on the board
	                    if (pieceImage != null) {
	                        drawPiece(pieceImage, col, row);
	                    }
	                }
	            }
	        }
	    }

	    public static void handleImage(Piece piece, int squareSize) {
	        String pieceImageAddress = piece.getImage();
	        BufferedImage pieceImage = null;
	        try {
	            pieceImage = ImageIO.read(new File(pieceImageAddress));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        // Scale the piece image to fit the square on the board
	        BufferedImage scaledPieceImage = new BufferedImage(squareSize, squareSize, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = (Graphics2D) scaledPieceImage.getGraphics();
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.drawImage(pieceImage, 0, 0, squareSize, squareSize, null);
	        g2d.dispose();
	        
	    }
	    
	    public void repaintBoard() {
	        repaint();
	    }
	    
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Draw the board and pieces
	        for (int row = 0; row < 8; row++) {
	            for (int col = 0; col < 8; col++) {
	                // Draw the square
	                g.setColor((row + col) % 2 == 0 ? Color.WHITE : new Color(68, 173, 88));
	                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	                // Draw the piece
	                if (pieces[row][col] != null) {
	                    // Get the image address of the piece
	                    String pieceImageAddress = pieces[row][col].getImage();
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
	        
	        board.repaint();
	    }
}
