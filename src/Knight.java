
public class Knight extends Piece{
    public Knight(boolean isWhite) {
        super(isWhite, "N");
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
    	if(!isMoveValidBasedOnColor(endRow, endCol, board)) {
        	return false;
        }
        int deltaRow = Math.abs(startRow - endRow);
        int deltaCol = Math.abs(startCol - endCol);
        return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
    }
    @Override
    public String pieceName() {return "N";}
    
    @Override
    public String toString(){
        return isWhite ? "\u2658" : "\u265E";
    }
}