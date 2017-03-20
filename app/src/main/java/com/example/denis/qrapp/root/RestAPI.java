package com.example.denis.qrapp.root;

import com.example.denis.qrapp.data.Kafic;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Denis on 17.2.2017..
 */

public interface RestAPI {

    @GET("/test")
    Call<Kafic> getKafic();
}
