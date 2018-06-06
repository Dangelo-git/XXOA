package com.dangelo.xxoa.mvp.welcome;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.mvp.login.LoginActivity;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.Map;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class WelPresenter implements WelContact.Presenter {
    private final Context mContext;
    private WelContact.View mView;
    private String TAG = WelPresenter.class.getSimpleName();
    private String name;
    private String md5Password;
    private String imei;
    private String registerid;

    public WelPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
//
//        String account=UserInfoManager.readAccount(mContext);
//        if(!TextUtils.isEmpty(account)){
//            mView.showAccount(account);
//        }
        autoLogin();
    }

    @Override
    public void attach(BaseView view) {
        this.mView = (WelContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    //	通
    @Override
    public void autoLogin() {

//        Json json = new Json();
//        json = getSubscribeJson();
//        Log.d(TAG, json.toString())

        if (ShortCut.getUser(mContext) == null) {
            ShortCut.showToast("请登录！", mContext);
            return;
        }


        user myuser = new user();
        myuser.setPassWord(ShortCut.getUser(mContext).getPassWord());
        myuser.setUserEnglishName(ShortCut.getUser(mContext).getUserEnglishName());
        myuser.setRegistrationId(SharedPrefUtil.getRegistrationId(mContext));
        myuser.setUserPhoneImeid("123456");

        Gson gson = new Gson();
        String jsonStr = gson.toJson(myuser);
        NetEngine.submitPostTask(mContext, API.Method_LOGIN_USERLOGIN, jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
                        String userkey = result.getUserKey();
                        Log.d(TAG, "result=" + " UuCName:" + ShortCut.getUser(mContext).getUserEnglishName() + " " +
                                "userkey:" + userkey + " imei:" + ShortCut.getUser(mContext).getUserPhoneImeid());
                        DataShare share = new DataShare(mContext);
                        share.write("UserKey", userkey);
//				Log.d(TAG, "result=" + "uId:"+user.getuId()+" UuCName:"+user.getUuCName()+" UuEName:"+user.getUuEName
// ()+" UuKeyID:"+user.getUuKeyID());
                        ShortCut.showToast("登录成功！", mContext);
                        mView.toMainActivity();

                    }

                    @Override
                    public void resultFailure(Map result) throws JSONException {
                        super.resultFailure(result);
                        ShortCut.showToast("登录失败！", mContext);
//                        if (result.getCode() != null && (!result.getCode().equals(""))) {
                        ShortCut.setUserclear();
                        SharedPrefUtil.setAudoLogin(false, mContext);
                        Intent mIntent = new Intent();
                        mIntent.setClass(mContext, LoginActivity.class);
                        mContext.startActivity(mIntent);
//                            mContext.finish();
//                        }
                    }

                    @Override
                    public void finallyDo() {
                        super.finallyDo();
                    }
                });


    }






}
