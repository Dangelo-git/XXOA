package com.dangelo.xxoa.mvp.main.second;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author lz.
 * @date on 2017/10/23 18:08.
 */

public class SecondPresenter implements SecondContact.Presenter {

    private SecondContact.View view;
    private final static String TAG = SecondPresenter.class.getSimpleName();

    private Context context;


    public SecondPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        Log.i(TAG, "start");
    }

    @Override
    public void attach(SecondContact.View view) {
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
