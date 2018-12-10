package chess3;

import java.util.Map;
import chess3.piece.Piece;

public interface ReadableBoard {
	public Square getSquare(String string);
	public Square getSquare(int x,int y);
	public Piece getPiece(String string);
	public Square get(Piece piece);
	public Piece get(Square square);
	public boolean contains(Object object);
	public Map<Piece, Square> boardCopy();
}
