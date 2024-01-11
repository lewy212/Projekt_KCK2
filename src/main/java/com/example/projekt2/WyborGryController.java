package com.example.projekt2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WyborGryController {
    @FXML
    Label labelWyboruGry;
    @FXML
    Button dinoButton;
    @FXML
    Button ticTacToeButton;

    private Stage stage;
    public void initialize() {
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(dinoButton);
        fadeAnimacje.addFadeInAnimation(ticTacToeButton);
    }
    @FXML
    protected void goToDino()  throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logowanieORrejestracja-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelWyboruGry.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }


    @FXML
    protected void goToKolkoKrzyzyk() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("kolko-krzyzyk-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelWyboruGry.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/styleKolkoKrzyzyk.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }

}
