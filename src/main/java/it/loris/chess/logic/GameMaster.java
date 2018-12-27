package it.loris.chess.logic;

import static it.loris.chess.util.MyMath.*;
import it.loris.chess.basicdata.*;

public class GameMaster {
	
	public static Board makeMove(Board board, Square movingFrom, Square movingTo) {
		int[] moveArray = arrayDiff(movingTo.position.toArray(), movingFrom.position.toArray());
		checkPath(board, movingFrom, movingTo);
		Square movingFromPromoted = promote(board, movingFrom, movingTo);
		board = board.add(movingFromPromoted).remove(movingFrom);
		movingFrom = movingFromPromoted;
		if (movingTo.piece == null) {
			if (movingFrom.piece.legalShortCastling(moveArray)) {
				return shortCastling(board, movingFrom, movingTo);
			}
			if (movingFrom.piece.legalLongCastling(moveArray)) {
				return longCastling(board, movingFrom, movingTo);
			}			
			if (movingFrom.piece.legalMove(moveArray) == false && movingFrom.piece.legalCapture(moveArray)) {
				return enPassant(board, movingFrom, movingTo);
			}
			if (movingFrom.piece.legalMove(moveArray)) {
				return board.moveAndReplace(movingFrom, movingTo);
			}
			else {
				throw new IllegalArgumentException("Invalid move! Piece can't move that way!");
			}
		}
		else {
			if (movingTo.piece.color != movingFrom.piece.color && movingFrom.piece.legalCapture(moveArray)) {
				return board.moveAndReplace(movingFrom, movingTo);
			}
			if (movingTo.piece.color == movingFrom.piece.color) {
				throw new IllegalArgumentException("Invalid move! Square occupied by friendly piece!");
			}	
			else {
				throw new IllegalArgumentException("Invalid move! Piece can't capture that way");
			}	
		}
	}
	
	private static void checkPath(Board board, Square movingFrom, Square movingTo) {
		int[] moveArray = arrayDiff(movingTo.position.toArray(), movingFrom.position.toArray());
		if (isDiagonal(moveArray) || isStraight(moveArray)) {
			for (int k=1; k < arrayMax(arrayAbs(moveArray)); k++) {
				if (board.contains(board.getPosition(arrayScaleAdd(1, movingFrom.position.toArray(), k, (arrayOfGenDir(moveArray)))))) {
					throw new IllegalArgumentException("Invalid move! Path not clear!");
				}
			}
		}
	}
	
	private static Board shortCastling(Board board, Square movingFrom, Square movingTo){
		char color = movingFrom.piece.color.toString().charAt(0); 
		checkPath(board, board.getPiece(color + "_K"), board.getPiece(color + "_R2"));
		if (board.getPiece(color + "_R2").piece.moveCount != 0) {
			throw new IllegalArgumentException("Invalid Move! Castling not allowed!");
		}
		Square oo = board.getPosition(array(5, movingFrom.position.y));
		return board.moveAndReplace(movingFrom, movingTo).moveAndReplace(board.getPiece(color + "_R2"), oo);
	}
	
	private static Board longCastling(Board board, Square movingFrom, Square movingTo){
		char color = movingFrom.piece.color.toString().charAt(0); 
		checkPath(board, board.getPiece(color + "_K"), board.getPiece(color + "_R1"));
		if (board.getPiece(color + "_R1").piece.moveCount != 0) {
			throw new IllegalArgumentException("Invalid Move! Castling not allowed!");
		}
		Square ooo = board.getPosition(array(3, movingFrom.position.y));
		return board.moveAndReplace(movingFrom, movingTo).moveAndReplace(board.getPiece(color + "_R1"), ooo);
	}
	
	private static Board enPassant(Board board, Square movingFrom, Square movingTo) {
		Square enPassant;
		switch (movingFrom.piece.color) {
		case WHITE: 
			enPassant = board.getPosition(arrayAdd(movingTo.position.toArray(), array(0, -1)));
			break;
		case BLACK: 
			enPassant = board.getPosition(arrayAdd(movingTo.position.toArray(), array(0, +1)));
			break;
		default: 
			throw new IllegalStateException("???");
		}
		if (board.contains(enPassant) && enPassant.piece.color != movingFrom.piece.color) {
			return board.moveAndReplace(movingFrom, movingTo).remove(enPassant);
		}	
		else {
			throw new IllegalArgumentException("Invalid Move! Illegal En Passant!");
		}
	}
	
	private static Square promote(Board board, Square movingFrom, Square movingTo) {
		if (movingFrom.piece.legalPromote(movingTo.position.toArray())) {
			return new Square(new Queen(movingFrom.piece.color, "Q" + movingFrom.piece.name.charAt(1)), movingFrom.position);
		}
		else return movingFrom;
	}
}
