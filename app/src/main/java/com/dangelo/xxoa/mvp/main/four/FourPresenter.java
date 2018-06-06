package com.dangelo.xxoa.mvp.main.four;

import android.content.Context;
import android.content.Intent;
import android.util.Log;



/**
 * @author lz.
 * @date on 2017/10/23 18:08.
 */

public class FourPresenter implements FourContact.Presenter {

    private FourContact.View view;
    private final static String TAG = FourPresenter.class.getSimpleName();

    private Context context;


    public FourPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        Log.i(TAG, "start");
    }

    @Override
    public void attach(FourContact.View view) {
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
