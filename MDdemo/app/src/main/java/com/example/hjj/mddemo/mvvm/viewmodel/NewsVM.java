package com.example.hjj.mddemo.mvvm.viewmodel;

import com.example.hjj.mddemo.mvvm.adapter.NewsAdapter;
import com.example.hjj.mddemo.mvvm.base.BaseLoadListener;
import com.example.hjj.mddemo.mvvm.bean.SimpleNewsBean;
import com.example.hjj.mddemo.mvvm.constant.MainConstant;
import com.example.hjj.mddemo.mvvm.model.INewsModel;
import com.example.hjj.mddemo.mvvm.model.impl.INewsModelImpl;
import com.example.hjj.mddemo.mvvm.view.INewsView;

import java.util.List;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {

    private INewsView mNewsView;
    private INewsModel mNewsModel;
    private NewsAdapter mAdapter;
    private int currentPage = 1;
    private int loadType;

    public NewsVM(INewsView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new INewsModelImpl();
        getNewData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currentPage = 1;
        mNewsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currentPage++;
        mNewsModel.loadNewsData(currentPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currentPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
// 加载失败后的提示
        if (currentPage > 1) {
            //加载失败需要回到加载之前的页数
            currentPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}
