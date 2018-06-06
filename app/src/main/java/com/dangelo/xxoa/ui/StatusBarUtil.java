package com.dangelo.xxoa.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * @author lz.
 * @date on 2017/12/25 14:20.
 */

public class StatusBarUtil {

    private static final String TAG_FAKE_STATUS_BAR_VIEW = "statusBarView";
    private static final String TAG_MARGIN_ADDED = "marginAdded";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void setStatusBarColor(Activity activity, int statusDrawble) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mContentChild = mContentView.getChildAt(0);
        int statusBarHeight = getStatusBarHeight(activity);

        removeFakeStatusBarViewIfExist(activity);
        addFakeStatusBarView(activity, statusDrawble, statusBarHeight);
        addMarginTopToContentChild(mContentChild, statusBarHeight);

        if (mContentChild != null) {
            ViewCompat.setFitsSystemWindows(mContentChild, false);
        }
    }
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    /**
     * use reserved order to remove is more quickly.
     */
    private static void removeFakeStatusBarViewIfExist(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();

        View fakeView = mDecorView.findViewWithTag(TAG_FAKE_STATUS_BAR_VIEW);
        if (fakeView != null) {
            mDecorView.removeView(fakeView);
        }
    }
    /**
     * 1. Add fake statusBarView.
     * 2. set tag to statusBarView.
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private static View addFakeStatusBarView(Activity activity, int statusDrawble, int statusBarHeight) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();

        View mStatusBarView = new View(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        layoutParams.gravity = Gravity.TOP;
        mStatusBarView.setLayoutParams(layoutParams);
        mStatusBarView.setBackground(activity.getResources().getDrawable(statusDrawble));
        mStatusBarView.setTag(TAG_FAKE_STATUS_BAR_VIEW);
        mDecorView.addView(mStatusBarView);
        return mStatusBarView;
    }


    /**
     * add marginTop to simulate set FitsSystemWindow true
     */
    private static void addMarginTopToContentChild(View mContentChild, int statusBarHeight) {
        if (mContentChild == null) {
            return;
        }
        if (!TAG_MARGIN_ADDED.equals(mContentChild.getTag())) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mContentChild.getLayoutParams();
            lp.topMargin += statusBarHeight;
            mContentChild.setLayoutParams(lp);
            mContentChild.setTag(TAG_MARGIN_ADDED);
        }
    }
}
