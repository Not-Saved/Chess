package chess3.piece;

import java.util.Map;
import chess3.Color;
import chess3.Square;

public class PieceBuilder {
	public static Map<Piece, Square> getPieces(Map<Piece, Square> pieces) {
		int frontRow=0; int backRow=0;
		for (Color color : Color.values()) {
			switch(color) {
				case WHITE: 
					frontRow=1; 
					backRow=0; 
					break;
				case BLACK: 
					frontRow=6; 
					backRow=7; 
					break;
			}
			pieces.put(new Pawn(color, "P1"), new Square(0,frontRow));
			pieces.put(new Pawn(color, "P2"), new Square(1,frontRow));
			pieces.put(new Pawn(color, "P3"), new Square(2,frontRow));		
			pieces.put(new Pawn(color, "P4"), new Square(3,frontRow));		
			pieces.put(new Pawn(color, "P5"), new Square(4,frontRow));		
			pieces.put(new Pawn(color, "P6"), new Square(5,frontRow));		
			pieces.put(new Pawn(color, "P7"), new Square(6,frontRow));		
			pieces.put(new Pawn(color, "P8"), new Square(7,frontRow));		
			pieces.put(new Rook(color, "R1"), new Square(0,backRow));		
			pieces.put(new Rook(color, "R2"), new Square(7,backRow));
			pieces.put(new Knight(color, "K1"), new Square(1,backRow));		
			pieces.put(new Knight(color, "K2"), new Square(6,backRow));	
			pieces.put(new Bishop(color, "B1"), new Square(2,backRow));		
			pieces.put(new Bishop(color, "B2"), new Square(5,backRow));	
			pieces.put(new Queen(color, "Q"), new Square(3,backRow));	
			pieces.put(new King(color, "K"), new Square(4,backRow));		
				
		}
		return pieces;
	}
}
