package com.dangelo.xxoa.mvp.main.third;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author lz.
 * @date on 2017/10/23 18:08.
 */

public class ThirdPresenter implements ThirdContact.Presenter {

    private ThirdContact.View view;
    private final static String TAG = ThirdPresenter.class.getSimpleName();

    private Context context;


    public ThirdPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        Log.i(TAG, "start");
    }

    @Override
    public void attach(ThirdContact.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }



    @Override
    public void newIntent(Intent intent) {
//        Log.i(TAG,"newIntent");

    }




}
