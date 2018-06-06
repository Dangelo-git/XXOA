package com.dangelo.xxoa.mvp.login;

import android.os.Bundle;

import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

/**
 * Created by mga on 2017/8/11 17:26.
 */

public class LoginContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);
        String getAccount();


        String getPassword();
        String getImei();
        String getregisterId();




        void toMainActivity();

        void showFailReason(String error);
        void showProgressDialog(boolean isShow, String content);


        void showAccount(String account);
    }
    interface Presenter extends BasePresenter {
        void LogInOnClick();
    }
}
