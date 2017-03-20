package com.example.denis.qrapp.menu;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.denis.qrapp.data.Kafic;

import org.json.JSONException;
import org.json.JSONObject;

import dagger.Provides;

/**
 * Created by Denis on 13.2.2017..
 */

public interface MenuActivityMVP {

    interface View{
        void setViewData();




    }

    interface Presenter{
        void setView(MenuActivityMVP.View view);
        JSONObject requestData(Context context);
        Kafic getActiveKafic();
        void itemDodan(String naziv, int kolicina, String nacin);
        void getNaruceno();



    }

    interface Model{
        JSONObject getData(RequestQueue queue, StringRequest stringRequest);
        Kafic parseData(String data);
        void setActiveKafic(Kafic kafic);
        Kafic getActiveKafic();
        void setNaruceno(String naziv, int kolicina, String nacin);
        void getNaruceno();

    }
}
