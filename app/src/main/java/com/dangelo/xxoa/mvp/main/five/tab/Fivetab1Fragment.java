package com.dangelo.xxoa.mvp.main.five.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.adapter.KledgeCaRAdapter;
import com.dangelo.xxoa.bean.knowledgeCategories;
import com.dangelo.xxoa.mvp.base.BaseFragment;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.knowledge.KnowLdlistActivity;
import com.dangelo.xxoa.mvp.notice.NoticeDetailActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.ui.MyDecoration;
import com.dangelo.xxoa.ui.RvLinearLayoutManager;
import com.dangelo.xxoa.ui.SwipeMenuLayout;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luozhi on 2018/3/12.
 */

public class Fivetab1Fragment extends BaseFragment implements FivetabContact.View {

    private FivetabPresenter fivetabPresenter;
    private String TAG = Fivetab1Fragment.class.getSimpleName();
    private RecyclerView recyclerView;

    private RecyclerView listView;
    private List<knowledgeCategories> datas = new ArrayList<knowledgeCategories>();
    private KledgeCaRAdapter mAdAdapter;
    private LinearLayout noActivityLl;
    private RelativeLayout hasActivityRl;
    private boolean isEnd;
    public final static int TYPE_SELF=1;//个人
    public final static int TYPE_KE=2;//科室
    public final static int TYPE_JU=3;//局

    private int type_temp;


    public boolean swipState() {
        SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
        if (null != viewCache) {
            viewCache.smoothClose();
            return true;
        }
        return false;
    }


    @Override
    public void showLoading(boolean isShow) {
        if (isShow) {
            MProgressDialog.show(getActivity(), null);
        } else {
            MProgressDialog.cancle();
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_five_tab;
    }

    @Override
    protected BasePresenter createPresenter() {
        fivetabPresenter = new FivetabPresenter(getActivity());
        return fivetabPresenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        noActivityLl = (LinearLayout) view.findViewById(R.id.no_activity_ll);
        hasActivityRl = (RelativeLayout) view.findViewById(R.id.has_activity_rl);
        listView = (RecyclerView) view.findViewById(R.id.sysmsg_list);
        listView.setLayoutManager(new RvLinearLayoutManager(getActivity()));
        listView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fivetabPresenter.setType(TYPE_SELF);
        fivetabPresenter.GetDate(1);
    }

    @Override
    public void refreshData(List<knowledgeCategories> data) {
        datas.clear();
        JLog.i(TAG, "adlist size :" + data.size());
        for (int i = 0; i < data.size(); i++) {
            JLog.i(TAG, "data.get(i).getKnowledgeCategory():" + data.get(i).getKnowledgeCategory());
            if (TextUtils.isEmpty(data.get(i).getKnowledgeCategory())) {
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
//                JLog.i(TAG, "resetRecordDatas");
//                mAdAdapter.resetRecordDatas(datas);
//            }else {

                setAdapter(datas);
//            }
        }

    }

    private void setAdapter(final List<knowledgeCategories> data) {
//        JLog.i(TAG,"ShortCut.isShowAdList"+ShortCut.isShowAdList);
        mAdAdapter = new KledgeCaRAdapter(getActivity(), data, true,false);

        //初始化 开始加载更多的loading View
        mAdAdapter.setLoadingView(R.layout.load_loading_layout);
        //加载失败，更新footer view提示
        mAdAdapter.setLoadFailedView(R.layout.load_failed_layout);
        //加载完成，更新footer view提示b
        mAdAdapter.setLoadEndView(R.layout.load_end_layout);

        /**
         *  判断是否数据加载完，这里是限制50条加载一次
         *  实际可能需要判断其他值
         */
        if (data.size() < 50 || data.size() == 0) {
            isEnd = true;
            mAdAdapter.removeFooterView();
        }

        //设置加载更多触发的事件监听
        mAdAdapter.setOnLoadMoreListener(new KledgeCaRAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (!isEnd) {
                    fivetabPresenter.setType(TYPE_SELF);
                    fivetabPresenter.GetDate(type_temp);
                } else {
                    //加载完成，更新footer view提示
                    mAdAdapter.setLoadEndView(R.layout.load_end_layout);
                    mAdAdapter.loadEnd();
                }
            }
        });
        mAdAdapter.setOnItemClickListener(new KledgeCaRAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                JLog.i(TAG,"onItemClick positon :"+postion);
                Bundle bundle=new Bundle();
                bundle.putSerializable("knowledgeCategories",data.get(postion));
                bundle.putInt("type",TYPE_SELF);
                KnowLdlistActivity.toThisActivity(getContext(),bundle);
            }
        });
        mAdAdapter.setOnItemDeleteClickListener(new KledgeCaRAdapter.MyItemDeleteClickListener() {
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        JLog.d(TAG,"* * * * * * * * * * * * * * * * * * *"+TAG+"onPause");
    }



    public void refreshData() {
        JLog.i(TAG, "refreshData-type_temp" + type_temp);
        if (fivetabPresenter != null&&type_temp!=0) {
            fivetabPresenter.setType(TYPE_SELF);
            fivetabPresenter.GetDate(type_temp);
        }
    }

    /**
     *
     * @param type
     */
    public void Setvalue(int type) {
        type_temp=type;
        JLog.i(TAG, "Setvalue:type" + type);

        if (fivetabPresenter != null) {
            fivetabPresenter.setType(type);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden);
        if (!hidden) {


//			LogOutOnClick();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        JLog.d(TAG,"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"+TAG+"onDestroy");
    }
}
