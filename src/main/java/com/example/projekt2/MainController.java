package com.example.projekt2;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MainController {
    @FXML
    Label labelMenu;
    @FXML
    private Button graButton;

    @FXML
    private Button ustawieniaButton;

    @FXML
    private Button tablicaWynikowButton;

    @FXML
    private Button wylogujButton;
    private Stage stage;

    public void initialize() {
        // Dodaj animacje fade in dla przycisków
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(graButton);
        fadeAnimacje.addFadeInAnimation(graButton);
        fadeAnimacje.addFadeInAnimation(ustawieniaButton);
        fadeAnimacje.addFadeInAnimation(tablicaWynikowButton);
        fadeAnimacje.addFadeInAnimation(wylogujButton);
    }

    @FXML
    protected void goToGra() throws IOException{

        String savedDifficulty = readDifficultySetting();
        Boolean czyMuzyka = readMuzykaOn();

        // Pobieramy obiekt Stage z aktualnej sceny
        stage = (Stage) labelMenu.getScene().getWindow();
        Gra gra = new Gra();
        // Ustawiamy scenę z PaneExample w bieżącym oknie
        Scene scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());


        stage.setScene(scene);
        scene.getRoot().requestFocus();
        String cssPath = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        gra.addKeyEvents(scene);
    }

    @FXML
    protected void goToUstawienia() throws IOException {

        String savedDifficulty = readDifficultySetting();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ustawienia-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        //WynikiModel.getInstance().getWyniki().add(new Wynik("GraczXD", savedDifficulty, 100));


        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }
    @FXML
    protected void goToTablicaWynikow() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TablicaWynikow-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }

    // Metoda do odczytu preferencji z poziomem trudności
    private String readDifficultySetting() {
        Preferences prefs = Preferences.userNodeForPackage(UstawieniaController.class);
        return prefs.get("poziomTrudnosci", "Średni"); // "Średni" to wartość domyślna
    }
    private boolean readMuzykaOn() {
        Preferences prefs = Preferences.userNodeForPackage(UstawieniaController.class);
       return prefs.getBoolean("checkEfektyDzwiekowe", false);
    }
    protected void powrocDoMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            Scene currentScene = labelMenu.getScene();

            // Ustaw scenę w obiekcie stage zdefiniowanym w klasie MainController
            stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void wylaczAplikacje()throws IOException{
        WynikiModel.getInstance().zapiszDoPliku();
        ZalogowanyUser.getInstance().setNickname("");
        String savedDifficulty = readDifficultySetting();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("wyborGry-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());
        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        //Platform.exit();
    }
}