
public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite, "P");
    }
    // ... other Pawn methods (e.g., isValidMove)
    
    @Override
    public String pieceName() {return "P";}
    
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        if (isWhite) {
            // Check if the move is one square forward
            if (startCol == endCol && startRow == endRow + 1 && board[endRow][endCol] == null) {
                return true;
            }
            // Check if the move is two squares forward from the starting position
            if (startCol == endCol && startRow == 6 && endRow == 4 && board[endRow][endCol] == null && board[endRow + 1][endCol] == null) {
                return true;
            }
            // Check if the move is a capture
            if (Math.abs(startCol - endCol) == 1 && startRow == endRow + 1 && board[endRow][endCol] != null && board[endRow][endCol].getColor() != isWhite) {
                return true;
            }
        } else {
            // Check if the move is one square forward
            if (startCol == endCol && startRow == endRow - 1 && board[endRow][endCol] == null) {
                return true;
            }
            // Check if the move is two squares forward from the starting position
            if (startCol == endCol && startRow == 1 && endRow == 3 && board[endRow][endCol] == null && board[endRow - 1][endCol] == null) {
                return true;
            }
            // Check if the move is a capture
            if (Math.abs(startCol - endCol) == 1 && startRow == endRow - 1 && board[endRow][endCol] != null && board[endRow][endCol].getColor() == isWhite) {
                return true;
            }
        }
        return false;
    }
}
