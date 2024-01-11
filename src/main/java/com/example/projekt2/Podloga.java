package com.example.projekt2;

import javafx.scene.image.Image;

public class Podloga {
    private double x;
    private double y;

    private Image wyglad;

    public Podloga(double x, double y) {
        this.x = x;
        this.y = y;
        this.wyglad = new Image(getClass().getResourceAsStream("/block1.png"));
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
}
