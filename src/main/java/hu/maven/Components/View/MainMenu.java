package hu.maven.Components.View;

import hu.maven.Components.Controller.BoardGameController;
import hu.maven.Components.Model.Turn;
import hu.maven.Components.Ranking.Result;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * A {@link Class} indítja el a Main Menut.
 */
public class MainMenu {
    /**
     * Az első játékos.
     */
    @FXML
    public javafx.scene.control.TextField player1;

    /**
     * A második játékos.
     */
    @FXML
    public javafx.scene.control.TextField player2;

    /**
     * Üzenet.
     */
    @FXML
    public Label prompt;

    /**
     * Elrejti a hibaüzeneteket indításkor.
     */
    public void initialize(){
        Logger.info("Started the menu!");
        prompt.setVisible(false);
    }

    /**
     *Segéd {@link java.lang.reflect.Method} a hibaüzenetek kiiratásához.
     *@param message hibaüzenet
     */
    public void setPrompt(String message){
        prompt.setText(message);
        prompt.setVisible(true);
        Logger.warn(message);
    }

    /**
     * A {@link java.lang.reflect.Method} elínditja a játékot gombnyomásra.
     * @param mouseEvent gombnyomás
     * @throws IOException rossz adatbevitel esetén
     */
    @FXML
    public void startMenu(MouseEvent mouseEvent) throws IOException {
        if (player1.getText().isEmpty()) {
            setPrompt("Please enter a nickname Player 1!");
        }else if(player2.getText().isEmpty()){
            setPrompt("Please enter a nickname Player 2!");
        } else if(player1.getText().length() > 15){
            setPrompt("I can't remember a nickname that long Player 1!");
        }else if(player2.getText().length() > 15){
            setPrompt("I can't remember a nickname that long player 2!");
        }else if(player2.getText().equals(player1.getText())){
            setPrompt("Don't play against yourself,you can't beat that guy!");
        }else {
            Logger.info("Enjoy the Game! Last One Standing Loses!");
            startGame(mouseEvent, "/ui.fxml");
        }
    }

    private HashMap<Turn, String> getPlayerNames() {
        var players = new HashMap<Turn, String>();
        players.put(Turn.PLAYER1, player1.getText());
        players.put(Turn.PLAYER2, player2.getText());

        return  players;
    }

    private void startGame(MouseEvent mouseEvent, String filename) throws IOException {
        String filePath = System.getProperty("user.home") + File.separator + ".results";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filename));
        Parent root = fxmlLoader.load();
        var controller = (BoardGameController)fxmlLoader.getController();
        controller.setPlayerNames(getPlayerNames());
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void exitButton(){
        Logger.info("Have You Changed Your Mind? Have a Nice Day Then!");
        Platform.exit();
    }

}