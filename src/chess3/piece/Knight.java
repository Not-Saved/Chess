package chess3.piece;

import chess3.Color;
import static scuffedmath.ScuffedAlgebra2D.*;

public class Knight extends Piece {
	public Knight(Color color, String name) {
		super(color, name);
	}
	
	Knight(Knight knight){
		super(knight);
	}
	
	@Override
	public Knight copy(){  
		return new Knight(this);
	} 

	@Override
	public boolean legalMove(int[] move) {
		int[] option1 = {1,2};
		int[] option2 = {2,1};
		if (arrayEquals(arrayAbs(move), option1) || arrayEquals(arrayAbs(move), option2)) return true;
		else return false;
	}
}