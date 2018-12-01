package chess2;
import static chess2.Color.BLACK;
import static chess2.Color.WHITE;

public class Game {
	private final Board board;
	private final Player P1;
	private final Player P2;
	private Color turn;
	private int turnCount;
	private Color winner;
    
	public Game(String P1, String P2) {
		board = new Board();
		Referee referee = new Referee(this);
		this.P1 = new Player(P1, WHITE, referee);
		this.P2 = new Player(P2, BLACK, referee);
		turn=WHITE;
		turnCount=1;
		winner=null;
		paleoGraphic();
	}
	
	public Board getBoard() {
		return board;
	}
	public Player getP1() {
		return P1;
	}
	public Player getP2() {
		return P2;
	}
	public Color getTurn() {
		return turn;
	}
	public int getTurnCount() {
		return turnCount;
	}
	public Color getWinner(){
		return winner;
	}
	
	public void paleoGraphic() {
		System.out.println("\tTURN " + getTurnCount() +"\n");
		for (int i=8; i>0; --i) {
			for (char j='A'; j<='H'; ++j) {
				Piece u = getBoard().isOccupiedBy(getBoard().getSquare(j+""+i));
				if (u!=null) System.out.print("\t["+u.toString()+"]");
				else System.out.print("\t[    ]");
			}
			System.out.println();
		}
		System.out.println();
	}
	public void winCondition() {
		if (board.contains("B_K")==false) {
			this.winner=WHITE;
		}
		if (board.contains("W_K")==false) {
			this.winner=BLACK;
		}
	}
	private void changeTurn() {
		if (getTurn()==WHITE) this.turn=BLACK;
		else if (getTurn()==BLACK) this.turn=WHITE;
		turnCount++;
	}
	private void advanceGame() {
		winCondition();
		changeTurn();
		paleoGraphic();
	}
	public void makeMove(Piece myPiece, Square targetSquare){
		Piece targetPiece = getBoard().isOccupiedBy(targetSquare);
		if (targetPiece==null) {
			board.occupies(myPiece).setFileRank(targetSquare.toString());
			System.out.println("\t"+ myPiece.toString() +" moved to "+ targetSquare.toString() +"!\n");
		}
		else if (targetPiece!=null) {
			board.removePiece(targetPiece);
			board.occupies(myPiece).setFileRank(targetSquare.toString());
			System.out.println("\t"+ myPiece.toString() +" moved to "+ targetSquare.toString() +" and captured " +targetPiece.toString()+ "!\n");
		}
		if (myPiece.getMovedYet()==false) myPiece.setMovedYet(true);
		advanceGame();
	}
	
	
	
}

