package chess2;
import java.util.Scanner;

public class Player {
	private final Color color;
	private final String name;
	private Referee referee;
	
	public Player(String name, Color color, Referee referee) {
		this.name=name;
		this.color=color;
		this.referee=referee;
	}
	
	public Color getColor() {
		return color;
	}
	@Override
	public String toString() {
		return name +", "+ color;
	}
	
	public void action(Scanner Input) {
		try{
			System.out.print("\tEnter piece to move: ");
			String piece = Input.nextLine();
			piece = color.toString().charAt(0) +"_"+ piece.trim();			
			System.out.print("\tEnter square to move to: ");
			String square = Input.nextLine();
			square = square.trim();
			System.out.println();
			referee.proposeMove(this, piece, square);
		}
		catch (IllegalArgumentException exc){
			System.out.println("\tInvalid selection!\n");
		}	
	}
}
