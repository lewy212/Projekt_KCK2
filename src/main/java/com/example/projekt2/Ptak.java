package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Ptak {
    private double x;
    private double y;

    private Image wyglad[];
    MediaPlayer mediaPlayer;
    public Ptak(double x, double y) {
        this.x = x;
        this.y = y;
        Image ustaw = new Image(getClass().getResourceAsStream("/ptaki_lista.png"));
        this.wyglad = splitImage(ustaw, 5, 3);

        Media sound = new Media(getClass().getResource("/zderzenie_z_ptakiem.mp3").toExternalForm());

        // Tworzenie obiektu MediaPlayer
        this.mediaPlayer = new MediaPlayer(sound);
    }
    public Image getAnimacja1() {
        return wyglad[0];
    }

    public Image getAnimacja2() {
        return wyglad[1];
    }

    public Image getAnimacja3() {
        return wyglad[2];
    }

    public Image getAnimacja4() {
        return wyglad[3];
    }

    public Image getAnimacja5() {
        return wyglad[4];
    }

    public Image getAnimacja6() {
        return wyglad[5];
    }

    public Image getAnimacja7() {
        return wyglad[6];
    }

    public Image getAnimacja8() {
        return wyglad[7];
    }

    public Image getAnimacja9() {
        return wyglad[8];
    }

    public Image getAnimacja10() {
        return wyglad[9];
    }

    public Image getAnimacja11() {
        return wyglad[10];
    }

    public Image getAnimacja12() {
        return wyglad[11];
    }

    public Image getAnimacja13() {
        return wyglad[12];
    }

    public Image getAnimacja14() {
        return wyglad[13];
    }

    public Image getAnimacja15() {
        return wyglad[14];
    }



    private Image[] splitImage(Image originalImage, int cols, int rows) {
        PixelReader pixelReader = originalImage.getPixelReader();
        int width = (int) originalImage.getWidth() / cols;
        int height = (int) originalImage.getHeight() / rows;

        Image[] subImages = new Image[cols * rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                WritableImage subImage = new WritableImage(width, height);
                PixelWriter pixelWriter = subImage.getPixelWriter();

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int originalX = col * width + x;
                        int originalY = row * height + y;
                        pixelWriter.setArgb(x, y, pixelReader.getArgb(originalX, originalY));
                    }
                }

                int index = row * cols + col;
                subImages[index] = subImage;
            }
        }

        return subImages;
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


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
