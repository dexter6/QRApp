package com.example.denis.qrapp.root;

import android.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 17.2.2017..
 */
@Module
public class FragmentModule {

    Fragment fragment;
    FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }
}
