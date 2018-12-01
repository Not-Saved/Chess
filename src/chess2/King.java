package chess2;

import static chess2.Movement.squareDistance;

public class King extends Piece{
	public King(Color color, String name) {
		super(color, name);
	}
	
	@Override
	public boolean legalMove(Board board, Square targetSquare) {
		Square startSquare = board.occupies(this);
		int[] stPos = startSquare.getFileRank();
		int[] fnPos = targetSquare.getFileRank();
		
		if (legalPath(board,startSquare,targetSquare)){
			if (squareDistance(stPos,fnPos)==1) return true;
			else return false;
		}
		return false;
	}
}
