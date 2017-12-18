package com.example.hjj.mddemo.jdnews.base;

/**
 * Created by 黄俊杰 on 2017-12-18.
 * 基础的视图
 */

public interface IBaseView {
    /**
     * 开始加载
     *
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);
}
