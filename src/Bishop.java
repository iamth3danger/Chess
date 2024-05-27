
public class Bishop extends Piece{
    public Bishop(boolean isWhite) {
        super(isWhite, "B");
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
    	int deltaRow = Math.abs(startRow - endRow);
        int deltaCol = Math.abs(startCol - endCol);
        if (deltaRow != deltaCol) {
            return false;
        }

        int rowStep = startRow < endRow ? 1 : -1;
        int colStep = startCol < endCol ? 1 : -1;
        for (int x = startRow + rowStep, y = startCol + colStep; x != endRow; x += rowStep, y += colStep) {
            if (board[x][y] != null) {
                return false;
            }
        }

        if(!isMoveValidBasedOnColor(endRow, endCol, board)) {
        	return false;
        }

        return true;
    }

    @Override
    public String pieceName() {return "B";}
    @Override
    public String toString(){
        return isWhite ? "\u2657" : "\u265D";
    }
}
