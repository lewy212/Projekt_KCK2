package com.example.projekt2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RejestracjaController {
    @FXML
    private Label labelMenu;
    @FXML
    private Label warningField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nicknameField;
    @FXML
    private Button wrocButton;
    @FXML
    private Button zarejestrujButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label nicknameLabel;

    private Map<String, User> users = new HashMap<>();
    private boolean czyZarejestrowano;
    private static final String FILE_PATH;
    static {
        try {
            FILE_PATH = WynikiModel.class.getResource("/daneUzytkownikow.txt").toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize() {
        wczytajDaneZPliku();
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(loginField);
        fadeAnimacje.addFadeInAnimation(passwordField);
        fadeAnimacje.addFadeInAnimation(nicknameField);
        fadeAnimacje.addFadeInAnimation(wrocButton);
        fadeAnimacje.addFadeInAnimation(zarejestrujButton);
        fadeAnimacje.addFadeInAnimation(passwordLabel);
        fadeAnimacje.addFadeInAnimation(loginLabel);
        fadeAnimacje.addFadeInAnimation(nicknameLabel);

        loginField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if(!loginField.getText().isEmpty() && !passwordField.getText().isEmpty() && !nicknameField.getText().isEmpty())
                    {
                        zarejestruj();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Dodaj obsługę zdarzenia wciśnięcia klawisza Enter
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if(!loginField.getText().isEmpty() && !passwordField.getText().isEmpty() && !nicknameField.getText().isEmpty())
                    {
                        zarejestruj();
                    };
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        nicknameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if(!loginField.getText().isEmpty() && !passwordField.getText().isEmpty() && !nicknameField.getText().isEmpty())
                    {
                        zarejestruj();
                    };
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    protected void zarejestruj() throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        String nickname = nicknameField.getText();

        if (login.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
            warningField.setText("Podano nieprawidlowe dane");
            czyZarejestrowano=false;
        }
        else
        {
            czyZarejestrowano=true;
        }
        if(czyIstniejeNickname(nickname))
        {
            warningField.setText("Istnieje osoba o takim nicku");
            czyZarejestrowano=false;
        }
        if(czyIstniejeLogin(login))
        {
            warningField.setText("Istnieje osoba o takim loginie");
            czyZarejestrowano=false;
        }

        if(czyZarejestrowano)
        {
            zapiszDoPliku(login, password, nickname);

            // Przejdź z powrotem do widoku logowania/rejestracji
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logowanieORrejestracja-view.fxml"));
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

    private void zapiszDoPliku(String login, String password, String nickname) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User user = entry.getValue();
                String line = String.format("%s,%s,%s", user.getLogin(), user.getPassword(), user.getNickname());
                writer.write(line);
                writer.newLine();
            }
            String line = String.format("%s,%s,%s",login,password,nickname);
            writer.write(line);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean czyIstniejeNickname(String nickname) {
        // Sprawdź, czy istnieje już osoba o danym nickname
        return users.values().stream().anyMatch(user -> user.getNickname().equals(nickname));
    }
    private boolean czyIstniejeLogin(String login) {
        // Sprawdź, czy istnieje już osoba o danym nickname
        return users.values().stream().anyMatch(user -> user.getLogin().equals(login));
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
