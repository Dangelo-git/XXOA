package com.dangelo.xxoa.tools;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.Utils;
import com.dangelo.xxoa.uitl.SharedPrefUtil;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by dangelo on 17/4/14.
 */
public class Jpush {
    public static Set<String> tags=new HashSet<String>();
    private static String TAG=Jpush.class.getSimpleName();
    public static void initJpush() {
        Log.d(TAG,"initjpush");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(Utils.getContext());
        boolean isCon=JPushInterface.getConnectionState(Utils.getContext());
        Log.i(TAG, isCon + "");
//        注册registration_id=170976fa8a8220f42d4
        String registration_id=JPushInterface.getRegistrationID(Utils.getContext());
        Log.i(TAG,"注册mid_registration_id"+registration_id);
        if(!TextUtils.isEmpty(registration_id)){
            SharedPrefUtil.setRegistrationId(registration_id,Utils.getContext());
        }
        tags.add("htcone");

//        JPushInterface.setAlias(Utils.getContext(), "ebupt", new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.i(TAG,"setAlias"+ "huwei" + s + "");
//            }
//        });
//
////        设置通知别名alias与标签tags
//
//        JPushInterface.setTags(Utils.getContext(), tags, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.i(TAG,"resp"+String.valueOf(i));
//                if (i == 0) {
//                    Log.i("tags", "设置tags成功");
//                }
//            }
//        });
    }

}
