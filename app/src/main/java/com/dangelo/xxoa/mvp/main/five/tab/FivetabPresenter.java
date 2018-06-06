package com.dangelo.xxoa.mvp.main.five.tab;

import android.content.Context;
import android.os.AsyncTask;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.Rqtknowledge;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.knowledgeCategories;
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
 * Created by luozhi on 2018/3/12.
 */

public class FivetabPresenter implements FivetabContact.Presenter {


    private final Context mContext;
    private FivetabContact.View mView;
    private String TAG = FivetabPresenter.class.getSimpleName();
    private ArrayList<knowledgeCategories> knowledgeCategoriesDates = new ArrayList<>();
    //    private SysMsgDbUtils mSysMsgDbUtils;
    private AsyncTask mytask;
    private int type_temp;

    public FivetabPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
//        mSysMsgDbUtils = new SysMsgDbUtils(mContext);
        mView.showLoading(true);
    }

    @Override
    public void attach(BaseView view) {
        this.mView = (FivetabContact.View) view;
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
        if (Testswith.Test_UADISPATCHDOC) {

            Gson gson = new Gson();
            DataBody json = null;
                    json= gson.fromJson(API.RSPONSE_KNOWLEDGECATEGORY_FINDALL, DataBody.class);

            knowledgeCategoriesDates = (ArrayList<knowledgeCategories>) json.getaList();
            mView.refreshData(knowledgeCategoriesDates);
            return;
        }
        Rqtknowledge mRqtknowledge= new Rqtknowledge();
        mRqtknowledge.setKnowledgeType(type_temp+"");
//        if (ShortCut.getUser(mContext) == null) {
//            ShortCut.showToast("请登录！", mContext);
//            return;
//        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(mRqtknowledge);
        NetEngine.submitPostTask(mContext, API.Method_KNOWLEDGECATEGORY_FINDALL,jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
                        knowledgeCategoriesDates = (ArrayList<knowledgeCategories>) result.getaList();
//


                        JLog.d(TAG, "result=" + "dispatchDocDates size:" + knowledgeCategoriesDates.size());

                        mView.refreshData(knowledgeCategoriesDates);
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
