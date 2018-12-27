package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.array;
import static org.junit.Assert.*;

import org.junit.Test;

public class KnightTest {
	Knight test1 = new Knight(Color.WHITE, "Knight");
	
	@Test
	public void testLegalMove() {
		assertEquals(true, test1.legalMove(array(1, 2)) && test1.legalCapture(array(1, 2)));
		assertEquals(true, test1.legalMove(array(1, -2)) && test1.legalCapture(array(1, -2)));
		assertEquals(true, test1.legalMove(array(2, 1)) && test1.legalCapture(array(2, 1)));
		assertEquals(true, test1.legalMove(array(-2, 1)) && test1.legalCapture(array(-2, 1)));
		assertEquals(false, test1.legalMove(array(3, 0)) && test1.legalCapture(array(3, 0)));
		assertEquals(false, test1.legalMove(array(3, 3)) && test1.legalCapture(array(3, 3)));
	}

	@Test
	public void testCopy() {
		Knight test1copy = test1.copy();
		assertEquals(false, test1copy.equals(test1));
		assertEquals(true, test1copy.color.equals(test1.color));
		assertEquals(true, test1copy.name.equals(test1.name));
		assertEquals(false, test1copy.moveCount == test1.moveCount);
		assertEquals(true, test1copy.moveCount == test1.moveCount +1);
	}

}
