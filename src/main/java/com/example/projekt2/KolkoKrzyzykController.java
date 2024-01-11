package com.example.projekt2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class KolkoKrzyzykController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;


    @FXML
    private Label labelMenu;

    @FXML
    private Label wypiszRuch;
    @FXML
    private Label wypiszWynik;
    @FXML
    private FlowPane plansza;

    private ArrayList<Button> buttons;

    private int ktory_gracz=1;
    private int ile_wygralO=0;
    private int ile_wygralX=0;


    public void initialize() {
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(plansza);
        fadeAnimacje.addFadeInAnimation(resetButton);
        fadeAnimacje.addFadeInAnimation(backButton);
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        resetButton.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        for (Button button : buttons) {
            button.setFocusTraversable(false);
            button.setOnAction(event -> wcisnietyPrzycisk(button));
        }

    }
   private void wcisnietyPrzycisk(Button button)
   {
       if(ktory_gracz%2==1)
       {
           button.setText("O");
           wypiszRuch.setText("Gracz X");
       }
       else
       {
           button.setText("X");
           wypiszRuch.setText("Gracz O");
       }
       ktory_gracz+=1;
       button.setDisable(true);
       sprawdzCzyKoniec();
   }
    @FXML
    void restart(ActionEvent event) {
        for (Button button : buttons) {
            button.setDisable(false);
            button.setText("");
        }
        ktory_gracz=1;
        wypiszRuch.setText("Gracz O");
    }
    @FXML
    protected void cofnij() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("wyborGry-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelMenu.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }


    public void sprawdzCzyKoniec(){
        boolean czyKoniec = false;
        for (int a = 0; a < 8; a++) {
            String tekst = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };
            if (tekst.equals("OOO")) {
                wypiszRuch.setText("Wygrał O");
                czyKoniec=true;
                ile_wygralO++;
                wypiszWynik.setText(ile_wygralO+":"+ile_wygralX);
                break;
            }
            else if (tekst.equals("XXX")) {
                wypiszRuch.setText("Wygrał X");
                ile_wygralX++;
                wypiszWynik.setText(ile_wygralO+":"+ile_wygralX);
                czyKoniec=true;
                break;
            }

        }
        if(czyKoniec)
        {
            for (Button button : buttons) {
                button.setDisable(true);
            }
        }
        else if(czyKoniec==false && buttons.stream().allMatch(Button::isDisabled))
        {
            wypiszRuch.setText("Remis");
        }
    }
}
