package com.cheney.mvvm_demo.base.di;

import android.app.Application;

import com.cheney.mvvm_demo.di.ActivityModule;
import com.cheney.mvvm_demo.di.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, SystemModule.class, BaseViewModelModule.class, ActivityModule.class, ViewModelModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        AppComponent create(NetworkModule networkModule, @BindsInstance Application application);
    }
}
