package com.example.projekt2;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.util.Optional;
import java.util.prefs.Preferences;

public class MainController {
    @FXML
    private Label labelMenu;
    @FXML
    private Button graButton;

    @FXML
    private Button ustawieniaButton;

    @FXML
    private Button tablicaWynikowButton;
    @FXML
    private Button instrukcjaButton;

    @FXML
    private Button wylogujButton;
    private Stage stage;


    public void initialize() {

        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(graButton);
        fadeAnimacje.addFadeInAnimation(graButton);
        fadeAnimacje.addFadeInAnimation(ustawieniaButton);
        fadeAnimacje.addFadeInAnimation(tablicaWynikowButton);
        fadeAnimacje.addFadeInAnimation(wylogujButton);
        fadeAnimacje.addFadeInAnimation(instrukcjaButton);
    }

    @FXML
    protected void goToGra() throws IOException{

        String savedDifficulty = readDifficultySetting();
        Boolean czyMuzyka = readMuzykaOn();


        stage = (Stage) labelMenu.getScene().getWindow();

        Gra gra = new Gra();
        Scene scene;

        if(ZalogowanyUser.getInstance().getPrzegrane_latwy()>=3 && savedDifficulty.equals("Łatwy"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText("Czy chcesz zagrać w ułatwiony łatwy poziom?");
            Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
            stage2.getIcons().clear();
            // Dodajemy przyciski do okna
            ButtonType buttonTypeOK = new ButtonType("Tak");
            ButtonType buttonTypeCancel = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            // Czekamy na odpowiedź użytkownika
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOK) {
                savedDifficulty = "latwy_ulatwiony";
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
            else if (result.get() == buttonTypeCancel) {
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
        } else if (ZalogowanyUser.getInstance().getPrzegrane_sredni()>=3 && savedDifficulty.equals("Średni")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText("Czy chcesz zagrac w ulatwiony sredni poziom?");

            // Dodajemy przyciski do okna
            ButtonType buttonTypeOK = new ButtonType("Tak");
            ButtonType buttonTypeCancel = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            // Czekamy na odpowiedź użytkownika
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOK) {
                savedDifficulty = "sredni_ulatwiony";
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
            else if (result.get() == buttonTypeCancel) {
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
        }
        else if (ZalogowanyUser.getInstance().getPrzegrane_trudny()>=3 && savedDifficulty.equals("Trudny")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText("Czy chcesz zagrac w ulatwiony trudny poziom?");

            // Dodajemy przyciski do okna
            ButtonType buttonTypeOK = new ButtonType("Tak");
            ButtonType buttonTypeCancel = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            // Czekamy na odpowiedź użytkownika
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOK) {
                savedDifficulty = "trudny_ulatwiony";
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
            else if (result.get() == buttonTypeCancel) {
                scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                String cssPath = getClass().getResource("/style.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                gra.addKeyEvents(scene);
            }
        }
        else
        {
            scene = new Scene(gra.createPane(stage, this::powrocDoMenu,savedDifficulty,czyMuzyka), stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            scene.getRoot().requestFocus();
            String cssPath = getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
            gra.addKeyEvents(scene);
        }



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
    @FXML
    protected void goToInstrukcja() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("instrukcja-view.fxml"));
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
    protected void wylaczAplikacje() throws IOException {
        // Tworzymy okno potwierdzające
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy na pewno chcesz się wylogować?");
        alert.setContentText("Kliknij OK, aby potwierdzić.");

        // Dodajemy przyciski do okna
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        // Czekamy na odpowiedź użytkownika
        Optional<ButtonType> result = alert.showAndWait();

        // Jeżeli użytkownik kliknął OK, to zamykamy aplikację
        if (result.isPresent() && result.get() == buttonTypeOK) {
            WynikiModel.getInstance().zapiszDoPliku();
            ZalogowanyUser.getInstance().setNickname("");
            String savedDifficulty = readDifficultySetting();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("wyborGry-view.fxml"));
            Parent root = loader.load();
            Scene currentScene = labelMenu.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            root.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());
            stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        }
    }
}