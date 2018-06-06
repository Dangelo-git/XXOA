package com.dangelo.xxoa.mvp.senddetail;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.dangelo.xxoa.mvp.base.BaseView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class SendtailPresenter implements SendDetailContact.Presenter {
    private final Context mContext;
    private SendDetailContact.View mView;
    private String TAG = SendtailPresenter.class.getSimpleName();
    private String vfc;

    public SendtailPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (SendDetailContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    @Override
    public void getSendDetail() {

    }

    @Override
    public void DoSave() {

    }

    @Override
    public void DoSubmit() {

    }

    @Override
    public void DoReset() {

    }

    @Override
    public void DoOpen() {

    }
}
