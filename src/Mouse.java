import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Mouse extends JPanel {

    private BufferedImage[] pieces;
    private int pieceWidth;
    private int pieceHeight;
    private int selectedPieceIndex = -1;
    private int selectedPieceX;
    private int selectedPieceY;

    public Mouse(BufferedImage[] pieces, int pieceWidth, int pieceHeight) {
        this.pieces = pieces;
        this.pieceWidth = pieceWidth;
        this.pieceHeight = pieceHeight;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / pieceWidth;
                int y = e.getY() / pieceHeight;
                selectedPieceIndex = x + y * 8;
                selectedPieceX = x;
                selectedPieceY = y;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPieceIndex != -1) {
                    int x = e.getX() / pieceWidth;
                    int y = e.getY() / pieceHeight;
                    if (x != selectedPieceX || y != selectedPieceY) {
                        // Move the piece
                        pieces[selectedPieceIndex] = pieces[x + y * 8];
                        pieces[x + y * 8] = null;
                        selectedPieceIndex = -1;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 64; i++) {
            int x = i % 8;
            int y = i / 8;
            if (pieces[i] != null) {
                g.drawImage(pieces[i], x * pieceWidth, y * pieceHeight, pieceWidth, pieceHeight, null);
            }
        }
    }
}
