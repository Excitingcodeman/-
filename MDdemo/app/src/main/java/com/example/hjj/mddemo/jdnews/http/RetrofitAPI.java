package com.example.hjj.mddemo.jdnews.http;

import com.example.hjj.mddemo.jdnews.beans.ChannelBean;
import com.example.hjj.mddemo.jdnews.beans.NewsBean;
import com.example.hjj.mddemo.jdnews.constants.Myconfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public interface RetrofitAPI {

    @GET(Myconfig.GET)
    Observable<NewsBean> getNewsBean(@Query("appkey") String app_key,
                                     @Query("channel") String channel,
                                     @Query("num") String num,
                                     @Query("start") String start);

    @GET(Myconfig.CHANEL)
    Observable<ChannelBean> getChannelBean(@Query("appkey") String app_key);

    @GET(Myconfig.NEW_SEARCH)
    Observable<NewsBean> getNew_SEARCH(@Query("appkey") String app_key,
                                       @Query("keyword") String keyword);
}
