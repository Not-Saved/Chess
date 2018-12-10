package chess3;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import chess3.piece.Piece;

public class Game implements Playable{
	private final Board board;
	private final GameMaster gm;
	private final Player p1;
	private final Player p2;
	
	private TreeMap<Integer, Map<Piece, Square>> gameUntilNow = new TreeMap<>();
	private int turnCount;
	private boolean ended;
	private Player winner;
	
	public Game() {
		board = new Board();
		gm = new GameMaster(board);
		p1 = new Player(this, Color.WHITE);
		p2 = new Player(this, Color.BLACK);
		
		gameUntilNow.put(1, board.boardCopy());
		turnCount = gameUntilNow.lastKey();
		ended = false;
	}
	
	public void advanceGame(Scanner input) {
		System.out.println("\n\tTURN "+ turnCount +"\n");
		board.paleoGraphic();
		
		System.out.println("\n\t"+ getPlayer(turnCount).color.toString() +" PLAYER TURN");
		System.out.print("\tSelect action (Type HELP for guide): ");
		getPlayer(turnCount).action(input);
		
		checkWin();
	}
		
	private void checkWin(){
		if (gm.whoWon(board) != null) {
			setWinner(getPlayer(gm.whoWon(board)));
		}
	}
	
	@Override
	public void makeMove(Player player, String piece, String square) throws IOException{
		board.setBoardState(gm.executeMove(piece, square));
		gameUntilNow.put(turnCount+1, board.boardCopy());
		turnCount = gameUntilNow.lastKey();
		System.out.println("\n\tMove completed!\n");
	}
	
	@Override
	public void resign(Player player) {
		switch(player.color) {
		case WHITE: 
			setWinner(getPlayer(Color.BLACK));
			break;
		case BLACK: 
			setWinner(getPlayer(Color.WHITE));
			break;
		}
	}
	
	@Override
	public void rewind(Player player, int howMany) throws IOException {
		if (howMany < 0 || howMany >= turnCount) throw new IOException("Can't go back that many moves!");
		for(int i = 0; i<howMany; i++) {
			gameUntilNow.remove(gameUntilNow.lastKey());
			turnCount = gameUntilNow.lastKey();
		}
		board.setBoardState(gameUntilNow.get(gameUntilNow.lastKey()));
		System.out.println("\n\tGame rewinded!\n");
	}
	
	@Override
	public void help (Player player) {
		System.out.println("\n\tAvailable actions: MOVE, RESIGN, REWIND");
		System.out.println("\tPiece insert formats: p1 / P1 / q ");
		System.out.println("\tSquare insert formats: a4 / B3 / c7\n");
	}
		
	private Player getPlayer(int turn) {
		switch (turn%2) {
		case 0: return p2;
		case 1: return p1;
		default: throw new IllegalArgumentException("Should have never went here!");
		}
	}
		
	private Player getPlayer(Color color) {
		switch (color) {
		case WHITE: return p1;
		case BLACK: return p2;
		default: throw new IllegalArgumentException("Should have never went here!");
		}
	}
	
	private void setWinner(Player winner) {
		this.winner = winner;
		ended = true;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public boolean getEnded() {
		return ended;
	}
}
