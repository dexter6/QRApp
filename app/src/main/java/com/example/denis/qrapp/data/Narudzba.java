package com.example.denis.qrapp.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Denis on 24.2.2017..
 */

public class Narudzba {
    private ArrayList<NarucenoPice> pica;
    private String napomena;

    public Narudzba(){
        pica = new ArrayList<>();
        napomena = "";
    }

    public void addPice(NarucenoPice pice){
        pica.add(pice);
    }

    public void removePice(int i){
        pica.remove(i);
    }

    public ArrayList<NarucenoPice> getPica() {
        return pica;
    }

    public void setPica(ArrayList<NarucenoPice> pica) {
        this.pica = pica;
    }
}
