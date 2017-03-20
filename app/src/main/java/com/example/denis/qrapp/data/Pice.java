package com.example.denis.qrapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Denis on 13.2.2017..
 */

public class Pice implements Serializable {

    private String naziv;

    private ArrayList<String> kolicina;
    private ArrayList<Double> cijena;
    private int id;
    private String kategorija;



    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<String> getKolicina() {
        return kolicina;
    }

    public void setKolicina(ArrayList<String> kolicina) {
        this.kolicina = kolicina;
    }

    public ArrayList<Double> getCijena() {
        return cijena;
    }

    public void setCijena(ArrayList<Double> cijena) {
        this.cijena = cijena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public Pice(String naziv, ArrayList<String> kolicina, ArrayList<Double> cijena, int id, String kategorija) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cijena = cijena;
        this.id = id;
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return naziv + kolicina + cijena + kategorija;
    }

    public Pice(String naziv) {
        this.naziv = naziv;
        this.kolicina = new ArrayList<>();
        this.cijena = new ArrayList<>();
        this.id = -1;
        this.kategorija = "nije";
    }
}
