package com.dangelo.xxoa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dangelo.xxoa.adapter.TopicListAdapter;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.Topics;
import com.dangelo.xxoa.bean.meetinfo;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MeetDetailActivity extends BaseActivity {
    public static final String TAG = MeetDetailActivity.class.getSimpleName();
    private TextView meet_title_tv;
    private TextView meet_site_tv;
    private TextView meet_type_tv;
    private TextView meet_num_tv;
    private TextView meet_meetdate_tv;
    private TextView meet_enddate_tv;
    private TextView meet_attendone_tv;
    private TextView meet_charge_tv;
    private TextView meet_contacts_tv;
    private TextView meet_phone_tv;
    private TextView meet_status_tv;

    private meetinfo meetlists;
    private TopicListAdapter mAdapter;
    private List<Topics> wsTopics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetdetail);
        initToolbar("会议详情");
//        getData();
        initViews();

        initOnClick();
    }


    private void initViews() {


        meet_title_tv = (TextView) findViewById(R.id.meet_title_tv);
        meet_site_tv = (TextView) findViewById(R.id.meet_site_tv);
        meet_type_tv = (TextView) findViewById(R.id.meet_type_tv);
        meet_num_tv = (TextView) findViewById(R.id.meet_num_tv);
        meet_meetdate_tv = (TextView) findViewById(R.id.meet_meetdate_tv);
        meet_enddate_tv = (TextView) findViewById(R.id.meet_enddate_tv);
        meet_attendone_tv = (TextView) findViewById(R.id.meet_attendone_tv);
        meet_charge_tv = (TextView) findViewById(R.id.meet_charge_tv);
        meet_contacts_tv = (TextView) findViewById(R.id.meet_contacts_tv);
        meet_phone_tv = (TextView) findViewById(R.id.meet_phone_tv);
        meet_title_tv = (TextView) findViewById(R.id.meet_title_tv);
        meet_status_tv = (TextView) findViewById(R.id.meet_status_tv);
        if (ShortCut.Meetinfo != null) {
            meetlists = ShortCut.Meetinfo;
            LoadlistAllTopic();
            meet_title_tv.setText(meetlists.getMeetName());
            meet_site_tv.setText(meetlists.getMeetSite());
            meet_type_tv.setText(meetlists.getMeetType());
            meet_num_tv.setText(meetlists.getNum()+"次");
            meet_meetdate_tv.setText(meetlists.getMeetDate());
            meet_enddate_tv.setText(meetlists.getEndDate());
            meet_attendone_tv.setText(meetlists.getAttendDept());
            meet_charge_tv.setText(meetlists.getCharge());
            meet_contacts_tv.setText(meetlists.getContacts());
            meet_phone_tv.setText(meetlists.getPhone());
            int status = 0;
            String statusStr = "";
            if(meetlists.getStatus()!=null&&!meetlists.getStatus().equals("")){
                status = Integer.parseInt(meetlists.getStatus());
                //1 新建、2 通知(审核)、3 安排(审核完成)、4 纪要、5 完成、9 取消
                switch (status){
                    case 1:
                        statusStr = "新建";
                        break;
                    case 2:
                        statusStr = "审核";
                        break;
                    case 3:
                        statusStr = "安排";
                        break;
                    case 4:
                        statusStr = "纪要";
                        break;
                    case 5:
                        statusStr = "完成";
                        break;
                    case 9:
                        statusStr = "取消";
                        break;
                    default:
                        statusStr = "默认";
                        break;

                }
            }

            meet_status_tv.setText(statusStr);
        }


    }

    private void initOnClick() {

    }

    private void buildMonArticlesList() {
        // TODO Auto-generated method stub
        ListView keyListView = (ListView) findViewById(R.id.meet_topic_liset);

        mAdapter = new TopicListAdapter(this, wsTopics);
        keyListView.setAdapter(mAdapter);

        keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListViewHeightBasedOnChildren(keyListView);


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
private void LoadlistAllTopic() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
    Json json = new Json();
    if (ShortCut.getUser(this) == null) {
        ShortCut.showToast("请登录！", this);
        return;
    }
    json.setUuEName(ShortCut.getUser(this).getUserEnglishName());
    json.setMeetID(meetlists.getId());
    Gson gson = new Gson();
    String jsonstr = gson.toJson(json);
    NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(API.Method_LISTALLTOPIC, jsonstr, "")
            , new MyJsonHttpResponseHandler() {
        @Override
        public void resultSuccess(Json result) throws JSONException {
            super.resultSuccess(result);
            wsTopics = result.getWsTopics();


            Log.d(TAG, "result=" + "MeetID:" + wsTopics.get(0).getMeetID() + "TopicName:" + wsTopics.get(0)
                    .getTopicName() + "AttendDept:" + wsTopics.get(0).getAttendDept() + "ReportDept:" + wsTopics
                    .get(0).getReportDept());
            buildMonArticlesList();

        }

        @Override
        public void resultFailure(Json result) throws JSONException {
            super.resultFailure(result);
        }
    });


}

}
