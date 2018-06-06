package com.dangelo.xxoa.mvp.main.frist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangelo.xxoa.MainActivity;
import com.dangelo.xxoa.R;
import com.dangelo.xxoa.adapter.NoticeRecyclerAdapter;
import com.dangelo.xxoa.bean.noticeInfo;
import com.dangelo.xxoa.mvp.base.BaseFragment;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.notice.NoticeDetailActivity;
import com.dangelo.xxoa.ui.RvLinearLayoutManager;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nutc
 * @ClassName: CalendarFragment
 * @Description: 显示习惯的签到情况，以日历的形式呈现
 * @date 2013-8-9 下午3:34:24
 */
// 需要提供habitid 和 who！！！！
public class FristFragment extends BaseFragment implements FristContact.FristView, View.OnClickListener {

    public static final String TAG = FristFragment.class.getSimpleName();

    private Context mContext;


    private RelativeLayout nolistlayout;
    private RelativeLayout send_unfinished_layout;
    private RelativeLayout receive_unfinished_layout;
    private RelativeLayout meet_unfinished_layout;
    private boolean isEnd;
    private int type_temp;
    private RelativeLayout hasActivityRl;

//    private ListView newsListView;

    private RecyclerView listView;
    private List<noticeInfo> datas = new ArrayList<noticeInfo>();
    private NoticeRecyclerAdapter mAdAdapter;

    private FristPresenter mPresenter;
    private Submit submit;
    private TextView send_unfinished_num_tv;
    private TextView receive_unfinished_num_tv;
    private TextView meet_unfinished_num_tv;

    public static FristFragment newInstance(int sectionNumber) {
        FristFragment fragment = new FristFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
        Log.d("Main", "newInstance:" + sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        Log.i(TAG, "onCreate");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.GetDate(1);
        mPresenter.GetUaMessageNum();

    }

    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        // right_btn.setVisibility(View.GONE);


        if (nolistlayout != null) {
            nolistlayout.setVisibility(View.GONE);
        }
//        LoadData();


//		}
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden);
        if (!hidden) {


//			LogOutOnClick();
        }
    }
//    @Override
//    public void buildBroeseList(List<Broese> broesenews) {
//        // TODO Auto-generated method stub
//
//        Log.i(TAG, "buildBroeseList" + " broesenews:" + broesenews.size());
//
//        newsmAdapter.resetDatas(broesenews);
//        if(broesenews.size()==0){
//            new_text.setVisibility(View.GONE);
//        }else{
//            new_text.setVisibility(View.VISIBLE);
//        }
//        setListViewHeightBasedOnChildren(newsListView);
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected int bindLayout() {
        return R.layout.layout_frist;
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new FristPresenter(getActivity());
        mPresenter.start();
        return mPresenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        nolistlayout = (RelativeLayout) view
                .findViewById(R.id.frist_nolist_layout);
        send_unfinished_layout = (RelativeLayout) view.findViewById(R.id.send_unfinished_layout);
        receive_unfinished_layout = (RelativeLayout) view.findViewById(R.id.receive_unfinished_layout);
        meet_unfinished_layout = (RelativeLayout) view.findViewById(R.id.meet_unfinished_layout);
        hasActivityRl = (RelativeLayout) view.findViewById(R.id.has_activity_rl);
        send_unfinished_num_tv = (TextView) view.findViewById(R.id.send_unfinished_num_tv);
        receive_unfinished_num_tv = (TextView) view.findViewById(R.id.receive_unfinished_num_tv);
        meet_unfinished_num_tv = (TextView) view.findViewById(R.id.meet_unfinished_num_tv);

        send_unfinished_layout.setOnClickListener(this);
        receive_unfinished_layout.setOnClickListener(this);
        meet_unfinished_layout.setOnClickListener(this);
        // 获得屏幕宽和高,减去两边留出的padding值,并计算出算出屏幕宽度度分七等份的大小

//        newsListView = (ListView) view
//                .findViewById(R.id.frist_news_list);
        listView = (RecyclerView) view.findViewById(R.id.notice_list);
        listView.setLayoutManager(new RvLinearLayoutManager(getActivity()));
//        listView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));


    }
//
//	private void TestLoaddate() {
//
//		childList = new ArrayList<school>();
//		for (int j = 0; j < text[j].length(); j++) {
//			school school = new school();
//			String subIndex = String.valueOf(j + 1);
//			String subString = subIndex.length() == 1 ? "0" + subIndex
//					: subIndex;
//			school.setBmIntro((j + 1) + subString);
//			school.setSchoolName(text[j]);
//			childList.add(school);
//		}
//	}

    @Override
    public void refreshData(List<noticeInfo> data) {
        datas.clear();
        JLog.i(TAG, "adlist size :" + data.size());
        for (int i = 0; i < data.size(); i++) {
            JLog.i(TAG, "data.get(i).getAd_id():" + data.get(i).getNotice());
            if (TextUtils.isEmpty(data.get(i).getNotice())) {
            } else {
                datas.add(data.get(i));
            }
        }
        if (datas.size() == 0) {
        } else {
            hasActivityRl.setVisibility(View.VISIBLE);
//            if(mAdAdapter!=null){
//                JLog.i(TAG, "resetRecordDatas");
//                mAdAdapter.resetRecordDatas(datas);
//            }else {

            setAdapter(datas);
//            }
        }

    }

    private void setAdapter(final List<noticeInfo> data) {
//        JLog.i(TAG,"ShortCut.isShowAdList"+ShortCut.isShowAdList);
        mAdAdapter = new NoticeRecyclerAdapter(getActivity(), data, true, false);

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
        mAdAdapter.setOnLoadMoreListener(new NoticeRecyclerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (!isEnd) {
                    mPresenter.GetDate(type_temp);
                } else {
                    //加载完成，更新footer view提示
                    mAdAdapter.setLoadEndView(R.layout.load_end_layout);
                    mAdAdapter.loadEnd();
                }
            }
        });
        mAdAdapter.setOnItemClickListener(new NoticeRecyclerAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                JLog.i(TAG, "onItemClick positon :" + postion);
                Bundle bundle = new Bundle();
                bundle.putSerializable("noticeInfo", data.get(postion));
                NoticeDetailActivity.toThisActivity(getContext(), bundle);
            }
        });
        mAdAdapter.setOnItemDeleteClickListener(new NoticeRecyclerAdapter.MyItemDeleteClickListener() {
            @Override
            public void onItemClick(View view, final RecyclerView.ViewHolder holder, final int postion) {


//                new AlertDialog.Builder(getActivity())
//                        .setMessage(getResources().getString(R.string.clean_sysmsg_hint))
//                        .setNegativeButton(getResources().getString(R.string.clean_records_cancle), new
// DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ((SwipeMenuLayout) (holder.itemView)).quickClose();
//                            }
//                        }).setPositiveButton(getResources().getString(R.string.clean_records_confirm), new
// DialogInterface.OnClickListener() {
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
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }


    @Override
    public void showErrinfo(boolean isshow, String errinfo) {

    }

    @Override
    public void showFailReason(int error) {

    }

    @Override
    public void showProgressDialog(boolean isShow, String content) {

    }

    @Override
    public void setNumber(String sendnumber, String receivenumber, String meetnumber) {
        if (sendnumber.equals("0")) {
            send_unfinished_num_tv.setVisibility(View.GONE);
        } else {
            send_unfinished_num_tv.setVisibility(View.VISIBLE);
            send_unfinished_num_tv.setText(sendnumber);
        }

        if (receivenumber.equals("0")) {
            receive_unfinished_num_tv.setVisibility(View.GONE);
        } else {
            receive_unfinished_num_tv.setVisibility(View.VISIBLE);
            receive_unfinished_num_tv.setText(receivenumber);
        }
        if (meetnumber.equals("0")) {
            meet_unfinished_num_tv.setVisibility(View.GONE);
        } else {
            meet_unfinished_num_tv.setVisibility(View.VISIBLE);
            meet_unfinished_num_tv.setText(meetnumber);
        }

    }

    @Override
    public void onClick(View v) {
        submit = (Submit) getActivity();
        switch (v.getId()) {
            case R.id.send_unfinished_layout:
                submit.submit(1);
                break;
            case R.id.receive_unfinished_layout:
                submit.submit(2);
                break;
            case R.id.meet_unfinished_layout:
                submit.submit(3);
                break;
            default:
                break;
        }

    }

    public interface Submit {
        /**
         * value 1;2;3
         *
         * @param comStype 1 跳转发文 2跳转收文 3会议通知
         */
        public void submit(int comStype);
    }
}
