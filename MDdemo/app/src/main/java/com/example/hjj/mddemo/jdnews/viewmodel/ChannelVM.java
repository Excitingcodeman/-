package com.example.hjj.mddemo.jdnews.viewmodel;

import com.example.hjj.mddemo.jdnews.adapter.ChannelAdapter;
import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.ChannelBeanHelper;
import com.example.hjj.mddemo.jdnews.constants.Myconfig;
import com.example.hjj.mddemo.jdnews.model.IChannelModel;
import com.example.hjj.mddemo.jdnews.model.impl.IChannelModelImpl;
import com.example.hjj.mddemo.jdnews.view.IChannelView;

import java.util.List;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class ChannelVM implements BaseLoadListener<ChannelBeanHelper> {
    private IChannelView channelView;
    private IChannelModel channelModel;
    private ChannelAdapter adapter;

    public ChannelVM(IChannelView channelView, ChannelAdapter adapter) {
        this.channelView = channelView;
        this.adapter = adapter;
        channelModel = new IChannelModelImpl();
        getData();
    }

    private void getData() {
        channelModel.getChannelData(this);
    }

    /**
     * 数据加载成功
     *
     * @param list
     */
    @Override
    public void loadSuccess(List<ChannelBeanHelper> list) {
        adapter.refreshData(list);
    }

    /**
     * 加载失败
     *
     * @param message
     */
    @Override
    public void loadFailure(String message) {
        channelView.loadFailure(message);
    }

    /**
     * 开始加载
     */
    @Override
    public void loadStart() {
        channelView.loadStart(Myconfig.FIRST_LOAD);
    }

    /**
     * 加载结束
     */
    @Override
    public void loadComplete() {
        channelView.loadComplete();
    }
}
