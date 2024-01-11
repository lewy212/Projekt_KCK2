package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Ptak {
    private double x;
    private double y;

    private Image wyglad;
    MediaPlayer mediaPlayer;
    public Ptak(double x, double y) {
        this.x = x;
        this.y = y;
        this.wyglad = new Image(getClass().getResourceAsStream("/ptak_zdjecie.png"));
        Media sound = new Media(getClass().getResource("/zderzenie_z_ptakiem.mp3").toExternalForm());

        // Tworzenie obiektu MediaPlayer
        this.mediaPlayer = new MediaPlayer(sound);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getWyglad() {
        return wyglad;
    }

    public void setWyglad(Image wyglad) {
        this.wyglad = wyglad;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
