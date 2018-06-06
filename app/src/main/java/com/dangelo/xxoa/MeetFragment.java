package com.dangelo.xxoa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dangelo.xxoa.adapter.MeetingListAdapter;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.Topics;
import com.dangelo.xxoa.bean.meetinfo;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.model.MonArtcles;
import com.dangelo.xxoa.model.UserMonSchedule;
import com.dangelo.xxoa.model.contents;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.ksoap2.SoapFault;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nutc
 * @ClassName: CalendarFragment
 * @Description: 显示习惯的签到情况，以日历的形式呈现
 * @date 2013-8-9 下午3:34:24
 */
// 需要提供habitid 和 who！！！！
public class MeetFragment extends Fragment {

    public static final String TAG = MeetFragment.class.getSimpleName();
    public static final int CONTENT_DP_PADDING = 12;// 屏幕两边留出的宽度
    // 用于判断手势
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public List<MonArtcles> monArtcles = null;
    GestureDetector mGesture = null;
    View mainview;
    List<contents> contentsList = new ArrayList<contents>();
    List<UserMonSchedule> userMonSchedule = null;
    private ViewFlipper viewFlipper;
    private SoapFault weather;
    private Context mContext;
    private ArrayList<Integer> checkList = new ArrayList<Integer>();
    //	private List<school> childList;
    private String[] text = {"中餐", "西餐", "小吃快餐", "火锅海鲜", "甜点饮料"};
    private String month = "2014-08";// 月份日程[可选]
    private int page = -1;// [可选]
    private String pagesize = null;// 分页大小[可选]
    private String uid = "8";
    private String area = "";
    private RelativeLayout nolistlayout;
    private PullToRefreshListView mPullToRefreshListView;
    private MeetingListAdapter mAdapter;

    private TextView MeetAddBtn;

    private List<Topics> wsTopics = new ArrayList<>();
    private List<meetinfo> meetlists = new ArrayList<>();
    private List<meetinfo> meetlistsTemp = new ArrayList<>();
    private int pageNo = 1;
    private String isInform = "true";
    boolean onHiddenChanged = false;//

    public static MeetFragment newInstance(int sectionNumber) {
        MeetFragment fragment = new MeetFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
        Log.d("Main", "newInstance:" + sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainview = inflater.inflate(R.layout.layout_new, container, false);
        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.new_nolist_layout);
        MeetAddBtn = (TextView) mainview
                .findViewById(R.id.meet_add_btn);
        MeetAddBtn.setTextColor(getResources().getColor(R.color.white));
        // 获得屏幕宽和高,减去两边留出的padding值,并计算出算出屏幕宽度度分七等份的大小

//		 mainoutLayout = (LinearLayout) mainview
//				.findViewById(R.id.new_layout);
        MeetAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddMeetActivity.class);
//
                startActivity(i);


            }
        });
        MeetAddBtn.setVisibility(View.GONE);
        mPullToRefreshListView = (PullToRefreshListView) mainview
				.findViewById(R.id.new_list);


//        、View> refreshView) {
//
//                if (refreshView.isHeaderShown()){
//                    Log.i(TAG, "下拉刷新");
//                    //下拉刷新 业务代码
//                }else {
//                    Log.i(TAG, "上拉加载更多");
//                    //上拉加载更多 业务代码
//                }
//
//            }
//        });
        pageNo = 1;
        meetlists.clear();
        LoadMeetall();
        return mainview;
    }

    public void onResume() {
        super.onResume();
        // right_btn.setVisibility(View.GONE);

        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.new_nolist_layout);
//		if (ShortCut.IsScheduleVisable(area)) {
        nolistlayout.setVisibility(View.GONE);
        if(!onHiddenChanged){
//            pageNo = 1;
//            meetlists.clear();
//            LoadMeetall();
        }

//		}
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden);
        onHiddenChanged = hidden;
        if (!hidden) {
//            LoadmeetingBasis();
//            LoadlistAllTopic();

        }
    }

	private void buildMonArticlesList() {
		// TODO Auto-generated method stub
        Log.i(TAG,"meetlists"+meetlists.size());
        //初始化控件
        ListView mListView = mPullToRefreshListView.getRefreshableView();
//        mListView.setAdapter(adapter);
        mAdapter = new MeetingListAdapter(mContext, meetlists);
        mListView.setAdapter(mAdapter);

        //设置pull-to-refresh模式为Mode.Both
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        //设置上拉下拉事件
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                meetlists.clear();
                LoadMeetall();
                Log.i(TAG, "下拉刷新");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.i(TAG, "上拉加载更多");

                pageNo++;
                LoadMeetall();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
                Log.i(TAG, "position"+position);
				ShortCut.Meetinfo = meetlists.get(position-1);
				Intent mIntent = new Intent();
				mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mIntent.setClass(mContext, MeetDetailActivity.class);
				mContext.startActivity(mIntent);
			}
		});

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


    // 获取每月文章
    private void LoadData() {
//


    }

    //
    private void LoadMeetall() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        json.setPageNo(pageNo+"");
        json.setUuEName(ShortCut.getUser(getActivity()).getUserEnglishName());
        json.setIsInform(isInform);
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_MEETALLLIST, jsonStr, "")
                , new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                meetlistsTemp = result.getMeetlists();
                for( meetinfo meet:meetlistsTemp){
                    meetlists.add(meet);
                }
                buildMonArticlesList();
                mAdapter.notifyDataSetChanged();
                Log.d(TAG, "result=" + meetlists.size()+"MeetName:" + meetlists.get(0).getMeetName()+"getId:" + meetlists.get(0).getId());
                mPullToRefreshListView.onRefreshComplete();

            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
                mPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                mPullToRefreshListView.onRefreshComplete();
            }
        });



    }
    //



}
