package com.example.projekt2;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gra {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView zycieView;
    private Text scoreText;
    private ArrayList<Podloga> listaPodlogi;
    private ArrayList<Podloga> listaPlatform;
    private ArrayList<Ptak> listaPtakow;
    private ArrayList<ArrayList<ImageView>> listaWszystkichPlatform;
    private ArrayList<Sciana> listaScian;
    private ArrayList<Moneta> listaMonet;
    private ArrayList<Serce> listaSerc;

    private ArrayList<ImageView> listaPodlogiView;
    private ArrayList<ImageView> listaScianView;
    private ArrayList<ImageView> listaPlatformView;
    private ArrayList<ImageView> listaZyciaView;
    private ArrayList<ImageView> listaPtakowView;
    private ArrayList<ImageView> listaMonetView;
    private ArrayList<ImageView> listaSercView;
    private Image image_pomocnicze;
    private Postac postac;
    private Moneta moneta;
    private Rectangle obramowanieGry,kryjacyLewo,kryjacyPrawo;
    private double windowWidth, windowHeight;
    private boolean skok = false,spadek = false,dodajPoPrzeskoczeniu=true,bylem_na_platformie,wlaczSpadanie = false,czy_sa_ptaki,stworz_zycie=true,czy_jest_muzyka;
    private int czas_skoku = 0,czas_spadku=0;
    private Pane pane;
    private Timeline skokline, czasPrzesuwaniaPodlogi,zmianaWygladuPostaci, dodawajWynik,spadanieline,zmianaWygladuMonety;
    private Duration frameDuration;
    private double wspolrzedneXpierwszejPodlogi,dlugosc_platformy_x=0, pozycja_startowa_Y_postaci=0;
    private String poziom;
    private int postaw_sciane,co_ile_sciana,dlugosc_platformy,co_ile_platforma,postaw_platforme,ile_postawilem_platformy=0, ile_usunalem_platform=0,co_ile_ptak,postaw_ptaka,co_ile_zmiana_tempa=0,ile_razy_zmniejszylem=1;
    private int ile_dodaje_moneta,co_ile_moneta,ile_stworzylem_platform=0,co_ile_serce;
    private int wynik=0,mnoznik_wyniku,ile_dodac_po_ominieciu,rodzaj_skoku,postaw_monete;
    private MediaPlayer dzwiekSciany,dzwiekMonety,dzwiekSerca,dzwiekPtaka;
    private Button powrotButton= new Button("Powrót do menu");;
    public Pane createPane(Stage stage, Callback powrotCallback,String trudnosc_poziomu,boolean czyMuzyka) {

        pane = new Pane();
        pane.setStyle("-fx-background-color: White;");
        windowWidth = stage.getWidth();//szerokosc
        windowHeight = stage.getHeight();//wysokosc
        postac = new Postac(1,2);
        moneta = new Moneta(1,2);
        listaPodlogi = new ArrayList<>();
        listaScian = new ArrayList<>();
        listaPlatform = new ArrayList<>();
        listaPtakow = new ArrayList<>();
        listaPodlogiView = new ArrayList<>();
        listaScianView = new ArrayList<>();
        listaPlatformView = new ArrayList<>();
        listaWszystkichPlatform = new ArrayList<>();
        listaZyciaView = new ArrayList<>();
        listaPtakowView = new ArrayList<>();
        listaMonet = new ArrayList<>();
        listaMonetView = new ArrayList<>();
        listaSerc = new ArrayList<>();
        listaSercView = new ArrayList<>();
        image_pomocnicze = new Image(getClass().getResourceAsStream("/block1.png"));
        zycieView = new ImageView(new Image(getClass().getResourceAsStream("/wyglad_zycia.png")));
        imageView1 = createImageViewWithMargin(postac.getSkok());

        postaw_ptaka=0;
        postaw_monete=0;
        poziom = trudnosc_poziomu;
        czy_jest_muzyka=czyMuzyka;
        System.out.println(czy_jest_muzyka);
        obramowanieGry = new Rectangle(810,400);
        kryjacyLewo = new Rectangle(50,300);
        kryjacyPrawo=new Rectangle(50,300);
        ustawDanePoziomu(poziom);
        ustawCalyWyglad(pane);

        pane.getChildren().addAll(obramowanieGry,kryjacyLewo,kryjacyPrawo);

        pane.getChildren().addAll(imageView1);

        powrotButton.setOnAction(event -> powrotCallback.execute());
        powrotButton.setDisable(true);
        ustawListenery();
        powrotButton.setTranslateX(obramowanieGry.getTranslateX()+obramowanieGry.getWidth()/2 -powrotButton.getWidth()-80);
        powrotButton.setTranslateY(obramowanieGry.getTranslateY()+ obramowanieGry.getHeight() +70);
        powrotButton.getStyleClass().add("powrot-button");
        pane.getChildren().addAll(listaScianView);
        // Dodaj przycisk do Pane
        pane.getChildren().add(powrotButton);



        return pane;
    }

    private void ustawCalyWyglad (Pane pane)
    {

        obramowanieGry.setTranslateX((windowWidth - obramowanieGry.getWidth()) / 2);
        obramowanieGry.setTranslateY((windowHeight - obramowanieGry.getHeight()) / 2);

        postac.setX(obramowanieGry.getTranslateX()+77);
        postac.setY(obramowanieGry.getTranslateY() + obramowanieGry.getHeight() - imageView1.getBoundsInLocal().getHeight() -33);

        for(int i=0; i<26;i++){
            Podloga nowaPodloga = new Podloga(obramowanieGry.getTranslateX()+5 + i*32,obramowanieGry.getTranslateY() + obramowanieGry.getHeight() - imageView1.getBoundsInLocal().getHeight() -7);
            listaPodlogi.add(nowaPodloga);
            ImageView nowaPodlogaImage  = new ImageView();
            nowaPodlogaImage.setImage(nowaPodloga.getWyglad());
            nowaPodlogaImage.setTranslateX(nowaPodloga.getX());
            nowaPodlogaImage.setTranslateY(nowaPodloga.getY());
            listaPodlogiView.add(nowaPodlogaImage);
            if(i==0)
                wspolrzedneXpierwszejPodlogi = nowaPodlogaImage.getTranslateX();
        }

        pane.getChildren().addAll(listaPodlogiView);


        imageView1.setTranslateX(postac.getX());
        imageView1.setTranslateY(postac.getY());
        pozycja_startowa_Y_postaci = imageView1.getTranslateY();
        kryjacyLewo.setTranslateX(obramowanieGry.getTranslateX()-53);
        kryjacyLewo.setTranslateY(obramowanieGry.getTranslateY()+ obramowanieGry.getHeight()-kryjacyLewo.getHeight());
        kryjacyLewo.setFill(Color.WHITE);
        kryjacyPrawo.setTranslateX(obramowanieGry.getTranslateX()+obramowanieGry.getWidth()+3);
        kryjacyPrawo.setTranslateY(obramowanieGry.getTranslateY()+ obramowanieGry.getHeight()-kryjacyPrawo.getHeight());
        kryjacyPrawo.setFill(Color.WHITE);
        obramowanieGry.setFill(Color.TRANSPARENT);

        obramowanieGry.setStroke(Color.BLACK);
        obramowanieGry.setStrokeWidth(6);

        scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setFill(Color.BLACK);

        // Ustaw położenie napisu "Score:"
        scoreText.setTranslateX(obramowanieGry.getTranslateX() + obramowanieGry.getWidth() -140);
        scoreText.setTranslateY(obramowanieGry.getTranslateY() -14);
        for(int i=0; i<postac.getZycie();i++)
        {
            int x=0;
            ImageView noweZycieView = new ImageView();
            noweZycieView.setImage(zycieView.getImage());
            noweZycieView.setTranslateX(obramowanieGry.getTranslateX() + obramowanieGry.getWidth() -140 +i*noweZycieView.getImage().getWidth()+i*5);
            noweZycieView.setTranslateY(obramowanieGry.getTranslateY()+8);
            listaZyciaView.add(noweZycieView);
        }

        //listaMonetView.add(nowaMonetaView);

//        zycieView.setTranslateX(obramowanieGry.getTranslateX() + obramowanieGry.getWidth() -140);
//        zycieView.setTranslateY(obramowanieGry.getTranslateY()+8);
        pane.getChildren().add(scoreText);
        pane.getChildren().addAll(listaZyciaView);
        pane.getChildren().addAll(listaPlatformView);
        pane.getChildren().addAll(listaPtakowView);
        pane.getChildren().addAll(listaMonetView);
        pane.getChildren().addAll(listaSercView);
    }
    private void stworzNowaPodloge(Pane pane)
    {
        Podloga nowaPodloga = new Podloga(obramowanieGry.getTranslateX()+5 + 25*32,obramowanieGry.getTranslateY() + obramowanieGry.getHeight() - imageView1.getBoundsInLocal().getHeight() -7);
        ImageView nowaPodlogaImage  = new ImageView();
        nowaPodlogaImage.setImage(nowaPodloga.getWyglad());
        nowaPodlogaImage.setTranslateX(nowaPodloga.getX());
        nowaPodlogaImage.setTranslateY(nowaPodloga.getY());

        listaPodlogiView.add(nowaPodlogaImage);
        listaPodlogi.add(nowaPodloga);
        pane.getChildren().add(listaPodlogiView.get(listaPodlogiView.size()-1));
//        frameDuration = Duration.seconds(0.001);
//        czasPrzesuwaniaPodlogi.stop();
//        czasPrzesuwaniaPodlogi.getKeyFrames().clear();
//        czasPrzesuwaniaPodlogi.getKeyFrames().add(new KeyFrame(frameDuration, new ChangeImageHandler()));
//
//        // Restart the timeline
//        czasPrzesuwaniaPodlogi.play();

    }
    private void stworzNowaSciane()
    {
        Sciana nowaSciana1 =  new Sciana(obramowanieGry.getTranslateX()+5 + 25*32,obramowanieGry.getTranslateY() + obramowanieGry.getHeight() - imageView1.getBoundsInLocal().getHeight() -7 -listaPodlogiView.get(0).getImage().getHeight());
        Sciana nowaSciana2 =new Sciana(nowaSciana1.getX(), nowaSciana1.getY()-nowaSciana1.getWyglad().getHeight());
        ImageView nowaScianaImage1 = new ImageView();
        ImageView nowaScianaImage2 = new ImageView();
        nowaScianaImage1.setImage(nowaSciana1.getWyglad());
        nowaScianaImage1.setTranslateX(nowaSciana1.getX());
        nowaScianaImage1.setTranslateY(nowaSciana1.getY());
        nowaScianaImage2.setImage(nowaSciana2.getWyglad());
        nowaScianaImage2.setTranslateX(nowaSciana2.getX());
        nowaScianaImage2.setTranslateY(nowaSciana2.getY());
        listaScianView.add(nowaScianaImage1);
        pane.getChildren().add(listaScianView.get(listaScianView.size()-1));
        listaScianView.add(nowaScianaImage2);
        listaScian.add(nowaSciana1);
        listaScian.add(nowaSciana2);
        pane.getChildren().add(listaScianView.get(listaScianView.size()-1));
    }
    private void stworzNowaPlatforme(){
        Podloga nowaPlatforma = new Podloga(obramowanieGry.getTranslateX()+5 + 25*32,listaPodlogiView.get(1).getTranslateY()-listaPodlogiView.get(1).getImage().getHeight()*4);
        ImageView nowaPlatformaImage = new ImageView();
        nowaPlatformaImage.setImage(nowaPlatforma.getWyglad());
        nowaPlatformaImage.setTranslateX(nowaPlatforma.getX());
        nowaPlatformaImage.setTranslateY(nowaPlatforma.getY());
        listaPlatformView.add(nowaPlatformaImage);
        listaPlatform.add(nowaPlatforma);
        pane.getChildren().add(listaPlatformView.get(listaPlatformView.size()-1));
    }
    private void stworzNowegoPtaka(int ile){
        Ptak nowyPtak = new Ptak(obramowanieGry.getTranslateX()+8 + 25*32,listaPodlogiView.get(1).getTranslateY()-listaPodlogiView.get(1).getImage().getHeight()*5);
        ImageView nowyPtakView = new ImageView();
        nowyPtakView.setImage(nowyPtak.getWyglad());
        nowyPtakView.setTranslateX(nowyPtak.getX()+ile*nowyPtak.getWyglad().getWidth()+5*ile);
        nowyPtakView.setTranslateY(nowyPtak.getY());
        Scale flip = new Scale(-1, 1, nowyPtakView.getImage().getWidth() / 2, nowyPtakView.getImage().getHeight() / 2);
        nowyPtakView.getTransforms().add(flip);
        listaPtakow.add(nowyPtak);
        listaPtakowView.add(nowyPtakView);

        pane.getChildren().add(listaPtakowView.get(listaPtakowView.size()-1));
    }
    private void stworzNowaMonete()
    {
        Moneta nowaMoneta = new Moneta(obramowanieGry.getTranslateX()+5 + 26*32,listaPodlogiView.get(2).getTranslateY()-listaPodlogiView.get(0).getImage().getHeight()/2-1);
        ImageView nowaMonetaView = new ImageView();
        nowaMonetaView.setImage(nowaMoneta.getAnimacja1());
        nowaMonetaView.setTranslateX(nowaMoneta.getX());
        nowaMonetaView.setTranslateY(nowaMoneta.getY());
        listaMonetView.add(nowaMonetaView);
        listaMonet.add(nowaMoneta);
        pane.getChildren().add(listaMonetView.get(listaMonetView.size()-1));
    }
    private void stworzNoweSerce()
    {
        if(listaZyciaView.size()<3)
        {
            stworz_zycie=false;
            Serce serce = new Serce(obramowanieGry.getTranslateX()+5 + 26*32,listaPodlogiView.get(1).getTranslateY()-listaPodlogiView.get(1).getImage().getHeight()*5);
            ImageView noweSerceView = new ImageView();
            noweSerceView.setImage(serce.getWyglad());
            noweSerceView.setTranslateX(serce.getX());
            noweSerceView.setTranslateY(serce.getY());
            listaSercView.add(noweSerceView);
            listaSerc.add(serce);
            pane.getChildren().add(listaSercView.get(listaSercView.size()-1));
        }
    }
    private void ustawListenery()
    {
        frameDuration = Duration.seconds(0.01);
        czasPrzesuwaniaPodlogi = new Timeline(
                new KeyFrame(frameDuration, new PrzesunEkran())
        );

        frameDuration = Duration.seconds(0.3);
        zmianaWygladuPostaci = new Timeline(
                new KeyFrame(frameDuration, new ChangeImageHandler())
        );
        frameDuration = Duration.seconds(0.1);
        zmianaWygladuMonety = new Timeline(
                new KeyFrame(frameDuration, new zmienWygladMonety())
        );

        Duration czasSkoku = Duration.seconds(0.005);
        skokline = new Timeline(
                new KeyFrame(czasSkoku, new ZmienWygladSkok())
        );
        spadanieline =  new Timeline(
                new KeyFrame(czasSkoku, new SpadekPoPlatformie())
        );

        frameDuration = Duration.seconds(1);
        dodawajWynik = new Timeline(
                new KeyFrame(frameDuration, new DodajWynik())
        );


        skokline.setCycleCount(Timeline.INDEFINITE);
        skokline.play();

        spadanieline.setCycleCount(Timeline.INDEFINITE);
        spadanieline.play();

        czasPrzesuwaniaPodlogi.setCycleCount(Timeline.INDEFINITE);
        czasPrzesuwaniaPodlogi.play();

        zmianaWygladuPostaci.setCycleCount(Timeline.INDEFINITE);
        zmianaWygladuPostaci.play();

        zmianaWygladuMonety.setCycleCount(Timeline.INDEFINITE);
        zmianaWygladuMonety.play();

        dodawajWynik.setCycleCount(Timeline.INDEFINITE);
        dodawajWynik.play();
    }

    private class ChangeImageHandler implements EventHandler<ActionEvent> {
        private int currentImageIndex = 1;
        private int zmianaMonetyIndex=1;
        private boolean strona=true;

        @Override
        public void handle(ActionEvent event) {
            if(true)
            {
                switch (currentImageIndex) {
                    case 1:
                        imageView1.setImage(postac.getAnimacjaRuchu1());
                        break;
                    case 2:
                        imageView1.setImage(postac.getAnimacjaRuchu2());
                        break;

                }
                // Przejdź do następnej klatki
                currentImageIndex = (currentImageIndex % 2) + 1;
            }

        }
    }
    private class zmienWygladMonety implements EventHandler<ActionEvent> {
        private int zmianaMonetyIndex=1;
        private boolean strona=true;

        @Override
        public void handle(ActionEvent event) {
            if(listaMonetView.size()>0)
            {
                switch (zmianaMonetyIndex) {
                    case 1:
                        for(int i = 0;i<listaMonetView.size();i++)
                        {
                            listaMonetView.get(i).setImage(moneta.getAnimacja1());
                        }
                        strona=true;
                        break;
                    case 2:
                        for(int i = 0;i<listaMonetView.size();i++)
                        {
                            listaMonetView.get(i).setImage(moneta.getAnimacja2());
                        }
                        break;
                    case 3:
                        for(int i = 0;i<listaMonetView.size();i++)
                        {
                            listaMonetView.get(i).setImage(moneta.getAnimacja3());
                        }
                        break;
                    case 4:
                        for(int i = 0;i<listaMonetView.size();i++)
                        {
                            listaMonetView.get(i).setImage(moneta.getAnimacja4());
                            strona = false;
                        }
                        break;

                }
                if(strona)
                {
                    zmianaMonetyIndex +=1;
                }
                if(!strona)
                {
                    zmianaMonetyIndex -=1;
                }

            }
        }
    }
    private class PrzesunEkran implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            boolean flaga_blokady_tworzenia = true;
            for(int i=0;i<listaPodlogiView.size();i++)
            {
                listaPodlogiView.get(i).setTranslateX((listaPodlogiView.get(i).getTranslateX()-1));
            }
            for(int i=0;i<listaPlatformView.size();i++)
            {
                listaPlatformView.get(i).setTranslateX((listaPlatformView.get(i).getTranslateX()-1));
            }
            for(int i=0;i<listaScianView.size();i++)
            {
                listaScianView.get(i).setTranslateX((listaScianView.get(i).getTranslateX()-1));
            }
            for(int i=0;i<listaPtakowView.size();i++)
            {
                listaPtakowView.get(i).setTranslateX((listaPtakowView.get(i).getTranslateX()-1));
            }
            for(int i=0;i<listaMonetView.size();i++)
            {
                listaMonetView.get(i).setTranslateX((listaMonetView.get(i).getTranslateX()-1));
            }
            for(int i=0;i<listaSercView.size();i++)
            {
                listaSercView.get(i).setTranslateX((listaSercView.get(i).getTranslateX()-1));
            }
            kolizjaZplatforma();
            kolizjaZmoneta();
            kolizjaZsercem();
            if(listaScianView.size()>0){
                kolizjaZeSciana();
                if(listaScianView.get(0).getTranslateX()+listaScianView.get(0).getImage().getWidth()+1.5<=wspolrzedneXpierwszejPodlogi){

                    listaScianView.remove(0);
                    listaScianView.remove(0);
                    dodajPoPrzeskoczeniu=true;
                }
                if(listaScianView.get(0).getTranslateX()+listaScianView.get(0).getImage().getWidth()<imageView1.getTranslateX() && dodajPoPrzeskoczeniu==true)
                {
                    wynik+=ile_dodac_po_ominieciu;
                    scoreText.setText("Score: "+wynik);
                    dodajPoPrzeskoczeniu=false;
                }
            }
            if(listaPlatformView.size()>0)
            {
                if(listaPlatformView.get(0).getTranslateX()+listaPlatformView.get(0).getImage().getWidth()+1.5<=wspolrzedneXpierwszejPodlogi)
                {
                    ile_usunalem_platform++;
                    listaPlatformView.remove(0);
                    dlugosc_platformy_x -= image_pomocnicze.getWidth();
                }
            }
            if(listaPtakowView.size()>0)
            {

                if(listaPtakowView.get(0).getTranslateX()+listaPtakowView.get(0).getImage().getWidth()+1.5<=wspolrzedneXpierwszejPodlogi)
                {
                    listaPtakowView.remove(0);
                    // listaScianView.remove(0);
                }
                kolizjaZptakiem();
            }

            if(listaMonetView.size()>0)
            {
                if(listaMonetView.get(0).getTranslateX()+listaMonetView.get(0).getImage().getWidth()+1.5<=wspolrzedneXpierwszejPodlogi)
                {
                    listaMonetView.remove(0);
                }
            }

            if(listaSercView.size()>0)
            {
                if(listaSercView.get(0).getTranslateX()+listaSercView.get(0).getImage().getWidth()+1.5<=wspolrzedneXpierwszejPodlogi)
                {
                    listaSercView.remove(0);
                }
            }

            if(ile_usunalem_platform==dlugosc_platformy)
            {
                listaWszystkichPlatform.remove(0);
                dlugosc_platformy_x = image_pomocnicze.getWidth()*dlugosc_platformy;

                ile_usunalem_platform=0;
            }
            if(listaPodlogiView.get(1).getTranslateX()<=wspolrzedneXpierwszejPodlogi)
            {

                listaPodlogiView.remove(0);

                stworzNowaPodloge(pane);
                if(postaw_sciane==co_ile_sciana && poziom.equals("Łatwy"))
                {
                    stworzNowaSciane();
                }
                else if (postaw_sciane==co_ile_sciana&& (poziom.equals("Średni")||poziom.equals("Trudny")))
                {
                    Random random = new Random();
                    double losowaLiczba = random.nextDouble();

                    if(losowaLiczba<0.5)
                    {
                        stworzNowaSciane();
                        postaw_sciane = (postaw_sciane % co_ile_sciana) + 1;
                    }
                }
                else if (postaw_sciane!=co_ile_sciana&& (poziom.equals("Średni")||poziom.equals("Trudny")))
                {

                    postaw_sciane = (postaw_sciane % co_ile_sciana) + 1;

                }
                if(co_ile_moneta == postaw_monete)
                {
                    stworzNowaMonete();
                    postaw_monete = (postaw_monete % co_ile_moneta)+1;
                }

                if(co_ile_platforma==postaw_platforme)
                {
                    flaga_blokady_tworzenia = false;
                    ile_postawilem_platformy++;
                    stworzNowaPlatforme();
                    if(ile_postawilem_platformy == dlugosc_platformy/2)
                    {
                        if(stworz_zycie==true)
                        {
                            stworzNoweSerce();
                        }
                    }
                }
                pane.getChildren().remove(obramowanieGry);
                pane.getChildren().remove(kryjacyLewo);
                pane.getChildren().remove(kryjacyPrawo);
                pane.getChildren().add(obramowanieGry);
                pane.getChildren().add(kryjacyLewo);
                pane.getChildren().add(kryjacyPrawo);
                if(poziom.equals("Łatwy"))
                {
                    postaw_sciane = (postaw_sciane % co_ile_sciana) + 1;
                }
                if(ile_postawilem_platformy==dlugosc_platformy)
                {


                    flaga_blokady_tworzenia = true;
                    ile_postawilem_platformy=0;
                    listaWszystkichPlatform.add(listaPlatformView);
                    ile_stworzylem_platform++;
                    if(ile_stworzylem_platform%co_ile_serce==0)
                    {
                        stworz_zycie = true;
                    }
                }
                if(flaga_blokady_tworzenia)
                {
                    postaw_platforme = (postaw_platforme % co_ile_platforma)+1;
                }
                if(postaw_ptaka==co_ile_ptak && czy_sa_ptaki)
                {
                    int liczba;
                    Random rand = new Random();
                    liczba = rand.nextInt((3 - 1) + 1) +1 ;
                    for(int i=0;i<liczba;i++)
                    {
                        stworzNowegoPtaka(i);
                    }

                }
                postaw_monete = (postaw_monete % co_ile_moneta)+1;
                postaw_ptaka=(postaw_ptaka%co_ile_ptak)+1;
            }
        }
    }
    private void kolizjaZeSciana(){

        if(listaScianView.size()>0)
        {

            if(listaScianView.get(0).getTranslateX()-(listaScianView.get(0).getImage().getWidth()/2)<=imageView1.getTranslateX() &&
                    listaScianView.get(0).getTranslateX()+(listaScianView.get(0).getImage().getWidth()/2)>=imageView1.getTranslateX())
            {
                if (listaScianView.get(0).getTranslateY() + (listaScianView.get(0).getImage().getHeight() / 2) >= imageView1.getTranslateY() +(imageView1.getImage().getHeight()/2) -5 &&
                        imageView1.getTranslateY() +(imageView1.getImage().getHeight()/2) -2 >= listaScianView.get(1).getTranslateY() - (listaScianView.get(1).getImage().getHeight() / 2))
                {
                    if(czy_jest_muzyka)
                    {
                        dzwiekSciany = listaScian.get(0).getMediaPlayer();
                        dzwiekSciany.setCycleCount(1);
                        dzwiekSciany.seek(Duration.ZERO);
                        dzwiekSciany.play();
                    }


                    if(postac.getZycie()>1)
                    {
                        pane.getChildren().remove(listaScianView.get(0));
                        pane.getChildren().remove(listaScianView.get(1));
                        listaScianView.remove(0);
                        listaScianView.remove(0);
                        postac.setZycie(postac.getZycie()-1);
                        pane.getChildren().remove(listaZyciaView.get(listaZyciaView.size()-1));
                        listaZyciaView.remove(listaZyciaView.size()-1);
                    }
                    else
                    {
                        if(listaZyciaView.size()>0)
                        {
                            pane.getChildren().remove(listaZyciaView.get(listaZyciaView.size()-1));
                            listaZyciaView.remove(listaZyciaView.size()-1);
                        }

                        czasPrzesuwaniaPodlogi.stop();
                        skokline.stop();
                        dodawajWynik.stop();
                        WynikiModel.getInstance().getWyniki().add(new Wynik(ZalogowanyUser.getInstance().getNickname(), poziom, wynik));
                        powrotButton.setDisable(false);
                    }
                }
            }
        }
    }
    private boolean kolizjaZplatforma()
    {
        if(listaWszystkichPlatform.size()>0)
        {

            if(listaWszystkichPlatform.get(listaWszystkichPlatform.size()-1).size()>0)
            {
                int indeks=0;
                ArrayList<ImageView> listaPomocniczaPlatform = listaWszystkichPlatform.get(listaWszystkichPlatform.size()-1);

                if(listaPomocniczaPlatform.size()>0)
                {
                    for(int i=listaPomocniczaPlatform.size()-1;i>=0;i--)
                    {
                        if(listaPomocniczaPlatform.get(i).getTranslateX()-listaPomocniczaPlatform.get(0).getTranslateX()<=dlugosc_platformy_x)
                        {
                            indeks = i;
                            break;
                        }
                    }
                    if(listaPomocniczaPlatform.get(0).getTranslateX()-(listaPomocniczaPlatform.get(0).getImage().getWidth()/2)<=imageView1.getTranslateX() &&
                            listaPomocniczaPlatform.get(indeks).getTranslateX()+(listaPomocniczaPlatform.get(0).getImage().getWidth()/2)>=imageView1.getTranslateX())
                    {
                        if(listaPomocniczaPlatform.get(0).getTranslateY()-(listaPomocniczaPlatform.get(0).getImage().getHeight()/2)-0.5==imageView1.getTranslateY()+imageView1.getImage().getHeight()/2-2)
                        {

                            if(true)
                            {
                                spadek=false;
                                bylem_na_platformie = true;
                                czas_skoku = 0;
                                czas_spadku = 0;
                                return true;
                            }
                        }


                    } else if (bylem_na_platformie && skok==false && spadek==false) {
                        wlaczSpadanie = true;
                    }
                }

            }
        }

        return false;
    }

    private void kolizjaZptakiem()
    {

        if(listaPtakowView.size()>0)
        {
            for(int i=0;i<listaPtakowView.size();i++)
            {
                if(listaPtakowView.get(i).getTranslateX()-(listaPtakowView.get(i).getImage().getWidth()/2)<=imageView1.getTranslateX() &&
                        listaPtakowView.get(i).getTranslateX()+(listaPtakowView.get(i).getImage().getWidth()/2)>=imageView1.getTranslateX())
                {
                    if (listaPtakowView.get(i).getTranslateY() + (listaPtakowView.get(i).getImage().getHeight() / 2) >= imageView1.getTranslateY() -(imageView1.getImage().getHeight()/2) -5 &&
                            imageView1.getTranslateY() +(imageView1.getImage().getHeight()/2) -2 >= listaPtakowView.get(i).getTranslateY() - (listaPtakowView.get(i).getImage().getHeight() / 2))
                    {
                        if(czy_jest_muzyka)
                        {
                            dzwiekPtaka = listaPtakow.get(0).getMediaPlayer();
                            dzwiekPtaka.setCycleCount(1);
                            dzwiekPtaka.seek(Duration.ZERO);
                            dzwiekPtaka.play();
                        }

                        if(postac.getZycie()>1)
                        {
                            pane.getChildren().remove(listaPtakowView.get(i));
                            listaPtakowView.remove(i);
                            postac.setZycie(postac.getZycie()-1);
                            pane.getChildren().remove(listaZyciaView.get(listaZyciaView.size()-1));
                            listaZyciaView.remove(listaZyciaView.size()-1);
                        }
                        else
                        {
                            if(listaZyciaView.size()>0)
                            {
                                pane.getChildren().remove(listaZyciaView.get(listaZyciaView.size()-1));
                                listaZyciaView.remove(listaZyciaView.size()-1);
                            }

                            czasPrzesuwaniaPodlogi.stop();
                            skokline.stop();
                            dodawajWynik.stop();
                            WynikiModel.getInstance().getWyniki().add(new Wynik(ZalogowanyUser.getInstance().getNickname(), poziom, wynik));
                            powrotButton.setDisable(false);
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    private void kolizjaZmoneta()
    {

        if(listaMonetView.size()>0)
        {
            for(int i=0;i<listaMonetView.size();i++)
            {
                if(listaMonetView.get(i).getTranslateX()-(listaMonetView.get(i).getImage().getWidth()/2)<=imageView1.getTranslateX() &&
                        listaMonetView.get(i).getTranslateX()+(listaMonetView.get(i).getImage().getWidth()/2)>=imageView1.getTranslateX())
                {
                    if (listaMonetView.get(i).getTranslateY() + (listaMonetView.get(i).getImage().getHeight() / 2) >= imageView1.getTranslateY() -(imageView1.getImage().getHeight()/2) -5 &&
                            imageView1.getTranslateY() +(imageView1.getImage().getHeight()/2) -2 >= listaMonetView.get(i).getTranslateY() - (listaMonetView.get(i).getImage().getHeight() / 2))
                    {
                        if(czy_jest_muzyka)
                        {
                            dzwiekMonety = listaMonet.get(0).getMediaPlayer();
                            dzwiekMonety.setCycleCount(1);
                            dzwiekMonety.seek(Duration.ZERO);
                            dzwiekMonety.play();
                        }

                        pane.getChildren().remove(listaMonetView.get(i));
                        listaMonetView.remove(i);
                        wynik+=ile_dodaje_moneta;
                        scoreText.setText("Score: "+wynik);
                    }
                }
            }
        }
    }
    private void kolizjaZsercem()
    {
        if(listaSercView.size()>0)
        {
            for(int i=0;i<listaSercView.size();i++)
            {
                if(listaSercView.get(i).getTranslateX()-(listaSercView.get(i).getImage().getWidth()/2)<=imageView1.getTranslateX() &&
                        listaSercView.get(i).getTranslateX()+(listaSercView.get(i).getImage().getWidth()/2)>=imageView1.getTranslateX())
                {
                    if (listaSercView.get(i).getTranslateY() + (listaSercView.get(i).getImage().getHeight() / 2) >= imageView1.getTranslateY() -(imageView1.getImage().getHeight()/2) -5 &&
                            imageView1.getTranslateY() +(imageView1.getImage().getHeight()/2) -2 >= listaSercView.get(i).getTranslateY() - (listaSercView.get(i).getImage().getHeight() / 2))
                    {
                        pane.getChildren().remove(listaSercView.get(i));
                        listaSercView.remove(i);
                        postac.setZycie(postac.getZycie()+1);
                        ImageView noweZycieView = new ImageView();
                        noweZycieView.setImage(zycieView.getImage());
                        int x = listaZyciaView.size();
                        noweZycieView.setTranslateX(obramowanieGry.getTranslateX() + obramowanieGry.getWidth() -140 +x*noweZycieView.getImage().getWidth()+x*5);
                        noweZycieView.setTranslateY(obramowanieGry.getTranslateY()+8);
                        listaZyciaView.add(noweZycieView);
                        if(czy_jest_muzyka)
                        {
                            dzwiekSerca = listaSerc.get(0).getMediaPlayer();
                            dzwiekSerca.setCycleCount(1);
                            dzwiekSerca.seek(Duration.ZERO);
                            dzwiekSerca.play();
                        }

                        pane.getChildren().add(listaZyciaView.get(listaZyciaView.size()-1));
                    }
                }
            }
        }
    }
    private class ZmienWygladSkok implements EventHandler<ActionEvent> {


        @Override
        public void handle(ActionEvent event) {

            if(skok == true && spadek==false && bylem_na_platformie==false && wlaczSpadanie == false)
            {
                kolizjaZeSciana();

                //imageView1.setImage(postac.getAnimacjaRuchu1());
                postac.setY(postac.getY()-1);
                imageView1.setTranslateY(postac.getY());

                czas_skoku++;
                if(czas_skoku>=150)
                {
                    skok = false;
                    czas_skoku = 0;
                    spadek = true;
                }

            }
            if(skok == false && spadek == true && bylem_na_platformie==false && wlaczSpadanie == false)
            {
                kolizjaZplatforma();
                kolizjaZeSciana();
                postac.setY(postac.getY()+1);
                imageView1.setTranslateY(postac.getY());
                czas_spadku++;
                if(czas_spadku>=150)
                {
                    spadek=false;
                    czas_spadku=0;
                    imageView1.setImage(postac.getAnimacjaRuchu1());
                }
            }
            if(bylem_na_platformie == true && skok==true && wlaczSpadanie == false)
            {
                kolizjaZeSciana();

                //imageView1.setImage(postac.getAnimacjaRuchu1());
                postac.setY(postac.getY()-1);
                imageView1.setTranslateY(postac.getY());

                czas_skoku++;
                if(czas_skoku>=120)
                {
                    skok = false;
                    czas_skoku = 0;
                    spadek = true;
                }
            }
            if(bylem_na_platformie == true && spadek==true && wlaczSpadanie == false)
            {
                kolizjaZplatforma();
                kolizjaZeSciana();
                if(imageView1.getTranslateY()+1 <= pozycja_startowa_Y_postaci)
                {
                    postac.setY(postac.getY()+1);
                    imageView1.setTranslateY(postac.getY());
                }

                czas_spadku++;
                if(czas_spadku>=250)
                {
                    spadek=false;
                    czas_spadku=0;
                    imageView1.setImage(postac.getAnimacjaRuchu1());
                    bylem_na_platformie = false;
                }

            }

        }
    }
    private class SpadekPoPlatformie implements EventHandler<ActionEvent> {


        @Override
        public void handle(ActionEvent event) {
            if(wlaczSpadanie == true)
            {
                kolizjaZplatforma();
                kolizjaZeSciana();
                if(imageView1.getTranslateY()+1 <= pozycja_startowa_Y_postaci)
                {
                    postac.setY(postac.getY()+1);
                    imageView1.setTranslateY(postac.getY());
                }

                czas_spadku++;
                if(czas_spadku>=140)
                {
                    spadek=false;
                    czas_spadku=0;
                    imageView1.setImage(postac.getAnimacjaRuchu1());
                    bylem_na_platformie = false;
                    wlaczSpadanie = false;
                }
            }

        }
    }

    private class DodajWynik implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){
            wynik+=mnoznik_wyniku;
            scoreText.setText("Score: "+wynik);
            if(poziom.equals("Średni")|| poziom.equals("Trudny"))
            {
                if(wynik>co_ile_zmiana_tempa*ile_razy_zmniejszylem && ile_razy_zmniejszylem<9)
                {
                    Duration zmientempo = Duration.seconds(0.001);
                    Duration czasPodlogi = czasPrzesuwaniaPodlogi.getKeyFrames().get(0).getTime();

                    zmientempo = czasPodlogi.subtract(zmientempo);
                    czasPrzesuwaniaPodlogi.stop();
                    czasPrzesuwaniaPodlogi.getKeyFrames().clear();
                    czasPrzesuwaniaPodlogi.getKeyFrames().add(new KeyFrame(zmientempo,new PrzesunEkran()));
                    czasPrzesuwaniaPodlogi.play();

                    zmientempo = Duration.seconds(0.0005);
                    Duration czasSkoku = skokline.getKeyFrames().get(0).getTime();
                    zmientempo = czasSkoku.subtract(zmientempo);
                    skokline.stop();
                    skokline.getKeyFrames().clear();
                    skokline.getKeyFrames().add(new KeyFrame(zmientempo,new ZmienWygladSkok()));
                    skokline.play();
                    spadanieline.stop();
                    spadanieline.getKeyFrames().clear();
                    spadanieline.getKeyFrames().add(new KeyFrame(zmientempo, new SpadekPoPlatformie()));
                    spadanieline.play();
                    ile_razy_zmniejszylem++;

                }
            }
        }

    }
    private void ustawDanePoziomu(String trudnosc){
        if(trudnosc.equals("Łatwy"))
        {
            co_ile_sciana=12;
            co_ile_platforma=10;
            co_ile_ptak=18;
            co_ile_moneta = 15;
            ile_dodaje_moneta = 30;
            mnoznik_wyniku=1;
            dlugosc_platformy=14;
            ile_dodac_po_ominieciu=10;
            dlugosc_platformy_x = image_pomocnicze.getWidth()*14;
            czy_sa_ptaki=false;
            co_ile_serce=3;
            postac.setZycie(2);
        }
        if(trudnosc.equals("Średni"))
        {
            co_ile_sciana=8;
            co_ile_platforma=12;
            co_ile_ptak=18;
            co_ile_moneta = 20;
            ile_dodaje_moneta = 100;
            mnoznik_wyniku=10;
            dlugosc_platformy=8;
            ile_dodac_po_ominieciu=100;
            co_ile_zmiana_tempa=300;
            co_ile_serce=5;
            dlugosc_platformy_x = image_pomocnicze.getWidth()*8;
            czy_sa_ptaki=true;
            postac.setZycie(2);
        }
        if(trudnosc.equals("Trudny"))
        {
            co_ile_sciana=6;
            co_ile_platforma=15;
            co_ile_ptak=16;
            co_ile_moneta = 25;
            ile_dodaje_moneta = 200;
            mnoznik_wyniku=1;
            dlugosc_platformy=6;
            ile_dodac_po_ominieciu=200;
            co_ile_zmiana_tempa=500;
            dlugosc_platformy_x = image_pomocnicze.getWidth()*8;
            czy_sa_ptaki=true;
            co_ile_serce=6;
            postac.setZycie(1);
        }
    }
    public void addKeyEvents(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {


                    if(spadek!=true)
                    {
                        skok = true;
                    }
            }
            if (event.getCode() == KeyCode.DOWN) {
                if(spadek!=true && skok!=true)
                {
                    wlaczSpadanie=true;
                }


            }
        });
    }
    private ImageView createImageViewWithMargin(Image image) {
        ImageView imageView = new ImageView(image);
        // Dodaj odstęp między obrazkami
        VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
        return imageView;
    }
}
