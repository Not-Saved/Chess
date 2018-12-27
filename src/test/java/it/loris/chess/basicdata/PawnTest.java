package it.loris.chess.basicdata;

import static org.junit.Assert.*;
import static it.loris.chess.util.MyMath.*;
import org.junit.Test;

public class PawnTest {
	Pawn test1 = new Pawn(Color.WHITE, "pawn1");
	Pawn test2 = new Pawn(Color.BLACK, "pawn2");
	
	@Test
	public void testLegalMove() {
		assertEquals(true, test1.legalMove(array(0, 1)));
		assertEquals(false, test1.legalMove(array(0, -1)));
		assertEquals(false, test1.legalMove(array(1, 1)));
		assertEquals(false, test2.legalMove(array(0, 1)));
		assertEquals(true, test2.legalMove(array(0, -1)));
		assertEquals(false, test2.legalMove(array(1, -1)));
	}

	@Test
	public void testLegalCapture() {
		assertEquals(false, test1.legalCapture(array(0, 1)));
		assertEquals(true, test1.legalCapture(array(1, 1)));
		assertEquals(false, test1.legalCapture(array(1, -1)));
		assertEquals(false, test2.legalCapture(array(0, -1)));
		assertEquals(false, test2.legalCapture(array(1, 1)));
		assertEquals(true, test2.legalCapture(array(1, -1)));
	}

	@Test
	public void testLegalPromote() {
		assertEquals(true, test1.legalPromote(array(0, 7)));
		assertEquals(false, test1.legalPromote(array(0, 0)));
		assertEquals(false, test2.legalPromote(array(0, 7)));
		assertEquals(true, test2.legalPromote(array(0, 0)));
	}

	@Test
	public void testCopy() {
		Pawn test1copy = test1.copy();
		assertEquals(false, test1copy.equals(test1));
		assertEquals(true, test1copy.color.equals(test1.color));
		assertEquals(true, test1copy.name.equals(test1.name));
		assertEquals(false, test1copy.moveCount == test1.moveCount);
		assertEquals(true, test1copy.moveCount == test1.moveCount +1);
	}

}
