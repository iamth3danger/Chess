
public abstract class Piece {
    public boolean isWhite;
    private String symbol;
    private int row, col;

    public Piece(boolean isWhite, String symbol) {
        this.isWhite = isWhite;
        this.symbol = symbol;
    }

    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board);
   
    public abstract String pieceName();
    
    public boolean getColor() {return isWhite;}
    // Getters and Setters for row, col, and isWhite

    @Override
    public String toString() {
        return isWhite ?  "\u265F" : "\u2659"; // Default Pawn symbols
    }
    
    public String getImage() {
    	if (isWhite) return "White" + pieceName() + ".png"; 
    	else return "Black" + pieceName() + ".png"; 
    }
    
    protected boolean isMoveValidBasedOnColor(int endRow, int endCol, Piece[][] board) {
        if (board[endRow][endCol] != null && board[endRow][endCol].getColor() == this.getColor()) {
            return false;
        }
        return true;
    }

}




// Similar classes for Rook, Knight, Bishop, Queen, and King, each with their unique symbols




