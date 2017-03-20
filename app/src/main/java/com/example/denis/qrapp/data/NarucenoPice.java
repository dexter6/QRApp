package com.example.denis.qrapp.data;

/**
 * Created by Denis on 24.2.2017..
 */

public class NarucenoPice {
    private String naziv;
   // private int id;
    private double cijena;
    private int kolicina;
    private String nacin;

    public String getNacin() {
        return nacin;
    }

    public NarucenoPice(String naziv, double cijena, int kolicina, String nacin) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.nacin = nacin;
    }

    public void setNacin(String nacin) {
        this.nacin = nacin;
    }

    public NarucenoPice(String naziv, double cijena, int kolicina) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
