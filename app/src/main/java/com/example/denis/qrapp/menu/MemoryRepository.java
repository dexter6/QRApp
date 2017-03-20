package com.example.denis.qrapp.menu;

import android.util.Log;

import com.example.denis.qrapp.data.Kafic;
import com.example.denis.qrapp.data.Narudzba;
import com.example.denis.qrapp.data.Pice;

import dagger.Provides;

/**
 * Created by Denis on 13.2.2017..
 */

public class MemoryRepository implements MenuRepository {

    Kafic activeKafic;
    String naziv;
    int kolicina;
    String nacin;

    Narudzba narudzba;


    @Override

    public Kafic getActiveKafic() {
        return activeKafic;
    }

    public void setActiveKafic(Kafic activeKafic) {
        this.activeKafic = activeKafic;
    }

    public void setNarucene(String naziv, int kolicina, String nacin){
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.nacin = nacin;
    }

    public void getNarucene(){

    }

    public Narudzba getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(Narudzba narudzba) {
        this.narudzba = narudzba;
    }
}
