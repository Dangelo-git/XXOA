package com.dangelo.xxoa.mvp.main.five;

import android.content.Intent;

import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;


/**
 * @author lz.
 * @date on 2017/10/23 18:05.
 */

public class FiveContact {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();


    }

    interface Presenter extends BasePresenter<View> {


        void newIntent(Intent intent);


    }

}
