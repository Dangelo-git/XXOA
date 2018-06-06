package com.dangelo.xxoa.mvp.main.frist;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.FileInfo;
import com.dangelo.xxoa.bean.JsonBody;
import com.dangelo.xxoa.bean.noticeInfo;
import com.dangelo.xxoa.bean.messageNum;
import com.dangelo.xxoa.bean.requestJson;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyDownLoader;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mga on 2017/7/10 17:17.
 */
public class FristPresenter implements FristContact.FristPresenter {

    private final Context mContext;
    private FristContact.FristView mView;
    private String TAG = FristPresenter.class.getSimpleName();
    private String account;
    private String password;
    private ArrayList<noticeInfo> noticeInfo = new ArrayList<>();
    //    private SysMsgDbUtils mSysMsgDbUtils;
    private AsyncTask mytask;
    private int type_temp;

    public FristPresenter(@NonNull Context context) {
        this.mContext = context;
    }


    @Override
    public void start() {

    }

    @Override
    public void attach(BaseView view) {
        this.mView = (FristContact.FristView) view;
    }

    @Override
    public void detach() {

    }

    @Override
    public void showErrinfo() {

    }


    // 获取每月文章
    private void LoadData() {
        final String requesl = "http://txt.bxwxtxt.com/packdown/fulltxt/107/107656.txt?46";
        FileInfo info = new FileInfo();
        info.setFileName("107656t");
        info.setFiletype("txt");
        info.setFileUrl(requesl);
        MyDownLoader dl = new MyDownLoader();
        dl.downFile(info, mContext);


    }

    @Override
    public void GetDate(int page) {
        JLog.d(TAG, "GetDate=");
        if (Testswith.Test_Notice) {

            Gson gson = new Gson();
            DataBody json = null;

            json = gson.fromJson(API.RSPONSE_UADISPATCHDOC_GETUSERACTIVITI, DataBody.class);

            noticeInfo = (ArrayList<noticeInfo>) json.getNoticeList().getRows();
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            noticeInfo.add(noticeInfo.get(0));
            mView.refreshData(noticeInfo);
            return;
        }
//        if (ShortCut.getUser(mContext) == null) {
//            ShortCut.showToast("请登录！", mContext);
//            return;
//        }
//读缓存
        try {
            String cacheJson = FileService.readFileSdcardFile(API.Method_NOTICEPUBLIC_NOTICEPUBLICLIST.replace("/","_"),mContext);
            if (cacheJson!=null&&!cacheJson.equals("")){
                Gson gson = new Gson();
                DataBody json = gson.fromJson(cacheJson, DataBody.class);
                noticeInfo = (ArrayList<noticeInfo>) json.getNoticeList().getRows();
                JLog.d(TAG, "缓存result=" + "noticeInfo size:" + noticeInfo.size());
                mView.refreshData(noticeInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        user myuser = new user();
        if(ShortCut.getUser(mContext)!=null){

            myuser.setUSER_KEY(ShortCut.getUser(mContext).getUSER_KEY());
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(myuser);
        NetEngine.submitPostTask(mContext, API.Method_NOTICEPUBLIC_NOTICEPUBLICLIST, jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
                        noticeInfo = (ArrayList<noticeInfo>) result.getNoticeList().getRows();
//


                        JLog.d(TAG, "result=" + "noticeInfo size:" + noticeInfo.size());

                        mView.refreshData(noticeInfo);
                        //写缓存
                        try {
                            Gson gson = new Gson();
                            String jsonStr = gson.toJson(result);
                            FileService.writeFileSdcardFile(API.Method_NOTICEPUBLIC_NOTICEPUBLICLIST.replace("/","_"),jsonStr,mContext);
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


    @Override
    public void GetUaMessageNum() {
        JLog.d(TAG, "GetUaMessageNum=");

        //读缓存
        try {
            JLog.d(TAG, "-》读取缓存！");
            String cacheJson = FileService.readFileSdcardFile(API.Method_UAMESSAGENUM_REFRESHMESSAGENUM.replace("/","_"),mContext);
            if (cacheJson!=null&&!cacheJson.equals("")){
                Gson gson = new Gson();
                DataBody json = gson.fromJson(cacheJson, DataBody.class);


                messageNum messageNumAll= json.getMessageNumAll();

                String tableConferenceNameNum = messageNumAll.getTableConferenceNameNum();
                String tableMeetingNameNum = messageNumAll.getTableMeetingNameNum();
                String massageSum = messageNumAll.getMassageSum();
                String table2 = messageNumAll.getTable2();
                String table1 = messageNumAll.getTable1();
                String table3 = messageNumAll.getTable3();


                int meetnumber = Integer.parseInt(tableConferenceNameNum) + Integer.parseInt
                        (tableMeetingNameNum);
                mView.setNumber(table2, table3, meetnumber + "");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        user myuser = new user();
        if(ShortCut.getUser(mContext)!=null){

            myuser.setUSER_KEY(ShortCut.getUser(mContext).getUSER_KEY());
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(myuser);
        NetEngine.submitPostTask(mContext, API.Method_UAMESSAGENUM_REFRESHMESSAGENUM, jsonStr
                , new MyJsonBodyHttpResponseHandler() {
                    @Override
                    public void resultSuccess(DataBody result) throws JSONException {
                        super.resultSuccess(result);
//                        tableConferenceNameNum 内部会议消息数量
//                        tableMeetingNameNum 外部会议消息数量
//                        massageSum 消息总量
//                        table1 档案消息量
//                        table2 发文数量
//                        table3 收文数量
                        messageNum messageNumAll= result.getMessageNumAll();

                        String tableConferenceNameNum = messageNumAll.getTableConferenceNameNum();
                        String tableMeetingNameNum = messageNumAll.getTableMeetingNameNum();
                        String massageSum = messageNumAll.getMassageSum();
                        String table2 = messageNumAll.getTable2();
                        String table1 = messageNumAll.getTable1();
                        String table3 = messageNumAll.getTable3();
                        Log.d(TAG, "result=" + " tableConferenceNameNum:" + tableConferenceNameNum + " table2" +
                                table2 + " table3" + table3 +
                                "tableMeetingNameNum:" + tableMeetingNameNum + " massageSum:" + massageSum + " " );

                        int meetnumber = Integer.parseInt(tableConferenceNameNum) + Integer.parseInt
                                (tableMeetingNameNum);
                        JLog.i(TAG, "meetnumber:" + meetnumber);
                        mView.setNumber(table2, table3, meetnumber + "");
                        //写缓存
                        try {
                            JLog.d(TAG, "-》写入缓存！");
                            Gson gson = new Gson();
                            String jsonStr = gson.toJson(result);
                            FileService.writeFileSdcardFile(API.Method_UAMESSAGENUM_REFRESHMESSAGENUM.replace("/","_"),jsonStr,mContext);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void resultFailure(Map result) throws JSONException {
                        super.resultFailure(result);
                    }

                    @Override
                    public void finallyDo() {
                        super.finallyDo();
                    }
                });


    }
}