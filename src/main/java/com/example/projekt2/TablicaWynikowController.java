package com.example.projekt2;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TablicaWynikowController {
    @FXML
    private Label labelWynikow;
    @FXML
    private HBox wyborHbox;
    @FXML
    private ChoiceBox<String> poziomTrudnosci;
    @FXML
    private CheckBox wyswietlWynikiCheckBox;
    @FXML
    private Button powrotButton;
    @FXML
    private TableView<Wynik> tablica; // Ustaw typ obiektów w TableView na typ twoich danych
    @FXML
    private TableColumn<Wynik, String> nickColumn;
    @FXML
    private TableColumn<Wynik, Integer> punktyColumn;
    @FXML
    private TableColumn<Wynik, String> poziomColumn;
    private ObservableList<Wynik> wyniki = WynikiModel.getInstance().getWyniki();

    public ObservableList<Wynik> getWyniki() {
        return wyniki;
    }
    @FXML
    private TableColumn<Wynik, Integer> numeracjaColumn;
    public void initialize() {
        FadeAnimacje fadeAnimacje = new FadeAnimacje();
        fadeAnimacje.addFadeInAnimation(wyborHbox);
        fadeAnimacje.addFadeInAnimation(wyswietlWynikiCheckBox);
        fadeAnimacje.addFadeInAnimation(tablica);
        fadeAnimacje.addFadeInAnimation(powrotButton);
        poziomTrudnosci.setValue("Łatwy");
        nickColumn.setCellValueFactory(new PropertyValueFactory<>("Nick"));
        punktyColumn.setCellValueFactory(new PropertyValueFactory<>("punkty"));
        poziomColumn.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        numeracjaColumn.setCellFactory(column -> {
            return new TableCell<Wynik, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            };
        });


        wyswietlWynikiCheckBox.setOnAction(this::getListaWynikowPoziomu);
        poziomTrudnosci.setOnAction(this::getListaWynikowPoziomu);

        ObservableList<Wynik> wynikiWyswietl =  FXCollections.observableArrayList();
        WynikiModel.getInstance().posortujWyniki();
        int ile=0;
        for(int i=0;i< wyniki.size();i++)
        {
                if(wyniki.get(i).getPoziom().equals(poziomTrudnosci.getValue())){
                    ile++;
                    wynikiWyswietl.add(wyniki.get(i));
                }
                if(ile==3)
                {
                    break;
                }

        }
        tablica.setItems(wynikiWyswietl);
    }
    @FXML
    protected void Powrot() throws IOException {
        // Zapisanie wybranej wartości przed przejściem do innego widoku

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();
        Scene currentScene = labelWynikow.getScene();
        Stage stage = (Stage) currentScene.getWindow();
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
    }
    public void setWyniki(ObservableList<Wynik> wyniki) {
        this.wyniki = wyniki;
        tablica.setItems(wyniki);
    }
    public void getListaWynikowPoziomu(ActionEvent event){
        ObservableList<Wynik> wynikiWyswietl =  FXCollections.observableArrayList();
        int ile=0;
        for(int i=0;i< wyniki.size();i++)
        {
            if(wyswietlWynikiCheckBox.isSelected())
            {
                if(wyniki.get(i).getPoziom().equals(poziomTrudnosci.getValue())){
                    if(wyniki.get(i).getNick().equals(ZalogowanyUser.getInstance().getNickname()))
                    {
                        wynikiWyswietl.add(wyniki.get(i));
                        ile++;
                    }
                    if(ile==3)
                    {
                        break;
                    }
                }

            }
            else
            {
                if(wyniki.get(i).getPoziom().equals(poziomTrudnosci.getValue())){
                    wynikiWyswietl.add(wyniki.get(i));
                    ile++;
                }
                if(ile==3)
                {
                    break;
                }
            }
        }

        tablica.setItems(wynikiWyswietl);

        numeracjaColumn.setCellFactory(column -> {
            return new TableCell<Wynik, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            };
        });
    }
}
