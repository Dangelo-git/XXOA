package com.dangelo.xxoa.mvp.knowledge;

import android.content.Context;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.Rqtknowledge;
import com.dangelo.xxoa.bean.knowledgePersonal;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class KonwPresenter implements KonwDetailContact.Presenter {
    private final Context mContext;
    private KonwDetailContact.View mView;
    private String TAG = KonwPresenter.class.getSimpleName();
    private String vfc;

    public KonwPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (KonwDetailContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    @Override
    public void getSendDetail() {

    }

    @Override
    public void DoShare(Rqtknowledge mRqtknowledge) {
        JLog.i(TAG, "DoShare" );
        if (Testswith.Test_UADISPATCHDOC) {

            Gson gson = new Gson();
            DataBody json = null;
            json= gson.fromJson(API.RSPONSE_KNOWLEDGEUPLOADFILE_PERSONALADD, DataBody.class);
            JLog.d(TAG, "resultSuccess");

            return;
        }

//        if (ShortCut.getUser(mContext) == null) {
//            ShortCut.showToast("请登录！", mContext);
//            return;
//        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(mRqtknowledge);
        NetEngine.submitPostTask(mContext, API.Method_KNOWLEDGEUPLOADFILE_PERSONALADD,jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
//


                        JLog.d(TAG, "resultSuccess");

                    }


                    @Override
                    public void finallyDo() {
                        super.finallyDo();
//
//
                    }

                });
    }


}
