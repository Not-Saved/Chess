package it.loris.chess.basicdata;

public abstract class Piece {
	public final Color color;
	public final String name;
	public final int moveCount;
	
	public Piece(Color color, String name) {
		this.color = color;
		this.name = name;
		this.moveCount = 0;
	}
	
	public Piece(Piece piece){
		color = piece.color;
		name = piece.name;
		moveCount = piece.moveCount + 1;
	}
	
	public abstract Piece copy();
	
	public abstract boolean legalMove(int[] move);
	
	public String toString() {
		return (color.toString().charAt(0) +"_"+ name).toUpperCase();
	}
	
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
