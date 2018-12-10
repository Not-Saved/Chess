package chess3.piece;

import static scuffedmath.ScuffedAlgebra2D.*;
import chess3.Color;

public class Queen extends Piece {
	public Queen(Color color, String name) {
		super(color, name);
	}
	
	Queen(Queen queen){
		super(queen);
	}
	
	@Override
	public Queen copy(){  
		return new Queen(this);
	} 
	
	@Override
	public boolean legalMove(int[] move) {
		if (isStraight(move) == false && isDiagonal(move) == false) return false;
		else return true;
	}
}