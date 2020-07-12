package com.cheney.mvvm_demo.di;

import androidx.lifecycle.ViewModel;

import com.cheney.mvvm_demo.base.di.ViewModelKey;
import com.cheney.mvvm_demo.ui.detail.WebViewViewModel;
import com.cheney.mvvm_demo.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel.class)
    abstract ViewModel bindWebViewViewModel(WebViewViewModel webViewViewModel);


}
