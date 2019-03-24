package com.example.demo;

public class Masina {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private boolean butonRezervare;
    private boolean venitFaraRezervare;
    private String numarMasina;

    public Masina(boolean butonRezervare, boolean venitFaraRezervare, String numarMasina)
    {
        this.butonRezervare = butonRezervare;
        this.venitFaraRezervare = venitFaraRezervare;
        this.numarMasina = numarMasina;
    }

    public String toString() {
        return "Buton Rezervare: "+this.butonRezervare+", Venit fara rezervare: "+this.venitFaraRezervare;
    }

    public void setButonRezervare(boolean butonRezervare) {
        this.butonRezervare = butonRezervare;
    }

    public boolean getButonRezervare() {
        return butonRezervare;
    }

    public void setVenitFaraRezervare(boolean venitFaraRezervare) {
        this.venitFaraRezervare = venitFaraRezervare;
    }

    public boolean getVenitFaraRezervare() {
        return venitFaraRezervare;
    }

    public void setNumarMasina(String numarMasina) {
        this.numarMasina = numarMasina;
    }

    public String getNumarMasina() {
        return numarMasina;
    }

    public String generareCodMasina(boolean butonRezervare, String numarMasina) {
        String s = "";
        if(butonRezervare == true )
        {
            int count = 5;
            StringBuilder builder = new StringBuilder();
            while (count-- != 0)
            {
                int character = (int)(Math.random()* ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            s = s.concat(numarMasina);
            String aux = builder.toString();
            s = s.concat(aux);
            return s;
        }
        s = "Aceasta masina nu doreste sa parcheze in aceasta parcare";
        return s;
    }
}
