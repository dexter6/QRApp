package com.example.denis.qrapp.root;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 17.2.2017..
 */

@Module
public class NetModule {

    String baseUrl;

    public NetModule(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application){
        int cacheSize = 10*1024*1024; //10MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY.UPPER_CAMEL_CASE);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        OkHttpClient client = new OkHttpClient().newBuilder().cache(cache).build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(client)
                .build();

        return retrofit;
    }


}
