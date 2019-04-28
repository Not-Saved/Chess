package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.*;

public class Rook extends Piece {
	public Rook(Color color, String name) {
		super(color, name);
	}
	
	public Rook(Rook rook){
		super(rook);
	}
	
	@Override
	public Rook copy(){  
		return new Rook(this);
	}  
	
	@Override
	public boolean legalMove(int[] move) {
		if (isStraight(move)) return true;
		else return false;
	}
	
}