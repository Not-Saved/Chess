package it.loris.chess.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import it.loris.chess.basicdata.Position;

public class Board {
	private final List<Square> boardState;
	
	public Board(List<Square> boardState){
		this.boardState = boardState;
	}
	
	public Square getPosition(String name) {
		for (Square i : boardState) {
			if (i.position.toString().equalsIgnoreCase(name)) return i;
		}
		return new Square(new Position(name));
	}
	
	public Square getPosition(int[] position) {
		for(Square i : boardState) {
			if (Arrays.equals(position, i.position.toArray())) return i;
		}
		return new Square(new Position(position));
	}

	public Square getPosition(int x, int y) {
		for(Square i : boardState) {
			if (i.position.x == x && i.position.y == y) return i;
		}
		return new Square(new Position(x, y));
	}
	
	public Square getPiece(String name) {
		for (Square i : boardState) {
			if (i.piece.toString().equalsIgnoreCase(name)) return i;
		}
		throw new IllegalArgumentException("Can't find "+name+"!");
	}
	
	public int size() {
		return boardState.size();
	}
		
	public boolean contains(Square searching) {
		return boardState.stream().anyMatch(p -> Arrays.equals(searching.position.toArray(), p.position.toArray()));
	}
			
	public Board copy() {
		return new Board(new ArrayList<>(this.boardState));
	}

	public List<Square> copyBoardState() {
		return new ArrayList<>(this.boardState);
	}
	
	public Board add(Square adding){
		List<Square> boardStateCopy = new ArrayList<>(this.boardState);
		boardStateCopy.add(adding);
		return new Board(new ArrayList<>(boardStateCopy));
	}
	
	public Board remove(Square removing){
		List<Square> boardStateCopy = new ArrayList<>(this.boardState);
		boardStateCopy.remove(removing);
		return new Board(new ArrayList<>(boardStateCopy));
	}
		
	public Board moveAndReplace(Square movingFrom, Square movingTo){
		List<Square> boardStateCopy = new ArrayList<>(this.boardState);
		boardStateCopy.add(movingFrom.move(movingTo.position.toArray()));
		boardStateCopy.remove(movingFrom);
		boardStateCopy.remove(movingTo);
		return new Board(new ArrayList<>(boardStateCopy));
	}
	
	public void print() {
		for (int i=8; i>0; --i) {
			System.out.print("");
			for (char j='A'; j<'I'; ++j) {
				if (getPosition(j+""+i).piece != null) System.out.print("[" +(getPosition(j+""+i)).piece.toString() +"]\t");
				else System.out.print("[    ]\t");
			}
			System.out.println();
		}
	}
}
