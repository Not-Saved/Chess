package it.loris.chess.basicdata;

public class Position {
	public final int x;
	public final int y;
		
	public Position(int x, int y) {
		if (x<0 || x>7 || y<0 || y>7) throw new IllegalArgumentException("Invalid position!");
		this.x=x;
		this.y=y;
	}
	
	public Position(int[] array) {
		this(array[0], array[1]);
	}

	public Position(String name) {
		this(name.toUpperCase().charAt(0) - 'A', name.toUpperCase().charAt(1) - '1');
		if (name.length() != 2) throw new IllegalArgumentException("Invalid position!");
	}
	
	public int[] toArray() {
		int[] array = new int[2];
		array[0]= x;
		array[1]= y;
		return array;
	}
	
	@Override
	public String toString() {
		return (char)(x + 'A') +""+ (char)(y + '1');
	}
}