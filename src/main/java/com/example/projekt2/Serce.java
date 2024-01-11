package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Serce {
    private double x;
    private double y;
    private Image wyglad;
    MediaPlayer mediaPlayer;
    public Serce(double x, double y) {
        this.x = x;
        this.y = y;
        this.wyglad = new Image(getClass().getResourceAsStream("/wyglad_zycia.png"));
        Media sound = new Media(getClass().getResource("/zebranie_serca.mp3").toExternalForm());

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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
