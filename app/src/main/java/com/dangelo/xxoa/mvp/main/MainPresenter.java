package com.dangelo.xxoa.mvp.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.model.Config;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.dangelo.xxoa.mvp.login.LoginActivity;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonBodyHttpResponseHandler;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


/**
 * Created by mga on 2017/8/16 17:05.
 */

public class MainPresenter implements MainContact.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();
    private final Context mContext;
    private MainContact.View mView;
    private boolean isDoAutologin;
    private boolean isConnected = false;
    private Config config = null;
    private boolean UpdateFlag = false;


    public MainPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {
        Log.i(TAG, "start");

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (MainContact.View) view;
    }

    @Override
    public void detach() {
        JLog.i(TAG, "detach isConnected" + isConnected);

        mView = null;
    }


    @Override
    public String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "今天是" + mYear + "年" + mMonth + "月" + mDay + "日" + "，星期" + mWay;
    }


    @Override
    public void doDownLoad() {
        mView.doDownLoad(config.getAndroidvdurl());
    }

    @Override
    public void setValue() {
        //显示保存文件夹的大小
        if (config != null) {
//			mailbox.setText(config.getKefuEmail());
        }
        String FolderSize = null;
        Long Size = FileService.getCacheFolderSize(mContext);
        Log.i(TAG, "CacheFolderSize:" + Size);
        if (Size > 1048 && 1048576 > Size) {
            FolderSize = Size / 1048 + "KB";
        } else if (Size > 1048576) {
            FolderSize = Size / 1048576 + "M";
        } else {
            FolderSize = Size + "B";
        }
        mView.setDate(FolderSize);


        //检查版本更新
        if (GetVersionCode() != -1 && config != null && !config.getAndroidv().equals("")) {
            int locversion = GetVersionCode();
            Log.i(TAG, "config.getAndroidv()" + config.getAndroidv());
            if (Integer.parseInt(config.getAndroidv()) > locversion) {

                UpdateFlag = true;
            }
            mView.setUpdate(config.getAndroidv(), UpdateFlag);
        }
    }

    private int GetVersionCode() {
        PackageManager pm = mContext.getPackageManager();
        // 得到系统安装的所有程序包的PackageInfo对象
        // List<ApplicationInfo> packs = pm.getInstalledApplications(0);
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        Log.i(TAG, "本地读取的所有应用个数:" + packs.size());
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo pi = new PackageInfo();
            pi = packs.get(i);
            // Log.i(TAG, "packageInfo:" +
            // packageInfo.applicationInfo.packageName);
            if (pi.applicationInfo.packageName.equals("com.dangelo.xxoa")) {

                return pi.versionCode;
            }
        }
        return -1;

    }

    // 获取下载文件数
    @Override
    public void LoadFilenum() {
        FileService fileutils = new FileService();
        List<String> fileList = new ArrayList<>();
        fileList = fileutils.getDownloadFileList(mContext);
        Log.i(TAG, "文件数" + fileList.size() + "");
        int downNum = fileList.size();
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).toString().equals("File")) {
                    downNum = downNum - 1;
                }
                Log.i(TAG, "文件名" + fileList.get(i).toString());
            }
        }
        mView.setLoadFilenum(downNum);


    }

    //通
    @Override
    public void LogOutOnClick() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(mContext) == null) {
            ShortCut.showToast("请登录！", mContext);
            return;
        }
        NetEngine.submitPostTask(mContext, SOAPStringB.AssembleSoapRequest(API.Method_LOGOUT, API.JSON_LOGOUT,
                ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
//						Json json1 = new Json();
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
                Log.d(TAG, "result" + result);
//						Gson gson = new Gson();
//						Json json = gson.fromJson(result.toString(), Json.class);
//
//						Log.d(TAG, json.toString());


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }

            @Override
            public void finallyDo() {
                super.finallyDo();

            }
        });
        ShortCut.setUserclear();
        SharedPrefUtil.setAudoLogin(false, mContext);
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, LoginActivity.class);

        mContext.startActivity(mIntent);


    }


}
