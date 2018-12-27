package it.loris.chess.logic;

import static org.junit.Assert.*;

import it.loris.chess.logic.Board;
import it.loris.chess.logic.BoardBuilder;
import it.loris.chess.logic.GameMaster;
import org.junit.Test;

public class GameMasterTest {

	Board board = new Board(BoardBuilder.buildAtStart());
	
	@Test
	public void testMakeMove() {
		assertEquals(false, board == GameMaster.makeMove(board, board.getPiece("W_P1"), board.getPosition("a3")));
		assertEquals(true, board.size() == GameMaster.makeMove(board, board.getPiece("W_P1"), board.getPosition("a3")).size());
	}

}
