package com.example.denis.qrapp.root;

import android.content.SharedPreferences;

import com.example.denis.qrapp.menu.MenuActivity;
import com.example.denis.qrapp.menu.MenuActivityMVP;
import com.example.denis.qrapp.menu.MenuModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Denis on 17.2.2017..
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, MenuModule.class})
public interface NetComponent {


    void inject(MenuActivity target);
}
