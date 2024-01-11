package com.example.projekt2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LogowanieOrRejestracjaController {
    @FXML
    private Label labelMenu;
    private Stage stage;
    @FXML
    private Button zalogujButton;
    @FXML
    private Button zarejestrujButton;
    @FXML
    private Button powrotButton;
    public void initialize() {
        // Dodaj animacje fade in dla przycisków
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(zalogujButton);
        fadeAnimacje.addFadeInAnimation(zarejestrujButton);
        fadeAnimacje.addFadeInAnimation(powrotButton);
    }
    @FXML
    protected void goToLogowanie()  throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logowanie-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }

    @FXML
    protected void goToRejestracja() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("rejestracja-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }
    @FXML
    protected void powrot() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("wyborGry-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }
}
