package com.example.recyclerview;

public class Feld {
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    private boolean isBomb;
    private boolean opened = false;

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    private boolean flagged = false;
    private int anliegendeBomben;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x, y;

    public Feld(boolean isBomb, int x, int y, int anliegendeBomben){
        this.isBomb = isBomb;
        this.y = y;
        this.x = x;
        this.anliegendeBomben = anliegendeBomben;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getAnliegendeBomben() {
        return anliegendeBomben;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
