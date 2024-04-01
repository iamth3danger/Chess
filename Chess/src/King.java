
public class King extends Piece{
    public King(boolean isWhite) {
        super(isWhite, "K");
    }
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        int deltaRow = Math.abs(startRow - endRow);
        int deltaCol = Math.abs(startCol - endCol);
        return (deltaRow == 1 && deltaCol == 0) || (deltaRow == 0 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 1);
    }
    
    @Override
    public String pieceName() {return "K";}
    
    @Override
    public String toString(){
        return isWhite ? "\u2654" : "\u265A";
    }
}
