package com.dangelo.xxoa.mvp.receivedetail;

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
import com.dangelo.xxoa.bean.currentStation;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.bean.nextStation;
import com.dangelo.xxoa.bean.node;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.jiongbull.jlog.JLog;

import java.util.List;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class ReceiveDetailActivity extends MBaseActivity implements ReceiveDetailContact.View {

    private final String TAG = ReceiveDetailActivity.class.getSimpleName();
    private ReceivetailPresenter mReceivetailPresenter;
    private Button resetPassword;
    private EditText receive_urgency;
    private EditText receive_tag;
    private EditText receive_title;
    private EditText receive_bg;
    private EditText receive_time;
    private EditText receive_organize;
    private EditText receive_opinion;
    private EditText receive_opinion2;
    private EditText receive_audit;
    private EditText receive_registrant;
    private EditText receive_inagency;
    private EditText receive_infileno;
    private EditText receive_receivefileno;
    private EditText receive_receivefilestype;
    private EditText receive_fileabstract;
    private EditText receive_filecount;
    private LinearLayout receive_opinion_et_ly;
    private LinearLayout receive_opinion2_et_ly;
    private TextView receive_opinion_name_tv;
    private TextView receive_opinion2_name_tv;
    private TextView receive_opinion_date_tv;
    private TextView receive_opinion2_date_tv;
    private TextView receive_save_btn;
    private TextView receive_submit_btn;
    private TextView receive_reset_btn;
    private inDoc mInDoc = new inDoc();
    private node mtempNode = new node();
    private currentStation mtempStation = new currentStation();

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ReceiveDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onCreate");
        mInDoc = (inDoc) getIntent().getSerializableExtra("inDoc");
        JLog.d(TAG, "inDoc:" + mInDoc.toString());
        mReceivetailPresenter.start();
        mReceivetailPresenter.getNode(Long.parseLong(mInDoc.getId()));
        initDate();
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
        return R.layout.mvp_activity_receivedetail;
    }

    @Override
    protected void initView() {
        receive_urgency = (EditText) findViewById(R.id.receive_urgency_et);
        receive_tag = (EditText) findViewById(R.id.receive_tag_et);
        receive_title = (EditText) findViewById(R.id.receive_title_et);
        receive_bg = (EditText) findViewById(R.id.receive_bg_et);
        receive_time = (EditText) findViewById(R.id.receive_time_et);
        receive_organize = (EditText) findViewById(R.id.receive_organize_et);
        receive_opinion = (EditText) findViewById(R.id.receive_opinion_et);
        receive_opinion2 = (EditText) findViewById(R.id.receive_opinion2_et);
        receive_audit = (EditText) findViewById(R.id.receive_audit_et);


        receive_registrant = (EditText) findViewById(R.id.receive_registrant_et);
        receive_inagency = (EditText) findViewById(R.id.receive_inagency_et);
        receive_infileno = (EditText) findViewById(R.id.receive_infileno_et);
        receive_receivefileno = (EditText) findViewById(R.id.receive_receivefileno_et);
        receive_receivefilestype = (EditText) findViewById(R.id.receive_receivefilestype_et);
        receive_filecount = (EditText) findViewById(R.id.receive_filecount_et);
        receive_fileabstract = (EditText) findViewById(R.id.receive_fileabstract_et);


        receive_opinion_et_ly = (LinearLayout) findViewById(R.id.receive_opinion_et_ly);
        receive_opinion2_et_ly = (LinearLayout) findViewById(R.id.receive_opinion2_et_ly);
        receive_opinion_name_tv = (TextView) findViewById(R.id.receive_opinion_name_tv);
        receive_opinion2_name_tv = (TextView) findViewById(R.id.receive_opinion2_name_tv);
        receive_opinion_date_tv = (TextView) findViewById(R.id.receive_opinion_date_tv);
        receive_opinion2_date_tv = (TextView) findViewById(R.id.receive_opinion2_date_tv);
        receive_save_btn = (TextView) findViewById(R.id.receive_save_btn);
        receive_submit_btn = (TextView) findViewById(R.id.receive_submit_btn);
        receive_reset_btn = (TextView) findViewById(R.id.receive_reset_btn);
        receive_save_btn.setOnClickListener(this);
        receive_submit_btn.setOnClickListener(this);
        receive_reset_btn.setOnClickListener(this);
        receive_bg.setOnClickListener(this);
        receive_bg.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        receive_bg.getPaint().setAntiAlias(true);//抗锯齿


    }

    private void initDate() {
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - -initDate");
        receive_urgency.setText(mInDoc.getEmergencyDegree());
        receive_tag.setText(mInDoc.getLabel());
        receive_title.setText(mInDoc.getFileTitle());
        receive_bg.setText(mInDoc.getFileName());
        receive_time.setText(mInDoc.getInTime());
        receive_organize.setText(mInDoc.getTheReceiving());
//    receive_opinion .setText(mIndoc.ge);
//    receive_opinion2.setText(mIndoc.ge);
//    receive_audit.setText(mIndoc.ge);


        receive_registrant.setText(mInDoc.getUserName());
        receive_inagency.setText(mInDoc.getCivilService());
        receive_infileno.setText(mInDoc.getCivilNumber());
        receive_receivefileno.setText(mInDoc.getInNumber());
        receive_receivefilestype.setText(mInDoc.getFileType());
        receive_filecount.setText(mInDoc.getNumberOf());
        receive_fileabstract.setText(mInDoc.getFileAbstract());
    }

    @Override
    protected BasePresenter createPresenter() {
        mReceivetailPresenter = new ReceivetailPresenter(this);
        return mReceivetailPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.receive_save_btn:
                JLog.i(TAG, "DoSave");
                mInDoc.setCurrentOpinion(receive_audit.getText().toString());
                mReceivetailPresenter.DoSave(mInDoc);
                break;

            case R.id.receive_submit_btn:
                JLog.i(TAG, "DoSubmit");
                mReceivetailPresenter.DoSubmit();
                break;
            case R.id.receive_reset_btn:
                JLog.i(TAG, "DoReset");
                receive_audit.setText("");
                mInDoc.setCurrentOpinion("");
                mReceivetailPresenter.DoSave(mInDoc);
                break;
            case R.id.receive_bg_et:
                JLog.i(TAG, "DoOpen");
                mReceivetailPresenter.DoOpen();
                break;


            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText("收文详情");
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
    public void showPopDialog(List<node> data) {
        for (node temp : data) {
            JLog.d(TAG, temp.toString());
        }
        mtempNode = data.get(0);
        mReceivetailPresenter.getCurrentStation(mtempNode.getPresonsationid(), mtempNode.getSignbatchtypeid());
    }

    @Override
    public void showPopDialogPerson(List<currentStation> data) {
        for (currentStation temp : data) {
            JLog.d(TAG, temp.toString());
        }
        mtempStation = data.get(0);
        nextStation nextstation = new nextStation();
        nextstation.setOption(receive_audit.getText().toString());
        nextstation.setConditionText(mtempNode.getConditionText());
        nextstation.setId(Long.parseLong(mInDoc.getId()));
        nextstation.setPresonsationid(mtempNode.getPresonsationid());
        nextstation.setUserId(mtempStation.getId());
        nextstation.setLinkName(mtempNode.getName());
        mReceivetailPresenter.PostnextUserActiviti(nextstation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onDestroy");
    }

}
