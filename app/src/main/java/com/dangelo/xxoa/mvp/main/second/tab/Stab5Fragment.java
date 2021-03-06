package com.dangelo.xxoa.mvp.main.second.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.adapter.DispDocRecyclerAdapter;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.mvp.base.BaseFragment;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.senddetail.SendDetailActivity;
import com.dangelo.xxoa.ui.MProgressDialog;
import com.dangelo.xxoa.ui.MyDecoration;
import com.dangelo.xxoa.ui.RvLinearLayoutManager;
import com.dangelo.xxoa.ui.SwipeMenuLayout;
import com.dangelo.xxoa.uitl.ShortCut;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luozhi on 2018/3/12.
 */

public class Stab5Fragment extends BaseFragment implements StabContact.View {

    private StabPresenter stabPresenter;
    private String TAG = Stab5Fragment.class.getSimpleName();
    private RecyclerView recyclerView;

    private RecyclerView listView;
    private List<dispatchDoc> datas = new ArrayList<dispatchDoc>();
    private DispDocRecyclerAdapter mAdAdapter;
    private LinearLayout noActivityLl;
    private RelativeLayout hasActivityRl;
    private boolean isEnd;
    public final static int TYPE_WAIT=1;//待办
    public final static int TYPE_UNFINISH=2;//未完成
    public final static int TYPE_FINISH=3;//已完成
    public final static int TYPE_DRAFT=4;//草稿
    public final static int TYPE_STOP=5;//停办
    private int page;


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
        return R.layout.fragment_second_tab;
    }

    @Override
    protected BasePresenter createPresenter() {
        stabPresenter = new StabPresenter(getActivity());
        return stabPresenter;
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
        stabPresenter.setType(TYPE_STOP);
        page=1;
        stabPresenter.GetDate(page);
    }

    @Override
    public void refreshData(List<dispatchDoc> data) {
        datas.clear();
        JLog.i(TAG, "adlist size :" + data.size());
        for (int i = 0; i < data.size(); i++) {
            JLog.i(TAG, "data.get(i).getAd_id():" + data.get(i).getDocTitle());
            if (TextUtils.isEmpty(data.get(i).getDocTitle())) {
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

    private void setAdapter(final List<dispatchDoc> data) {
//        JLog.i(TAG,"ShortCut.isShowAdList"+ShortCut.isShowAdList);
        mAdAdapter = new DispDocRecyclerAdapter(getActivity(), data, false,false);

        //初始化 开始加载更多的loading View
        mAdAdapter.setLoadingView(R.layout.load_loading_layout);
        //加载失败，更新footer view提示
        mAdAdapter.setLoadFailedView(R.layout.load_failed_layout);
        //加载完成，更新footer view提示b
        mAdAdapter.setLoadEndView(R.layout.load_end_layout);

        if (data.size() < ShortCut.Page || data.size() == 0) {
            isEnd = true;
            mAdAdapter.removeFooterView();
        }

        //设置加载更多触发的事件监听
        mAdAdapter.setOnLoadMoreListener(new DispDocRecyclerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (!isEnd) {
                    stabPresenter.setType(TYPE_STOP);
                    page++;
                    stabPresenter.GetDate(page);
                } else {
                    //加载完成，更新footer view提示
                    mAdAdapter.setLoadEndView(R.layout.load_end_layout);
                    mAdAdapter.loadEnd();
                }
            }
        });
        mAdAdapter.setOnItemClickListener(new DispDocRecyclerAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                JLog.i(TAG,"onItemClick positon :"+postion);
                Bundle bundle=new Bundle();
                bundle.putSerializable("dispatchDoc",data.get(postion));
                SendDetailActivity.toThisActivity(getContext(),bundle);
            }
        });
        mAdAdapter.setOnItemDeleteClickListener(new DispDocRecyclerAdapter.MyItemDeleteClickListener() {
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
        JLog.i(TAG, "refreshData-type_temp" + page);
        if (stabPresenter != null&&page!=0) {
            stabPresenter.setType(TYPE_STOP);
            page=1;
            stabPresenter.GetDate(page);
        }
    }

    /**
     *
     * @param type
     */
    public void Setvalue(int type) {
        JLog.i(TAG, "Setvalue:type" + type);

        if (stabPresenter != null) {
            stabPresenter.setType(type);
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
