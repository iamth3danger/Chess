
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieceMover extends JPanel {
    private int draggedPieceX = -1;
    private int draggedPieceY = -1;
    private int draggedPieceOffsetX = 0;
    private int draggedPieceOffsetY = 0;
    private Board board;

    public PieceMover(Board board) {
        this.board = board;
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	System.out.println("dragggg");
                if (draggedPieceX != -1 && draggedPieceY != -1) {
                    int newX = (e.getX() - draggedPieceOffsetX) / board.SQUARE_SIZE;
                    int newY = (e.getY() - draggedPieceOffsetY) / board.SQUARE_SIZE;
                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        board.movePiece(draggedPieceX, draggedPieceY, newX, newY);
                        draggedPieceX = newX;
                        draggedPieceY = newY;
                    }
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / board.SQUARE_SIZE;
                int y = e.getY() / board.SQUARE_SIZE;
                if (x >= 0 && x < 8 && y >= 0 && y < 8 && board.board[y][x] != null) {
                    draggedPieceX = x;
                    draggedPieceY = y;
                    draggedPieceOffsetX = e.getX() % board.SQUARE_SIZE;
                    draggedPieceOffsetY = e.getY() % board.SQUARE_SIZE;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedPieceX = -1;
                draggedPieceY = -1;
            }
        });
    }
}