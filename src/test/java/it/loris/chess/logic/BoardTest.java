package it.loris.chess.logic;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import it.loris.chess.logic.Board;
import it.loris.chess.logic.BoardBuilder;
import it.loris.chess.logic.Square;
import org.junit.Test;
import it.loris.chess.basicdata.Position;

public class BoardTest {
	
	Board board = new Board(BoardBuilder.buildAtStart());
	Board testBoard = new Board(new ArrayList<>());
	
	@Test
	public void testGet() {
		assertEquals("W_P1", board.getPiece("W_P1").piece.toString());
		assertEquals("B2", board.getPosition("B2").position.toString());
	}
		
	@Test
	public void testAdd() {
		int[] testArray = {1,1};
		Square testPieceSquare = new Square(new Position(testArray));
		Board newBoard = testBoard.add(testPieceSquare);
		assertEquals(false, newBoard == testBoard);
		assertEquals(true, newBoard.contains(testPieceSquare));
	}
	
	@Test
	public void testRemove() {
		int[] testArray = {1,1};
		Square testPieceSquare = new Square(new Position(testArray));
		Board testBoardCopy = testBoard.add(testPieceSquare);
		Board newBoard = testBoardCopy.remove(testPieceSquare);
		assertEquals(false, newBoard == testBoardCopy);
		assertEquals(true, testBoardCopy.contains(testPieceSquare));
		assertEquals(false, newBoard.contains(testPieceSquare));
	}
	
	@Test
	public void testMoveAndReplace() {
		Square testPieceSquare1 = board.getPiece("W_P1");
		Square testPieceSquare2 = board.getPiece("W_P2");
		Board newBoard = board.moveAndReplace(testPieceSquare1, testPieceSquare2);
		assertEquals(false, newBoard == board);
		assertEquals(true, board.contains(testPieceSquare1));
		assertEquals(true, board.contains(testPieceSquare2));
		assertEquals(newBoard.getPiece("W_P1").position.x, testPieceSquare2.position.x);
	}
	
	@Test
	public void testCopyBoard() {
		Board boardCopy = board.copy();
		assertEquals(false, boardCopy == board);
		assertEquals(board.getPiece("W_P1"), boardCopy.getPiece("W_P1"));
	}
}
