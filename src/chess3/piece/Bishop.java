package chess3.piece;
import static scuffedmath.ScuffedAlgebra2D.*;
import chess3.Color;

public class Bishop extends Piece {
	public Bishop(Color color, String name) {
		super(color, name);
	}
	
	Bishop(Bishop bishop){
		super(bishop);
	}
	
	@Override
	public Bishop copy(){  
		return new Bishop(this);
	} 

	@Override
	public boolean legalMove(int[] move) {
		if (isDiagonal(move) == false) return false;
		else return true;
	}
}