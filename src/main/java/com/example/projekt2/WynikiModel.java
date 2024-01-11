package com.example.projekt2;

// WynikiModel.java
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;

public class WynikiModel {

    //private static final String FILE_PATH = "C:\\Users\\Lewy\\Desktop\\studia\\sem 5\\KCK\\Projekt 2\\projekt2\\src\\main\\resources\\wynikiWczytaj.txt";
    private static final WynikiModel instance = new WynikiModel();
    private final ObservableList<Wynik> wyniki = FXCollections.observableArrayList();
    //private static final String FILE_PATH = "C:\\Users\\Lewy\\Desktop\\studia\\sem 5\\KCK\\Projekt 2\\dane.txt";
    private static final String FILE_PATH;
    static {
        try {
            FILE_PATH = WynikiModel.class.getResource("/wynikiWczytaj.txt").toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private WynikiModel() {
        wczytajDaneZPliku();
        posortujWyniki();
    }
    public void posortujWyniki() {
        wyniki.sort(Comparator.comparingInt(Wynik::getPunkty).reversed());
    }
    private void wczytajDaneZPliku() {
        try (InputStream is = getClass().getResourceAsStream("/wynikiWczytaj.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nick = parts[0].trim();
                    String poziom = parts[1].trim();
                    int punkty = Integer.parseInt(parts[2].trim());

                    wyniki.add(new Wynik(nick, poziom, punkty));
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void zapiszDoPliku() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
           // System.out.println(FILE_PATH);
            for (Wynik wynik : wyniki) {
                String line = String.format("%s,%s,%d",
                        wynik.getNick(), wynik.getPoziom(), wynik.getPunkty());
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WynikiModel getInstance() {
        return instance;
    }

    public ObservableList<Wynik> getWyniki() {
        return wyniki;
    }
}
