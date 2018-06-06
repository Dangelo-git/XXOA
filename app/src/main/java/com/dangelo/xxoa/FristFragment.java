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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dangelo.xxoa.adapter.BroeseListAdapter;
import com.dangelo.xxoa.bean.Broese;
import com.dangelo.xxoa.bean.FileInfo;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.model.MonArtcles;
import com.dangelo.xxoa.model.UserMonSchedule;
import com.dangelo.xxoa.model.contents;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyDownLoader;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

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
public class FristFragment extends Fragment {

    public static final String TAG = FristFragment.class.getSimpleName();
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
    private BroeseListAdapter newsmAdapter;
    private String[] text = {"中餐", "西餐", "小吃快餐", "火锅海鲜", "甜点饮料"};
    private String month = "2014-08";// 月份日程[可选]
    private int page = -1;// [可选]
    private String pagesize = null;// 分页大小[可选]
    private String uid = "8";
    private String area = "";
    private RelativeLayout nolistlayout;
    private TextView title;
    private List<Broese> broesenews = new ArrayList<>();
    private TextView new_text;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainview = inflater.inflate(R.layout.layout_frist, container, false);
        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.frist_nolist_layout);
        // 获得屏幕宽和高,减去两边留出的padding值,并计算出算出屏幕宽度度分七等份的大小

//		 mainoutLayout = (LinearLayout) mainview
//				.findViewById(R.id.frist_layout);
//        LoadData();
         new_text = (TextView) mainview.findViewById(R.id.new_text);
        new_text.setTextColor(getResources().getColor(R.color.activity_text_blue));
        if (SharedPrefUtil.getAudoLogin(getContext())) {

            LogInOnClick();
        }
        LoadBrowseNews();
        return mainview;
    }

    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        // right_btn.setVisibility(View.GONE);

        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.frist_nolist_layout);
//		if (ShortCut.IsScheduleVisable(area)) {
        nolistlayout.setVisibility(View.GONE);
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

    private void buildBroeseList() {
        // TODO Auto-generated method stub

        Log.i(TAG, "buildBroeseList" + " broesenews:" + broesenews.size());
        ListView newsListView = (ListView) mainview
                .findViewById(R.id.frist_news_list);

        newsmAdapter = new BroeseListAdapter(mContext);
        newsListView.setAdapter(newsmAdapter);
        if(broesenews.size()==0){
            new_text.setVisibility(View.GONE);
        }else{
            new_text.setVisibility(View.VISIBLE);
        }
        newsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListViewHeightBasedOnChildren(newsListView);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                ShortCut.broese = broesenews.get(arg2);
                Intent i = new Intent(getActivity(), BroeseActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
//				getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            }
        });


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
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
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    // 获取每月文章
    private void LoadData() {
        final String requesl = "http://txt.bxwxtxt.com/packdown/fulltxt/107/107656.txt?46";
        FileInfo info = new FileInfo();
        info.setFileName("107656t");
        info.setFiletype("txt");
        info.setFileUrl(requesl);
        MyDownLoader dl = new MyDownLoader();
        dl.downFile(info, getActivity());


    }

    //	通
    private void LogInOnClick() {

//        Json json = new Json();
//        json = getSubscribeJson();
//        Log.d(TAG, json.toString())

        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
//        Json json = new Json();
//        json.setUuEName(ShortCut.getUser(getActivity()).getUuEName());
//        json.setUuPassword(ShortCut.getUser(getActivity()).getPassword());
//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(json);
//        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_LOGIN, jsonStr,
//                ShortCut.getUser(getActivity()).getUuKeyID())
//                , new MyJsonHttpResponseHandler() {
//            @Override
//            public void resultSuccess(Json result) throws JSONException {
//                super.resultSuccess(result);
//
//
//                user user = result.getUser();
//                Log.d(TAG, "result=" + "uId:" + user.getuId() + " UuCName:" + user.getUuCName() + " UuEName:" + user
//                        .getUuEName() + " UuKeyID:" + user.getUuKeyID());
//                DataShare share = new DataShare(getActivity());
//                share.write("uId", user.getuId() + "");
//                share.write("UuCName", user.getUuCName());
//                share.write("UuEName", user.getUuEName());
//                share.write("UuKeyID", user.getUuKeyID());
////				Log.d(TAG, "result=" + "uId:"+user.getuId()+" UuCName:"+user.getUuCName()+" UuEName:"+user.getUuEName
//// ()+" UuKeyID:"+user.getUuKeyID());
////                ShortCut.showToast("登录成功！", getActivity());
////                LoadBrowseNews();
//            }
//
//            @Override
//            public void resultFailure(Json result) throws JSONException {
//                super.resultFailure(result);
//                ShortCut.showToast("登录失败！", getActivity());
//                if (result.getCode() != null && (!result.getCode().equals(""))) {
//                    ShortCut.setUserclear();
//                    SharedPrefUtil.setAudoLogin(false, mContext);
//                    Intent mIntent = new Intent();
//                    mIntent.setClass(mContext, WelActivity.class);
//                    startActivity(mIntent);
//                    getActivity().finish();
//                }
//
//
//            }
//
//            @Override
//            public void finallyDo() {
//                super.finallyDo();
//
//            }
//        });


    }

    private void LoadBrowseNews() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask1(getActivity(), SOAPStringB.AssembleSoapRequest1(API.JSON_BROWSENEWS)
                , new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                broesenews = result.getBroese();
//                if (Testswith.Testbroese) {
//
//                    Gson gson = new Gson();
//                    Json json = gson.fromJson(API.RSPONSE_BROWSENEWS, Json.class);
//                    broesenews = json.getBroese();
//                }


//                Log.d(TAG, "result=" + "broesenews Title:" + broesenews.get(0).getTitle() + "Content:" + broesenews.get(0)
//                        .getContent());

                buildBroeseList();
            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                if (Testswith.Testbroese) {

                    Gson gson = new Gson();
                    Json json = gson.fromJson(API.JSON_SCHEDULALL, Json.class);
                    broesenews = json.getBroese();
                    buildBroeseList();
                }
//
            }

        });

    }

}
