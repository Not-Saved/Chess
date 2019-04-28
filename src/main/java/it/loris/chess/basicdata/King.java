package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.isDiagonal;
import static it.loris.chess.util.MyMath.isStraight;
import static it.loris.chess.util.MyMath.squareDistance;

import java.util.Arrays;

public class King extends Piece {
	public King(Color color, String name) {
		super(color, name);
	}
	
	public King(King king){
		super(king);
	}
	
	@Override
	public King copy(){  
		return new King(this);
	} 

	@Override
	public boolean legalMove(int[] move) {
		if ((isStraight(move) == false && isDiagonal(move) == false) || squareDistance(move)>1) return false;
		else return true;
	}
	
	@Override
	public boolean legalShortCastling(int[] move) {
		int[] oo = {2, 0};
		if (Arrays.equals(move, oo) && moveCount == 0) return true;
		else return false;
	}
	
	@Override
	public boolean legalLongCastling(int[] move) {
		int[] ooo = {-2, 0};
		if (Arrays.equals(move, ooo) && moveCount == 0) return true;
		else return false;
	}
}
