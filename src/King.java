
public class King extends Piece implements Movable{
	 private boolean hasMoved;

	    public King(boolean isWhite) {
	        super(isWhite, "K");
	        this.hasMoved = false;
	    }
	    
	    public boolean getHasMoved() {
	        return hasMoved;
	    }
	    
	    public void setHasMoved(boolean hasMoved) {
	        this.hasMoved = hasMoved;
	    }
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        if(!isMoveValidBasedOnColor(endRow, endCol, board)) {
            return false;
        }
        int deltaRow = Math.abs(startRow - endRow);
        int deltaCol = Math.abs(startCol - endCol);
        return (deltaRow == 1 && deltaCol == 0) || (deltaRow == 0 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 1) || (deltaRow == 0 && deltaCol == 2);
    }
    
    @Override
    public String pieceName() {return "K";}
    
    @Override
    public String toString(){
        return isWhite ? "\u2654" : "\u265A";
    }
}
