package com.example.hjj.mddemo.mvvm.retrofit;

import com.example.hjj.mddemo.mvvm.bean.NewsBean;
import com.example.hjj.mddemo.mvvm.constant.MainConstant;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(MainConstant.URLConstant.URL_PATH)
    Observable<NewsBean> getNewsData();
}
