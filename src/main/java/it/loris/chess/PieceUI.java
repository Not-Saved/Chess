package it.loris.chess;

import it.loris.chess.basicdata.Piece;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PieceUI extends StackPane {

    public PieceUI(Piece piece, int size){
        Ellipse circle = new Ellipse(size * 0.33, size * 0.33);
        Text pieceName = new Text(piece.name);
        switch(piece.color) {
            case BLACK:
                circle.setFill(Color.color(0.05,0.05,0.05));
                pieceName.setFill(Color.color(0.95, 0.95, 0.95));
                break;
            case WHITE:
                circle.setFill(Color.web("#FDF5EF"));
                pieceName.setFill(Color.color(0.05,0.05,0.05));

                break;
        }
        circle.setStrokeWidth(size * 0.035);
        circle.setStroke(Color.color(0.05,0.05,0.05));
        circle.setStrokeType(StrokeType.CENTERED);
        pieceName.setFont(Font.font("Verdana", FontWeight.BOLD, size*0.21));
        this.getChildren().addAll(circle, pieceName);
    }
}
