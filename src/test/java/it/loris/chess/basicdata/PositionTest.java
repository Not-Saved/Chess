package it.loris.chess.basicdata;

import static org.junit.Assert.*;

import org.junit.Test;

import it.loris.chess.basicdata.Position;

public class PositionTest {
	
	@Test
	public void testSquareString() {
		Position testSquare = new Position("a1");
		assertEquals(0, testSquare.x);
		assertEquals(0, testSquare.y);
	}
	
	@Test
	public void testToArray() {
		Position testSquare = new Position(0,0);
		int[] testArray = {0,0};
		assertEquals(testArray[0], testSquare.toArray()[0]);
		assertEquals(testArray[1], testSquare.toArray()[1]);
	}

	@Test
	public void testToString() {
		Position testSquare = new Position(1,3);
		assertEquals("B4", testSquare.toString());
	}

}
