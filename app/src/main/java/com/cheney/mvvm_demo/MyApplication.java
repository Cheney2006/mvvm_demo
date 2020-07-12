package com.cheney.mvvm_demo;

import com.cheney.mvvm_demo.base.di.DaggerAppComponent;
import com.cheney.mvvm_demo.base.di.NetworkModule;
import com.cheney.mvvm_demo.constants.Constants;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInstance.getInstance().setAppContext(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(new NetworkModule(Constants.Api.BASE_URL), this);
    }


}
