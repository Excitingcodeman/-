package com.example.hjj.mddemo.jdnews.helper;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class ImageHelper {
    /**
     * mv_vm xml 传入加载图片
     * imageUrl 为xml中的命名
     *
     * @param iv
     * @param url
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String url) {
        Glide.with(iv.getContext()).load(url).into(iv);
    }
    @BindingAdapter({"resId"})
    public static void loadMipmapResource(ImageView iv,int resId){
        iv.setImageResource(resId);
    }
}
