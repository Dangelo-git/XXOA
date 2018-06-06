package com.dangelo.xxoa.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;


import com.dangelo.xxoa.*;
import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class LoginPresenter implements LoginContact.Presenter {
    private final Context mContext;
    private LoginContact.View mView;
    private String TAG = LoginPresenter.class.getSimpleName();
    private String name;
    private String md5Password;
    private String imei;
    private String registerid;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
//
//        String account=UserInfoManager.readAccount(mContext);
//        if(!TextUtils.isEmpty(account)){
//            mView.showAccount(account);
//        }
    }

    @Override
    public void attach(BaseView view) {
        this.mView = (LoginContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }



    //	通
    @Override
    public void  LogInOnClick() {
        mView.showLoading(true);
//        if(Testswith.Testlogin){
//            mView.toMainActivity();
//            mView.showLoading(false);
//            return;
//        }
        name = mView.getAccount();
        md5Password = mView.getPassword();
        imei = ShortCut.GetDeivceSN(mContext);
        user myuser = new user();

        myuser.setPassWord(md5Password);
        myuser.setUserEnglishName(name);
        myuser.setUserPhoneImeid("123456");
//        myuser.setUserPhoneImeid(ShortCut.GetDeivceSN(mContext));
        myuser.setRegistrationId(SharedPrefUtil.getRegistrationId(mContext));
        Gson gson = new Gson();
        String jsonStr = gson.toJson(myuser);
        NetEngine.submitPostTask(mContext, API.Method_LOGIN_USERLOGIN,jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
                        String  userkey = result.getUserKey();
                        Log.d(TAG, "result=" +" UuCName:" + name + " userkey:" + userkey + " imei:" + imei);
                        DataShare share = new DataShare(mContext);
                        share.write("UuEName", name);
                        share.write("UserKey", userkey);
                        share.write("UuKeyID", imei);
                        share.write("Password", md5Password);
//				Log.d(TAG, "result=" + "uId:"+user.getuId()+" UuCName:"+user.getUuCName()+" UuEName:"+user.getUuEName
// ()+" UuKeyID:"+user.getUuKeyID());
                        ShortCut.showToast("登录成功！", mContext);
                        mView.showLoading(false);
                        mView.toMainActivity();
                    }

                    @Override
                    public void resultFailure(Map result) throws JSONException {
                        super.resultFailure(result);
                        if(result.containsKey("errorMsg")){
                            ShortCut.showToast("登录失败！"+  result.get("errorMsg"), mContext);
                        }else{

                            ShortCut.showToast("登录失败！", mContext);
                        }
                        mView.showLoading(false);
                        if(Testswith.Testlogin){
                            mView.toMainActivity();
                            DataShare share = new DataShare(mContext);
                            share.write("UuEName", name);
                            share.write("UserKey", "userkey");
                            share.write("UuKeyID", imei);
                            share.write("Password", md5Password);
                        }
                    }

                    @Override
                    public void finallyDo() {
                        super.finallyDo();
                        if(Testswith.Testlogin){
                            mView.toMainActivity();
                        }
                        ShortCut.showToast("登录失败！", mContext);
                        mView.showLoading(false);
                    }
                });


    }






}
