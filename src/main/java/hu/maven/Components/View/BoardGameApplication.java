package hu.maven.Components.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A {@link Class} felel a játék elindításáért.
 */
public class BoardGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        stage.setTitle("JavaFX Board Game Example");
        Scene scene = new Scene(root,700, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}