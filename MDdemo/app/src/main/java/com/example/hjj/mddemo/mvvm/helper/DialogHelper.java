package com.example.hjj.mddemo.mvvm.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class DialogHelper {
    public static DialogHelper getInstance() {
        return LoadDialogHolder.instance;
    }

    private static class LoadDialogHolder {
        static DialogHelper instance = new DialogHelper();
    }

    /**
     * progressDialog
     */
    private ProgressDialog progressDialog;

    /**
     * 展示加载框
     *
     * @param context
     * @param msg     加载信息
     */
    public void show(Context context, String msg) {
        close();
        createDialog(context, msg);
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 创建加载对话框
     *
     * @param context
     * @param msg
     */
    public void createDialog(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressDialog = null;
            }
        });
    }

    /**
     * 关闭加载对话框
     */
    public void close() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
