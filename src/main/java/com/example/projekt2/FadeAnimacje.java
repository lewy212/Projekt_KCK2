package com.example.projekt2;

import javafx.animation.FadeTransition;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FadeAnimacje {

    public void addFadeInAnimation(HBox hBox) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), hBox);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(Button button) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), button);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(TableView tableView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), tableView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(CheckBox checkBox) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), checkBox);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(TextField textField) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), textField);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(FlowPane flowPane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), flowPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), label);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    public void addFadeInAnimation(VBox vBox) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), vBox);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
