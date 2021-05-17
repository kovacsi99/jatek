package hu.maven.Components.Controller;

import hu.maven.Components.Model.Board;
import hu.maven.Components.Model.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import static javafx.scene.paint.Color.*;

public class BoardGameController {

    private Board gameState;

    @FXML
    private GridPane board;

    @FXML
    public javafx.scene.control.Label player1;

    @FXML
    public Label player2;


    @FXML
    private void initialize() {
        for (int i = 0; i < board.getColumnCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }
        gameState = new Board();
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(50);
        piece.setFill(Color.BLACK);
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var coin = (Circle) square.getChildren().get(0);
        Paint fill = coin.getFill();


        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Position pos = new Position(row, col);
        System.out.printf("Click on square (%d,%d)\n", row, col);

        if(coin.getFill().equals(RED)) {
            gameState.removeSelected(pos);
            coin.setFill(BLACK);
        } else if(coin.getFill().equals(BLACK)) {
            gameState.addSelected(pos);
            coin.setFill(RED);
        } else if(coin.getFill().equals(TRANSPARENT)) {
            coin.setFill(TRANSPARENT);
        }

        System.out.println(gameState.getSelected());

    }

    private Node getNodeFromGridPane(int row, int col) {
        for (Node node : board.getChildren()) {
            if (node instanceof StackPane) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return node;
                }
            }
        }

        throw new IllegalArgumentException();
    }


    public void buttonClick(ActionEvent actionEvent) {
        if(!gameState.isValidSelection()){
            return;
        }
        gameState.changePlayer();

        for(var p: gameState.getSelected()) {
            var square = (StackPane) getNodeFromGridPane(p.row(), p.col());
            var coin = (Circle) square.getChildren().get(0);
            coin.setFill(TRANSPARENT);
        }

        gameState.resetSelected();

       /* if(playerWon()) {
            endGame(actionEvent);
        }*/

    }

    public void setPlayers(String player1, String player2) {
        this.player1.setText(player1);
        this.player2.setText(player2);
    }

    /*public boolean playerWon(){

        for(int i = 0; i< 4 ;++i) {
            for(int j = 0; j< 4; ++j) {
                var square = (StackPane) getNodeFromGridPane(i, j);
                var coin = (Circle) square.getChildren().get(0);
                if(!coin.getFill().equals(TRANSPARENT)) {
                    return false;
                }
            }
        }

        return true;
    }


    private void endGame(ActionEvent actionEvent){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }*/

}