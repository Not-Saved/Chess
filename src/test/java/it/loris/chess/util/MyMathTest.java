package it.loris.chess.util;

import static it.loris.chess.util.MyMath.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import it.loris.chess.util.MyMath;

public class MyMathTest {

	@Test
	public void testArray() {
		int[] testArray = {1, 2};
		assertEquals(true, Arrays.equals(testArray, array(1,2)));
	}
	
	@Test
	public void testArrayScale() {
		assertEquals(true, Arrays.equals(array(2, 0), arrayScale(MyMath.Vx(), 2)));
		assertEquals(false, Arrays.equals(array(0, 2), arrayScale(MyMath.Vx(), 2)));
	}

	@Test
	public void testArrayAbs() {
		assertEquals(true, Arrays.equals(array(2, 0), arrayAbs(array(-2, 0))));
		assertEquals(false, Arrays.equals(array(-2, 0), arrayAbs(array(-2, 0))));
	}

	@Test
	public void testArrayMax() {
		assertEquals(1, arrayMax(array(-2, 1)));
		assertEquals(-1, arrayMax(array(-2, -1)));
		assertEquals(0, arrayMax(array(0, 0)));
	}
	
	@Test
	public void testArrayAdd() {
		assertEquals(true, Arrays.equals(array(0, 0), arrayAdd(array(-2, -1), array(2, 1))));
		assertEquals(false, Arrays.equals(array(0, 0), arrayAdd(array(2, 1), array(2, 1))));
	}

	@Test
	public void testArrayScaleAdd() {
		assertEquals(true, Arrays.equals(array(0, 0), arrayScaleAdd(3, array(-2, -1), 3, array(2, 1))));
		assertEquals(false, Arrays.equals(array(4, 2), arrayScaleAdd(2, array(2, 1), 2, array(2, 1))));
	}

	@Test
	public void testArrayDiff() {
		assertEquals(true, Arrays.equals(array(1, -1), arrayDiff(MyMath.Vx(), MyMath.Vy())));
		assertEquals(true, Arrays.equals(array(2, -5), arrayDiff(array(3, -1), array(1, 4))));
		assertEquals(false, Arrays.equals(array(2, -3), arrayDiff(array(3, -1), array(1, 4))));
	}

	@Test
	public void testArrayOfGenDir() {
		assertEquals(true, Arrays.equals(array(1, 0, -1), arrayOfGenDir(array(1, 0, -8))));
		assertEquals(false, Arrays.equals(array(1, 0, 1), arrayOfGenDir(array(1, 0, -8))));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOOBException() {
		arrayAdd(array(-2, -1), array(1, 0, 1));
	}

	@Test
	public void testIsStraight() {
		assertEquals(false, isStraight(array(2, 1)));
		assertEquals(true, isStraight(array(2, 0)));
	}

	@Test
	public void testIsDiagonal() {
		assertEquals(false, isDiagonal(array(2, 1)));
		assertEquals(true, isDiagonal(array(2, 2)));
	}

	@Test
	public void testSquareDistance() {
		assertEquals(2, squareDistance(array(2, 2)));
		assertEquals(2, squareDistance(array(-2, 2)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSquareDistanceIndexOOBException() {
		squareDistance(array(1, 3));
	}

}
