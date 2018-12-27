package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.*;

public class Bishop extends Piece {
	public Bishop(Color color, String name) {
		super(color, name);
	}
	
	public Bishop(Bishop bishop){
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
