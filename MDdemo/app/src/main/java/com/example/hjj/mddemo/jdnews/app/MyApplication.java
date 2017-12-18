package com.example.hjj.mddemo.jdnews.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        
    }

    public static Context getContext() {
        return context;
    }
}
