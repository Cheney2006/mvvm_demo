package com.cheney.mvvm_demo.base.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.cheney.mvvm_demo.db.AppDatabase;
import com.cheney.mvvm_demo.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = {RepositoryModule.class})
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Context provideContext(Application application);

    @Singleton
    @Provides
   static AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "demo.db")
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade()
                .build();
    }

}
