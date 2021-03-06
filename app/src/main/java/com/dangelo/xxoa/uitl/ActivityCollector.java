package com.dangelo.xxoa.uitl;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mga on 2017/7/14 11:34.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    private static String TAG=ActivityCollector.class.getSimpleName();

    public static void addActivity(Activity activity) {
        activities.add(activity);
        for (Activity activity1 : activities) {
            Log.d(TAG,activity1.getClass().getSimpleName());
        }
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
        for (Activity activity1 : activities) {
            Log.d(TAG,activity1.getClass().getSimpleName());
        }
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
