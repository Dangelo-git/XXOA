package com.dangelo.xxoa.mvp.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.mvp.main.MainActivity;
import com.dangelo.xxoa.togglebutton.ToggleButton;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.ui.MyRoundProgressBar;
import com.dangelo.xxoa.ui.ToastUtil;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class WelActivity extends MBaseActivity implements WelContact.View {

    private WelPresenter mWelPresenter;
    String msg = "";
    MyRoundProgressBar myProgressBar;
    private final String TAG= WelActivity.class.getSimpleName();


    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WelActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onCreate");

        mWelPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - -"+TAG+" onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - -"+TAG+" onPause");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        myProgressBar = new MyRoundProgressBar(this);


    }

    @Override
    protected BasePresenter createPresenter() {
        mWelPresenter = new WelPresenter(this);
        return mWelPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        showNoToolbar();
        super.setupAppBar();
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow) {
            MProgressDialog.show(this, null);
        } else {
            MProgressDialog.cancle();
        }
    }


    @Override
    public void toMainActivity() {
        MainActivity.toThisActivity(this, null);
        JLog.d(TAG, "LoginFragment------>toMainActivity");
        finish();
    }


    @Override
    public void showFailReason(String error) {
//        if ("20000108".equals(error)||"20000109".equals(error)) {
//            MAlertDialog.showErrorDialog(this,"验证码错误",null);
////            ToastUtil.show(this, "验证码错误");
//        }else if ("20000120".equals(error)) {
//            MAlertDialog.showErrorDialog(this,"密码重置失败",null);
////      ToastUtil.show(this, "密码重置失败");
//        }else if ("20000113".equals(error)) {
//            MAlertDialog.showErrorDialog(this,"用户尚未开户",null);
////      ToastUtil.show(this, "密码重置失败");
//        }

    }




    @Override
    public void showProgressDialog(boolean isShow, String content) {
        if (isShow) {
            MProgressDialog.show(this, content);
        } else {
            MProgressDialog.cancle();
        }
    }

    @Override
    public void showAccount(String account) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onDestroy");
    }
}
