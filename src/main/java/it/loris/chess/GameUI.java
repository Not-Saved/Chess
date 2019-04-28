package it.loris.chess;

import it.loris.chess.logic.Board;
import it.loris.chess.logic.BoardBuilder;
import it.loris.chess.logic.Game;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameUI extends Application {

    private Game chessGame;
    private GridPane mainPane;
    private BoardUI myBoard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);

        newGame();

        Scene myScene = new Scene(mainPane, Color.LIGHTGRAY);

        myScene.heightProperty().addListener((observable, oldVal, newVal) -> {
            mainPane.setScaleY(Math.min(myScene.getWidth()/(myBoard.getWidth()+ 60), (double)newVal/(myBoard.getHeight()+ 60)));
            mainPane.setScaleX(mainPane.getScaleY());
        });

        myScene.widthProperty().addListener((observable, oldVal, newVal) -> {
            mainPane.setScaleY(Math.min((double)newVal/(myBoard.getWidth()+ 60), myScene.getHeight()/(myBoard.getHeight()+ 60)));
            mainPane.setScaleX(mainPane.getScaleY());
        });

        myScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Z && event.isShortcutDown()) {
                chessGame.rewindGame();
                update();
            }
            if (event.getCode() == KeyCode.Y && event.isShortcutDown()) {
                chessGame.forwardGame();
                update();
            }
        });

        primaryStage.setTitle("Chess v0.0.1");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void newGame(){
        mainPane.getChildren().clear();
        chessGame = new Game(new Board(BoardBuilder.buildAtStart()));
        myBoard = new BoardUI(chessGame.getBoard(), -1);
        mainPane.add(myBoard,0,0);
        makeBoardInteractive(myBoard);

        chessGame.getState().addListener((observable, oldVal, newVal)-> {
            Text winMessage = new Text(chessGame.getTurn().getValue() +" PLAYER WINS \n IN "+ chessGame.getTurnCount() +" MOVES!");
            winMessage.setFont(Font.font("Wingdings", 65));
            winMessage.setEffect(new DropShadow(6, Color.BLACK));
            winMessage.setStrokeWidth(1.5);
            winMessage.setTextAlignment(TextAlignment.CENTER);
            switch(chessGame.getTurn().getValue()){
                case WHITE:
                    winMessage.setFill(Color.WHITE);
                    winMessage.setStroke(Color.BLACK);
                    break;
                case BLACK:
                    winMessage.setFill(Color.BLACK);
                    winMessage.setStroke(Color.WHITE);
                    break;
            }
            GridPane.setHalignment(winMessage, HPos.CENTER);
            mainPane.add(winMessage,0, 0);
            winMessage.toFront();
            myBoard.toBack();
            winMessage.setOnMouseClicked(event -> newGame());
        });
    }

    public void update(){
        BoardUI myOldBoard = myBoard;
        mainPane.getChildren().remove(myBoard);
        myBoard = new BoardUI(chessGame.getBoard(), -1);
        myBoard.setScaleX((myOldBoard.getScaleX()));
        myBoard.setScaleY((myOldBoard.getScaleY()));
        makeBoardInteractive(myBoard);
        if (chessGame.getState().getValue() == false) {
            myBoard.setMouseTransparent(true);
        }
        mainPane.add(myBoard, 0, 0);
        myBoard.toBack();
    }

    public void makeBoardInteractive(BoardUI myBoard){
        myBoard.getChildren().filtered(p->p instanceof PieceUI && ((PieceUI) p).piece.color == chessGame.getTurn().getValue()).forEach(piece ->{
            piece.setMouseTransparent(false);
            piece.setOnMouseReleased(event->{
                try {
                    if (event.getButton() == MouseButton.PRIMARY && piece.isManaged() == false) {
                        chessGame.advanceGame(((PieceUI)piece).startX, ((PieceUI)piece).startY, ((PieceUI)piece).endX, ((PieceUI)piece).endY);
                        update();
                    } else {
                            piece.setManaged(true);
                    }
                } catch (IllegalArgumentException exc){
                    System.out.println(exc.getMessage());
                } finally {
                    piece.setManaged(true);
                    piece.setMouseTransparent(false);
                    ((PieceUI) piece).pieceName.setEffect(null);
                }
            });
        });
    }
}

