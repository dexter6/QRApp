package com.example.denis.qrapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Denis on 13.2.2017..
 */

public class Kafic implements Serializable {


    private String naziv;
    private int id;
    private String adresa;
    private ArrayList<Pice> pica;
    private ArrayList<Pice> topli, vina, bezalkoholni, pivo, zestoki;

    public Kafic(String naziv, int id, String adresa, ArrayList<Pice> pica) {
        this.naziv = naziv;
        this.id = id;
        this.adresa = adresa;
        this.pica = pica;
        topli = new ArrayList<>();
        vina = new ArrayList<>();
        bezalkoholni = new ArrayList<>();
        pivo = new ArrayList<>();
        zestoki = new ArrayList<>();
        for (int i = 0;i<pica.size();i++){
            Pice temp = pica.get(i);
            switch (temp.getKategorija()){
                case "Topli napitci":
                    topli.add(temp);
                    break;
                case "Vina":
                    vina.add(temp);
                    break;
                case "Bezalkoholna pića":
                    bezalkoholni.add(temp);
                    break;
                case "Pivo":
                    pivo.add(temp);
                    break;
                case "Žestoka pića":
                    zestoki.add(temp);
                    break;
            }
        }

    }

    public String getNaziv() {

        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public ArrayList<Pice> getPica() {
        return pica;
    }

    public void setPica(ArrayList<Pice> pica) {
        this.pica = pica;
    }

    public String toString() {
        return naziv + " " + adresa;
    }

    public ArrayList<Pice> getTopli() {
        return topli;
    }

    public ArrayList<Pice> getVina() {
        return vina;
    }

    public ArrayList<Pice> getBezalkoholni() {
        return bezalkoholni;
    }

    public ArrayList<Pice> getPivo() {
        return pivo;
    }

    public ArrayList<Pice> getZestoki() {
        return zestoki;
    }
}
