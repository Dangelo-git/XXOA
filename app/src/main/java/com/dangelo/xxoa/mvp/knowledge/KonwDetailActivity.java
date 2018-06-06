package com.dangelo.xxoa.mvp.knowledge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.Rqtknowledge;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.knowledgePersonal;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.jiongbull.jlog.JLog;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class KonwDetailActivity extends MBaseActivity implements KonwDetailContact.View {

    private final String TAG = KonwDetailActivity.class.getSimpleName();
    private KonwPresenter mKonwPresenter;
    private TextView noticereceive_title;
    private TextView noticereceive_content;
    private TextView noticereceive_time;
    private TextView notice_share_btn;
    private knowledgePersonal mknowledgePersonal = new knowledgePersonal();
    private String mKnowledgeCategory ;
    private Rqtknowledge mRqtknowledge = new Rqtknowledge();

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, KonwDetailActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("knowledgePersonal")) {
            JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onCreate");
            mknowledgePersonal = (knowledgePersonal) getIntent().getSerializableExtra("knowledgePersonal");
            mKnowledgeCategory = getIntent().getStringExtra("KnowledgeCategory");
        }
        JLog.d(TAG, "mknowledgePersonal:" + mknowledgePersonal.toString()+" KnowledgeCategory:"+mKnowledgeCategory);
        mKonwPresenter.start();


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
        mKonwPresenter = new KonwPresenter(this);
        return mKonwPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
            case R.id.notice_share_btn:
                JLog.i(TAG, "DoShare");
                mRqtknowledge.setId(mknowledgePersonal.getId());
                mRqtknowledge.setKnowledgeCategoryId(mknowledgePersonal.getKnowledgeCategoryId());
                mRqtknowledge.setKnowledgeCategory(mKnowledgeCategory);
                mRqtknowledge.setKnowledgeType(2+"");
                mKonwPresenter.DoShare(mRqtknowledge);
                break;


            default:
                break;
        }
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText("知识库详情");
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
