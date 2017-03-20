package com.example.denis.qrapp.menu;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 13.2.2017..
 */

@Module
public class MenuModule {

    @Provides
    public MenuActivityMVP.Presenter provideMenuActivityPresenter(MenuActivityMVP.Model model){
        return new MenuActivityPresenter(model);
    }

    @Provides
    public MenuActivityMVP.Model provideMenuActivityModel(MenuRepository repository){
        return new MenuModel(repository);
    }

    @Provides
    public MenuRepository provideMenuRepository(){
        return new MemoryRepository();
    }
}
