package com.example.projekt2;

public class ZalogowanyUser {
    private String nickname;
    private static ZalogowanyUser instance = new ZalogowanyUser();
    public String getNickname(){return nickname;}

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public static ZalogowanyUser getInstance()
    {
        if(instance==null) instance = new ZalogowanyUser();
        return instance;
    }
}
