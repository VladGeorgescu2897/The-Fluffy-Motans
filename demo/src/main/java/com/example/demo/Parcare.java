package com.example.demo;

import java.util.ArrayList;

public class Parcare {
    private int id;
    ArrayList<Loc> locuri;
    private String adresa;

    public Parcare(int id, String adresa) {
        locuri = new ArrayList<>();
        this.id = id;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

//    @Override
//    public String toString() {
//        return "Parcare{" +
//                "id=" + id +
//                ", locuri=" + locuri +
//                ", adresa='" + adresa + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Parcare{" +
                "locuri=" + locuri +
                '}';
    }
}
