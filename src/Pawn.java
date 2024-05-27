
public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite, "P");
    }
    // ... other Pawn methods (e.g., isValidMove)
    
    @Override
    public String pieceName() {return "P";}
    
  
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
    	if(!isMoveValidBasedOnColor(endRow, endCol, board)) {
        	return false;
        }
        // Check if the move is one square forward
        if (startCol == endCol && Math.abs(startRow - endRow) == 1 && board[endRow][endCol] == null) {
            return true;
        }
        // Check if the move is two squares forward from the starting position
        if (startCol == endCol && (startRow == 6 && endRow == 4 || startRow == 1 && endRow == 3) && board[endRow][endCol] == null && board[endRow + (startRow == 6 ? 1 : -1)][endCol] == null) {
            return true;
        }
        // Check if the move is a capture
        if (Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1 && board[endRow][endCol] != null && board[endRow][endCol].getColor() != isWhite) {
            return true;
        }
        // Check if the move is en Passant
        /*
        if (Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1 && board[endRow][endCol] == null) {
            return true;
        }
        */
        
        return false;
    }
    
}
