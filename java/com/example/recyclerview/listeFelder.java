package com.example.recyclerview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class listeFelder {
    private List<Feld> felder;
    private int amountMines;
    int size;

    public listeFelder(int size, int amountMines){
        this.amountMines = amountMines;
        this.size = size;
        felder = new ArrayList<Feld>();
        for(int i = 0; i <size*size; i++){
            Feld feld = new Feld(false, i, i % size, 0);
            felder.add(feld);
        }
    }
    public void spielfeldGenerieren(){
        int bombsPlaced = 0;
        while (bombsPlaced<this.amountMines){
            int x = (int)(Math.random() * this.size);
            int y = (int)(Math.random() * this.size);

            int index = getIndex(x, y);
            felder.get(index).setBomb(true);
            bombsPlaced++;
        }
        for (int x = 0; x<this.size; x++){
            for(int y = 0; y<this.size; y++){
                if(!indexToFeld(x, y).isBomb()){
                    felder.get(x+ (y*size)).setX(x);
                    felder.get(x+ (y*size)).setY(y);
                    List<Feld> temp = anliegendeFelder(x, y);
                    int bomben = 0;
                    for(Feld feld: temp){
                        if(feld.isBomb()){
                            bomben++;
                        }
                    }
                    if(bomben>0){
                        felder.set(x + (y*size), new Feld(false, x, y, bomben));
                    }
                }
            }
        }
    }
    public List<Feld> anliegendeFelder(int x , int y){
        List<Feld> anliegendeFelder = new ArrayList<>();
        List<Feld> listeFelder = new ArrayList<>();

        listeFelder.add(indexToFeld(x-1, y+1));
        listeFelder.add(indexToFeld(x, y+1));
        listeFelder.add(indexToFeld(x+1, y+1));
        listeFelder.add(indexToFeld(x-1, y));
        listeFelder.add(indexToFeld(x+1, y));
        listeFelder.add(indexToFeld(x-1, y-1));
        listeFelder.add(indexToFeld(x, y-1));
        listeFelder.add(indexToFeld(x+1, y-1));
        for(Feld feld : listeFelder){
            if(feld!=null) {
                    anliegendeFelder.add(feld);
            }
        }
        return anliegendeFelder;
    }
    public Feld indexToFeld(int x, int y){
        if(x>=this.size||x<0||y>=this.size||y<0){
            return null;
        }
        return felder.get(getIndex(x, y));
    }
    public int getIndex(int x, int y){
        return x + (y*size);
    }
    public int[] toXY(int index){
        int y = index / size;
        int x = index / (y*size);
        return new int []{x, y};
    }
    public List<Feld> getFelder() {
        return felder;
    }

    public int getSize() {
        return size;
    }
}
