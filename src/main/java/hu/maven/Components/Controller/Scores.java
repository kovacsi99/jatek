package hu.maven.Components.Controller;
import java.io.IOException;
import java.util.List;

import hu.maven.Components.Ranking.Result;
import hu.maven.Components.Ranking.jdbiConnection;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tinylog.Logger;


public class Scores {
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Result, String> p1;

    @FXML
    private TableColumn<Result, String> p2;

    @FXML
    private TableColumn<Result, String> winner;


    @FXML
    private void initialize() {
        p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        p2.setCellValueFactory(new PropertyValueFactory<>("p2"));
        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        List<Result> results = jdbiConnection.lister();
        ObservableList<Result> observableList = FXCollections.observableArrayList();
        observableList.addAll(results);
        tableView.setItems(observableList);
    }

    @FXML
    void exitButton3(){
        Logger.info("Have You Changed Your Mind? Have a Nice Day Then!");
        Platform.exit();
    }
}
