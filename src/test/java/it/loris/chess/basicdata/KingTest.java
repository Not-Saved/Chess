package it.loris.chess.basicdata;

import static it.loris.chess.util.MyMath.array;
import static org.junit.Assert.*;

import org.junit.Test;

public class KingTest {
	King test1 = new King(Color.WHITE, "Rook");
	King test1copy = test1.copy();
	
	@Test
	public void testLegalMoveAndCapture() {
		assertEquals(true, test1.legalMove(array(0, 1)) && test1.legalCapture(array(0, 1)));
		assertEquals(true, test1.legalMove(array(-1, 0)) && test1.legalCapture(array(-1, 0)));
		assertEquals(true, test1.legalMove(array(-1, -1)) && test1.legalCapture(array(-1, -1)));
		assertEquals(true, test1.legalMove(array(1, 1)) && test1.legalCapture(array(1, 1)));
		assertEquals(false, test1.legalMove(array(2, 0)) && test1.legalCapture(array(2, 0)));
		assertEquals(false, test1.legalMove(array(1, 2)) && test1.legalCapture(array(1, 2)));
		assertEquals(false, test1.legalMove(array(2, 2)) && test1.legalCapture(array(2, 2)));
	}

	@Test
	public void testLegalShortCastling() {
		assertEquals(true, test1.legalShortCastling(array(2, 0)));
		assertEquals(false, test1.legalShortCastling(array(-2, 0)));
		assertEquals(false, test1copy.legalShortCastling(array(2, 0)));
		assertEquals(false, test1copy.legalShortCastling(array(-2, 0)));
	}

	@Test
	public void testLegalLongCastling() {
		assertEquals(false, test1.legalLongCastling(array(2, 0)));
		assertEquals(true, test1.legalLongCastling(array(-2, 0)));
		assertEquals(false, test1copy.legalLongCastling(array(2, 0)));
		assertEquals(false, test1copy.legalLongCastling(array(-2, 0)));
	}

	@Test
	public void testCopy() {
		assertEquals(false, test1copy.equals(test1));
		assertEquals(true, test1copy.color.equals(test1.color));
		assertEquals(true, test1copy.name.equals(test1.name));
		assertEquals(false, test1copy.moveCount == test1.moveCount);
		assertEquals(true, test1copy.moveCount == test1.moveCount +1);
	}

}
