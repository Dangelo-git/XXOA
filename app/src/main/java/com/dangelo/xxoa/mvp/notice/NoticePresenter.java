package com.dangelo.xxoa.mvp.notice;

import android.content.Context;

import com.dangelo.xxoa.mvp.base.BaseView;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class NoticePresenter implements NoticeDetailContact.Presenter {
    private final Context mContext;
    private NoticeDetailContact.View mView;
    private String TAG = NoticePresenter.class.getSimpleName();
    private String vfc;

    public NoticePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void start() {

    }


    @Override
    public void attach(BaseView view) {
        this.mView = (NoticeDetailContact.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }




    @Override
    public void getSendDetail() {

    }

    @Override
    public void DoShare() {

    }


}
