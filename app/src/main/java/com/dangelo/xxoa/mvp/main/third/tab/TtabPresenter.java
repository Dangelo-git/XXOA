package com.dangelo.xxoa.mvp.main.third.tab;

import android.content.Context;
import android.os.AsyncTask;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.bean.requestJson;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luozhi on 2018/3/12.
 */

public class TtabPresenter implements TtabContact.Presenter {


    private final Context mContext;
    private TtabContact.View mView;
    private String TAG = TtabPresenter.class.getSimpleName();
    private ArrayList<inDoc> dispatchDocDates = new ArrayList<>();
    //    private SysMsgDbUtils mSysMsgDbUtils;
    private AsyncTask mytask;
    private int type_temp;

    public TtabPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
//        mSysMsgDbUtils = new SysMsgDbUtils(mContext);
        mView.showLoading(true);
    }

    @Override
    public void attach(BaseView view) {
        this.mView = (TtabContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void deteleRecord(int postion) {

    }



    @Override
    public void setType(int type) {
        JLog.i(TAG, "setType-type" + type);
        type_temp = type;
    }

    @Override
    public void GetDate(int page) {
        JLog.i(TAG, "setType-type" + type_temp);
        if (Testswith.Test_UAAPPROVALOPINION) {

            Gson gson = new Gson();
            DataBody json = null;
            switch (type_temp){
                case 1:
                    json= gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_SELECTUSERACTIVITI, DataBody.class);
                    break;
                case 2:
                    json= gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_GETNOTUSERFINISH, DataBody.class);
                    break;
                case 3:
                    json= gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_GETUSERFINISH, DataBody.class);
                    break;
                case 4:
                    json= gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_VAGUEUAINFILETABLE, DataBody.class);
                    break;
                case 5:
                    json= gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_SELECTALLSTOP, DataBody.class);
                    break;
            }
            
            dispatchDocDates = (ArrayList<inDoc>) json.getUaInFileTable().getRows();
            mView.refreshData(dispatchDocDates);
            return;
        }else{
            switch (type_temp){
                case 1:
                    DoPost(page,type_temp,API.Method_UAAPPROVALOPINION_SELECTUSERACTIVITI);
                    break;
                case 2:
                    DoPost(page,type_temp,API.Method_UAAPPROVALOPINION_GETNOTUSERFINISH);
                    break;
                case 3:
                    DoPost(page,type_temp,API.Method_UAAPPROVALOPINION_GETUSERFINISH);
                    break;
                case 4:
                    DoPost(page,type_temp,API.Method_UAINFILETABLE_VAGUEUAINFILETABLE);
                    break;
                case 5:
                    DoPost(page,type_temp,API.Method_UAAPPROVALOPINION_SELECTALLSTOP);

            }
        }

    }
    private void DoPost(final int page,final int type,final String Method){
        JLog.i(TAG, "setType-type" + type+" page:"+page+" Method:"+Method);

//读缓存
        try {
            String cacheJson = FileService.readFileSdcardFile(Method.replace("/","_")+type+page,mContext);
            if (cacheJson!=null&&!cacheJson.equals("")){
                Gson gson = new Gson();
                DataBody json = gson.fromJson(cacheJson, DataBody.class);
                dispatchDocDates = (ArrayList<inDoc>) json.getUaInFileTable().getRows();
                JLog.d(TAG, "缓存result=" + "dispatchDocDates size:" + dispatchDocDates.size());
                mView.refreshData(dispatchDocDates);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        requestJson Rj = new requestJson();
        Rj.setPage(page);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(Rj);
        NetEngine.submitPostTask(mContext, Method,jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
                        dispatchDocDates = (ArrayList<inDoc>) result.getUaInFileTable().getRows();
//


                        JLog.d(TAG, "result=" + "dispatchDocDates size:" + dispatchDocDates.size());

                        mView.refreshData(dispatchDocDates);
                        //写缓存
                        try {
                            Gson gson = new Gson();
                            String jsonStr = gson.toJson(result);
                            FileService.writeFileSdcardFile(Method.replace("/","_")+type+page,jsonStr,mContext);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
