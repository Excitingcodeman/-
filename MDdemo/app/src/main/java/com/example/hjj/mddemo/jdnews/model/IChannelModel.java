package com.example.hjj.mddemo.jdnews.model;

import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.ChannelBeanHelper;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public interface IChannelModel {
    /**
     * 获取新闻channel
     */
    void getChannelData(BaseLoadListener<ChannelBeanHelper> loadListener);
}
