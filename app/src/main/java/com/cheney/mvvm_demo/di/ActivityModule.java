package com.cheney.mvvm_demo.di;

import com.cheney.mvvm_demo.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity provideMainActivity();
}
