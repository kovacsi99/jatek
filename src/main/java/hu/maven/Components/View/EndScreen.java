package hu.maven.Components.View;

import com.google.errorprone.annotations.FormatMethod;
import hu.maven.Components.Controller.BoardGameController;
import hu.maven.Components.Controller.Scores;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;


/**
 * A {@link Class} kezeli a játék eredményének a képét.
 */
public class EndScreen {
    /**
     * A győztes.
     */
    @FXML
    public TextField winner;

    /**
     * A {@link java.lang.reflect.Method} iratja ki a  győztes nevét.
     * @param winnerName a győztes neve
     */
    public void setWinnerName(String winnerName) {
        winner.setText(winnerName);

    }

    @FXML
    private void highScores(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Scores.fxml"));
        Parent root = fxmlLoader.load();
        var controller = (Scores) fxmlLoader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void quitButton(){
        Logger.info("You Closed The Game! Have a Nice Day!");
        Platform.exit();
    }

}