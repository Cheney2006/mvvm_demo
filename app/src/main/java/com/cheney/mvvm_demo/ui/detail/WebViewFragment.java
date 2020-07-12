package com.cheney.mvvm_demo.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.cheney.mvvm_demo.R;
import com.cheney.mvvm_demo.databinding.WebViewFragmentBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class WebViewFragment extends DaggerFragment {
    public static final String GANK_ID = "gank_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private WebViewViewModel mViewModel;

    private WebViewFragmentBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                System.out.println("点击返回键");
                requireActivity().finish();
            }
        });


        mViewModel = new ViewModelProvider(this, viewModelFactory).get(WebViewViewModel.class);

        String gankId = WebViewFragmentArgs.fromBundle(getArguments()).getGankId();
        if (!TextUtils.isEmpty(gankId)) {
            mViewModel.getGankById(gankId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.web_view_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);

        mBinding.toolbar.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        mBinding.toolbar.myToolbar.setNavigationIcon(R.mipmap.navigation_back_white);

        mBinding.webview.setWebViewClient(new WebViewClient());
        mBinding.webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //设置进度
                mViewModel.progress.postValue(newProgress);
            }
        });
        mBinding.webview.getSettings().setJavaScriptEnabled(true);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.gank.observe(getViewLifecycleOwner(), gank -> {
            mBinding.toolbar.myToolbar.setTitle(gank.getTitle());
            mBinding.webview.loadUrl(gank.getUrl());
        });
        mViewModel.error.observe(getViewLifecycleOwner(), throwable -> {
            Log.e("MVVM", "load failed", throwable);
            Toast.makeText(this.getActivity(), "Load Error", Toast.LENGTH_LONG).show();
        });
    }


}