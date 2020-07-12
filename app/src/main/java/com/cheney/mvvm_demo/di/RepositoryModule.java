package com.cheney.mvvm_demo.di;

import com.cheney.mvvm_demo.api.GankApi;
import com.cheney.mvvm_demo.base.di.NetworkModule;
import com.cheney.mvvm_demo.db.AppDatabase;
import com.cheney.mvvm_demo.db.GankDao;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class RepositoryModule {

//    @Singleton
    @Provides
    GankApi provideGankApi(Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }

//    @Singleton
    @Provides
    GankDao provideGankDao(AppDatabase database) {
        return database.getGankDao();
    }
}
