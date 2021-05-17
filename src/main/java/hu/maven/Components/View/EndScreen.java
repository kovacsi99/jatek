package hu.maven.Components.View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.tinylog.Logger;



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
    void quitButton(){
        Logger.info("You Closed The Game! Have a Nice Day!");
        Platform.exit();
    }
}