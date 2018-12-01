package chess2;
import static chess2.PieceBuilder.getPieces;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private Map<Piece, Square> Pieces = new HashMap<>();
	public Board() {
		getPieces(Pieces);
	}
	
	public Piece getPiece(String name) {
		for(Piece i : Pieces.keySet()) {
			if(name.equalsIgnoreCase(i.toString())) return i;
		}
		throw new IllegalArgumentException();
	}
	public Square getSquare(String name) {
		for(Square i : Pieces.values()) {
			if(name.equalsIgnoreCase(i.toString())) return i;
		}
		return new Square(name);
	}
	public Square getSquare(int[] FileRank) {
		for(Square i : Pieces.values()) {
			if(FileRank==i.getFileRank()) return i;
		}
		return new Square(FileRank);
	}
	public Piece isOccupiedBy(Square square) {
		for(Piece i : Pieces.keySet()) {
				if(square.equals(Pieces.get(i))) return i;
		}
		return null;
	}
	public Square occupies(Piece piece) {
		return Pieces.get(piece);
	}
	public boolean contains(String name) {
		for(Piece i : Pieces.keySet()) {
			if(name.equalsIgnoreCase(i.toString())) return true;
		}
		return false;
	}
	@Override
	public String toString() {
		String string="";
		for(Piece i : Pieces.keySet()) string+= i.toString() +" "+ Pieces.get(i).toString() +"\n";	
		return string;
	}
	
	public void removePiece(Piece piece) {
		Pieces.remove(piece);
	}
}
