package hu.maven.Components.Controller;

import hu.maven.Components.Model.Turn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import hu.maven.Components.Model.Board;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;


public class MainMenu {
    @FXML
    public javafx.scene.control.TextField player1;

    @FXML
    public javafx.scene.control.TextField player2;

    @FXML
    public Label prompt;

    public void initialize(){
        //logger.info("Initializing Launch scene");
        prompt.setVisible(false);
    }

    public void setPrompt(String message){
        prompt.setText(message);
        prompt.setVisible(true);
        //logger.info(message);
    }

    @FXML
    public void startMenu(MouseEvent mouseEvent) throws IOException {
        //logger.info("Clicked on Start Game Button");
        if (player1.getText().isEmpty() || player2.getText().isEmpty()) {
            setPrompt("Please enter a nickname!");
        }else if(player1.getText().length() > 15){
            setPrompt("I can't remember a nickname that long Player 1!");
        }else if(player2.getText().length() > 15){
            setPrompt("I can't remember a nickname that long player 2!");
        }else if(player2.getText().equals(player1.getText())){
            setPrompt("Don't play against yourself,you can't beat that guy!");
        }else {
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
        //logger.info("Clicked on Quit button");
        Platform.exit();
    }
}