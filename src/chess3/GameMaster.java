package chess3;

import java.io.IOException;
import java.util.Map;
import chess3.piece.Piece;

public class GameMaster {
	private final ReadableBoard board;
	public GameMaster(ReadableBoard board) {
		this.board = board;
	}
		
	public Map<Piece, Square> executeMove(String piece, String square) throws IOException{
		Map <Piece, Square> copyBoardState = board.boardCopy();
		Move myMove = createMove(piece, square);
		
		if (myMove.isShortCastling()) {
			return shortCastling(copyBoardState, myMove);
		}
		else if (myMove.isLongCastling()) {
			return longCastling(copyBoardState, myMove);
		}
		else {
			return normalMove(copyBoardState, myMove);
		}
	}
	
	public Move createMove(String piece, String square) throws IOException{
		//IF PIECE IS NOT A VALID STRING//
		if (board.getPiece(piece) == null) {
			throw new IOException("Selected piece doesn't exist!");
		}
		//CREATE THE APPROPRIATE MOVE//
		if (board.getSquare(square) == null) {
			return new Move(board.getPiece(piece), board.get(board.getPiece(piece)), null, new Square(square));
		}
		else {
			return new Move(board.getPiece(piece), board.get(board.getPiece(piece)), board.get(board.getSquare(square)), board.getSquare(square));
		}				
	}
	
	public Map<Piece, Square> shortCastling(Map<Piece, Square> copyBoardState, Move myMove) throws IOException {
		switch(myMove.fromPiece.color) {
		case WHITE:
			checkPath(myMove, new Square("e1"), new Square("h1"));
			if (board.getPiece("W_R2").moveCount != 0) {
				throw new IOException("Invalid Move! Castling not allowed!");
			}
			copyBoardState.put(board.getPiece("W_R2").copy(), new Square("f1"));
			copyBoardState.remove(board.getPiece("W_R2"));
			break;
		case BLACK:
			checkPath(myMove, new Square("e8"), new Square("h8"));
			if (board.getPiece("B_R2").moveCount != 0) {
				throw new IOException("Invalid Move! Castling not allowed!");
			}
			copyBoardState.put(board.getPiece("B_R2").copy(), new Square("f8"));
			copyBoardState.remove(board.getPiece("B_R2"));
			break;
		}
		copyBoardState.put(myMove.movingPiece, myMove.toSquare);
		copyBoardState.remove(myMove.fromPiece);
		return copyBoardState;
	}
	
	public Map<Piece, Square> longCastling(Map<Piece, Square> copyBoardState, Move myMove) throws IOException {
		switch(myMove.fromPiece.color) {
		case WHITE:
			checkPath(myMove, new Square("e1"), new Square("a1"));
			if (board.getPiece("W_R1").moveCount != 0) {
				throw new IOException("Invalid Move! Castling not allowed!");
			}
			copyBoardState.put(board.getPiece("W_R1").copy(), new Square("d1"));
			copyBoardState.remove(board.getPiece("W_R1"));
			break;
		case BLACK:
			checkPath(myMove, new Square("e8"), new Square("a8"));
			if (board.getPiece("B_R1").moveCount != 0) {
				throw new IOException("Invalid Move! Castling not allowed!");
			}
			copyBoardState.put(board.getPiece("B_R1").copy(), new Square("d8"));
			copyBoardState.remove(board.getPiece("B_R1"));
			break;
		}
		copyBoardState.put(myMove.movingPiece, myMove.toSquare);
		copyBoardState.remove(myMove.fromPiece);
		return copyBoardState;
	}
	
	public Map<Piece, Square> normalMove(Map <Piece, Square> copyBoardState, Move myMove) throws IOException {
		//CHECK PATH//
		checkPath(myMove, myMove.fromSquare, myMove.toSquare);
		//CHECK EN PASSANT//
		if (myMove.toPiece == null && myMove.fromPiece.legalMove(myMove.moveArray) == false && myMove.fromPiece.legalCapture(myMove.moveArray) == true) {
			copyBoardState.remove(enPassant(myMove));
		}	
		//CHECK IF IT'S A CAPTURE//
		else if (myMove.isLegalMove() == true && myMove.isLegalCapture() == true) {
			copyBoardState.remove(myMove.toPiece);
		}
		//IF IT'S NOT A CAPTURE//
		copyBoardState.remove(myMove.fromPiece);
		copyBoardState.put(myMove.movingPiece, myMove.toSquare);
		return copyBoardState;
	}
	
	public Piece enPassant(Move myMove) throws IOException {
		//FIND THE SQUARE BEHIND THE SQUARE THE PIECE IS MOVING TO//
		Square squareBehindToSquare;
		switch (myMove.fromPiece.color) {
		case WHITE: 
			squareBehindToSquare = board.getSquare(myMove.toSquare.x, myMove.toSquare.y-1); 
			break;
		case BLACK: 
			squareBehindToSquare = board.getSquare(myMove.toSquare.x, myMove.toSquare.y+1); 
			break;
		default: throw new IllegalArgumentException("Should have never went here!");
		}
		//CHECK IF IT'S A LEGAL ENPASSANT//
		if (board.contains(squareBehindToSquare) && myMove.fromPiece.color != board.get(squareBehindToSquare).color) {
			return board.get(squareBehindToSquare);
		}
		else {
			throw new IOException("Invalid Move! Selected piece can't capture that way!");
		}
	}
		
	public void checkPath(Move myMove, Square fromSquare, Square toSquare) throws IOException {
		for (Square i : myMove.path(fromSquare.toArray(), toSquare.toArray())) {
			if(board.getSquare(i.toString()) != null) {
				throw new IOException("Invalid move! Path not clear!");
			}
		}
	}
		
	public Color whoWon(ReadableBoard board) {
		if (board.contains(board.getPiece("W_K")) == false) {
			return Color.BLACK;
		}
		if (board.contains(board.getPiece("B_K")) == false) {
			return Color.WHITE;
		}
		else {
			return null;
		}
	}
}

