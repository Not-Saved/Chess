package chess2;

public class Referee {
	private Game game;
	public Referee(Game game) {
		this.game=game;
	}
	
	public boolean validateMove(Player player, Piece myPiece, Square targetSquare) {
		Piece targetPiece = game.getBoard().isOccupiedBy(targetSquare);	
		if (player.getColor()!=game.getTurn()) {
			System.out.println("\tIt's not your turn!\n");
			return false;
		}
		if (myPiece.getColor()!=player.getColor()) {
			System.out.println("\t"+ targetPiece.toString() +" belongs to opponent!\n");
			return false;
		}
		if (targetPiece!=null) {
			if (targetPiece.getColor() == myPiece.getColor()) {
				System.out.println("\t"+ targetSquare.toString() +" occupied by friendly piece!\n");	
				return false;
			}	
		}
		if (myPiece.legalMove(game.getBoard(), targetSquare)==false) {
			System.out.println("\t"+ myPiece.toString() +" can't move that way!\n");	
			return false;
		}
		else return true;
	}
	
	public void proposeMove(Player player, String piece, String square) {
		Piece myPiece = game.getBoard().getPiece(piece);
		Square targetSquare = game.getBoard().getSquare(square);
		if (validateMove(player, myPiece, targetSquare)) game.makeMove(myPiece, targetSquare);
	}
	
	public void getBoard() {
		game.getBoard().toString();
	}
}
