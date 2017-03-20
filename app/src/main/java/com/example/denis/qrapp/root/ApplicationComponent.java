package com.example.denis.qrapp.root;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.denis.qrapp.menu.MenuActivity;
import com.example.denis.qrapp.menu.MenuModule;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Denis on 13.2.2017..
 */

@Singleton
@Component(modules = {ApplicationModule.class, MenuModule.class, NetModule.class, FragmentModule.class})
public interface ApplicationComponent {




    void inject(MenuActivity target);
}

