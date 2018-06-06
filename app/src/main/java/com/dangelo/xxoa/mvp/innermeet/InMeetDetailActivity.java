package com.dangelo.xxoa.mvp.innermeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.ExMeet;
import com.dangelo.xxoa.bean.InnerMeet;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class InMeetDetailActivity extends MBaseActivity implements InMeetDetailContact.View {

    private InMeetDetailPresenter mInMeetDetailPresenter;
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
    private InnerMeet mInMeet = new InnerMeet();
    private ExMeet mExMeet = new ExMeet();
    private final String TAG= InMeetDetailActivity.class.getSimpleName();



    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InMeetDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG,"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+TAG+" onCreate");
        mInMeet = (InnerMeet) getIntent().getSerializableExtra("InMeet");
        mExMeet = (ExMeet) getIntent().getSerializableExtra("ExMeet");
        if(mInMeet!=null){

            JLog.d(TAG,"InMeet:"+mInMeet.toString());
        }
        if(mExMeet!=null){

            JLog.d(TAG,"ExMeet:"+mExMeet.toString());
        }
        mInMeetDetailPresenter.start();
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

    }

    @Override
    protected BasePresenter createPresenter() {
        mInMeetDetailPresenter = new InMeetDetailPresenter(this);
        return mInMeetDetailPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.send_save_btn:
                JLog.i(TAG, "DoSave");
                mInMeetDetailPresenter.DoSave();
                break;

            case R.id.send_submit_btn:
                JLog.i(TAG, "DoSubmit");
                mInMeetDetailPresenter.DoSubmit();
                break;
            case R.id.send_reset_btn:
                JLog.i(TAG, "DoReset");
                mInMeetDetailPresenter.DoReset();
                break;
                case R.id.send_bg_et:
                JLog.i(TAG, "DoOpen");
                mInMeetDetailPresenter.DoOpen();
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
