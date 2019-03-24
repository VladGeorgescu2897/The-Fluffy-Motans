package com.example.demo;

public class Loc {
    private int id, idParcare;
    private boolean ocupat;
    private int numar;

    public Loc(int id, int idParcare, boolean ocupat) {
        this.id = id;
        this.idParcare = idParcare;
        this.ocupat = ocupat;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParcare() {
        return idParcare;
    }

    public void setIdParcare(int idParcare) {
        this.idParcare = idParcare;
    }

    public boolean isOcupat() {
        return ocupat;
    }

    public void setOcupat(boolean ocupat) {
        this.ocupat = ocupat;
    }

    @Override
    public String toString() {
        return "Loc{" +
                "id=" + id +
                ", numar=" + numar +
                '}';
    }
}
