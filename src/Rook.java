
public class Rook extends Piece implements Movable{
	 private boolean hasMoved;

	    public Rook(boolean isWhite) {
	        super(isWhite, "R");
	        this.hasMoved = false;
	    }
	    
	    public boolean getHasMoved() {
	        return hasMoved;
	    }
	    
	    public void setHasMoved(boolean hasMoved) {
	        this.hasMoved = hasMoved;
	    }
    
    @Override 
    public String toString() {
        return isWhite ? "\u2656" : "\u265C"; // White and black Rook symbols
    }
    
    @Override
    public String pieceName() {return "R";}
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
	 // Check if the piece at the end position is of the same color as the rook
    if(!isMoveValidBasedOnColor(endRow, endCol, board)) {
    	return false;
    }

   	 if (startRow == endRow) {
            int minY = Math.min(startCol, endCol);
            int maxY = Math.max(startCol, endCol);
            for (int col = minY + 1; col < maxY; col++) {
                if (board[startRow][col] != null) {
                    return false;
                }
            }
            return true;
        } else if (startCol == endCol) {
            int minX = Math.min(startRow, endRow);
            int maxX = Math.max(startRow, endRow);
            for (int row = minX + 1; row < maxX; row++) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
            return true;
       } else {
           return false;
       }
   }

    
    
}
