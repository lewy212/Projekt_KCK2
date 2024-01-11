package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sciana {
    private double x;
    private double y;

    MediaPlayer mediaPlayer;
    private Image wyglad;
    public Sciana(double x, double y) {
        this.x = x;
        this.y = y;
        this.wyglad = new Image(getClass().getResourceAsStream("/block2.png"));
        Media sound = new Media(getClass().getResource("/uderzenie_w_sciane.mp3").toExternalForm());

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
