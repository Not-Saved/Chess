package chess3.piece;
import chess3.Color;

public abstract class Piece {
	public final Color color;
	public final String name;
	public final int moveCount;
	
	public Piece(Color color, String name) {
		this.color = color;
		this.name = (color.toString().charAt(0) +"_"+ name).toUpperCase();
		this.moveCount = 0;
	}
	
	Piece(Piece piece){
		color = piece.color;
		name = piece.name;
		moveCount = piece.moveCount + 1;
	}
	
	public abstract Piece copy();
	
	public abstract boolean legalMove(int[] move);
	
	public boolean legalCapture(int[] move) {
		return legalMove(move);
	}
	
	public boolean legalPromote(int[] toSquareArray) {
		return false;
	}
	
	public boolean legalShortCastling(int[] move) {
		return false;
	}
	
	public boolean legalLongCastling(int[] move) {
		return false;
	}
}
