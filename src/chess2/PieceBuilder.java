package chess2;
import java.util.Map;

public class PieceBuilder {
	public static Map<Piece, Square> getPieces(Map<Piece, Square> Pieces) {
		int frontRow=0; int backRow=0;
		for (Color color : Color.values()) {
			switch(color) {
				case WHITE: frontRow=1; backRow=0; break;
				case BLACK: frontRow=6; backRow=7; break;
			}
			Pieces.put(new Pawn(color, "P1"), new Square(0,frontRow));
			Pieces.put(new Pawn(color, "P2"), new Square(1,frontRow));
			Pieces.put(new Pawn(color, "P3"), new Square(2,frontRow));		
			Pieces.put(new Pawn(color, "P4"), new Square(3,frontRow));		
			Pieces.put(new Pawn(color, "P5"), new Square(4,frontRow));		
			Pieces.put(new Pawn(color, "P6"), new Square(5,frontRow));		
			Pieces.put(new Pawn(color, "P7"), new Square(6,frontRow));		
			Pieces.put(new Pawn(color, "P8"), new Square(7,frontRow));		
			Pieces.put(new Rook(color, "R1"), new Square(0,backRow));		
			Pieces.put(new Rook(color, "R2"), new Square(7,backRow));		
			Pieces.put(new Knight(color, "K1"), new Square(1,backRow));		
			Pieces.put(new Knight(color, "K2"), new Square(6,backRow));		
			Pieces.put(new Bishop(color, "B1"), new Square(2,backRow));		
			Pieces.put(new Bishop(color, "B2"), new Square(5,backRow));		
			Pieces.put(new Queen(color, "Q"), new Square(3,backRow));		
			Pieces.put(new King(color, "K"), new Square(4,backRow));			
		}
		return Pieces;
	}
}
