package com.dangelo.xxoa.mvp.innermeet;

import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class InMeetDetailContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);


        void showFailReason(String error);
        void showProgressDialog(boolean isShow, String content);



    }
    interface Presenter extends BasePresenter {
        void getSendDetail();
        void DoSave();
        void DoSubmit();
        void DoReset();
        void DoOpen();
    }
}
