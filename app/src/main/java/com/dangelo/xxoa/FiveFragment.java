package com.dangelo.xxoa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dangelo.xxoa.adapter.ScheduleListAdapter;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.model.Config;
import com.dangelo.xxoa.model.MonArtcles;
import com.dangelo.xxoa.model.UserMonSchedule;
import com.dangelo.xxoa.model.contents;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.DbUtil;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;

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
public class FiveFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = FiveFragment.class.getSimpleName();
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
    private ScheduleListAdapter mAdapter;
    private String[] text = {"中餐", "西餐", "小吃快餐", "火锅海鲜", "甜点饮料"};
    private String month = "2014-08";// 月份日程[可选]
    private int page = -1;// [可选]
    private String pagesize = null;// 分页大小[可选]
    private String uid = "8";
    private String area = "";
    //    private RelativeLayout nolistlayout;
    private RelativeLayout SettingPushLayout;
    private RelativeLayout SettingCleanLayout;
    private RelativeLayout SettingDownloadLayout;
    private RelativeLayout SettingUpdatepwdLayout;
    private RelativeLayout SettingCheckupdateLayout;
    private TextView SettingName;
    private TextView SettingText;
    private TextView settingLogoutBtn;
    private TextView clean;
    private TextView update;
    private TextView downnumTV;
    private int downNum;
    private int TYPE = 0;
    private Config config = null;
    private boolean UpdateFlag = false;

    public static FiveFragment newInstance(int sectionNumber) {
        FiveFragment fragment = new FiveFragment();
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

        mainview = inflater.inflate(R.layout.layout_five, container, false);
        SettingName = (TextView) mainview.findViewById(R.id.setting_name);
        SettingText = (TextView) mainview.findViewById(R.id.setting_text);
        clean = (TextView) mainview.findViewById(R.id.setting_clean);
        update = (TextView) mainview.findViewById(R.id.setting_check_update);
        downnumTV = (TextView) mainview.findViewById(R.id.setting_download_num);

        SettingPushLayout = (RelativeLayout) mainview.findViewById(R.id.setting_push_layout);
        SettingPushLayout.setOnClickListener(this);
        SettingCleanLayout = (RelativeLayout) mainview.findViewById(R.id.setting_clean_layout);
        SettingCleanLayout.setOnClickListener(this);
        SettingDownloadLayout = (RelativeLayout) mainview.findViewById(R.id.setting_download_layout);
        SettingDownloadLayout.setOnClickListener(this);
        SettingUpdatepwdLayout = (RelativeLayout) mainview.findViewById(R.id.setting_updatepwd_layout);
        SettingUpdatepwdLayout.setOnClickListener(this);
        SettingCheckupdateLayout = (RelativeLayout) mainview.findViewById(R.id.setting_check_update_layout);
        SettingCheckupdateLayout.setOnClickListener(this);
        settingLogoutBtn = (TextView) mainview.findViewById(R.id.setting_logout_btn);
        settingLogoutBtn.setTextColor(getResources().getColor(R.color.white));
        SettingName.setTextColor(getResources().getColor(R.color.white));
        SettingText.setTextColor(getResources().getColor(R.color.white));
        settingLogoutBtn.setOnClickListener(this);

        return mainview;
    }

    private void LoadData() {
        SettingName.setText(ShortCut.getUser(getActivity()).getUserEnglishName());
    }

    public void onResume() {
        super.onResume();
        // right_btn.setVisibility(View.GONE);

//        nolistlayout = (RelativeLayout) mainview
//                .findViewById(R.id.five_nolist_layout);
////		if (ShortCut.IsScheduleVisable(area)) {
//        nolistlayout.setVisibility(View.GONE);


//		}
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden);
        if (!hidden) {
            LoadData();
            setValue();
            LoadFilenum();

        }
    }

//	private void buildMonArticlesList() {
//		// TODO Auto-generated method stub
//		ListView keyListView = (ListView) mainview
//				.findViewById(R.id.five_list);
//
//		mAdapter = new ScheduleListAdapter(mContext, contentsList);
//		keyListView.setAdapter(mAdapter);
//
//		keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		keyListView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				ShortCut.monArtcles = contentsList.get(arg2);
//				Intent mIntent = new Intent();
//				mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				mIntent.setClass(mContext, ScheduleDetailActivity.class);
//				mContext.startActivity(mIntent);
//				getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
//			}
//		});
//
//	}
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


    // 获取下载文件数
    private void LoadFilenum() {
        FileService fileutils = new FileService();
        List<String> fileList = new ArrayList<>();
        fileList = fileutils.getDownloadFileList(getActivity());
        Log.i(TAG, "文件数"+fileList.size() + "");
        downNum = fileList.size();
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                if(fileList.get(i).toString().equals("File")){
                    downNum = downNum-1;
                }
                Log.i(TAG, "文件名"+fileList.get(i).toString());
            }
        }

        downnumTV.setText(downNum+ "");

    }

    //通
    private void LogOutOnClick() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_LOGOUT, API.JSON_LOGOUT,
                ""), new MyJsonHttpResponseHandler() {
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

            @Override
            public void finallyDo() {
                super.finallyDo();

            }
        });
        ShortCut.setUserclear();
        SharedPrefUtil.setAudoLogin(false, mContext);
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, LoginActivity.class);

        startActivity(mIntent);
        getActivity().finish();


    }

    private void scheduleDelRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULDELETE, API
                .JSON_SCHEDULDELETE,""), new MyJsonHttpResponseHandler() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_push_layout:
                break;
            case R.id.setting_clean_layout:
                alertclean();
                break;
            case R.id.setting_download_layout:
                if (downNum > 0) {
                    Intent mIntent = new Intent();
                    mIntent.setClass(mContext, DocListActivity.class);

                    startActivity(mIntent);
                }
                break;
            case R.id.setting_updatepwd_layout:
                break;
            case R.id.setting_logout_btn:
                LogOutOnClick();
                break;
            case R.id.setting_check_update_layout:
                if (UpdateFlag) {
                    alertdownload();
                }
                break;
            default:
                break;
        }

    }

    private void alertclean() {
        TYPE = 1;
        showDialogWithTwoBtn("提示", "您确定要清空缓存？");
    }

    private void alertdownload() {
        TYPE = 2;
        showDialogWithTwoBtn("提示", "您有新的版本，需要更新么？");
    }

    protected void showDialogWithTwoBtn(String title, String msg) {
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        doPositive();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void doPositive() {
        // TODO Auto-generated method stub
        if (TYPE == 1) {
            FileService.deleteCache2(mContext);
            DbUtil.getInstance(getActivity()).CleanFileInfo();
            setValue();
            LoadFilenum();
        } else {
            doDownLoad();
        }

    }

    private void doDownLoad() {
        getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri
                .parse(config.getAndroidvdurl())));


    }

    private void setValue() {
        //显示保存文件夹的大小
        if (config != null) {
//			mailbox.setText(config.getKefuEmail());
        }
        String FolderSize = null;
        Long Size = FileService.getCacheFolderSize(mContext);
        Log.i(TAG, "CacheFolderSize:" + Size);
        if (Size > 1048 && 1048576 > Size) {
            FolderSize = Size / 1048 + "KB";
        } else if (Size > 1048576) {
            FolderSize = Size / 1048576 + "M";
        } else {
            FolderSize = Size + "B";
        }
        if (clean != null) {
            clean.setText(FolderSize);
        }

        //检查版本更新
        if (GetVersionCode() != -1 && config != null && !config.getAndroidv().equals("")) {
            int locversion = GetVersionCode();
            Log.i(TAG, "config.getAndroidv()" + config.getAndroidv());
            if (Integer.parseInt(config.getAndroidv()) > locversion) {
                update.setText("新版本：" + config.getAndroidv());
                UpdateFlag = true;
            }
        }

    }

    private int GetVersionCode() {
        PackageManager pm = mContext.getPackageManager();
        // 得到系统安装的所有程序包的PackageInfo对象
        // List<ApplicationInfo> packs = pm.getInstalledApplications(0);
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        Log.i(TAG, "本地读取的所有应用个数:" + packs.size());
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo pi = new PackageInfo();
            pi = packs.get(i);
            // Log.i(TAG, "packageInfo:" +
            // packageInfo.applicationInfo.packageName);
            if (pi.applicationInfo.packageName.equals("com.dangelo.xxoa")) {

                return pi.versionCode;
            }
        }
        return -1;

    }


}
