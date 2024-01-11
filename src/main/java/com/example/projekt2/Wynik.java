package com.example.projekt2;

public class Wynik {
    private String Nick;
    private int punkty;
    private String poziom;

    public Wynik(String nazwaGracza, String poziomTrudnosci, int wynik) {
        this.Nick = nazwaGracza;
        this.punkty = wynik;
        this.poziom = poziomTrudnosci;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        this.Nick = nick;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public String getPoziom() {
        return poziom;
    }

    public void setPoziom(String poziom) {
        this.poziom = poziom;
    }
}
