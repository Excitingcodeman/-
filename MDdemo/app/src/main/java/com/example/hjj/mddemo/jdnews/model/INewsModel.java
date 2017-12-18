package com.example.hjj.mddemo.jdnews.model;

import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.NewsBeanHelper;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public interface INewsModel {
    /**
     * 获取新闻数据
     *
     * @param channel
     * @param num
     * @param start
     * @param loadListener
     */
    void loadNewsData(String channel, String num, String start, BaseLoadListener<NewsBeanHelper> loadListener);
}
