package com.example.denis.qrapp.menu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.denis.qrapp.data.Kafic;
import com.example.denis.qrapp.data.NarucenoPice;
import com.example.denis.qrapp.data.Pice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.Module;
import okhttp3.Connection;

import static com.example.denis.qrapp.R.array.Pica;

/**
 * Created by Denis on 13.2.2017..
 */
@Module
public class MenuModel implements MenuActivityMVP.Model {


    @Inject
    Context context;


    private MenuRepository repository;

    public MenuModel(MenuRepository repository){
        this.repository = repository;
    }

    public JSONObject getData(RequestQueue queue, StringRequest stringRequest){



        queue.add(stringRequest);
        return null;
    }

    public Kafic parseData(String data) {


        JSONObject json = null;
        try {
            json = new JSONObject(data);
            String naziv = json.getString("naziv");
            int id = json.getInt("id");
            String adresa = json.getString("adresa");
            JSONArray picaArray = json.getJSONArray("pica");
            ArrayList<Pice> pica = new ArrayList<>();
            for(int i = 0; i < picaArray.length();i++){
                JSONObject temp = picaArray.getJSONObject(i);
                ArrayList<String> kolicinaTemp = new ArrayList<>();
                ArrayList<Double> cijenaTemp = new ArrayList<>();
                JSONArray cijenaSon = temp.getJSONArray("cijena");
                JSONArray kolicinaSon = temp.getJSONArray("kolicina");
                for(int j = 0; j<cijenaSon.length();j++){
                    cijenaTemp.add(cijenaSon.getDouble(j));
                    kolicinaTemp.add(kolicinaSon.getString(j));

                }
                pica.add(new Pice(temp.getString("naziv"), kolicinaTemp, cijenaTemp, temp.getInt("id"), temp.getString("kategorija")));
            }

        return new Kafic(naziv, id, adresa, pica);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return null;
    }

    public void setActiveKafic(Kafic kafic){
        repository.setActiveKafic(kafic);

        Log.d("Model:", "Active kafić postavljen!");

    }

    public Kafic getActiveKafic(){
        Log.d("Model:", "Tražim active kafić");
        return repository.getActiveKafic();
    }

    public void getNaruceno(){
        repository.getNarucene();
    }

    public void setNaruceno(String naziv, int kolicina, String nacin){
        repository.setNarucene(naziv, kolicina, nacin);
    }

    public void onAddFab(ArrayList<NarucenoPice> pica){

        
    }

}
