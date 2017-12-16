package com.example.hjj.mddemo.mvvm.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class ToastUtils {
    private static Toast toast;

    /**
     * 显示一个弹框
     *
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出一个弹框
     *
     * @param context
     * @param msgId
     */
    public static void show(Context context, int msgId) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msgId);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
