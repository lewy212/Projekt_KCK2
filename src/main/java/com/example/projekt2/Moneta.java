package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Moneta {
    private double x;
    private double y;
    private Image[] wyglad;
    MediaPlayer mediaPlayer;

    public Moneta(double x, double y) {
        this.x = x;
        this.y = y;
        Image ustaw = new Image(getClass().getResourceAsStream("/bonus.png"));
        this.wyglad = splitImage(ustaw, 4,1);
        Media sound = new Media(getClass().getResource("/zebranie_monety.mp3").toExternalForm());

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

    public Image[] getWyglad() {
        return wyglad;
    }

    public void setWyglad(Image[] wyglad) {
        this.wyglad = wyglad;
    }
    public Image getAnimacja1(){
        return wyglad[0];
    }
    public Image getAnimacja2(){
        return wyglad[1];
    }
    public Image getAnimacja3(){
        return wyglad[2];
    }
    public Image getAnimacja4(){
        return wyglad[3];
    }
    private Image[] splitImage(Image originalImage, int numParts, int partIndex) {
        PixelReader pixelReader = originalImage.getPixelReader();

        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight() / numParts;

        WritableImage[] subImages = new WritableImage[numParts];

        for (int i = 0; i < numParts; i++) {
            subImages[i] = new WritableImage(width, height);
            PixelWriter pixelWriter = subImages[i].getPixelWriter();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int originalY = y + i * height;
                    pixelWriter.setArgb(x, y, pixelReader.getArgb(x, originalY));
                }
            }
        }

        return subImages;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
