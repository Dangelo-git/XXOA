package com.dangelo.xxoa.mvp.receivedetail;

import android.content.Context;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.currentStation;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.bean.nextStation;
import com.dangelo.xxoa.bean.node;
import com.dangelo.xxoa.bean.requestJson;
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

public class ReceivetailPresenter implements ReceiveDetailContact.Presenter {
    private final Context mContext;
    private ReceiveDetailContact.View mView;
    private String TAG = ReceivetailPresenter.class.getSimpleName();
    private String vfc;
    private ArrayList<node> nodeDates = new ArrayList<>();
    private ArrayList<currentStation> currentStationDates = new ArrayList<>();

    public ReceivetailPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (ReceiveDetailContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    @Override
    public void getSendDetail() {

    }
    /**
     * 1.1  添加收文信息（添加草稿箱）
     */
    @Override
    public void DoSave(inDoc minDoc) {
        if (Testswith.Test_UAAPPROVALOPINION) {

            Gson gson = new Gson();
            DataBody json = gson.fromJson(API.RSPONSE_UAINFILETABLE_SAVEUAINFILETABLE, DataBody.class);
            JLog.d(TAG, "resultSuccess" );

            return;
        }else {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(minDoc);
            NetEngine.submitPostTask(mContext, API.Method_UAINFILETABLE_SAVEUAINFILETABLE,jsonStr
                    , new MyJsonBodyHttpResponseHandler() {
                        @Override
                        public void resultSuccess(DataBody result) throws JSONException {
                            super.resultSuccess(result);
//


                            JLog.d(TAG, "result=" + "nodeDates size:" + nodeDates.size());

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

    @Override
    public void DoSubmit() {

    }

    @Override
    public void DoReset() {

    }

    @Override
    public void DoOpen() {

    }

    /**
     * 1.8 下一步流程节点
     * @param id
     */
    @Override
    public void getNode(Long id){
        if (Testswith.Test_UAAPPROVALOPINION) {

            Gson gson = new Gson();
            DataBody json = gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_FINDNEXTACTIVITI, DataBody.class);


            nodeDates = (ArrayList<node>) json.getPackInfo();
            mView.showPopDialog(nodeDates);
            return;
        }else {
            requestJson Rj = new requestJson();
            Rj.setId(id);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(Rj);
            NetEngine.submitPostTask(mContext, API.Method_UAAPPROVALOPINION_FINDNEXTACTIVITI, jsonStr
                    , new MyJsonBodyHttpResponseHandler() {
                        @Override
                        public void resultSuccess(DataBody result) throws JSONException {
                            super.resultSuccess(result);
                            nodeDates = (ArrayList<node>) result.getPackInfo();
//


                            JLog.d(TAG, "result=" + "nodeDates size:" + nodeDates.size());

                            mView.showPopDialog(nodeDates);
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
    /**
     * 1.9 查询岗位下的人
     */
    @Override
    public void getCurrentStation(String stationId , String signbatchtypeid ){
        if (Testswith.Test_UAAPPROVALOPINION) {

            Gson gson = new Gson();
            DataBody json = gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_GETALLUSERACTIVITI, DataBody.class);


            currentStationDates = (ArrayList<currentStation>) json.getCurrentStation();
            mView.showPopDialogPerson(currentStationDates);
            return;
        }else {
            requestJson Rj = new requestJson();
            Rj.setStationId(stationId);
            Rj.setSignbatchtypeid(signbatchtypeid);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(Rj);
            NetEngine.submitPostTask(mContext, API.Method_UAAPPROVALOPINION_GETALLUSERACTIVITI, jsonStr
                    , new MyJsonBodyHttpResponseHandler() {
                        @Override
                        public void resultSuccess(DataBody result) throws JSONException {
                            super.resultSuccess(result);
                            currentStationDates = (ArrayList<currentStation>) result.getCurrentStation();
                            mView.showPopDialogPerson(currentStationDates);
//


                            JLog.d(TAG, "result=" + "currentStationDates size:" + currentStationDates.size());

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
    /**
     * 1.10提交下一步给下一个审批人

     */
    @Override
    public void PostnextUserActiviti(nextStation nextstation){
        JLog.d(TAG, "PostnextUserActiviti-->nextstation"+nextstation.toString() );
        if (Testswith.Test_UAAPPROVALOPINION) {

            Gson gson = new Gson();
            DataBody json = gson.fromJson(API.RSPONSE_UAAPPROVALOPINION_NEXTUSERACTIVITI, DataBody.class);


            JLog.d(TAG, "resultSuccess" );
            return;
        }else {
            nextstation.setOption("sdffd");
            Gson gson = new Gson();
            String jsonStr = gson.toJson(nextstation);
            NetEngine.submitPostTask(mContext, API.Method_UAAPPROVALOPINION_NEXTUSERACTIVITI, jsonStr
                    , new MyJsonBodyHttpResponseHandler() {
                        @Override
                        public void resultSuccess(DataBody result) throws JSONException {
                            super.resultSuccess(result);
//


                            JLog.d(TAG, "resultSuccess" );

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
}
