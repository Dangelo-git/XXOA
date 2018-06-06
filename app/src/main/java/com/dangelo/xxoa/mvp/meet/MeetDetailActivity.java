package com.dangelo.xxoa.mvp.meet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.ExMeet;
import com.dangelo.xxoa.bean.InnerMeet;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class MeetDetailActivity extends MBaseActivity implements MeetDetailContact.View {

    private final String TAG = MeetDetailActivity.class.getSimpleName();
    private MeetdetailPresenter mMeetdetailPresenter;
    private Button resetPassword;
    private EditText meet_name;
    private EditText meet_time;
    private EditText meet_address;
    private EditText meet_stype;
    private EditText meet_presenter;
    private EditText meet_leader;
    private EditText meet_poster;
    private EditText meet_pubdate;
    private EditText meet_endtime;
    private EditText meet_ask;
    private EditText meet_talk;
    private EditText meet_bg;
    private EditText meet_attendee;
    private EditText meet_leaverson;
    private EditText meet_audit;
    private LinearLayout meet_sign_layout;
    private TextView meet_sign_tv;
    private ImageView meet_sign_iv;
    private LinearLayout meet_leave_layout;
    private TextView meet_leave_tv;
    private ImageView meet_leave_iv;
    private TextView meet_submit_btn;
    private InnerMeet mInMeet = new InnerMeet();
    private ExMeet mExMeet = new ExMeet();
    private boolean IS_leave = false;

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MeetDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onCreate");
        mInMeet = (InnerMeet) getIntent().getSerializableExtra("InMeet");
        mExMeet = (ExMeet) getIntent().getSerializableExtra("ExMeet");
        if (mInMeet != null) {

            JLog.d(TAG, "InMeet:" + mInMeet.toString());
        }
        if (mExMeet != null) {

            JLog.d(TAG, "ExMeet:" + mExMeet.toString());
        }
        mMeetdetailPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - -" + TAG + " onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - -" + TAG + " onPause");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mvp_activity_meetdetail;
    }

    @Override
    protected void initView() {
        meet_name = (EditText) findViewById(R.id.meet_name_et);
        meet_time = (EditText) findViewById(R.id.meet_time_et);
        meet_address = (EditText) findViewById(R.id.meet_address_et);
        meet_stype = (EditText) findViewById(R.id.meet_stype_et);
        meet_presenter = (EditText) findViewById(R.id.meet_presenter_et);
        meet_leader = (EditText) findViewById(R.id.meet_leader_et);
        meet_poster = (EditText) findViewById(R.id.meet_poster_et);
        meet_pubdate = (EditText) findViewById(R.id.meet_pubdate_et);
        meet_endtime = (EditText) findViewById(R.id.meet_endtime_et);
        meet_ask = (EditText) findViewById(R.id.meet_ask_et);
        meet_talk = (EditText) findViewById(R.id.meet_talk_et);
        meet_bg = (EditText) findViewById(R.id.meet_bg_et);
        meet_attendee = (EditText) findViewById(R.id.meet_attendee_et);
        meet_leaverson = (EditText) findViewById(R.id.meet_leaverson_et);
        meet_audit = (EditText) findViewById(R.id.meet_audit_et);

        meet_sign_layout = (LinearLayout) findViewById(R.id.meet_sign_layout);
        meet_sign_tv = (TextView) findViewById(R.id.meet_sign_tv);
        meet_sign_iv = (ImageView) findViewById(R.id.meet_sign_iv);
        meet_leave_layout = (LinearLayout) findViewById(R.id.meet_leave_layout);
        meet_leave_tv = (TextView) findViewById(R.id.meet_leave_tv);
        meet_leave_iv = (ImageView) findViewById(R.id.meet_leave_iv);

        meet_submit_btn = (TextView) findViewById(R.id.meet_submit_btn);

        meet_sign_layout.setOnClickListener(this);
        meet_leave_layout.setOnClickListener(this);
        meet_submit_btn.setOnClickListener(this);
        meet_bg.setOnClickListener(this);
        meet_bg.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        meet_bg.getPaint().setAntiAlias(true);//抗锯齿
        setRadioBtn(IS_leave);

    }

    private void setRadioBtn(boolean is_leave) {
        if (!is_leave) {
            meet_sign_iv.setImageResource(R.drawable.radio_press);
            meet_sign_tv.setTextColor(getResources().getColor(R.color.activity_text_blue));
            meet_leave_iv.setImageResource(R.drawable.radiobtn);
            meet_leave_tv.setTextColor(getResources().getColor(R.color.activity_detail_tv));
        } else {
            meet_leave_iv.setImageResource(R.drawable.radio_press);
            meet_leave_tv.setTextColor(getResources().getColor(R.color.activity_text_blue));
            meet_sign_iv.setImageResource(R.drawable.radiobtn);
            meet_sign_tv.setTextColor(getResources().getColor(R.color.activity_detail_tv));
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        mMeetdetailPresenter = new MeetdetailPresenter(this);
        return mMeetdetailPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;


            case R.id.meet_submit_btn:
                JLog.i(TAG, "DoSubmit");
                mMeetdetailPresenter.DoSubmit();
                break;

            case R.id.meet_bg_et:
                JLog.i(TAG, "DoOpen");
                mMeetdetailPresenter.DoOpen();
                break;
            case R.id.meet_sign_layout:
                JLog.i(TAG, "meet_sign_layout");
                IS_leave =false;
                setRadioBtn(IS_leave);
                break;
            case R.id.meet_leave_layout:
                JLog.i(TAG, "meet_leave_layout");
                IS_leave =true;
                setRadioBtn(IS_leave);
                break;


            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText("会议通知详情");
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
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onDestroy");
    }
}
