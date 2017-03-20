package com.example.denis.qrapp.menu;

import com.example.denis.qrapp.data.Kafic;

/**
 * Created by Denis on 13.2.2017..
 */

public interface MenuRepository {

        Kafic getActiveKafic();
        void setActiveKafic(Kafic kafic);
        void getNarucene();
        void setNarucene(String naziv, int kolicina, String nacin);
}
