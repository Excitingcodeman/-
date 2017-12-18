package com.example.hjj.mddemo.jdnews.beans.beanhelper;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class ChannelBeanHelper {
    public ObservableField<String> channel = new ObservableField<>();
    public ObservableBoolean isCheck = new ObservableBoolean(); //是否点赞
}
