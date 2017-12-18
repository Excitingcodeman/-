package com.example.hjj.mddemo.jdnews.constants;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public interface Myconfig {
    //京东api新闻的key
    String APP_KEY = "503d2ef83437c9b923599a6d35ff0041";
    String BASE_URL = "https://way.jd.com/jisuapi/";
    //获取新闻 分类  get或post
    String CHANEL = "channel";
    //获取新闻 get或post
    String GET = "get";
    //新闻搜索
    String NEW_SEARCH = "newSearch";
    int FIRST_LOAD = 0; //首次加载
    int REFRESH = 1; //下拉刷新
    int LOAD_MORE = 2; //上拉加载更多
    String NUM = "10";
    String CHANNEL = "头条";
}
