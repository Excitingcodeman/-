package com.example.hjj.mddemo.mvvm.base;

import java.util.List;

/**
 * Created by 黄俊杰 on 2017-12-16.
 * 数据加载的接口
 */

public interface BaseLoadListener<T> {
    /**
     * 数据加载成功
     *
     * @param list
     */
    void loadSuccess(List<T> list);

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
