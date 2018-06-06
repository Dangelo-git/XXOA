package com.dangelo.xxoa.mvp.innermeet;

import android.content.Context;

import com.dangelo.xxoa.mvp.base.BaseView;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class InMeetDetailPresenter implements InMeetDetailContact.Presenter {
    private final Context mContext;
    private InMeetDetailContact.View mView;
    private String TAG = InMeetDetailPresenter.class.getSimpleName();
    private String vfc;

    public InMeetDetailPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (InMeetDetailContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    @Override
    public void getSendDetail() {

    }

    @Override
    public void DoSave() {

    }

    @Override
    public void DoSubmit() {

    }

    @Override
    public void DoReset() {

    }

    @Override
    public void DoOpen() {

    }
}
