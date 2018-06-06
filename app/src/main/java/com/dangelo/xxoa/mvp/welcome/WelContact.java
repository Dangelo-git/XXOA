package com.dangelo.xxoa.mvp.welcome;

import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class WelContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);





        void toMainActivity();

        void showFailReason(String error);
        void showProgressDialog(boolean isShow, String content);


        void showAccount(String account);
    }
    interface Presenter extends BasePresenter {
        void autoLogin();
    }
}
