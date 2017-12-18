package com.example.hjj.mddemo.jdnews.model.impl;


import android.text.Html;
import android.text.Spanned;

import com.example.hjj.mddemo.jdnews.app.MyApplication;
import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.NewsBean;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.NewsBeanHelper;
import com.example.hjj.mddemo.jdnews.http.HttpUtil;
import com.example.hjj.mddemo.jdnews.model.INewsModel;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class INewsModelImpl implements INewsModel {
    List<NewsBeanHelper> beanHelpers = new ArrayList<>();

    /**
     * 获取新闻数据
     *
     * @param channel
     * @param num
     * @param start
     * @param loadListener
     */
    @Override
    public void loadNewsData(String channel, String num, String start, final BaseLoadListener<NewsBeanHelper> loadListener) {
        HttpUtil.getNewsBean(channel, num, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (!newsBean.getCode().equals("10000")) {//获取数据失败了
                            ToastUtils.show(MyApplication.getContext(), newsBean.getMsg());
                            return;
                        }
                        List<NewsBean.ResultBeanX.ResultBean.ListBean> listBeans = newsBean.getResult().getResult().getList();
                        beanHelpers.clear();
                        if (listBeans != null && listBeans.size() > 0) {
                            for (NewsBean.ResultBeanX.ResultBean.ListBean bean : listBeans) {
                                NewsBeanHelper newsBeanHelper = new NewsBeanHelper();
                                newsBeanHelper.title.set(bean.getTitle());
                                newsBeanHelper.time.set(bean.getTime());
                                newsBeanHelper.category.set(bean.getCategory());
                                Spanned spanned = Html.fromHtml(bean.getContent());
                                newsBeanHelper.content.set(spanned.toString());
                                newsBeanHelper.pic.set(bean.getPic());
                                newsBeanHelper.src.set(bean.getSrc());
                                newsBeanHelper.url.set(bean.getUrl());
                                newsBeanHelper.weburl.set(bean.getWeburl());
                                beanHelpers.add(newsBeanHelper);
                            }
                        }
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        loadListener.loadStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadListener.loadFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        loadListener.loadSuccess(beanHelpers);
                        loadListener.loadComplete();
                    }
                });
    }
}
