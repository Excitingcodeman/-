package com.um.hjj.umapplication.app;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

/**
 * Created by 黄俊杰 on 2017-12-20.
 * 自己的application
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //开启debug 模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
    }

    //添加配置各个平台的配置
    {
        //QQ的id 和key
        PlatformConfig.setQQZone("1106623622", "IGqxVcFPJcEgOaLf");
        //https://api.weibo.com/oauth2/default.html  默认的回调页面
        PlatformConfig.setSinaWeibo("2731939625", "bb2a0812c4c7e02b587fcc5c2fdc8654", "https://api.weibo.com/oauth2/default.html");
    }

    public static MyApplication getInstance() {
        return instance;
    }

}
