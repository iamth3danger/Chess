
public class CastlingPositions {
	private int row;
	private int kingStartCol = 4;
	private int kingEndCol;
	private int rookStartCol;
	private int rookEndCol;
	
	public CastlingPositions(int row, int kingEndCol, int rookStartCol, int rookEndCol) {
		this.row = row;
		this.kingEndCol = kingEndCol;
		this.rookStartCol = rookStartCol;
		this.rookEndCol = rookEndCol;
	}

	public int getRow() {
		return row;
	}

	public int getKingStartCol() {
		return kingStartCol;
	}

	public int getKingEndCol() {
		return kingEndCol;
	}

	public int getRookStartCol() {
		return rookStartCol;
	}

	public int getRookEndCol() {
		return rookEndCol;
	}
}
