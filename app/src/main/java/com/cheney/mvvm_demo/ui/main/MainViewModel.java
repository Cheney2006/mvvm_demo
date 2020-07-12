package com.cheney.mvvm_demo.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.mvvm_demo.bean.TodayGank;
import com.cheney.mvvm_demo.repository.GankRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private final GankRepository gankRepository;

    @Inject
    MainViewModel(GankRepository gankRepository) {
        this.gankRepository = gankRepository;
        init();
    }

    //是否加载
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<TodayGank> todayGank = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();


    private void init() {
        isLoading.postValue(true);
        gankRepository.getTodayGanks().subscribe(response -> {
            isLoading.postValue(false);
            todayGank.postValue(response);
        }, throwable -> {
            isLoading.postValue(false);
            error.postValue(throwable);
        });
    }

    public void refresh() {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            init();
        }
    }


}