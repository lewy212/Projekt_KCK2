package com.example.projekt2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LogowanieController {
    @FXML
    private Label labelMenu;
    @FXML
    private TextField loginField;
    @FXML
    private Label warningField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button zalogujButton;
    @FXML
    private Button powrotButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    private Stage stage;
    private boolean czyZalogowano;
    private Map<String, User> users = new HashMap<>();


    public void initialize() {

        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(zalogujButton);
        fadeAnimacje.addFadeInAnimation(powrotButton);
        fadeAnimacje.addFadeInAnimation(loginField);
        fadeAnimacje.addFadeInAnimation(passwordField);
        fadeAnimacje.addFadeInAnimation(loginLabel);
        fadeAnimacje.addFadeInAnimation(passwordLabel);

        wczytajDaneZPliku();
        czyZalogowano=false;

        loginField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    zaloguj();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Dodaj obsługę zdarzenia wciśnięcia klawisza Enter
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    zaloguj();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    protected void zaloguj()  throws IOException {

        login(loginField.getText(),passwordField.getText());
        if(czyZalogowano)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();
            Scene currentScene = labelMenu.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        }
    }

    @FXML
    protected void cofnij() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("logowanieORrejestracja-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }


    private void login(String username, String password) {
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            czyZalogowano = true;
            ZalogowanyUser zalogowanyUser = ZalogowanyUser.getInstance();
            zalogowanyUser.setNickname(users.get(username).getNickname());
        } else {
            warningField.setText("Podane błędne dane");
            loginField.setText("");
            passwordField.setText("");
        }
    }
    private void wczytajDaneZPliku() {
        try (InputStream is = getClass().getResourceAsStream("/daneUzytkownikow.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.put(parts[0], new User(parts[0], parts[1], parts[2]));
                } else {
                    // Obsługa sytuacji, gdy linia w pliku nie ma odpowiedniej liczby elementów
                    System.err.println("Błędny format linii w pliku: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
