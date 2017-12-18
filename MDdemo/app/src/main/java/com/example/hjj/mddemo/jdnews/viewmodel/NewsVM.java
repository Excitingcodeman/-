package com.example.hjj.mddemo.jdnews.viewmodel;

import com.example.hjj.mddemo.jdnews.adapter.NewsAdapter;
import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.NewsBeanHelper;
import com.example.hjj.mddemo.jdnews.constants.Myconfig;
import com.example.hjj.mddemo.jdnews.model.INewsModel;
import com.example.hjj.mddemo.jdnews.model.impl.INewsModelImpl;
import com.example.hjj.mddemo.jdnews.view.INewsView;

import java.util.List;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class NewsVM implements BaseLoadListener<NewsBeanHelper> {

    private INewsView iNewsView;
    private INewsModel iNewsModel;
    private NewsAdapter newsAdapter;
    private int loadType;
    private int start = 0;

    public NewsVM(INewsView iNewsView, NewsAdapter newsAdapter) {
        this.iNewsView = iNewsView;
        this.newsAdapter = newsAdapter;
        iNewsModel = new INewsModelImpl();
        getNewsData(Myconfig.CHANNEL);
    }

    /**
     * 第一次获取数据
     *
     * @param channel
     */
    public void getNewsData(String channel) {
        loadType = Myconfig.FIRST_LOAD;
        iNewsModel.loadNewsData(channel, Myconfig.NUM, String.valueOf(start), this);
    }

    /**
     * 下拉刷新
     *
     * @param channel
     */
    public void refreshNewsData(String channel) {
        loadType = Myconfig.REFRESH;
        start = 0;
        iNewsModel.loadNewsData(channel, Myconfig.NUM, String.valueOf(start), this);
    }

    /**
     * 加载更多
     *
     * @param channel
     */
    public void loadMore(String channel) {
        loadType = Myconfig.LOAD_MORE;
        start++;
        iNewsModel.loadNewsData(channel, Myconfig.NUM, String.valueOf(start), this);
    }

    /**
     * 数据加载成功
     *
     * @param list
     */
    @Override
    public void loadSuccess(List<NewsBeanHelper> list) {
        if (loadType == Myconfig.REFRESH || loadType == Myconfig.FIRST_LOAD) {
            newsAdapter.refreshData(list);
        } else {
            newsAdapter.loadMoreData(list);
        }
    }

    /**
     * 加载失败
     *
     * @param message
     */
    @Override
    public void loadFailure(String message) {
        start--;
        iNewsView.loadFailure(message);
    }

    /**
     * 开始加载
     */
    @Override
    public void loadStart() {
        iNewsView.loadStart(loadType);
    }

    /**
     * 加载结束
     */
    @Override
    public void loadComplete() {
        iNewsView.loadComplete();
    }
}
