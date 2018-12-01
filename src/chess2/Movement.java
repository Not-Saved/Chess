package chess2;
import static myMath.BasicAlgebra2D.*;
import java.util.List;
import java.util.Vector;
public class Movement {
	
	public static boolean isStraight(int[] stArray, int[] fnArray) {
		int[] versor = (arrayAbs(arrayOfGenDir(stArray,fnArray)));
		if (arrayEquals(versor, Vx()) || arrayEquals(versor, Vy())) return true;
		else return false;
	}
	public static boolean isDiagonal(int[] stArray, int[] fnArray) {
		int[] distance = (arrayAbs(arrayDiff(fnArray, stArray)));
		if (distance[0] == distance[1]) return true;
		else return false;
	}
	public static int squareDistance(int[] stArray, int[] fnArray) {
		int[] distance = (arrayAbs(arrayDiff(fnArray, stArray)));
		if (isStraight(stArray,fnArray)==false && isDiagonal(stArray,fnArray)==false) throw new IllegalArgumentException();
		if (distance[0] == distance[1]) return distance[0];
		else return arrayMax(distance);
		
	}
	public static List<int[]> path(int[] stArray, int[] fnArray){
		List<int[]> path = new Vector<>();
		if (isDiagonal(stArray, fnArray)==false && isStraight(stArray, fnArray)==false) throw new IllegalArgumentException();
		for (int k=1; k < arrayMax(arrayAbs(arrayDiff(fnArray, stArray))); ++k) {
			path.add(arrayScaleAdd(1, stArray, k, (arrayOfGenDir(stArray, fnArray))));
		}
		return path;
	}
}
