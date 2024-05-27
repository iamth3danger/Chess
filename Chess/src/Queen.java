
public class Queen extends Piece{
    public Queen(boolean isWhite) {
        super(isWhite, "Q");
    }
    
   
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        // Check if the end position is occupied by a piece of the same color
        Piece endPiece = board[endRow][endCol];
        if (endPiece != null && endPiece.isWhite == this.isWhite) {
            return false;
        }

        // Rest of the method remains the same
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
            int deltaRow = Math.abs(startRow - endRow);
            int deltaCol = Math.abs(startCol - endCol);
            if (deltaRow != deltaCol) {
                return false;
            }
            int minRow = Math.min(startRow, endRow);
            int maxRow = Math.max(startRow, endRow);
            int minCol = Math.min(startCol, endCol);
            int maxCol = Math.max(startCol, endCol);
            int rowStep = startRow < endRow ? 1 : -1;
            int colStep = startCol < endCol ? 1 : -1;
            for (int x = startRow + rowStep, y = startCol + colStep; x != endRow; x += rowStep, y += colStep) {
                if (board[x][y] != null) {
                    return false;
                }
            }
            return true;
        }
    }
    
   
    @Override
    public String pieceName() {return "Q";}
    @Override
    public String toString(){
        return isWhite ? "\u2655" : "\u265B";
    }
}
