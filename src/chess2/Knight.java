package chess2;

import static myMath.BasicAlgebra2D.*;

public class Knight extends Piece{
	public Knight(Color color, String name) {
		super(color,name);
	}
	
	@Override
	public boolean legalMove(Board board, Square targetSquare) {
		Square startSquare = board.occupies(this);
		int[] stPos = startSquare.getFileRank();
		int[] fnPos = targetSquare.getFileRank();
		int[] option1 = {2,1};
		int[] option2 = {1,2};
		int[] myMove = arrayAbs(arrayDiff(fnPos,stPos));
		
		if (arrayEquals(myMove,option1) || arrayEquals(myMove,option2)) return true;
		else return false;
	}
}
