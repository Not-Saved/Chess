package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.arrayAbs;
import java.util.Arrays;

public class Knight extends Piece {
	public Knight(Color color, String name) {
		super(color, name);
	}
	
	public Knight(Knight knight){
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
		if (Arrays.equals(arrayAbs(move), option1) || Arrays.equals(arrayAbs(move), option2)) return true;
		else return false;
	}
}
