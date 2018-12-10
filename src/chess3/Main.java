package chess3;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException, InterruptedException {
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		ProcessBuilder cls = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
		
		cls.start().waitFor();
		Thread.sleep(2);
		System.out.println("\n\tWELCOME TO CHESS v0.2!");
		Thread.sleep(1000);
		
		while (game.getEnded() == false) {
			Thread.sleep(1000);
			cls.start().waitFor();
			Thread.sleep(2);
			game.advanceGame(input);
		}
		
		System.out.println("\n\t"+ game.getWinner().color.toString() +" PLAYER WINS THE GAME!\n");
		
		System.out.print("\tType NEW to start a new game or press ENTER to close the application:");
		String action = input.nextLine().trim().toUpperCase();
		System.out.println();
		switch (action) {
		case "NEW": 
			cls.start().waitFor();
			Thread.sleep(2);
			main(args);
		default: input.close();
		}
	}
}
