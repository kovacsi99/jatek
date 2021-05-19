package hu.maven.Components.Controller;

import hu.maven.Components.Model.Board;
import hu.maven.Components.Model.Position;
import hu.maven.Components.Model.Turn;
import hu.maven.Components.Ranking.Result;
import hu.maven.Components.Ranking.jdbiConnection;
import hu.maven.Components.View.EndScreen;
import javafx.application.Platform;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import org.tinylog.Logger;

import static javafx.scene.paint.Color.*;

/**
 * A {@link Class} felel a játék működéséért.
 */
public class BoardGameController {

    private Board gameState;


    @FXML
    private GridPane board;
    /**
     * Játékos neve.
     */
    @FXML
    public TextField playerName;

    HashMap<Turn, String> playerNames;

    /**
     *Ez a {@link java.lang.reflect.Method} azért felel,hogy létrehozza a játék asztalát.
     */
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

    /**
     *Ez a {@link java.lang.reflect.Method} felelős a játékosok bekért neveit a Turn Player1 és Player2-jához.
     * @param playerNames játékosok
     */
    public void setPlayerNames(HashMap<Turn, String> playerNames) {
        this.playerNames = playerNames;
        playerName.setText(playerNames.get(gameState.getPlayer()));
    }

    /**
     *Ez a {@link java.lang.reflect.Method} tölti fel a táblát négyzetekkel és benne a kavicsokkal.
     * @return visszaadja az elkészített {@link StackPane}-t.
     */
    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(50);
        piece.setFill(Color.BLACK);
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    /**
     *Ez a {@link java.lang.reflect.Method} kezeli a {@link StackPane}-ben történő kattintásokat.
     * Ha az adott helyen van kavics kijelöli,ha nincs nem tesz vele semmit. Amennyiben már ki van jelölve,
     * elveti a kijelölést.
     * @param event egérkattintás
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var coin = (Circle) square.getChildren().get(0);


        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Position pos = new Position(row, col);

        if(coin.getFill().equals(RED)) {
            gameState.removeSelected(pos);
            coin.setFill(BLACK);
        } else if(coin.getFill().equals(BLACK)) {
            gameState.addSelected(pos);
            coin.setFill(RED);
        } else if(coin.getFill().equals(TRANSPARENT)) {
            coin.setFill(TRANSPARENT);
        }

        Logger.info("selected" + gameState.getSelected());

    }

    /**
     *Ez a {@link java.lang.reflect.Method} kéri el a mezőt az indexei alapján.
     * @throws IllegalArgumentException , amennyiben rossz adatokkal kerül meghívásra.
     * @param col oszlop
     * @param row sor
     * @return {@link Node} node
     */
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

    /**
     * Ez a {@link java.lang.reflect.Method} felelős a játék szabályainak betartattásáért.
     * @return {@link Boolean} igaz/hamis
     */
    private boolean isValidSelection() {
        if(gameState.getSelected().size() == 1) {
            return true;
        }

        if(!gameState.isSameRowOrCol()){
            return false;
        }

        if(gameState.isSameRow()) {
            int x = gameState.getSelected().get(0).row();

            Integer max = gameState.getSelected()
                    .stream()
                    .mapToInt(v -> v.col())
                    .max().getAsInt();

            Integer min = gameState.getSelected()
                    .stream()
                    .mapToInt(v -> v.col())
                    .min().getAsInt();

            for(int i = min; i < max; ++i) {
                var square = (StackPane) getNodeFromGridPane(x, i);
                var coin = (Circle) square.getChildren().get(0);

                if(coin.getFill().equals(TRANSPARENT)) {
                    return false;
                }
            }
        } else if(gameState.isSameCol()) {
            int y = gameState.getSelected().get(0).col();

            Integer max = gameState.getSelected()
                    .stream()
                    .mapToInt(v -> v.row())
                    .max().getAsInt();

            Integer min = gameState.getSelected()
                    .stream()
                    .mapToInt(v -> v.row())
                    .min().getAsInt();


            for(int i = min; i < max; ++i) {
                var square = (StackPane) getNodeFromGridPane(i, y);
                var coin = (Circle) square.getChildren().get(0);

                if(coin.getFill().equals(TRANSPARENT)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *Ez a {@link java.lang.reflect.Method} kezeli a ui.{@link javafx.fxml.FXML} End Turn gombját,
     * mely véglegesíti a játékos lépéseit, játékost vált, illetve meghívja
     * a playerWon{@link java.lang.reflect.Method}-t.
     * @param actionEvent gombnyomás
     */
    public void buttonClick(ActionEvent actionEvent) {
        if(!isValidSelection()){
            Logger.warn("Do Not Cheat!");
            return;
        }
        gameState.changePlayer();
        Logger.info("You finished your turn!");

        for(var p: gameState.getSelected()) {
            var square = (StackPane) getNodeFromGridPane(p.row(), p.col());
            var coin = (Circle) square.getChildren().get(0);
            coin.setFill(TRANSPARENT);
        }

        gameState.resetSelected();
        playerName.setText(playerNames.get(gameState.getPlayer()));

        if(playerWon()) {
            Logger.info("Game ended! Well Played!");
            endGame(actionEvent);
        }

    }

    /**
     *Ez a {@link java.lang.reflect.Method} ellenőrzi,hogy véget ért-e a játék.
     * @return true , ha már nincsen kavics, amit le lehetne venni.
     */
    public boolean playerWon(){

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

    /**
     *Ez a {@link java.lang.reflect.Method}  lépteti át az Endgame.{@link FXML}-re, amennyiben véget ért a játék.
     * @param actionEvent gombnyomás
     */
    private void endGame(ActionEvent actionEvent){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndGame.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            var controller = (EndScreen)fxmlLoader.getController();
            controller.setWinnerName(playerNames.get(gameState.getPlayer()));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            jdbiConnection.insert(new Result(playerNames.get(Turn.PLAYER1), playerNames.get(Turn.PLAYER2), playerNames.get(gameState.getPlayer())));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ez a {@link java.lang.reflect.Method} lép ki a programból az Exit gomb lenyomásával.
     */
    @FXML
    void exitButton2(){
        Logger.info("Have You Changed Your Mind? Have a Nice Day Then!");
        Platform.exit();
    }
}