package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.*;

public class Queen extends Piece {
	public Queen(Color color, String name) {
		super(color, name);
	}
	
	public Queen(Queen queen){
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
