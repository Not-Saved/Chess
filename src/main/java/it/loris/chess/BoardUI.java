package it.loris.chess;

import it.loris.chess.logic.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class BoardUI extends GridPane{

    public final static double T_SIZE = 87;
    public final static double BORDER_SIZE = T_SIZE * 0.170;
    public final static double INSETS_SIZE = T_SIZE * 0.020;
    public final static double EXTRA_SIZE = BORDER_SIZE + INSETS_SIZE;

    public double startX;
    public double startY;
    public double endX;
    public double endY;

    public final int orientation;

    public BoardUI(Board board, int orientation) {
        this.orientation = orientation;
        this.setMinSize(T_SIZE*8 + EXTRA_SIZE*2, T_SIZE*8 + EXTRA_SIZE*2);
        this.setAlignment(Pos.CENTER);
        this.setBorder(new Border(new BorderStroke(Color.color(0.05, 0.05, 0.05), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
        this.setPadding(new Insets(INSETS_SIZE));
        this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, new Insets(0))));
        this.setScaleY(orientation);
        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                startX = event.getX() - EXTRA_SIZE;
                startY = event.getY() - EXTRA_SIZE;
                endX = startX;
                endY = startY;
            }
        });

        this.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                endX = event.getX() - EXTRA_SIZE;
                endY = event.getY() - EXTRA_SIZE;
            }
        });

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle squareUI = new Rectangle(T_SIZE, T_SIZE);
                switch ((i + j) % 2) {
                    case 0:
                        squareUI.setFill(Color.web("#FDF5EF"));
                        break;
                    case 1:
                        squareUI.setFill(Color.color(0.25, 0.25, 0.25));
                        break;
                }
                squareUI.setStroke(Color.BLACK);
                squareUI.setStrokeType(StrokeType.INSIDE);
                squareUI.setStrokeWidth(T_SIZE * 0.013);
                this.add(squareUI, i, j);

                if (board.getPosition(i, j).piece!= null) {
                    PieceUI piece = new PieceUI(board.getPosition(i, j).piece, this);
                    piece.toFront();
                    piece.setScaleY(orientation);
                    this.add(piece, i, j);
                }
            }
        }
    }
}
