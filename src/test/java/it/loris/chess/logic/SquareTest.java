package it.loris.chess.logic;

import it.loris.chess.logic.Square;
import org.junit.Test;
import static org.junit.Assert.*;

import it.loris.chess.basicdata.Color;
import it.loris.chess.basicdata.Pawn;
import it.loris.chess.basicdata.Position;

public class SquareTest {
	Square test = new Square(new Pawn(Color.WHITE, "pawn"), new Position(0, 0));
	
	@Test
	public void testMove() {
		int[] array = {1, 1};
		Square testCopy = test.move(array);
		assertEquals(false, testCopy.equals(test));
		assertEquals(false, testCopy.piece.equals(test.piece));
		assertEquals(false, testCopy.position.equals(test.position));
		assertArrayEquals(array, testCopy.position.toArray());
	}

}
