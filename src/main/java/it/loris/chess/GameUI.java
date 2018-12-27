package it.loris.chess;

import it.loris.chess.logic.Board;
import it.loris.chess.logic.BoardBuilder;
import it.loris.chess.logic.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static it.loris.chess.BoardUI.*;

public class GameUI extends Application {

    private Game chessGame;
    private FlowPane mainPane;
    private GridPane myBoard;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        chessGame = new Game(new Board(BoardBuilder.buildAtStart()));
        chessGame.getState().addListener((observable, oldVal, newVal)-> Platform.exit());
        mainPane = new FlowPane();
        myBoard = BoardUI.makeBoard(chessGame.getBoard());
        makeBoardInteractive(myBoard);

        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(myBoard);
        Scene myScene = new Scene(mainPane, Color.LIGHTGRAY);

        primaryStage.setTitle("Chess v0.0.1");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void update(){
        chessGame.advanceGame(startX, startY, endX, endY);
        mainPane.getChildren().remove(myBoard);
        myBoard = makeBoard(chessGame.getBoard());
        makeBoardInteractive(myBoard);
        mainPane.getChildren().add(myBoard);
    }

    public void makeBoardInteractive(GridPane myBoard){
        myBoard.getChildren().filtered(p->p instanceof PieceUI).forEach(piece ->{
            piece.setOnMousePressed(event-> {
                this.startX = GridPane.getColumnIndex(piece);
                this.startY = GridPane.getRowIndex(piece);
                this.endX = startX;
                this.endY = startY;
                piece.setManaged(false);
                piece.setMouseTransparent(true);
            });
            piece.setOnMouseDragged(event-> {
                piece.toFront();
                double offSetX = (mainPane.getWidth() - myBoard.getWidth())/2;
                double offSetY = (mainPane.getHeight() - myBoard.getHeight())/2;
                endX = (int)((event.getSceneX() - offSetX)/T_SIZE);
                endY = (int)((event.getSceneY() - offSetY)/T_SIZE);
                if (endX < 0 || endX > 7 || endY < 0 || endY > 7) ;
                else {
                    piece.relocate(endX * T_SIZE + EXTRA_SIZE, endY * T_SIZE + EXTRA_SIZE);
                }

            });
            piece.setOnMouseReleased(event->{
                try {
                    update();
                    myBoard.getChildren().remove(piece);
                    if (endX < 0 || endX > 7 || endY < 0 || endY > 7) myBoard.add(piece, startX, startY);
                    else myBoard.add(piece, endX, endY);

                } catch (IllegalArgumentException exc){
                    System.out.println(exc.getMessage());
                } finally {
                    piece.setMouseTransparent(false);
                    piece.setManaged(true);
                }
            });
        });
    }
}

