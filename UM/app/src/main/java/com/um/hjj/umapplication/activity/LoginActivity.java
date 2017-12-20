package com.um.hjj.umapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.um.hjj.umapplication.R;
import com.um.hjj.umapplication.databinding.ActivityLoginBinding;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private Context context;
    private ProgressDialog dialog;
    private ActivityLoginBinding binding;
    public static final String PLATFORM = "PLATFORM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        context = this;
        dialog = new ProgressDialog(context);
    }

    /**
     * QQ授权
     */
    public void QQ(View view) {
        auth(SHARE_MEDIA.QQ);
    }

    public void Sina(View view) {
        auth(SHARE_MEDIA.SINA);
    }

    /**
     * 基础的获取授权的方法
     *
     * @param share_media
     */
    private void auth(SHARE_MEDIA share_media) {
        boolean authorize = UMShareAPI.get(context).isAuthorize(LoginActivity.this, share_media);
        if (authorize) {
            Toast.makeText(context, "已经获取过授权了", Toast.LENGTH_SHORT).show();
            deleteAuth(share_media);
        } else {
            UMShareAPI.get(context).doOauthVerify(LoginActivity.this, share_media, authListener);
        }
    }

    private void deleteAuth(SHARE_MEDIA share_media) {
        UMShareAPI.get(context).deleteOauth(LoginActivity.this, share_media, authListener_delete);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * 授权开始的回调
         * @param share_media 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         *授权成功的回调
         * @param share_media 平台名称
         * @param i 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.name() + "授权成功了", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(PLATFORM, share_media.name());
            startActivity(intent);
//            Log.i("MAP", map.toString());
        }

        /**
         *授权失败的回调
         * @param share_media 平台名称
         * @param i 行为序号
         * @param throwable 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.toString() + "失败\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /**
         * 授权取消的回调
         * @param share_media
         * @param i
         */
        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.toString() + "取消", Toast.LENGTH_SHORT).show();
        }
    };
    UMAuthListener authListener_delete = new UMAuthListener() {
        /**
         * 授权开始的回调
         * @param share_media 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         *授权成功的回调
         * @param share_media 平台名称
         * @param i 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.name() + "退出成功了", Toast.LENGTH_SHORT).show();

//            Log.i("MAP", map.toString());
        }

        /**
         *授权失败的回调
         * @param share_media 平台名称
         * @param i 行为序号
         * @param throwable 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.toString() + "失败\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /**
         * 授权取消的回调
         * @param share_media
         * @param i
         */
        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(context, share_media.toString() + "取消", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        SocializeUtils.safeCloseDialog(dialog);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

