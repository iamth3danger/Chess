
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceMover extends MouseAdapter {
    private ChessBoard chessBoard;
    private int startX, startY, endX, endY;

    /*
    public PieceMover(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        System.out.println("Mouse dragged to: " + endX + ", " + endY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        System.out.println("Mouse pressed at: " + startX + ", " + startY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released at: " + endX + ", " + endY);
    }

    public int[] getCoordinates() {
        return new int[] {startX, startY, endX, endY};
    }
    */
}

  
    