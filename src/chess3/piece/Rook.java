package chess3.piece;

import static scuffedmath.ScuffedAlgebra2D.*;
import chess3.Color;

public class Rook extends Piece {
	public Rook(Color color, String name) {
		super(color, name);
	}
	
	Rook(Rook rook){
		super(rook);
	}
	
	@Override
	public Rook copy(){  
		return new Rook(this);
	}  
	
	@Override
	public boolean legalMove(int[] move) {
		if (isStraight(move) == false) return false;
		else return true;
	}
	
}