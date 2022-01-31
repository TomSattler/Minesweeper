package com.example.recyclerview;

public class Minesweeper {

    private listeFelder listeFelder;

    public Minesweeper(int size, int mines){
        listeFelder = new listeFelder(size, mines);
        listeFelder.spielfeldGenerieren();
    }

    public listeFelder getListeFelder() {
        return listeFelder;
    }
}
