package com.example.projekt2;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.prefs.Preferences;

public class UstawieniaController {

    @FXML
    private ChoiceBox<String> poziomTrudnosci;
    @FXML
    private HBox tabela;
    @FXML
    private HBox efekty;
    @FXML
    private Button powrotButton;

    @FXML
    private Label labelMenu;
    @FXML
    private CheckBox checkEfektyDzwiekowe;


    public void initialize() {
        // Ustawienie domyślnej wartości
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(tabela);
        fadeAnimacje.addFadeInAnimation(efekty);
        poziomTrudnosci.setValue("Średni");
        fadeAnimacje.addFadeInAnimation(powrotButton);
        // Wczytanie zapisanej wartości
        loadSettings();
    }

    @FXML
    protected void Powrot() throws IOException {
        // Zapisanie wybranej wartości przed przejściem do innego widoku
        saveSettings();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }

    private void saveSettings() {
        Preferences prefs = Preferences.userNodeForPackage(UstawieniaController.class);
        prefs.put("poziomTrudnosci", poziomTrudnosci.getValue());
        prefs.putBoolean("checkEfektyDzwiekowe", checkEfektyDzwiekowe.isSelected());
    }

    private void loadSettings() {
        Preferences prefs = Preferences.userNodeForPackage(UstawieniaController.class);
        String savedDifficulty = prefs.get("poziomTrudnosci", null);
        if (savedDifficulty != null) {
            poziomTrudnosci.setValue(savedDifficulty);
        }
        boolean savedCheckBoxState = prefs.getBoolean("checkEfektyDzwiekowe", false);
        checkEfektyDzwiekowe.setSelected(savedCheckBoxState);
    }
}
