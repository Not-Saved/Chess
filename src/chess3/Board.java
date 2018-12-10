package chess3;

import java.util.HashMap;
import java.util.Map;

import chess3.piece.Piece;
import chess3.piece.PieceBuilder;

public class Board implements ReadableBoard{
	private Map<Piece, Square> boardState = new HashMap<>();
	public Board() {
		PieceBuilder.getPieces(boardState);
	}
	
	@Override
	public Square getSquare(String string) {
		for (Square i : boardState.values()) {
			if (string.equalsIgnoreCase(i.toString())) return i;
		}
		return null;
	}
	
	@Override
	public Square getSquare(int x, int y) {
		for (Square i : boardState.values()) {
			if (i.x ==x && i.y ==y) return i;
		}
		return null;
	}
	
	@Override
	public Piece getPiece(String string) {
		for (Piece i : boardState.keySet()) {
			if (string.equalsIgnoreCase(i.name)) return i;
		}
		return null;
	}
	
	@Override
	public Square get(Piece piece) {
		if (boardState.get(piece) == null) {
			throw new IllegalArgumentException("Selected square doesn't exist!");
		}
		else {
			return boardState.get(piece);
		}
	}
	
	@Override
	public Piece get(Square square) {
		for (Piece i : boardState.keySet()) {
			if (square.equals(boardState.get(i))) return i;
		}
		throw new IllegalArgumentException("Selected piece doesn't exist!");
	}
	
	@Override
	public boolean contains(Object object) {
		if(boardState.containsKey(object)) return true;
		if(boardState.containsValue(object)) return true;
		else return false;
	}
	
	@Override
	public Map<Piece, Square> boardCopy() {
		Map<Piece, Square> copy = new HashMap<>();
		copy.putAll(boardState);
		return copy;
	}
	
	public void setBoardState(Map<Piece, Square> newBoardState) {
		this.boardState = newBoardState;
	}
	
	public void paleoGraphic() {
		for (int i=8; i>0; --i) {
			System.out.print("\t");
			for (char j='A'; j<'I'; ++j) {
				if (getSquare(j+""+i)!=null) System.out.print("[" +get(getSquare(j+""+i)).name +"]\t");
				else System.out.print("[    ]\t");
			}
			System.out.println();
			
		}
	}
}
