
public class Move {
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private boolean isWhite;

    public Move(int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.isWhite = isWhite;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public String getColor() {
        if (isWhite) return "white";
        else return "black";
    }
    
    public String toString() {
    	return "startRow " + startRow + " startCol " + startCol + " endRow " + endRow + " endCol " + endCol;
    }
}
    