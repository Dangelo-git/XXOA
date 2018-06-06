package com.dangelo.xxoa.ui;

import android.app.ProgressDialog;
import android.content.Context;

import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/6/26 10:28.
 */
public class MProgressDialog {
    private static ProgressDialog mProgressDialog;
    private static final String TAG=MProgressDialog.class.getName();

    public static void show(Context context,String content){
        if(mProgressDialog==null||!mProgressDialog.isShowing()){
            mProgressDialog = ProgressDialog.show(context, null,
                    content);
            JLog.d(TAG, "mProgressDialog,show");
        }
    }
    public static void cancle(){
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog=null;
            JLog.i(TAG, "mProgressDialog.dismiss()");
        }
    }
}
