package com.example.projekt2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class InstrukcjaController {

    @FXML
    private Label labelMenu;
    @FXML
    private Label labelInstrukcja;
    @FXML
    private VBox VboxInstrukcja;
    @FXML
    private Button powrotButton;
    public void initialize() {
        // Ustawienie domyślnej wartości
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(VboxInstrukcja);
        fadeAnimacje.addFadeInAnimation(labelInstrukcja);
        fadeAnimacje.addFadeInAnimation(powrotButton);
        // Wczytanie zapisanej wartości

    }
    @FXML
    protected void Powrot() throws IOException {
        // Zapisanie wybranej wartości przed przejściem do innego widoku


        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }
}
