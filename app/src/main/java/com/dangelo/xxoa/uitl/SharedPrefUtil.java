package com.dangelo.xxoa.uitl;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dangelo on 16/11/18.
 */
public class SharedPrefUtil {
    public static boolean getAudoLogin(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return sp.getBoolean("AudoLogin", true);
    }
    public static void setAudoLogin(boolean AudoLogin, Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        sp.edit().putBoolean("AudoLogin", AudoLogin).commit();
    }
    public static void setRegistrationId(String registrationId, Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        sp.edit().putString("registrationid", registrationId).commit();
    }

    public static String getRegistrationId(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return sp.getString("registrationid", "");
    }

}
