
public class Move {

	    private Board board;
	    private String move;
	    private boolean color;

	    public Move(Board board, String move, boolean color) {
	        this.board = board;
	        this.move = move;
	        this.color = color;
	    }

	    /*
	    public boolean makeMove() {
	    	 if (move.length() != 5) {
	    	        return false; // invalid move
	    	    }

	        String piece = move.substring(0, 1);
	        String startSquare = move.substring(1, 3);
	        String endSquare = move.substring(3, 5);

	        int startRow = convertRow(startSquare);
	        int startCol = convertCol(startSquare);
	        int endRow = convertRow(endSquare);
	        int endCol = convertCol(endSquare);
	        
	        if (startRow == -1 || startCol == -1 || endRow == -1 || endCol == -1) {
	            return false; // invalid move
	        }

	        Piece pieceToMove = board.board[startRow][startCol];
	        if (pieceToMove != null && pieceToMove.pieceName().equals(piece) && pieceToMove.getColor() == color) {
	           if (pieceToMove.isValidMove(startRow, startCol, endRow, endCol, board.board)) {
	                board.board[startRow][startCol] = null;
	                board.board[endRow][endCol] = pieceToMove;
	                board.printBoard();
	                return true;
	            }
	        }
	        return false;
	    }

	   */
	    
	    
	    public boolean makeMove() {
	        if (move.length() != 5) {
	            return false; // invalid move
	        }

	        String piece = move.substring(0, 1);
	        String startSquare = move.substring(1, 3);
	        String endSquare = move.substring(3, 5);

	        int startRow = convertRow(startSquare);
	        int startCol = convertCol(startSquare);
	        int endRow = convertRow(endSquare);
	        int endCol = convertCol(endSquare);
	        
	        if (startRow == -1 || startCol == -1 || endRow == -1 || endCol == -1) {
	            return false; // invalid move
	        }

	        Piece pieceToMove = board.board[startRow][startCol];
	        if (pieceToMove != null && pieceToMove.pieceName().equals(piece) && pieceToMove.getColor() == color) {
	            if (pieceToMove.isValidMove(startRow, startCol, endRow, endCol, board.board)) {
	                Piece pieceAtEnd = board.board[endRow][endCol];
	                board.board[startRow][startCol] = null;
	                board.board[endRow][endCol] = pieceToMove;
	                if (board.isInCheck(color)) {
	                    // Undo the move
	                    board.board[startRow][startCol] = pieceToMove;
	                    board.board[endRow][endCol] = pieceAtEnd;
	                    System.out.println("You are in check. Please make a different move.");
	                    return false;
	                }
	                board.printBoard();
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    private int convertCol(String square) {
	        String col = "abcdefgh";
	        int index = col.indexOf(Character.toLowerCase(square.charAt(0)));
	        if (index == -1) {
	            return -1;
	        }
	        return index;
	    }

	    private int convertRow(String square) {
	        String row = "87654321";
	        int index = row.indexOf(square.charAt(1));
	        if (index == -1) {
	            return -1;
	        }
	        return index;
	    }
	}
    
    