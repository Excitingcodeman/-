package com.example.hjj.mddemo.login;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.CameraUpdateFactory;
import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String APP_ID = "1106623622";
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private BaseUiListener mIUilistener;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        //传入参数APP_ID 和全局上下文
        mTencent = Tencent.createInstance(APP_ID, getApplicationContext());
    }

    /**
     * 通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
     * 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
     * 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类
     */

    public void QQlogin(View v) {
        mIUilistener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this, "all", mIUilistener);
    }

    public void qqShare(View view) {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://blog.csdn.net/u013451048");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://avatar.csdn.net/C/3/D/1_u013451048.jpg");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "CSDN");
        mTencent.shareToQQ(this, params, mIUilistener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            //授权成功
            ToastUtils.show(context, "授权成功");

            try {

                JSONObject object = (JSONObject) response;
                String openID = object.getString("openid");
                String accessToken = object.getString("access_token");
                String expires = object.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object res) {
                        ToastUtils.show(context, "登录成功");
                        Log.i("QQ", res.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        ToastUtils.show(context, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.show(context, "登录取消");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtils.show(context, "授权失败" + uiError.toString());
        }

        @Override
        public void onCancel() {
            ToastUtils.show(context, "授权取消");
        }
    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUilistener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
