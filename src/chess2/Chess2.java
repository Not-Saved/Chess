package chess2;
import java.util.Scanner;

public class Chess2 {	
	public static void main(String args[]) {
		System.out.println("\tWELCOME TO CHESS v0.1!\n");
		Game gioco = new Game("Loris","Sabrina");
		Scanner Input = new Scanner(System.in);
		Player Player = null;
		
		while(gioco.getWinner()==null) {
			switch(gioco.getTurn()) {
				case WHITE: Player = gioco.getP1(); break;
				case BLACK: Player = gioco.getP2(); break;
			}
			System.out.println("\t"+ gioco.getTurnCount() +". "+ gioco.getTurn().toString() +" TURN");
			Player.action(Input);
		}
		
		System.out.println("\t"+ gioco.getWinner() +" PLAYER WINS THE GAME!\n");
		System.out.print("\tPress ENTER to close");
		Input.nextLine();
		Input.close();
	}
}
