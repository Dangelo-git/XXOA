package com.dangelo.xxoa.mvp.main.five;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author lz.
 * @date on 2017/10/23 18:08.
 */

public class FivePresenter implements FiveContact.Presenter {

    private FiveContact.View view;
    private final static String TAG = FivePresenter.class.getSimpleName();

    private Context context;


    public FivePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        Log.i(TAG, "start");
    }

    @Override
    public void attach(FiveContact.View view) {
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
