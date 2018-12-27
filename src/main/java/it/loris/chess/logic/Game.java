package it.loris.chess.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import it.loris.chess.basicdata.Color;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {
	private final Player p1;
	private final Player p2;
	
	private Board board;
	private List<Board> gameUntilNow = new ArrayList<>();
	private BooleanProperty playing = new SimpleBooleanProperty();
	private ObjectProperty<Color> turn = new SimpleObjectProperty<>();
	
	public Game(Board board) {
		this.board = board;
		this.p1 = new Player(Color.WHITE);
		this.p2 = new Player(Color.BLACK);
		gameUntilNow.add(board);
		playing.set(true);
		turn.setValue(getPlayer().color);
	}
	
	public void advanceGame(int fromX, int fromY, int toX, int toY) {
		this.board = getPlayer().proposeMove(board, fromX, fromY, toX, toY);
		if (winCondition(getPlayer())){
				playing.setValue(false);
		}
		gameUntilNow.add(board);
		turn.setValue(getPlayer().color);
	}
	
	public BooleanProperty getState() {
		return playing;
	}

	public ObjectProperty<Color> getTurn() {
		return turn;
	}

	public Board getBoard(){
		return board;
	}
		
	private Player getPlayer() {
		switch (gameUntilNow.size()%2) {
		case 0: return p2;
		case 1: return p1;
		default: throw new IllegalStateException("Should have never went here!");
		}
	}
	
	private boolean winCondition(Player player) {
		try {
			switch (player.color){
			case WHITE:
				board.getPiece("B_K");
				return false;
			case BLACK:
				board.getPiece("W_K");
				return false;
			default:
				throw new IllegalStateException("Should have never went here!");
			}
		}
		catch (IllegalArgumentException exc) {
			return true;
		}
	}
}
