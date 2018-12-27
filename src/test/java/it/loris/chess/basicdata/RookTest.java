package it.loris.chess.basicdata;

import static org.junit.Assert.*;
import static it.loris.chess.util.MyMath.*;
import org.junit.Test;

public class RookTest {
	Rook test1 = new Rook(Color.WHITE, "Rook");
	
	@Test
	public void testLegalMoveAndCapture() {
		assertEquals(true, test1.legalMove(array(0, 3)) && test1.legalCapture(array(0, 3)));
		assertEquals(true, test1.legalMove(array(-3, 0)) && test1.legalCapture(array(-3, 0)));
		assertEquals(false, test1.legalMove(array(1, 2)) && test1.legalCapture(array(1, 2)));
	}

	@Test
	public void testCopy() {
		Rook test1copy = test1.copy();
		assertEquals(false, test1copy.equals(test1));
		assertEquals(true, test1copy.color.equals(test1.color));
		assertEquals(true, test1copy.name.equals(test1.name));
		assertEquals(false, test1copy.moveCount == test1.moveCount);
		assertEquals(true, test1copy.moveCount == test1.moveCount +1);
	}

}
