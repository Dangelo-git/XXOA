package com.dangelo.xxoa.mvp.notice;

import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class NoticeDetailContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);


        void showFailReason(String error);
        void showProgressDialog(boolean isShow, String content);



    }
    interface Presenter extends BasePresenter {
        void getSendDetail();
        void DoShare();

    }
}
