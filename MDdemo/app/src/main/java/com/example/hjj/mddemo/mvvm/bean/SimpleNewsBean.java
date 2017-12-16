package com.example.hjj.mddemo.mvvm.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class SimpleNewsBean {
    public ObservableInt color = new ObservableInt();
    public ObservableField<String> thumbnail = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableInt id = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isGood = new ObservableBoolean(); //是否点赞
}
