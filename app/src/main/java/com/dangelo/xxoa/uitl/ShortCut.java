package com.dangelo.xxoa.uitl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.Broese;
import com.dangelo.xxoa.bean.DeptInfo;
import com.dangelo.xxoa.bean.MeetBasis;
import com.dangelo.xxoa.bean.docinfo;
import com.dangelo.xxoa.bean.meetinfo;
import com.dangelo.xxoa.bean.schedule;
import com.dangelo.xxoa.bean.user;
import com.jiongbull.jlog.JLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dangelo on 16/9/12.
 */
public class ShortCut {
    public static Display display;
    public static user currentUser;
    public static int ScheduleID;
    public static schedule Schedule;
    public static docinfo Docinfo;
    public static Broese broese;
    public static meetinfo Meetinfo;
    public static int CalendarCheckTime = 20160000;
    public static MeetBasis MeetBasis = null;
    public static List<DeptInfo> deptList_list = new ArrayList<>();
    public static List<DeptInfo> ConfirmdeptList_list = new ArrayList<DeptInfo>();
    public static boolean docunmentflag = false;
    public static Handler SelectHandler;//多选界面全选刷新handler
    private static String TAG = "ShortCut";
    public static int Page = 100;

    public static void initDefaultDisplay(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        display = windowManager.getDefaultDisplay();
    }

    public static user getUser(Context context) {
        if (context == null) {
            Log.i(TAG, "context==null!!!!");
        }

        DataShare share = new DataShare(context);
        if (Testswith.Testlogin) {


        }

//
        if (currentUser == null) {
            currentUser = new user();
//            currentUser.setUuCName(share.read("UuCName"));
//            if(share.read("uId")==null||share.read("uId").equals("")){
//                currentUser.setuId(0);
//            }else{
//                currentUser.setuId(Integer.parseInt(share.read("uId")));
//            }

            currentUser.setUserEnglishName(share.read("UuEName"));
            currentUser.setUserPhoneImeid(share.read("UuKeyID"));
            currentUser.setUSER_KEY(share.read("UserKey"));
            currentUser.setPassWord(share.read("Password"));
        }

        return ShortCut.currentUser;
    }

    public static void setUserclear() {
        ShortCut.currentUser = null;
    }

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String GetVersionCode(Context context) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionName;
//            LogUtil.d("TAG", "本软件的版本。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;


    }

    public static DisplayImageOptions getImageOptions() {
        DisplayImageOptions options = null;
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.schedule_loading)
                .showImageForEmptyUri(R.drawable.schedule_loading)
                .showImageOnFail(R.drawable.schedule_loading).cacheInMemory(true)
                .cacheOnDisc(true).build();
        return options;
    }

    public static String GetDeivceSN(Context mContext) {
        String ANDROID_ID = Settings.System.getString(mContext.getContentResolver(), Settings.System.ANDROID_ID);
        JLog.i(TAG, "ANDROID_ID" + ANDROID_ID);
        return ANDROID_ID;
    }
}
