package com.cheney.mvvm_demo.di;

import com.cheney.mvvm_demo.ui.detail.WebViewFragment;
import com.cheney.mvvm_demo.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract MainFragment provideMainFragment();

    @ContributesAndroidInjector
    abstract WebViewFragment provideWebViewFragment();
}
