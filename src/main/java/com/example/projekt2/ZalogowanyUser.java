package com.example.projekt2;

public class ZalogowanyUser {
    private String nickname;
    private int przegrane_latwy,przegrane_sredni,przegrane_trudny;
    private static ZalogowanyUser instance = new ZalogowanyUser();
    public String getNickname(){return nickname;}

    public void setNickname(String nickname) {
        this.nickname = nickname;
        przegrane_latwy=3;
        przegrane_sredni=3;
        przegrane_trudny=3;
    }
    public static ZalogowanyUser getInstance()
    {
        if(instance==null) instance = new ZalogowanyUser();
        return instance;
    }

    public int getPrzegrane_latwy() {
        return przegrane_latwy;
    }

    public void setPrzegrane_latwy(int przegrane_latwy) {
        this.przegrane_latwy = przegrane_latwy;
    }

    public int getPrzegrane_sredni() {
        return przegrane_sredni;
    }

    public void setPrzegrane_sredni(int przegrane_sredni) {
        this.przegrane_sredni = przegrane_sredni;
    }

    public int getPrzegrane_trudny() {
        return przegrane_trudny;
    }

    public void setPrzegrane_trudny(int przegrane_trudny) {
        this.przegrane_trudny = przegrane_trudny;
    }
}
