package com.example.denis.qrapp.root;

import android.app.Application;

import com.example.denis.qrapp.menu.MenuModule;

/**
 * Created by Denis on 13.2.2017..
 */

public class App extends Application {

    private ApplicationComponent component;
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .menuModule(new MenuModule())
                .netModule(new NetModule("http://demo3562678.mockable.io/"))
                .build();
    }


    public ApplicationComponent getComponent(){
        return component;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
