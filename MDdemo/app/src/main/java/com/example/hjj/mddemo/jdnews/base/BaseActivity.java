package com.example.hjj.mddemo.jdnews.base;

import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 黄俊杰 on 2017-12-18.
 * 基础的baseactivity
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        boolean registered = EventBus.getDefault().isRegistered(this);
        if (!registered) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
