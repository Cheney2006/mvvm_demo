package com.cheney.mvvm_demo.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.cheney.mvvm_demo.R;
import com.cheney.mvvm_demo.databinding.MainFragmentBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class MainFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mViewModel;
    private MainFragmentBinding mBinding;

    private GankRecyclerAdapter mGankRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        // 必须设置这个, 不然viewModel中的livedata改变不会收到通知
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding.toolbar.myToolbar.setTitle("主页");

        mGankRecyclerAdapter = new GankRecyclerAdapter(gank -> {
            if (TextUtils.isEmpty(gank.getUrl())) {
                return;
            }
            NavDirections navDirections = MainFragmentDirections.actionMainFragmentToWebViewFragment(gank.getGankId());
            NavHostFragment.findNavController(this).navigate(navDirections);

        });
        mBinding.gankList.setAdapter(mGankRecyclerAdapter);


        mViewModel.todayGank.observe(getViewLifecycleOwner(), response -> {
            mGankRecyclerAdapter.setTodayGanks(response.toList());
        });
        mViewModel.error.observe(getViewLifecycleOwner(), throwable -> {
            Log.e("MVVM", "load failed", throwable);
            Toast.makeText(this.getActivity(), "Load Error", Toast.LENGTH_LONG).show();
        });
    }

}