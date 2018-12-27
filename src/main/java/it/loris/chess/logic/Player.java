package it.loris.chess.logic;

import java.util.Scanner;
import it.loris.chess.basicdata.Color;

public class Player {
	public final Color color;
	public Player(Color color) {
		this.color = color;
	}
	
	public Board proposeMove(Board board, int fromX, int fromY, int toX, int toY) {
		Square movingFrom = board.getPosition(fromX, fromY);
		if (movingFrom.piece.color != this.color) {
			throw new IllegalArgumentException("Not your turn!");
		}
		Square movingTo = board.getPosition(toX, toY);

		return GameMaster.makeMove(board, movingFrom, movingTo);
	}
}
