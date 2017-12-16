package com.example.hjj.mddemo.mvvm.constant;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class MainConstant {

    public class LoadData {
        public static final int FIRST_LOAD = 0; //首次加载
        public static final int REFRESH = 1; //下拉刷新
        public static final int LOAD_MORE = 2; //上拉加载更多
    }

    public class URLConstant {
        public static final String URL_NEWS = "https://news-at.zhihu.com/api/4/themes";
        public static final String URL_BASE = "https://news-at.zhihu.com/";
        public static final String URL_PATH = "api/4/themes";
    }
}
