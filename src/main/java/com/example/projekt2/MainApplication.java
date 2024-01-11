package com.example.projekt2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("wyborGry-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);

        // Dodaj arkusz stylów do sceny
        scene.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());

        Image icon = new Image("ikonagry.jpg");
        stage.setTitle("Projekt kck2");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
