package chess3;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
	public final Playable game;
	public final Color color;
	public Player(Playable game, Color color) {
		this.game = game;
		this.color = color;
	}
	
	private String type(Scanner input) {
		return input.nextLine().trim().toUpperCase();
	}
				
	private void pressEnter(Scanner input) {
		System.out.print("\tPress ENTER to continue");
		input.nextLine();
		System.out.println();
	}
		
	private void confirm(Scanner input) throws IOException{
		System.out.print("\tConfirm? [Y/N]: ");
		String selection = input.nextLine().toUpperCase().trim();
		if (selection.equalsIgnoreCase("y")) {
			//DO NOTHING//
		}
		else {
			throw new IOException("Action aborted!");
		}
	}	
	
	public void action(Scanner input) {
		String action = type(input);
		try {
			switch (action) {
			case "MOVE": 
				proposeMove(input);
				break;
			case "RESIGN": 
				proposeResign(input);
				break;
			case "REWIND":
				proposeRewind(input);
				break;
			case "HELP":
				game.help(this);	
				pressEnter(input);
				break;
			default: throw new IOException("Invalid Action!");
			}
		}
		catch (IOException | IllegalArgumentException exc) {
			System.out.println("\n\t"+ exc.getMessage());
		}
	}
	
	private void proposeMove(Scanner input) throws IOException {
		System.out.print("\tSelect piece to move: ");
		String piece = color.toString().charAt(0) +"_"+ type(input);
		
		System.out.print("\tSelect square to move to: ");
		String square = type(input);
		confirm(input);
			
		game.makeMove(this, piece, square);
	}
	
	private void proposeResign(Scanner input) throws IOException {
		confirm(input);
		game.resign(this);
	}
	
	private void proposeRewind(Scanner input) throws IOException {
		try {
		System.out.print("\tInsert how many turns to rewind: ");
		int howMany = input.nextInt();
		input.nextLine();
		confirm(input);
		game.rewind(this, howMany);
		}
		catch (InputMismatchException exc){
			System.out.println("\n\tInvalid selection!");
			input.nextLine();
		}
	}
		
	
}
