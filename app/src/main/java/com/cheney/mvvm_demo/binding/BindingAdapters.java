package com.cheney.mvvm_demo.binding;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.cheney.mvvm_demo.R;

/**
 * databinding全局的自定义BindingAdapter
 */
public class BindingAdapters {

    @BindingAdapter("glideUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);
    }

    @BindingAdapter("visibleGone")
    public static void  showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
