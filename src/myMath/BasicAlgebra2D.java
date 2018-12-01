package myMath;

import static java.lang.Math.*;

public class BasicAlgebra2D {
	private static int[] Vx = {1,0};
	private static int[] Vy = {0,1};
	public static int[] Vx() {
		return Vx.clone();
	}
	public static int[] Vy() {
		return Vy.clone();
	}
	public static boolean arrayEquals(int[] array1, int[] array2) {
		if (array1.length!=array2.length) throw new IndexOutOfBoundsException();
		for (int k=0; k<array1.length; ++k) {
			if (array1[k]!=array2[k]) return false;
		}
		return true;
	}
	public static int[] arrayScale(int[] array, int a) {
		int[] result = array.clone();
			for (int k=0; k<array.length; ++k) result[k]*=a;
			return result;
	}
	public static int[] arrayAbs(int[] array) {
		int[] result = array.clone();
		for (int k=0; k<array.length; ++k) result[k]=abs(array[k]);
		return result;
	}
	public static int arrayMax(int[] array) {
		int result=0;
		for (int k : array) if (k>result) result = k;
		return result;
	}
	public static int[] arrayAdd(int[] array1, int[] array2) {
		int[] result = array1.clone();
		if (array1.length!=array2.length) throw new IndexOutOfBoundsException();
		for (int k=0; k<array1.length; ++k) result[k]+=array2[k];
		return result;
	}
	public static int[] arrayScaleAdd(int a, int[] array1,int b, int[] array2) {
		return arrayAdd(arrayScale(array1,a), arrayScale(array2,b));
	}
	public static int[] arrayDiff(int[] array1, int[] array2) {
		int[] result = array1.clone();
		if (array1.length!=array2.length) throw new IndexOutOfBoundsException();
		for (int k=0; k<array1.length; ++k) result[k]-=array2[k];
		return result;
	}
	public static int[] arrayOfGenDir(int[] array1, int[] array2) {
		int[] result = arrayDiff(array2, array1).clone();
		for (int k=0; k<array1.length; ++k) result[k]=(int)signum((double)result[k]);
		return result;
	}

}

