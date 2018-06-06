package com.dangelo.xxoa.uitl;

import android.content.Context;
import android.os.PowerManager;

import com.dangelo.xxoa.application.XZOAApplication;

/**
 * @author evilyin(ChenZhixi)
 * @since 16/1/8
 */
public class WakeLockUtil {

    private static PowerManager.WakeLock wakeLock;

    public static PowerManager.WakeLock acquireWakeLock() {
        Context context = XZOAApplication.getInstance();
        PowerManager powerManager = (PowerManager) (context.getSystemService(Context.POWER_SERVICE));
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SIP");
        if (!wakeLock.isHeld()) {
            wakeLock.acquire();
        }
        return wakeLock;
//        return null;
    }

    public static void releaseWakeLock() {
        if(wakeLock != null && wakeLock.isHeld()){
            wakeLock.release();
            wakeLock = null;
        }
    }
}
