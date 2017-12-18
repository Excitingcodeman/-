package com.example.hjj.mddemo.jdnews.http;

import com.example.hjj.mddemo.jdnews.beans.ChannelBean;
import com.example.hjj.mddemo.jdnews.beans.NewsBean;
import com.example.hjj.mddemo.jdnews.constants.Myconfig;
import com.example.hjj.mddemo.mvvm.http.HttpUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class HttpUtil {
    private static final int DEFAULT_TIMEOUT = 8;//连接 超时的时间，单位：秒

    private static final HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //构建okhttp
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build();
    private static Retrofit retrofit;
    private static RetrofitAPI retrofitAPI;
    private static HttpUtils httpUtils;

    public HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    private HttpUtil() {
    }

    private synchronized static RetrofitAPI getRetrofitAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Myconfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        return retrofitAPI;
    }

    /***
     * 获取指定channel的数据
     * @param channel
     * @param num
     * @param start
     * @return
     */
    public static Observable<NewsBean> getNewsBean(String channel, String num, String start) {
        return getRetrofitAPI().getNewsBean(Myconfig.APP_KEY, channel, num, start);
    }

    /**
     * 获取channel的信息
     *
     * @return
     */
    public static Observable<ChannelBean> getChannelBean() {
        return getRetrofitAPI().getChannelBean(Myconfig.APP_KEY);
    }
}
