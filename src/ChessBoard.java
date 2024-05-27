import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ChessBoard extends JPanel {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int SQUARE_SIZE = 50;

    private BufferedImage[] pieces;

    public ChessBoard() {
        setPreferredSize(new Dimension(COLS * SQUARE_SIZE, ROWS * SQUARE_SIZE));
        //new PieceMover(this);
    }

    public void createBoard() {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        /*
        JComponent newContentPane = new PieceMover();
        newContentPane.setOpaque(true); //content panes must be opaque
       
        frame.setContentPane(newContentPane);
        */
        frame.pack();
        frame.setVisible(true);
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(new Color(68, 173, 88)); // #44AD58
                }
                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
    
    public void drawPiece(BufferedImage pieceImage, int col, int row) {
        Graphics g = getGraphics();
        g.drawImage(pieceImage, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.createBoard();
    }
    
    
}