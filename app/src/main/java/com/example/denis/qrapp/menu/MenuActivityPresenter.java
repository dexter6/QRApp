package com.example.denis.qrapp.menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.denis.qrapp.data.Kafic;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.Provides;

import static android.content.ContentValues.TAG;

/**
 * Created by Denis on 13.2.2017..
 */

public class MenuActivityPresenter implements MenuActivityMVP.Presenter {

    @Nullable
    private MenuActivityMVP.View view;
    private MenuActivityMVP.Model model;

    @Inject
    public MenuActivityPresenter(MenuActivityMVP.Model model){
        this.model = model;
    }

    public void setView(MenuActivityMVP.View view) {
        this.view = view;
    }

    public JSONObject requestData(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://demo3562678.mockable.io/test";

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Malo ćemo pričekati");
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response){

                Kafic active = model.parseData(response);
               model.setActiveKafic(active);
                view.setViewData();
                dialog.dismiss();

                

            }
        }, new Response.ErrorListener(){
            @Override
            public  void onErrorResponse(VolleyError error){
                Log.d("Response", "error");
            }
        });


        model.getData(queue, stringRequest);

        return null;
    }

    public Kafic getActiveKafic(){
        return model.getActiveKafic();
    }

    public void itemDodan(String naziv, int kolicina, String nacin){
        model.setNaruceno(naziv, kolicina, nacin);
    }

    public void getNaruceno(){
        model.getNaruceno();
    }







}
