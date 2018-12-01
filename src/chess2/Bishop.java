package chess2;

import static chess2.Movement.*;

public class Bishop extends Piece{
	public Bishop(Color color, String name) {
		super(color, name);
	}
	
	@Override
	public boolean legalMove(Board board, Square targetSquare) {
		Square startSquare = board.occupies(this);
		int[] stPos = startSquare.getFileRank();
		int[] fnPos = targetSquare.getFileRank();
		
		if (legalPath(board,startSquare,targetSquare)){
			if (isDiagonal(stPos,fnPos)) return true;
			else return false;
		}
		return false;
	}
}
