package hu.maven.Components.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EndScreen {

    @FXML
    public TextField winner;

    public void setWinnerName(String winnerName) {
        winner.setText(winnerName);
    }



    @FXML
    void quitButton(){
        //logger.info("Clicked on Quit button");
        Platform.exit();
    }
}