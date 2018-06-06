package com.dangelo.xxoa.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;


import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/5/4.
 */
public class MJpushReceiver extends BroadcastReceiver {
    public static final String TAG = "MyJpushReceiver";
    private Context mContext;
    private String peername;
    public static PushCallback mPushCallback;
    private LocalBroadcastManager broadcastManager;
    private   static String OFFLINE="mebofflinenotification";

    public void SetOnBind(PushCallback pushcallback) {
        mPushCallback = pushcallback;
        JLog.d(TAG, "SetOnBind:mPushCallback - " + mPushCallback);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        mContext = context;
        JLog.d(TAG, "onReceive - " + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String registrationId=bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            JLog.d(TAG, "注册register_id成功" +registrationId );
            SharedPrefUtil.setRegistrationId(registrationId,mContext);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            JLog.i(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String messageString = bundle.getString(JPushInterface.EXTRA_MESSAGE).trim();
            String extras=bundle.getString(JPushInterface.EXTRA_EXTRA);
            try {
                JSONObject extrajson=new JSONObject(extras);
                String message_type=extrajson.getString("message_type").toString().trim();
                mPushCallback.PushMessageResult(messageString,message_type);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JLog.i(TAG, "msg_content:" + messageString);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 在这里可以做些统计，或者做些其他工作
            JLog.i(TAG, "收到了通知。通知内容是：" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //根据收到的内容判断执行流程
            //1.不操作，打开  后直接跳转主界面 2.未送达短信，打开后跳转短信fragment 3.未送达电话 ，打开后跳转跳转通话记录fragment
            String extraString = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String extratitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            try {
                JSONObject extrajson = new JSONObject(extraString);
                String msgTitle = extrajson.get("msgTitle").toString().trim();
                JLog.i(TAG, "notify_type:" + msgTitle + " extraString:" + extratitle + bundle.getString(JPushInterface.EXTRA_ALERT));

                    JLog.d(TAG, "PushOpenDResultk type - " +msgTitle);
                    mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                            .EXTRA_ALERT), msgTitle);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String extraString = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String extratitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            try {
                JSONObject extrajson = new JSONObject(extraString);
                String msgTitle = extrajson.get("msgTitle").toString().trim();
                JLog.d(TAG, "用户点击打开了通知 - msgTitle:" + msgTitle);

                    JLog.d(TAG, "PushOpenDResultk type - " +msgTitle);
                    mPushCallback.PushOpenDResult(bundle.getString(JPushInterface
                            .EXTRA_ALERT), msgTitle);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        } else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {


        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean isCon = JPushInterface.getConnectionState(context);
            try {
                int value1 = Settings.System.getInt(context.getContentResolver(), Settings.System.WIFI_SLEEP_POLICY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * push处理结果的接口
     */
    public interface PushCallback {
        /**
         * push返回方法
         *
         * @param pushInfo
         * @param push_code 说明（1.不操作,打开后直接跳转主界面 2.未送达短信,打开后跳转短信fragment
         *                  3.未送达电话 ,打开后跳转跳转通话记录fragment）4.APP注册未关机
         *                  5、通知用户余额不足 6、通知用户手机号在其他终端登录
         *                  7、通知用户套餐已过期或未订购套餐 8、系统消息
         *
         */
        void PushReceiveResult(String pushInfo, String push_code);

        void PushOpenDResult(String pushInfo, String push_code);

        void PushMessageResult(String pushInfo, String push_code);//自定义通知消息回调


    }
}
