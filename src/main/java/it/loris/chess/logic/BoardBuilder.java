package it.loris.chess.logic;

import java.util.ArrayList;
import java.util.List;
import it.loris.chess.basicdata.*;

public class BoardBuilder {
	
	public static List<Square> buildAtStart() {
		List<Square> pieces = new ArrayList<>();
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
			pieces.add(new Square(new Pawn(color, "P1"), new Position(0, frontRow)));
			pieces.add(new Square(new Pawn(color, "P2"), new Position(1, frontRow)));
			pieces.add(new Square(new Pawn(color, "P3"), new Position(2, frontRow)));
			pieces.add(new Square(new Pawn(color, "P4"), new Position(3, frontRow)));
			pieces.add(new Square(new Pawn(color, "P5"), new Position(4, frontRow)));
			pieces.add(new Square(new Pawn(color, "P6"), new Position(5, frontRow)));
			pieces.add(new Square(new Pawn(color, "P7"), new Position(6, frontRow)));
			pieces.add(new Square(new Pawn(color, "P8"), new Position(7, frontRow)));
			pieces.add(new Square(new Rook(color, "R1"), new Position(0, backRow)));
			pieces.add(new Square(new Rook(color, "R2"), new Position(7, backRow)));
			pieces.add(new Square(new Knight(color, "K1"), new Position(1, backRow)));
			pieces.add(new Square(new Knight(color, "K2"), new Position(6, backRow)));
			pieces.add(new Square(new Bishop(color, "B1"), new Position(2, backRow)));
			pieces.add(new Square(new Bishop(color, "B2"), new Position(5, backRow)));
			pieces.add(new Square(new Queen(color, "Q"), new Position(3, backRow)));
			pieces.add(new Square(new King(color, "K"), new Position(4, backRow)));
		}
		return pieces;
	}
}
