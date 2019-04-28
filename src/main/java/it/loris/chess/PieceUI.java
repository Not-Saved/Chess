package it.loris.chess;

import it.loris.chess.basicdata.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PieceUI extends StackPane {

    public final Piece piece;
    public Text pieceName;

    public int startX;
    public int startY;
    public int endX;
    public int endY;

    public PieceUI(Piece piece, BoardUI myBoard){
        this.piece = piece;
        switch(piece.getClass().getSimpleName()){
            case "Pawn": pieceName = new Text("\u265F"); break;
            case "Rook": pieceName = new Text("\u265C"); break;
            case "Knight": pieceName = new Text("\u265E"); break;
            case "Bishop": pieceName = new Text("\u265D"); break;
            case "Queen": pieceName = new Text("\u265B"); break;
            case "King": pieceName = new Text("\u265A"); break;
            default: throw new IllegalStateException("Should have never went here!");
        }
        DropShadow pieceShadow;
        switch(piece.color) {
            case WHITE:
                pieceName.setFill(Color.color(0.95, 0.95, 0.95));
                pieceName.setStroke(Color.color(0.05,0.05,0.05));
                pieceName.setFont(Font.font("Arial",myBoard.T_SIZE*0.822));
                pieceName.setStrokeWidth(myBoard.T_SIZE*0.018);
                pieceShadow = new DropShadow(myBoard.T_SIZE * 0.1, Color.BLACK);
                break;
            case BLACK:
                pieceName.setFill(Color.color(0.05,0.05,0.05));
                pieceName.setFont(Font.font("Arial",myBoard.T_SIZE*0.84));
                pieceShadow = new DropShadow(myBoard.T_SIZE * 0.1, Color.color(0.45, 0.45, 0.45));
                break;
            default:
                pieceShadow = null;
        }
        this.setMouseTransparent(true);
        this.getChildren().addAll(pieceName);

        this.setOnMouseEntered(event -> {
            this.pieceName.setEffect(pieceShadow);
        });

        this.setOnMouseExited(event -> {
            this.pieceName.setEffect(null);
        });

        this.setOnMousePressed(event-> {
            if (event.getButton() == MouseButton.PRIMARY) {
                this.toFront();
                this.setMouseTransparent(true);
                this.setManaged(false);
            }
        });

        this.setOnMouseDragged(event-> {
            if (event.getButton() == MouseButton.PRIMARY){
                this.startX = (int)(myBoard.startX/myBoard.T_SIZE);
                this.startY = (int)(myBoard.startY/myBoard.T_SIZE);
                this.endX = (int)(myBoard.endX/myBoard.T_SIZE);
                this.endY = (int)(myBoard.endY/myBoard.T_SIZE);
                this.pieceName.setEffect(pieceShadow);
                if (endX < 0 || endX > 7 || endY < 0 || endY > 7){
                } else{
                    this.relocate((endX * myBoard.T_SIZE) + myBoard.EXTRA_SIZE*1.03, (endY * myBoard.T_SIZE)+ myBoard.EXTRA_SIZE*1.03);
                }
            }
        });
    }
}
