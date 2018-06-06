package com.dangelo.xxoa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.dangelo.xxoa.adapter.DocumentListAdapter;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.docinfo;
import com.dangelo.xxoa.bean.schedule;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

// 需要提供habitid 和 who！！！！
public class DocumentFragment extends Fragment {

    public static final String TAG = DocumentFragment.class.getSimpleName();
    View mainview;
    //    List<contents> contentsList = new ArrayList<contents>();
    private int ShowRange = 1;
    private int docStatus = 1;
    private Context mContext;
    private DocumentListAdapter mAdapter;
    private RelativeLayout nolistlayout;
    private RadioButton imgbtn1;
    private RadioButton imgbtn2;
    private RadioButton imgbtn3;
    private RadioButton imgbtn4;
    private RadioButton imgbtn5;
    private RadioGroup clientRadioGroup;
    private List<docinfo> InfoList = new ArrayList<docinfo>();

    public static DocumentFragment newInstance(int sectionNumber) {
        DocumentFragment fragment = new DocumentFragment();
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

        mainview = inflater.inflate(R.layout.layout_four, container, false);
        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.four_nolist_layout);

        // 获得屏幕宽和高,减去两边留出的padding值,并计算出算出屏幕宽度度分七等份的大小

//		 mainoutLayout = (LinearLayout) mainview
//				.findViewById(R.id.four_layout);
        clientRadioGroup = (RadioGroup) mainview.findViewById(R.id.main_layout);
        clientRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
//                RadioButton rb = (RadioButton) mainview.findViewById(radioButtonId);
//                String radioButtonLabel = rb.getText().toString();
                switch (radioButtonId) {
                    case R.id.imgbtn1:
                        ShowRange = 1;
                        docStatus = 1;
                        break;
                    case R.id.imgbtn2:
                        ShowRange = 2;
                        docStatus = 2;
                        break;
                    case R.id.imgbtn3:
                        ShowRange = 0;
                        docStatus = 2;
                        break;
                    case R.id.imgbtn4:
                        ShowRange = 0;
                        docStatus = 3;
                        break;
                    case R.id.imgbtn5:
                        ShowRange = 0;
                        docStatus = 4;
                        break;
                }
                Log.i(TAG, "onCheckedChanged ShowRange " + ShowRange + " docStatus " + docStatus);
                infoListRsq();
            }
        });
//
        return mainview;
    }

    public void onResume() {
        super.onResume();
        // right_btn.setVisibility(View.GONE);

        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.four_nolist_layout);
//		if (ShortCut.IsScheduleVisable(area)) {
        nolistlayout.setVisibility(View.GONE);
//        LoadData();


//		}
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden+"ShortCut.docunmentflag:"+ShortCut.docunmentflag);
        if (!hidden) {
        if(ShortCut.docunmentflag){
            ShowRange = 2;
            docStatus = 2;
            RadioButton rb  = (RadioButton)mainview.findViewById(R.id.imgbtn2);
            rb.setChecked(true);
            Log.i(TAG,"RadioButton");
//            infoListRsq();
        }else{
            infoListRsq();

        }
//			scheduleUpdateRsq();
//			scheduleAllRsq();
        }

    }

    private void buildList() {
        // TODO Auto-generated method stub
        ListView keyListView = (ListView) mainview
                .findViewById(R.id.four_list);

        mAdapter = new DocumentListAdapter(mContext, InfoList);
        keyListView.setAdapter(mAdapter);

        keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        keyListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                ShortCut.Docinfo = InfoList.get(arg2);
                Intent mIntent = new Intent();
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.setClass(mContext, DocCumentDetailActivity.class);
                mContext.startActivity(mIntent);
//               String docid = InfoList.get(arg2).getDocId();
//                downloadRsq(docid);

            }
        });

    }


    // 获取公文数据
    private void LoadData() {
//


    }

    //通
    private void infoListRsq() {
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
//
        Json json = new Json();
        json.setUuEName(ShortCut.getUser(getActivity()).getUserEnglishName());
        json.setShowRange(ShowRange + "");
        json.setDocStatus(docStatus + "");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);

        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_INFOLIST, jsonStr,
                ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                Log.d(TAG, "result" + result);
                Gson gson = new Gson();
//                Json json = gson.fromJson(API.RSPONSE_INFOLIST, Json.class);
                Json json = result;
                InfoList.clear();
                InfoList = json.getInfoList();
                buildList();
                Log.d(TAG, "InfoList:" + InfoList.size());

            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                if(Testswith.TestinfoList){
                    Gson gson = new Gson();
                    Json json = gson.fromJson(API.JSON_SCHEDULALL, Json.class);
                    InfoList.clear();
                    InfoList = json.getInfoList();
                    buildList();
                }
            }
        });

    }



    //通
    private void scheduleAllRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULALL, API
                .JSON_SCHEDULALL, ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                List<schedule> ScheduleList = result.getSchedules();
                Log.d(TAG, "result" + ScheduleList.size() + ScheduleList.get(0).getTopic());


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }
        });
    }


    //	通
    private void scheduleAddRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULADD, API
                .JSON_SCHEDULADD, ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
//						Json json1 = new Json();
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
                Log.d(TAG, "result" + result);
//						Gson gson = new Gson();
//						Json json = gson.fromJson(result.toString(), Json.class);
//
//						Log.d(TAG, json.toString());


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }
        });
    }

    //	通
    private void scheduleUpdateRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULUPDATE, API
                .JSON_SCHEDULUPDATE, ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
//			Json json1 = new Json();
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
                Log.d(TAG, "result" + result);
//						Gson gson = new Gson();
//						Json json = gson.fromJson(result.toString(), Json.class);
//
//						Log.d(TAG, json.toString());


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }
        });
    }


}
