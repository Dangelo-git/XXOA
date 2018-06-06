package com.dangelo.xxoa.mvp.notice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.noticeInfo;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.uitl.TimeManagement;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class NoticeDetailActivity extends MBaseActivity implements NoticeDetailContact.View {

    private final String TAG = NoticeDetailActivity.class.getSimpleName();
    private NoticePresenter mNoticePresenter;
    private TextView noticereceive_title;
    private TextView noticereceive_content;
    private TextView noticereceive_time;
    private TextView notice_share_btn;
    private noticeInfo mNoticeInfo = new noticeInfo();
    private boolean isshare = true;

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NoticeDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("noticeInfo")) {
            JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onCreate");
            mNoticeInfo = (noticeInfo) getIntent().getSerializableExtra("noticeInfo");
        }
        if (getIntent().hasExtra("isshare")) {

            isshare = getIntent().getBooleanExtra("isshare", true);
//            JLog.d(TAG, "hasExtra isshare:"+isshare);
        }
        JLog.d(TAG, "mNoticeInfo:" + mNoticeInfo.toString() + " isshare:" + isshare);
        mNoticePresenter.start();
        if (!isshare) {
            notice_share_btn.setVisibility(View.GONE);
        } else {
            notice_share_btn.setVisibility(View.VISIBLE);
        }
        initdate();
    }

    private void initdate() {
        noticereceive_title.setText(mNoticeInfo.getNotice());
        noticereceive_time.setText(TimeManagement.longToStringDate(Long.parseLong(mNoticeInfo.getIssueDate()),"yyyy-MM-dd HH:mm"));
        noticereceive_content.setText(mNoticeInfo.getNoticeContent());

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
        return R.layout.mvp_activity_noticedetail;
    }

    @Override
    protected void initView() {
        noticereceive_title = (TextView) findViewById(R.id.noticereceive_title_tv);

        noticereceive_time = (TextView) findViewById(R.id.noticereceive_time_tv);
        noticereceive_content = (TextView) findViewById(R.id.noticereceive_content_tv);
        notice_share_btn = (TextView) findViewById(R.id.notice_share_btn);

        notice_share_btn.setOnClickListener(this);

    }

    @Override
    protected BasePresenter createPresenter() {
        mNoticePresenter = new NoticePresenter(this);
        return mNoticePresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.notice_share_btn:
                JLog.i(TAG, "DoShare");
                mNoticePresenter.DoShare();
                break;


            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText("通知公告详情");
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
