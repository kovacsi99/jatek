package hu.maven.Components.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        stage.setTitle("JavaFX Board Game Example");
        Scene scene = new Scene(root,1000, 1000);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}