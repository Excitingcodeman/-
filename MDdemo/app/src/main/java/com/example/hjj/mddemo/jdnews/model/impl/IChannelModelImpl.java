package com.example.hjj.mddemo.jdnews.model.impl;

import com.example.hjj.mddemo.jdnews.app.MyApplication;
import com.example.hjj.mddemo.jdnews.base.BaseLoadListener;
import com.example.hjj.mddemo.jdnews.beans.ChannelBean;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.ChannelBeanHelper;
import com.example.hjj.mddemo.jdnews.http.HttpUtil;
import com.example.hjj.mddemo.jdnews.model.IChannelModel;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class IChannelModelImpl implements IChannelModel {
    List<ChannelBeanHelper> channelBeanHelperList = new ArrayList<>();

    /**
     * 获取新闻channel
     */
    @Override
    public void getChannelData(final BaseLoadListener<ChannelBeanHelper> loadListener) {
        HttpUtil.getChannelBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ChannelBean>() {
                    @Override
                    public void onNext(ChannelBean channelBean) {
                        if (!channelBean.getCode().equals("10000")) {//获取数据失败了
                            ToastUtils.show(MyApplication.getContext(), channelBean.getMsg());
                            return;
                        }
                        List<String> result = channelBean.getResult().getResult();
                        channelBeanHelperList.clear();
                        if (result != null && result.size() > 0) {
                            for (String s : result) {
                                ChannelBeanHelper channelBeanHelper = new ChannelBeanHelper();
                                channelBeanHelper.channel.set(s);
                                channelBeanHelperList.add(channelBeanHelper);
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
                        loadListener.loadSuccess(channelBeanHelperList);
                        loadListener.loadComplete();
                    }
                });
    }
}
