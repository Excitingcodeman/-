package com.example.hjj.mddemo.mvvm.model;

import com.example.hjj.mddemo.mvvm.base.BaseLoadListener;
import com.example.hjj.mddemo.mvvm.bean.SimpleNewsBean;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public interface INewsModel {
    /**
     * 获取新闻数据
     *
     * @param page         页数
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);
}
