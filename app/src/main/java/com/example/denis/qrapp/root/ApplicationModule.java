package com.example.denis.qrapp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 13.2.2017..
 */

@Module
public class ApplicationModule {

    private Application application;


    public ApplicationModule(Application application){
        this.application = application;
    }



    @Provides
    @Singleton
    public Application provideContext(){
        return application;
    }
}
