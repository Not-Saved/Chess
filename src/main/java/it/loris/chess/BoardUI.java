package it.loris.chess;

import it.loris.chess.logic.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class BoardUI {

    public final static int T_SIZE = 87;
    public final static double BORDER_SIZE = T_SIZE * 0.15;
    public final static double INSETS_SIZE = T_SIZE * 0.01;
    public final static double EXTRA_SIZE = BORDER_SIZE + INSETS_SIZE;

    public static GridPane makeBoard(Board board) {
        GridPane boardUI = new GridPane();
        boardUI.setMaxSize(boardUI.getMinWidth(), boardUI.getMaxHeight());
        boardUI.setAlignment(Pos.CENTER);
        boardUI.setBorder(new Border(new BorderStroke(Color.color(0.05, 0.05, 0.05), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
        boardUI.setPadding(new Insets(INSETS_SIZE));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle squareUI = new Rectangle(T_SIZE, T_SIZE);
                switch ((i + j) % 2) {
                    case 0:
                        squareUI.setFill(Color.web("#FDF5EF"));
                        break;
                    case 1:
                        squareUI.setFill(Color.color(0.18, 0.18, 0.18));
                        break;
                }
                squareUI.setStroke(Color.color(0.05, 0.05, 0.05));
                squareUI.setStrokeType(StrokeType.INSIDE);
                squareUI.setStrokeWidth(T_SIZE * 0.013);

                boardUI.add(squareUI, i, j);

                if (board.getPosition(i, j).piece!= null) {
                    PieceUI piece = new PieceUI(board.getPosition(i, j).piece, T_SIZE);
                    piece.toFront();
                    boardUI.add(piece, i, j);
                }
            }
        }
        return boardUI;
    }
}
