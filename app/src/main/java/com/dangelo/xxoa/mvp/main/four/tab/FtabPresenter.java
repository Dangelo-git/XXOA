package com.dangelo.xxoa.mvp.main.four.tab;

import android.content.Context;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.ExMeet;
import com.dangelo.xxoa.bean.InnerMeet;
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

public class FtabPresenter implements FtabContact.Presenter {


    private final Context mContext;
    private FtabContact.View mView;
    private String TAG = FtabPresenter.class.getSimpleName();
    private ArrayList<InnerMeet> InnerMeetDates = new ArrayList<>();
    private ArrayList<ExMeet> ExMeetDates = new ArrayList<>();
    private int type_temp;

    public FtabPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
//        mSysMsgDbUtils = new SysMsgDbUtils(mContext);
        mView.showLoading(true);

    }

    @Override
    public void attach(BaseView view) {
        this.mView = (FtabContact.View) view;
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
        JLog.i(TAG, "GetDate-type" + type_temp+" page"+page);
        if (Testswith.Test_UADISPATCHDOC) {

            Gson gson = new Gson();
            DataBody json = null;
            switch (type_temp) {
                case 1:
                    JLog.i(TAG, "RSPONSE_UAENROLMENT_VAGUENOTCONFERENCEINFOAL" );
                    json = gson.fromJson(API.RSPONSE_UAENROLMENT_VAGUENOTCONFERENCEINFOALL, DataBody.class);
                    InnerMeetDates = (ArrayList<InnerMeet>) json.getUaEnrolment().getRows();
                    mView.refreshData(InnerMeetDates);
                    JLog.d(TAG, "result=" + "InnerMeetDates size:" + InnerMeetDates.size());
//                NetEngine.submitPostTask(mContext, API.Method_UADISPATCHDOC_GETUSERACTIVITI, json.toString()
//                        , new MyJsonBodyHttpResponseHandler() {
//                            @Override
//                            public void resultSuccess(DataBody result) throws JSONException {
//                                super.resultSuccess(result);
//                                InnerMeetDates = (ArrayList<InnerMeet>) result.getUaEnrolment().getRows();
//                                //
//
//
//                                JLog.d(TAG, "result=" + "InnerMeetDates size:" + InnerMeetDates.size());
//
//                                mView.refreshData(InnerMeetDates);
//                            }
//
//
//                            @Override
//                            public void finallyDo() {
//                                super.finallyDo();
//                                //
//                                //
//                            }
//
//                        });

                    break;
                case 2:
                    JLog.i(TAG, "RSPONSE_EXTERNALMEETING_VAGUEPARTICIPANTSALL" );
                    json = gson.fromJson(API.RSPONSE_EXTERNALMEETING_VAGUEPARTICIPANTSALL, DataBody.class);
                    ExMeetDates = (ArrayList<ExMeet>) json.getExternalMeeting().getRows();
                    mView.refreshData2(ExMeetDates);
                    JLog.d(TAG, "result=" + "ExMeetDates size:" + ExMeetDates.size());
//                    NetEngine.submitPostTask(mContext, API.Method_UADISPATCHDOC_GETUSERACTIVITI, json.toString()
//                            , new MyJsonBodyHttpResponseHandler() {
//                                @Override
//                                public void resultSuccess(DataBody result) throws JSONException {
//                                    super.resultSuccess(result);
//                                    ExMeetDates = (ArrayList<ExMeet>) result.getExternalMeeting().getRows();
//    //
//
//
//                                    JLog.d(TAG, "result=" + "InnerMeetDates size:" + InnerMeetDates.size());
//
//                                    mView.refreshData2(ExMeetDates);
//                                }
//
//
//                                @Override
//                                public void finallyDo() {
//                                    super.finallyDo();
//    //
//    //
//                                }
//
//                            });
                    break;

            }


        }


    }


}
