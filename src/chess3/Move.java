package chess3;

import static scuffedmath.ScuffedAlgebra2D.*;
import java.io.IOException;
import java.util.Vector;
import chess3.piece.Piece;
import chess3.piece.Queen;

public class Move {
	public final Piece fromPiece;
	public final Square fromSquare;
	public final Piece toPiece;
	public final Square toSquare;
	public final int[] moveArray;
	public final Piece movingPiece;
		
	public Move(Piece fromPiece, Square fromSquare, Piece toPiece, Square toSquare) throws IOException {
		this.fromPiece = fromPiece;
		this.fromSquare = fromSquare;
		this.toPiece = toPiece;
		this.toSquare = toSquare;
		this.moveArray = arrayDiff(toSquare.toArray(), fromSquare.toArray());	
		
		//EXCEPTIONS HERE//
		isLegalMove();
		isLegalCapture();
		
		movingPiece = createNewFromPiece();
	}
		
	public boolean isLegalMove() throws IOException{
		//SAME COLOR//
		if (toPiece != null && fromPiece.color == toPiece.color) {
			throw new IOException("Invalid Move! Selected square occupied by friendly piece!");
		}
		//CASTLINGS//
		if (isShortCastling() || isLongCastling()) {
			return true;
		}
		//ILLEGAL MOVE//
		if (toPiece == null && fromPiece.legalMove(moveArray) == false && fromPiece.legalCapture(moveArray) == false) {
			throw new IOException("Invalid Move! Selected piece can't move that way!");
		}	
		return true;
	}
	
	public boolean isLegalCapture() throws IOException{
		//ILLEGAL CAPTURE//
		if (toPiece != null && fromPiece.legalCapture(moveArray) == false) {
			throw new IOException("Invalid Move! Selected piece can't capture that way!");
		}
		//STANDARD CAPTURE//
		if (toPiece != null && fromPiece.legalCapture(moveArray) == true) {
			return true;
		}
		//IF IT'S NOT A MOVE BUT NOT A CAPTURE//
		else {
			return false;
		}
	}
	
	public boolean isShortCastling() {
		if (toPiece == null && fromPiece.legalShortCastling(moveArray) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isLongCastling() {
		if (toPiece == null && fromPiece.legalLongCastling(moveArray) == true) {
			return true;
		}
		else {
			return false;
		}
	}
			
	public Vector<Square> path(int[] fromArray, int[] toArray){
		Vector<Square> squareTraversed = new Vector<>();
		int[] move = arrayDiff(toArray,fromArray);
		if (isDiagonal(move) || isStraight(move)) {
			for (int k=1; k < arrayMax(arrayAbs(move)); ++k) {
				squareTraversed.add(new Square(arrayScaleAdd(1, fromArray, k, (arrayOfGenDir(move)))));
			}
		}
		return squareTraversed;
	}
	
	private Piece createNewFromPiece() {
		if (fromPiece.legalPromote(toSquare.toArray()) == true) {
			//ALWAYS PROMOTE TO QUEEN//
			return new Queen(fromPiece.color, "Q"+ fromPiece.name.charAt(3));
		}
		else {
			return fromPiece.copy();
		}
	}
}
