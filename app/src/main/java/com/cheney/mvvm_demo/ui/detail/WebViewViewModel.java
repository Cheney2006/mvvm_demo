package com.cheney.mvvm_demo.ui.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.mvvm_demo.entity.Gank;
import com.cheney.mvvm_demo.repository.GankRepository;

import javax.inject.Inject;

public class WebViewViewModel extends ViewModel {

    private final GankRepository gankRepository;

    @Inject
    public WebViewViewModel(GankRepository gankRepository) {
        this.gankRepository = gankRepository;
    }

    public MutableLiveData<Gank> gank = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();
    public MutableLiveData<Integer> progress = new MutableLiveData<>();


    public void getGankById(String id) {
        gankRepository.getGankById(id).subscribe(data -> gank.postValue(data), throwable -> error.postValue(throwable));
    }
}