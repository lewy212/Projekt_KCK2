package com.example.projekt2;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Postac {
    private double x;
    private double y;
    private int zycie;

    private Image[] wyglad;

    public Postac(double x, double y) {
        this.x = x;
        this.y = y;
        Image ustaw = new Image(getClass().getResourceAsStream("/mario.png"));
        this.wyglad = splitImage(ustaw, 3,1);
        this.zycie=1;
    }

    private Image[] splitImage(Image originalImage, int numParts, int partIndex) {
        PixelReader pixelReader = originalImage.getPixelReader();

        int width = (int) originalImage.getWidth() / numParts;
        int height = (int) originalImage.getHeight();

        WritableImage[] subImages = new WritableImage[numParts];

        for (int i = 0; i < numParts; i++) {
            subImages[i] = new WritableImage(width, height);
            PixelWriter pixelWriter = subImages[i].getPixelWriter();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int originalX = x + i * width;
                    pixelWriter.setArgb(x, y, pixelReader.getArgb(originalX, y));
                }
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

    public int getZycie() {
        return zycie;
    }

    public void setZycie(int zycie) {
        this.zycie = zycie;
    }

    public Image[] getWyglad(int ktory) {
        return wyglad;
    }
    public Image getSkok(){
        return wyglad[0];
    }
    public Image getAnimacjaRuchu1(){
        return wyglad[1];
    }
    public Image getAnimacjaRuchu2(){
        return wyglad[2];
    }
    public void setWyglad(Image[] wyglad) {
        this.wyglad = wyglad;
    }
}
