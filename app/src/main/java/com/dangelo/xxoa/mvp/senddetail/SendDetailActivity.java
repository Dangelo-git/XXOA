package com.dangelo.xxoa.mvp.senddetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class SendDetailActivity extends MBaseActivity implements SendDetailContact.View {

    private SendtailPresenter mSendtailPresenter;
    private Button resetPassword;
    private EditText send_urgency;
    private EditText send_tag;
    private EditText send_title;
    private EditText send_bg;
    private EditText send_mainsend;
    private EditText send_copysend;
    private EditText send_opinion;
    private EditText send_opinion2;
    private EditText send_audit;
    private LinearLayout send_opinion_et_ly;
    private LinearLayout send_opinion2_et_ly;
    private TextView send_opinion_name_tv;
    private TextView send_opinion2_name_tv;
    private TextView send_opinion_date_tv;
    private TextView send_opinion2_date_tv;
    private TextView send_save_btn;
    private TextView send_submit_btn;
    private TextView send_reset_btn;
    private dispatchDoc mDispatchDoc = new dispatchDoc();
    private final String TAG=SendDetailActivity.class.getSimpleName();



    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SendDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onCreate");
        mDispatchDoc = (dispatchDoc) getIntent().getSerializableExtra("dispatchDoc");
        JLog.d(TAG,"mDispatchDoc:"+mDispatchDoc.toString());
        mSendtailPresenter.start();
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
        return R.layout.mvp_activity_senddetail;
    }

    @Override
    protected void initView() {
        send_urgency = (EditText) findViewById(R.id.send_urgency_et);
        send_tag = (EditText) findViewById(R.id.send_tag_et);
        send_title = (EditText) findViewById(R.id.send_title_et);
        send_bg = (EditText) findViewById(R.id.send_bg_et);
        send_mainsend = (EditText) findViewById(R.id.send_mainsend_et);
        send_copysend = (EditText) findViewById(R.id.send_copysend_et);
        send_opinion = (EditText) findViewById(R.id.send_opinion_et);
        send_opinion2 = (EditText) findViewById(R.id.send_opinion2_et);
        send_audit = (EditText) findViewById(R.id.send_audit_et);
        send_opinion_et_ly = (LinearLayout) findViewById(R.id.send_opinion_et_ly);
        send_opinion2_et_ly = (LinearLayout) findViewById(R.id.send_opinion2_et_ly);
        send_opinion_name_tv = (TextView) findViewById(R.id.send_opinion_name_tv);
        send_opinion2_name_tv = (TextView) findViewById(R.id.send_opinion2_name_tv);
        send_opinion_date_tv = (TextView) findViewById(R.id.send_opinion_date_tv);
        send_opinion2_date_tv = (TextView) findViewById(R.id.send_opinion2_date_tv);
        send_save_btn = (TextView) findViewById(R.id.send_save_btn);
        send_submit_btn = (TextView) findViewById(R.id.send_submit_btn);
        send_reset_btn = (TextView) findViewById(R.id.send_reset_btn);
        send_save_btn.setOnClickListener(this);
        send_submit_btn.setOnClickListener(this);
        send_reset_btn.setOnClickListener(this);
        send_bg.setOnClickListener(this);
        send_bg.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        send_bg.getPaint().setAntiAlias(true);//抗锯齿
        initdate();
    }
    private void initdate(){
        send_urgency.setText(mDispatchDoc.getDocEmergencyDegree());
        send_tag.setText(mDispatchDoc.getDocLabel());
        send_title.setText(mDispatchDoc.getDocTitle());
        send_bg.setText(mDispatchDoc.getDocFileName());
        send_mainsend.setText(mDispatchDoc.getDocMainSendUnit());
        send_copysend.setText(mDispatchDoc.getDocSendOffice());
//        send_opinion.setText(mDispatchDoc.get);
//        send_opinion2.setText(mDispatchDoc.ge);
//        send_audit.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
//        send_urgency.setText(mDispatchDoc.ge);
    }

    @Override
    protected BasePresenter createPresenter() {
        mSendtailPresenter = new SendtailPresenter(this);
        return mSendtailPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.send_save_btn:
                JLog.i(TAG, "DoSave");
                mSendtailPresenter.DoSave();
                break;

            case R.id.send_submit_btn:
                JLog.i(TAG, "DoSubmit");
                mSendtailPresenter.DoSubmit();
                break;
            case R.id.send_reset_btn:
                JLog.i(TAG, "DoReset");
                mSendtailPresenter.DoReset();
                break;
                case R.id.send_bg_et:
                JLog.i(TAG, "DoOpen");
                mSendtailPresenter.DoOpen();
                break;


            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText("发文详情");
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
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
    public void showFailReason(String error) {

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
    protected void onDestroy() {
        super.onDestroy();
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onDestroy");
    }
}
