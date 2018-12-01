package chess2;
import static chess2.Movement.path;

public abstract class Piece{
	private Color color;
	private String name;
	private boolean movedYet;
	
	public Piece(Color color, String name){
		this.color=color;
		this.name= color.toString().charAt(0) +"_"+ name;
		this.movedYet=false;
	}
	
	public Color getColor(){
		return color;
	}
	@Override
	public String toString(){
		return name;
	}
	public boolean getMovedYet(){
		return movedYet;
	}
	public void setMovedYet(boolean movedYet){
		this.movedYet=movedYet;
	}
	public boolean equals(Piece piece){
		return piece.name.equalsIgnoreCase(this.name);
	}
		
	public boolean legalPath(Board board, Square startSquare, Square targetSquare) {
		int[] stPos = startSquare.getFileRank();
		int[] fnPos = targetSquare.getFileRank();
		try {
			for (int[] i : path(stPos,fnPos)) {
				if (board.isOccupiedBy(board.getSquare(i))!=null) return false;
			}
			return true;
		}
		catch (IllegalArgumentException exc) {
			return false;
		}
	}
	public abstract boolean legalMove(Board board, Square targetSquare);
}
