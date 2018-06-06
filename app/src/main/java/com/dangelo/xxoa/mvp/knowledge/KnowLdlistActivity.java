package com.dangelo.xxoa.mvp.knowledge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.adapter.KledgePsonRAdapter;
import com.dangelo.xxoa.bean.Rqtknowledge;
import com.dangelo.xxoa.bean.knowledgeCategories;
import com.dangelo.xxoa.bean.knowledgePersonal;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.MBaseActivity;
import com.dangelo.xxoa.mvp.senddetail.SendDetailActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.ui.MyDecoration;
import com.dangelo.xxoa.ui.RvLinearLayoutManager;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class KnowLdlistActivity extends MBaseActivity implements KnowLdlistContact.View {

    private final String TAG = KnowLdlistActivity.class.getSimpleName();
    private KnowLdlistPresenter mKnowLdlistPresenter;
    private knowledgeCategories mknowledgeCategories;
    private int type =0;

    private RecyclerView listView;
    private List<knowledgePersonal> datas = new ArrayList<knowledgePersonal>();
    private KledgePsonRAdapter mAdAdapter;
    private LinearLayout noActivityLl;
    private RelativeLayout hasActivityRl;
    private boolean isEnd;
    private int type_temp;
    Rqtknowledge mRqtknowledge;
    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, KnowLdlistActivity.class);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }
    @Override
    public void refreshData(List<knowledgePersonal> data) {
        datas.clear();
        JLog.i(TAG, "data size :" + data.size());
        for (int i = 0; i < data.size(); i++) {
            JLog.i(TAG, "data.get(i).getUpFileCategoryName():" + data.get(i).getUpFileCategoryName());
            if (TextUtils.isEmpty(data.get(i).getUpFileCategoryName())) {
            } else {
                datas.add(data.get(i));
            }
        }
        if (datas.size() == 0) {
            noActivityLl.setVisibility(View.VISIBLE);
            hasActivityRl.setVisibility(View.GONE);
        } else {
            noActivityLl.setVisibility(View.GONE);
            hasActivityRl.setVisibility(View.VISIBLE);
//            if(mAdAdapter!=null){
////                mAdAdapter.resetRecordDatas(datas);
//            }else {

            setAdapter(datas);
//            }
        }

    }
    private void setAdapter(final List<knowledgePersonal> data) {
//        JLog.i(TAG,"ShortCut.isShowAdList"+ShortCut.isShowAdList);
        mAdAdapter = new KledgePsonRAdapter(this, data, false,false);

        //初始化 开始加载更多的loading View
        mAdAdapter.setLoadingView(R.layout.load_loading_layout);
        //加载失败，更新footer view提示
        mAdAdapter.setLoadFailedView(R.layout.load_failed_layout);
        //加载完成，更新footer view提示b
        mAdAdapter.setLoadEndView(R.layout.load_end_layout);

        if (data.size() < 50 || data.size() == 0) {
            isEnd = true;
            mAdAdapter.removeFooterView();
        }

        //设置加载更多触发的事件监听
        mAdAdapter.setOnLoadMoreListener(new KledgePsonRAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (!isEnd) {
                    mKnowLdlistPresenter.GetDate(type_temp,mRqtknowledge);
                } else {
                    //加载完成，更新footer view提示
                    mAdAdapter.setLoadEndView(R.layout.load_end_layout);
                    mAdAdapter.loadEnd();
                }
            }
        });
        mAdAdapter.setOnItemClickListener(new KledgePsonRAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                JLog.i(TAG,"onItemClick positon :"+postion);
                Bundle bundle=new Bundle();
                bundle.putSerializable("knowledgePersonal",data.get(postion));
                bundle.putString("KnowledgeCategory",mknowledgeCategories.getKnowledgeCategory());
                KonwDetailActivity.toThisActivity(KnowLdlistActivity.this,bundle);
            }
        });
        mAdAdapter.setOnItemDeleteClickListener(new KledgePsonRAdapter.MyItemDeleteClickListener() {
            @Override
            public void onItemClick(View view, final RecyclerView.ViewHolder holder, final int postion) {


//                new AlertDialog.Builder(getActivity())
//                        .setMessage(getResources().getString(R.string.clean_sysmsg_hint))
//                        .setNegativeButton(getResources().getString(R.string.clean_records_cancle), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ((SwipeMenuLayout) (holder.itemView)).quickClose();
//                            }
//                        }).setPositiveButton(getResources().getString(R.string.clean_records_confirm), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ((SwipeMenuLayout) (holder.itemView)).quickClose();
//                        mSysmsgPresenter.deteleRecord(postion);
//                    }
//                })
//                        .setTitle("提示")
//                        .show();
            }
        });


//        rvData.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setAdapter(mAdAdapter);
    }
    public void refreshData() {
        JLog.i(TAG, "refreshData-type_temp" + type_temp);
        if (mKnowLdlistPresenter != null&&type_temp!=0) {
            mKnowLdlistPresenter.GetDate(type_temp,mRqtknowledge);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onCreate");
        mknowledgeCategories = (knowledgeCategories) getIntent().getSerializableExtra("knowledgeCategories");
        type = getIntent().getIntExtra("type",0);
        if (mknowledgeCategories != null) {

            JLog.d(TAG, "knowledgePersonal:" + mknowledgeCategories.toString());
        }
        mKnowLdlistPresenter.start();
        mAppBarTitle.setText(mknowledgeCategories.getKnowledgeCategory());
        mRqtknowledge = new Rqtknowledge();
        mRqtknowledge.setKnowledgeType(mknowledgeCategories.getKnowledgeType());
        mRqtknowledge.setKnowledgeCategory(mknowledgeCategories.getKnowledgeCategory());
        mRqtknowledge.setKnowledgeCategoryId(mknowledgeCategories.getId());
        mKnowLdlistPresenter.GetDate(1,mRqtknowledge);
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
        return R.layout.mvp_activity_knowldlist;
    }

    @Override
    protected void initView() {
        noActivityLl = (LinearLayout) findViewById(R.id.no_activity_ll);
        hasActivityRl = (RelativeLayout) findViewById(R.id.has_activity_rl);
        listView = (RecyclerView) findViewById(R.id.knowld_list);
        listView.setLayoutManager(new RvLinearLayoutManager(this));
        listView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

    }



    @Override
    protected BasePresenter createPresenter() {
        mKnowLdlistPresenter = new KnowLdlistPresenter(this);

        return mKnowLdlistPresenter;
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
        super.setupAppBar();
        mAppBarTitle.setText("");
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
    protected void onDestroy() {
        super.onDestroy();
        JLog.d(TAG, "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + TAG + " onDestroy");
    }
}
