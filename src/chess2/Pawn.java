package chess2;

import static chess2.Color.*;
import static myMath.BasicAlgebra2D.*;

public class Pawn extends Piece{
	public Pawn(Color color,String name) {
		super(color, name);
	}
	
	@Override
	public boolean legalMove(Board board, Square targetSquare){
		Square startSquare = board.occupies(this);
		Piece targetPiece = board.isOccupiedBy(targetSquare);
		int[] stPos = startSquare.getFileRank();
		int[] fnPos = targetSquare.getFileRank();
		int[] move = arrayDiff(fnPos,stPos);
						
		if (legalPath(board,startSquare,targetSquare)){
				if (this.getColor()==WHITE){
					if (arrayEquals(move, arrayScale(Vy(),+1)) && targetPiece==null) return true;
					if (arrayEquals(move, arrayScale(Vy(),+2)) && this.getMovedYet()==false) return true;
					if (arrayEquals(move, arrayScaleAdd(+1, Vx(), +1, Vy())) && (targetPiece!=null && targetPiece.getColor()!=WHITE)) return true;
					if (arrayEquals(move, arrayScaleAdd(-1, Vx(), +1, Vy())) && (targetPiece!=null && targetPiece.getColor()!=WHITE)) return true;
					}
				else if (this.getColor()==BLACK){
					if (arrayEquals(move, arrayScale(Vy(),-1)) && targetPiece==null) return true;
					if (arrayEquals(move, arrayScale(Vy(),-2)) && this.getMovedYet()==false) return true;
					if (arrayEquals(move, arrayScaleAdd(+1, Vx(), -1, Vy())) && (targetPiece!=null && targetPiece.getColor()!=BLACK)) return true;
					if (arrayEquals(move, arrayScaleAdd(-1, Vx(), -1, Vy())) && (targetPiece!=null && targetPiece.getColor()!=BLACK)) return true;
				}
			}
		return false;
	}
}
