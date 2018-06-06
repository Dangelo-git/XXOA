package com.dangelo.xxoa.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.mvp.main.MainActivity;
import com.dangelo.xxoa.mvp.welcome.WelActivity;
import com.dangelo.xxoa.togglebutton.ToggleButton;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.ui.MyRoundProgressBar;
import com.dangelo.xxoa.ui.StatusBarUtil;
import com.dangelo.xxoa.ui.ToastUtil;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class LoginActivity extends MBaseActivity implements LoginContact.View {

    private LoginPresenter mLoginPresenter;
    String msg = "";
    MyRoundProgressBar myProgressBar;
    private String Flag = "0";
    private EditText CallednameEt;
    private EditText CalledpwdEt;

    private TextView CommitBtn;
    private int timeoutlen = 60;
    private boolean isChange=false;
    private LinearLayout llCallIp;
    private final String TAG=LoginActivity.class.getSimpleName();

    private int UPDATE_TIME = 0;

    private ToggleButton ConnectShock;

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onCreate");
        if (ShortCut.getUser(this) != null) {
            if (SharedPrefUtil.getAudoLogin(this)){
//                Intent intent = new Intent();
//                intent.setClass(WelActivity.this, com.dangelo.xxoa.mvp.main.MainActivity.class);
//                startActivity(intent);
                toWelcomActivity();
            }

        }
        mLoginPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - -"+TAG+" onResume");
        if(getIntent().getExtras()!=null){
            isChange=getIntent().getExtras().getBoolean("isChange");
        }else {
            isChange=false;
        }
        mLoginPresenter.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - -"+TAG+" onPause");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        myProgressBar = new MyRoundProgressBar(this);
        CommitBtn = (TextView) findViewById(R.id.setting_login_btn);
        CallednameEt = (EditText) findViewById(R.id.called_name_et);


        CalledpwdEt = (EditText) findViewById(R.id.called_pwd_et);
        CallednameEt.setTextColor(getResources().getColor(R.color.black));
        CalledpwdEt.setTextColor(getResources().getColor(R.color.black));
        CommitBtn.setTextColor(getResources().getColor(R.color.white));
        CommitBtn.setOnClickListener(this);
        ConnectShock = (ToggleButton) findViewById(R.id.connect_shock_switch);

        if (SharedPrefUtil.getAudoLogin(this)) {

            ConnectShock.setToggleOn();
        } else {
            ConnectShock.setToggleOff();
        }
        ConnectShock.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                SharedPrefUtil.setAudoLogin(on, LoginActivity.this);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.setting_login_btn:
                JLog.i(TAG, "setting_login_btn");
                if(Testswith.Testloginname){
                    CallednameEt.setText("admin");
                    CalledpwdEt.setText("123456");
                }
                if (CallednameEt.getText().equals("")) {
                    msg = "用户名不能为空！";
                    ToastUtil.show(LoginActivity.this,msg);
                    return;
                }
                if (CalledpwdEt.getText().equals("")) {
                    msg = "密码不能为空！";
                    ToastUtil.show(LoginActivity.this,msg);
                    return;
                }
                mLoginPresenter.LogInOnClick();
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
    public String getAccount() {
        return CallednameEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return CalledpwdEt.getText().toString();
    }

    @Override
    public String getImei() {
        return null;
    }

    @Override
    public String getregisterId() {
        return null;
    }

    @Override
    public void toMainActivity() {
        MainActivity.toThisActivity(this, null);
        JLog.d(TAG, "LoginFragment------>toMainActivity");
        finish();
    }
    public void toWelcomActivity() {
        WelActivity.toThisActivity(this, null);
        JLog.d(TAG, "LoginFragment------>toWelcomActivity");
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
