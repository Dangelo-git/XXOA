package com.dangelo.xxoa;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dangelo.xxoa.adapter.ScheduleListAdapter;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.schedule;
import com.dangelo.xxoa.calendar.CalendarGridView;
import com.dangelo.xxoa.calendar.CalendarGridViewAdapter;
import com.dangelo.xxoa.calendar.Festival;
import com.dangelo.xxoa.calendar.LunarCalendar;
import com.dangelo.xxoa.calendar.NumberHelper;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.model.Lunar;
import com.dangelo.xxoa.model.MonArtcles;
import com.dangelo.xxoa.model.UserMonSchedule;
import com.dangelo.xxoa.model.contents;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

import org.json.JSONException;
import org.ksoap2.SoapFault;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author nutc
 * @ClassName: CalendarFragment
 * @Description: 显示习惯的签到情况，以日历的形式呈现
 * @date 2013-8-9 下午3:34:24
 */
// 需要提供habitid 和 who！！！！
public class CalendarFragment extends Fragment implements OnTouchListener {

    public static final String TAG = CalendarFragment.class.getSimpleName();
    public static final int CONTENT_DP_PADDING = 12;// 屏幕两边留出的宽度
    // 用于判断手势
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int mainLayoutID = R.id.mainLayoutid; // 设置主布局ID
    private static final int titleLayoutID = R.id.titleLayoutid; // title布局ID
    private static final int titleBelowLineID = R.id.titlebelowlineid; // title布局ID
    private static final int caltitleLayoutID = R.id.caltitleLayoutid; // title布局ID
    private static final int calLayoutID = R.id.calLayoutid; // 日历布局ID
    public List<MonArtcles> monArtcles = null;
    GestureDetector mGesture = null;
    boolean bIsSelection = false;// 是否是选择事件发生
    boolean onHiddenChanged = false;//
    /**
     * 底部菜单文字
     **/
    String[] menu_toolbar_name_array;
    View mainview;
    List<contents> contentsList = new ArrayList<contents>();
    List<UserMonSchedule> userMonSchedule = null;
    List<schedule> ScheduleList = new ArrayList<schedule>();
    List<schedule> ScheduleDayList = new ArrayList<schedule>();
    // 动画
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private Animation slideUpIn;
    private Animation slideUpOut;
    private Animation slideDownIn;
    private Animation slideDownOut;
    private ViewFlipper viewFlipper;
    private SoapFault weather;
    // 基本变量
    private GridView title_gView;
    private GridView gView1;// 上一个月
    private GridView gView2;// 当前月
    private GridView gView3;// 下一个月
    private Calendar calStartDate = Calendar.getInstance();// 当前显示的日历
    private Calendar calSelected = Calendar.getInstance(); // 选择的日历
    private Calendar calToday = Calendar.getInstance(); // 今日
    private CalendarGridViewAdapter gAdapter;
    private CalendarGridViewAdapter gAdapter1;
    private CalendarGridViewAdapter gAdapter3;
    // 顶部按钮
    private TextView btnToday = null;
    private TextView btnNowday = null;
    private TextView btnLunarday = null;
    private RelativeLayout mainLayout;
    private LinearLayout mainoutLayout;
    private int iMonthViewCurrentMonth = 0; // 当前视图月
    private int iMonthViewCurrentYear = 0; // 当前视图年
    private int iFirstDayOfWeek = Calendar.MONDAY;
    private Context mContext;
    private String ScheduleDELID;
    private int[] checkList = new int[40000];

    Date Currentday;
    AnimationListener animationListener = new AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 当动画完成后调用
            CreateGirdView();
        }
    };
    private ScheduleListAdapter mAdapter;
    private String month = "2014-08";// 月份日程[可选]
    private RelativeLayout nolistlayout;

    public static CalendarFragment newInstance(int sectionNumber) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
        Log.d("Main", "newInstance:" + sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    // 获取手势action;
    public boolean onTouch(View v, MotionEvent event) {
        return mGesture.onTouchEvent(event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainview = inflater.inflate(R.layout.layout_calendar, container, false);
        nolistlayout = (RelativeLayout) mainview
                .findViewById(R.id.calender_nolist_layout);
        // 获得屏幕宽和高,减去两边留出的padding值,并计算出算出屏幕宽度度分七等份的大小

        mainoutLayout = (LinearLayout) mainview
                .findViewById(R.id.main_layout);


//		初始化时间
        calStartDate = Calendar.getInstance();// 当前显示的日历
        calStartDate = getCalendarStartDate();
        final int iYear = calStartDate.get(Calendar.YEAR);
        final int iMonth = calStartDate.get(Calendar.MONTH);
        int date = iYear * 10000 + (iMonth + 1) * 100;
        String date2 = date + "";
        date2 = date2.substring(0, 4) + "-" + date2.substring(4, 6);
        Log.i(TAG, "ToDayViewItem!!date:" + date2);
        month = date2;
//		if (ShortCut.IsScheduleVisable(area)) {
//			nolistlayout.setVisibility(View.GONE);
//			LoadData();
//			InitCalenderView();
//		} else {
//			nolistlayout.setVisibility(View.VISIBLE);
//		}


        // 声明控件，并绑定事件
//		 Testloaddata();
//		 TestLoaddate();
        // 获取当前时间

        return mainview;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onHiddenChanged = hidden;
        Log.i(TAG, "onHiddenChanged" + hidden);
        if (!hidden) {
//			scheduleAddRsq();

            scheduleAllRsq();
            InitCalenderView();
        }
    }

    private void InitCalenderView() {


        // LoadMonScheduleData() ;
        // buildMonArticlesList();
        mainoutLayout.removeAllViews();
        mainoutLayout.addView(generateContentView());
        UpdateStartDateForMonth();

        // 添加Animation实现不同动画效果
        slideLeftIn = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_left_in);
        slideLeftOut = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_left_out);
        slideRightIn = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_right_in);
        slideRightOut = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_right_out);
        slideUpIn = AnimationUtils.loadAnimation(mContext, R.anim.slide_up_in);
        slideUpOut = AnimationUtils
                .loadAnimation(mContext, R.anim.slide_up_out);
        slideDownIn = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_down_in);
        slideDownOut = AnimationUtils.loadAnimation(mContext,
                R.anim.slide_down_out);

        slideLeftIn.setAnimationListener(animationListener);
        slideLeftOut.setAnimationListener(animationListener);
        slideRightIn.setAnimationListener(animationListener);
        slideRightOut.setAnimationListener(animationListener);
        slideUpIn.setAnimationListener(animationListener);
        slideUpOut.setAnimationListener(animationListener);
        slideDownIn.setAnimationListener(animationListener);
        slideDownOut.setAnimationListener(animationListener);

        mGesture = new GestureDetector(mContext, new GestureListener());
    }

    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume  +onHiddenChanged"+ onHiddenChanged);
        // right_btn.setVisibility(View.GONE);

//		if (ShortCut.IsScheduleVisable(area)) {
        nolistlayout.setVisibility(View.GONE);
        if(!onHiddenChanged){
            scheduleAllRsq();
        }
        InitCalenderView();
        if(Currentday!=null){

            LoadDaydate(Currentday);
        }
//		getTelAddress();
//		} else {
//		mainoutLayout.removeAllViews();
//		nolistlayout.setVisibility(View.VISIBLE);


        //web服务端天气预报url

        //调用web提供的方法

//		new AsyncTask<Object ,Object ,Object >() {
//			@Override
//			protected Object doInBackground(Object[] params) {
////				http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
//				String wsdlUrl= "http://10.54.40.50/cxfservices/cloudTeminalServiceFacade";
//				SoapObject weather=searchWea(wsdlUrl,"execute","xtgly");
////				soapmth();
//				return weather;
//			}
//
//			@Override
//			protected void onPostExecute(Object o) {
//				super.onPostExecute(o);
//				if(weather!=null){
//					weather= (SoapFault) o;
//					Log.i("weather", weather.toString());
////					String state=weather.getProperty(10).toString();
////
////
////					System.out.println(state);
////					String strIcon=weather.getProperty(15).toString();
//				}else{
//					Log.i("weather", "weather==null");
//				}
//
//
////				handlerAddress.sendEmptyMessage(0);
//			}
//		}.execute();

//		initOnClick();
//		}
    }

    // private void Testloaddata() {
    // Calendar calCalendar = Calendar.getInstance();
    // final int iYear = calCalendar.get(Calendar.YEAR);
    // final int iMonth = calCalendar.get(Calendar.MONTH);
    // final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);
    // int date = iYear * 10000 + (iMonth + 1) * 100 + iDay;
    // for (int i = 1; i < 10; i++) {
    // checkList.add(date + i);
    // i++;
    // }
    //
    // }

    private void buildMonArticlesList() {
        // TODO Auto-generated method stub
        ListView keyListView = (ListView) mainview
                .findViewById(R.id.schedule_list);

        mAdapter = new ScheduleListAdapter(mContext, ScheduleDayList);
        keyListView.setAdapter(mAdapter);

        keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        keyListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                ShortCut.Schedule = ScheduleDayList.get(arg2);
                Intent mIntent = new Intent();
                mIntent.setClass(mContext, CalenderDetailActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(mIntent);
            }
        });
        keyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleDELID = ScheduleDayList.get(position).getId();
                showDialogWithTwoBtn("提示","确定删除这条日程么？");
                return true;
            }
        });

    }

    // 生成内容视图
    private View generateContentView() {
        // 创建一个垂直的线性布局（整体内容）
        viewFlipper = new ViewFlipper(mContext);
        // viewFlipper.setId(calLayoutID);
        // 创建一个垂直的线性布局（整体内容）
        mainLayout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams params_main = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mainLayout.setLayoutParams(params_main);
        mainLayout.setId(mainLayoutID);
        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        // 生成顶部按钮布局
        RelativeLayout layTopControls = createLayout(LinearLayout.HORIZONTAL);
        // layTopControls.setBackgroundResource(R.color.red);
        // 生成顶部按钮 （上一月，下一月，当前月）
        generateTopButtons3(layTopControls);
        generateTopButtons(layTopControls);
        generateTopButtons2(layTopControls);

        RelativeLayout.LayoutParams params_title = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params_title.topMargin = 0;
        layTopControls.setBackgroundResource(R.color.white);
        layTopControls.setId(titleLayoutID);
        layTopControls.setPadding(0, 5, 0, 0);
        mainLayout.addView(layTopControls, params_title);

        // 横线颜色
        RelativeLayout layTopControlline = createLayout(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params_titleline = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, 2);
        layTopControlline
                .setBackgroundResource(R.color.schadule_calender_titlelinecolor);
        params_titleline.addRule(RelativeLayout.BELOW, titleLayoutID);
        layTopControlline.setId(titleBelowLineID);
        mainLayout.addView(layTopControlline, params_titleline);

        calStartDate = getCalendarStartDate();

        setTitleGirdView();

        RelativeLayout.LayoutParams params_cal_title = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params_cal_title.addRule(RelativeLayout.BELOW, titleBelowLineID);

        mainLayout.addView(title_gView, params_cal_title);

        CreateGirdView();

        RelativeLayout.LayoutParams params_cal = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params_cal.addRule(RelativeLayout.BELOW, caltitleLayoutID);

        mainLayout.addView(viewFlipper, params_cal);

        LinearLayout br = new LinearLayout(mContext);
        RelativeLayout.LayoutParams params_br = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, 1);
        params_br.addRule(RelativeLayout.BELOW, calLayoutID);
        // 设置背景色
        br.setBackgroundColor(getResources().getColor(R.color.no_color));
        mainLayout.addView(br, params_br);

        return mainLayout;

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

    // 创建一个线性布局
    // 参数：方向
    private RelativeLayout createLayout(int iOrientation) {
        RelativeLayout lay = new RelativeLayout(mContext);
        LayoutParams params = new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,// *fill_parent，填满父控件的空白
                10);
        params.topMargin = 1;
        // 设置布局参数
        // *wrap_content，表示大小刚好足够显示当前控件里的内容
        lay.setLayoutParams(params);
        // 设置方向
        // lay.setOrientation(iOrientation);
        // lay.setGravity(Gravity.CENTER_VERTICAL);
        // lay.setBackgroundColor(getResources().getColor(R.color.grey));
        return lay;
    }

    // 生成顶部按钮
    // 参数：布局
    private void generateTopButtons(RelativeLayout layTopControls) {
        // 创建一个当前月按钮（中间的按钮）
        btnToday = new TextView(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        // lp.leftMargin = 20;
        // btnToday.setLayoutParams(lp);
        // lp.addRule(RelativeLayout.ALIGN_PARENT_);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        btnToday.setTextSize(getResources().getDimension(R.dimen.calender_text_month));
        btnToday.setTextColor(getResources().getColor(R.color.activity_title_bg));
        btnToday.setBackgroundResource(R.color.no_color);//

        // 设置当前月按钮的背景颜色为按钮默认颜色

        // 当前月的点击事件的监听
        btnToday.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                setToDayViewItem();
            }
        });

        // layTopControls.setGravity(Gravity.CENTER_HORIZONTAL);
        layTopControls.addView(btnToday, lp);

    }

    // 生成顶部按钮 今天
    // 参数：布局
    private void generateTopButtons2(RelativeLayout layTopControls) {
        // 创建一个当前月按钮（中间的按钮）
        btnNowday = new TextView(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 20;
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        // btn1 位于父 View 的顶部，在父 View 中水平居中
        // btnNowday.setLayoutParams(lp);
        btnNowday.setTextSize(getResources().getDimension(R.dimen.calender_text_today));
        btnNowday.setText("今天");
        btnNowday.setTextColor(getResources().getColor(
                R.color.activity_title_bg));
        btnNowday.setBackgroundResource(R.color.no_color);//

        // 设置当前月按钮的背景颜色为按钮默认颜色

        // 当前月的点击事件的监听
        btnNowday.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                // calSelected = Calendar.getInstance();
                //
                iMonthViewCurrentMonth = 0; // 当前视图月
                iMonthViewCurrentYear = 0; // 当前视图年
                calStartDate = Calendar.getInstance();// 当前显示的日历
                calSelected = Calendar.getInstance(); // 选择的日历
                calToday = Calendar.getInstance(); // 今日
                calStartDate = getCalendarStartDate();
                CreateGirdView();
                UpdateStartDateForMonth();
                final int iYear = calStartDate.get(Calendar.YEAR);
                final int iMonth = calStartDate.get(Calendar.MONTH);
                int date = iYear * 10000 + (iMonth + 1) * 100;
                String date2 = date + "";
                date2 = date2.substring(0, 4) + "-" + date2.substring(4, 6);
                Log.i(TAG, "ToDayViewItem!!date:" + date2);
                month = date2;
                // LoadMonScheduleData() ;

            }
        });

        // layTopControls.setGravity(Gravity.RIGHT);
        // layTopControls.set
        // rl.addView(btn1, lp1 );
        layTopControls.addView(btnNowday, lp);

    }

    // 生成顶部按钮 今天
    // 参数：布局
    private void generateTopButtons3(RelativeLayout layTopControls) {
        // 创建一个当前月按钮（中间的按钮）
        btnLunarday = new TextView(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lp.leftMargin = 20;
        // btnLunarday.setLayoutParams(lp);
        btnLunarday.setTextSize(getResources().getDimension(R.dimen.calender_text_today));
        btnLunarday.setTextColor(getResources().getColor(
                R.color.activity_text_item));
        btnLunarday.setBackgroundResource(R.color.no_color);

        // 设置当前月按钮的背景颜色为按钮默认颜色

        // 当前月的点击事件的监听

        // layTopControls.setGravity(Gravity.LEFT);
        layTopControls.addView(btnLunarday, lp);

    }

    private void setTitleGirdView() {

        title_gView = setGirdView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        title_gView.setLayoutParams(params);
        title_gView.setVerticalSpacing(0);// 垂直间隔
        title_gView.setHorizontalSpacing(0);// 水平间隔
        TitleGridAdapter titleAdapter = new TitleGridAdapter(getActivity());
        WindowManager windowManager = (getActivity()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int gridHieght = display.getHeight() / 20;
        titleAdapter.setGridHeight(gridHieght);
        title_gView.setAdapter(titleAdapter);// 设置菜单Adapter
        title_gView.setId(caltitleLayoutID);
    }

    private void CreateGirdView() {

        Calendar tempSelected1 = Calendar.getInstance(); // 临时
        Calendar tempSelected2 = Calendar.getInstance(); // 临时
        Calendar tempSelected3 = Calendar.getInstance(); // 临时
        tempSelected1.setTime(calStartDate.getTime());
        tempSelected2.setTime(calStartDate.getTime());
        tempSelected3.setTime(calStartDate.getTime());

        WindowManager windowManager = (getActivity()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int gridHieght = display.getHeight() / 17;

        gView1 = new CalendarGridView(mContext);
        tempSelected1.add(Calendar.MONTH, -1);
        gAdapter1 = new CalendarGridViewAdapter(getActivity(), tempSelected1,
                checkList);
        gAdapter1.setGridHeight(gridHieght);
        gView1.setAdapter(gAdapter1);// 设置菜单Adapter
        gView1.setId(calLayoutID);

        gView2 = new CalendarGridView(mContext);
        gAdapter = new CalendarGridViewAdapter(getActivity(), tempSelected2,
                checkList);
        gAdapter.setGridHeight(gridHieght);
        gView2.setAdapter(gAdapter);// 设置菜单Adapter
        gView2.setId(calLayoutID);

        gView3 = new CalendarGridView(mContext);
        tempSelected3.add(Calendar.MONTH, 1);
        gAdapter3 = new CalendarGridViewAdapter(getActivity(), tempSelected3,
                checkList);
        gAdapter3.setGridHeight(gridHieght);
        gView3.setAdapter(gAdapter3);// 设置菜单Adapter
        gView3.setId(calLayoutID);

        gView2.setOnTouchListener(this);
        gView1.setOnTouchListener(this);
        gView3.setOnTouchListener(this);

        if (viewFlipper.getChildCount() != 0) {
            viewFlipper.removeAllViews();
        }

        viewFlipper.addView(gView2);
        viewFlipper.addView(gView3);
        viewFlipper.addView(gView1);

        String s = calStartDate.get(Calendar.YEAR)
                + "-"
                + NumberHelper.LeftPad_Tow_Zero(calStartDate
                .get(Calendar.MONTH) + 1);

        btnToday.setText(s);
        String str = new Festival().getPermanentFestivalName(calStartDate);
        if (str != null) {
            // txtLunarDay.setText(str);
        } else {
            str = LunarCalendar.getLauarTail(calStartDate);
        }
        btnLunarday.setText(str);
    }

    private GridView setGirdView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        GridView gridView = new GridView(mContext);
        gridView.setLayoutParams(params);
        gridView.setNumColumns(7);// 设置每行列数
        gridView.setGravity(Gravity.CENTER_VERTICAL);// 位置居中
        gridView.setVerticalSpacing(1);// 垂直间隔
        gridView.setHorizontalSpacing(1);// 水平间隔
        gridView.setBackgroundColor(getResources().getColor(R.color.no_color));// 设置背景
        // 设置显示参数
        WindowManager windowManager = (getActivity()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int i = display.getWidth() / 7;
        int j = display.getWidth() - (i * 7);
        int x = j / 2;
        gridView.setPadding(x, 0, 0, 0);// 居中

        return gridView;
    }

    // 上一个月
    private void setPrevViewItem() {
        iMonthViewCurrentMonth--;// 当前选择月--
        // 如果当前月为负数的话显示上一年
        if (iMonthViewCurrentMonth == -1) {
            iMonthViewCurrentMonth = 11;
            iMonthViewCurrentYear--;
        }
        calStartDate.set(Calendar.DAY_OF_MONTH, 1); // 设置日为当月1日
        calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth); // 设置月
        calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear); // 设置年
        int date = iMonthViewCurrentYear * 10000 + (iMonthViewCurrentMonth + 1)
                * 100;
        String date2 = date + "";
        date2 = date2.substring(0, 4) + "-" + date2.substring(4, 6);
        Log.i(TAG, "PrevViewItem!!date:" + date2);
        month = date2;
        // LoadMonScheduleData() ;

    }

    // 当月
    private void setToDayViewItem() {
        Log.i(TAG, "setToDayViewItem");
        calSelected.setTimeInMillis(calToday.getTimeInMillis());
        calSelected.setFirstDayOfWeek(iFirstDayOfWeek);
        calStartDate.setTimeInMillis(calToday.getTimeInMillis());
        calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);

    }

    // 下一个月
    private void setNextViewItem() {
        iMonthViewCurrentMonth++;
        if (iMonthViewCurrentMonth == 12) {
            iMonthViewCurrentMonth = 0;
            iMonthViewCurrentYear++;
        }
        calStartDate.set(Calendar.DAY_OF_MONTH, 1);
        calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
        calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
        int date = iMonthViewCurrentYear * 10000 + (iMonthViewCurrentMonth + 1)
                * 100;
        String date2 = date + "";
        date2 = date2.substring(0, 4) + "-" + date2.substring(4, 6);
        Log.i(TAG, "PrevViewItem!!date:" + date2);
        month = date2;
        // LoadMonScheduleData() ;

    }

    // 根据改变的日期更新日历
    // 填充日历控件用
    private void UpdateStartDateForMonth() {
        // 设置成当月第一天
        calStartDate.set(Calendar.DATE, 1);
        // 得到当前日历显示的月
        iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);
        // 得到当前日历显示的年
        iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR);

        String s = calStartDate.get(Calendar.YEAR)
                + "-"
                + NumberHelper.LeftPad_Tow_Zero(calStartDate
                .get(Calendar.MONTH) + 1);
        btnToday.setText(s);

        // 星期一是2 星期天是1 填充剩余天数
        int iDay = 0;
        int iFirstDayOfWeek = Calendar.MONDAY;
        int iStartDay = iFirstDayOfWeek;
        if (iStartDay == Calendar.MONDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            if (iDay < 0)
                iDay = 6;
        }
        if (iStartDay == Calendar.SUNDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            if (iDay < 0)
                iDay = 6;
        }
        calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);

    }

    private Calendar getCalendarStartDate() {
        calToday.setTimeInMillis(System.currentTimeMillis());
        calToday.setFirstDayOfWeek(iFirstDayOfWeek);

        if (calSelected.getTimeInMillis() == 0) {
            calStartDate.setTimeInMillis(System.currentTimeMillis());
            calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
        } else {
            calStartDate.setTimeInMillis(calSelected.getTimeInMillis());
            calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
        }

        return calStartDate;
    }




    // 得到用户收藏日期
    private void GetMonScheduleDate() {


        for (int i = 0; i < ScheduleList.size(); i++) {
            Log.i(TAG, "getStartDate:" + ScheduleList.get(i).getStartDate());
            String date = ScheduleList.get(i).getStartDate().replaceAll("年", "");
            String date1 = date.replaceAll("月", "");
            String date2 = date1.replaceAll("日", "");
            int dateint = Integer.parseInt(date2) - ShortCut.CalendarCheckTime;

            Log.i(TAG, " 保存当前日期!!date: " + date2);
            checkList[dateint] = 1;
        }

    }

    // 得到当天文章列表
    protected void LoadDaydate(Date datetime) {
        ScheduleDayList.clear();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");// 小写的mm表示的是分钟
        String str = sdf.format(datetime);
        for (int i = 0; i < ScheduleList.size(); i++) {
            if (ScheduleList.get(i).getStartDate().equals(str)) {
                ScheduleDayList.add(ScheduleList.get(i));
            }
        }
        Log.i(TAG, "ScheduleDayList:" + ScheduleDayList.size());
        buildMonArticlesList();


    }

    protected void showDialogWithTwoBtn(String title,String msg){
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        scheduleDelRsq();
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
    //通
    private void scheduleDelRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(getActivity()) == null) {
            ShortCut.showToast("请登录！", getActivity());
            return;
        }
        json.setId(ScheduleDELID);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULDELETE, jsonStr, ""), new MyJsonHttpResponseHandler() {
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

                scheduleAllRsq();


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
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
        json.setConstitutor(ShortCut.getUser(getActivity()).getUserEnglishName());
//
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);
        NetEngine.submitPostTask(getActivity(), SOAPStringB.AssembleSoapRequest(API.Method_SCHEDULALL,jsonStr, ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
              ScheduleList = result.getSchedules();
                if(Testswith.TestGetSCHEDULALL){

                    Gson gson = new Gson();
                    Json json = gson.fromJson(API.JSON_SCHEDULALL, Json.class);
                    ScheduleList = json.getSchedules();
                }
                if(ScheduleList.size()>0){

                    Log.d(TAG, "result" + ScheduleList.size() + ScheduleList.get(0).getTopic() + ScheduleList.get(0)
                            .getStartDate());
                    GetMonScheduleDate();
                    gAdapter.notifyDataSetChanged();
                    gAdapter1.notifyDataSetChanged();
                    gAdapter3.notifyDataSetChanged();
                    InitCalenderView();
                    if(Currentday!=null){

                        LoadDaydate(Currentday);
                    }
                }

            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }
        });
    }

    //通
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
                schedule Schedule = result.getScheduleAdd();
//                ShortCut.ScheduleID = Schedule.getId();
                Log.d(TAG, "result" + Schedule.getId() + result);

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

    // SimpleOnGestureListener 是Android SDK提供的一个listener类来侦测各种不同的手势;
    class GestureListener extends SimpleOnGestureListener {
        @Override
        // 在onFling方法中, 判断是不是一个合理的swipe动作;
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    viewFlipper.setInAnimation(slideUpIn);
                    viewFlipper.setOutAnimation(slideUpOut);
                    viewFlipper.showNext();
                    setNextViewItem();

                    return true;

                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 这里的viewFlipper是含有多个view的一个container, 可以很方便的调用prev/next
                    // view;

                    viewFlipper.setInAnimation(slideDownIn);
                    viewFlipper.setOutAnimation(slideDownOut);
                    viewFlipper.showPrevious();
                    setPrevViewItem();

                    return true;
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // ListView lv = getListView();
            // 得到当前选中的是第几个单元格
            int pos = gView2.pointToPosition((int) e.getX(), (int) e.getY());
            RelativeLayout txtDay = (RelativeLayout) gView2
                    .findViewById(pos + 5000);

            // map.put("Date", myDate);
            // map.put("Lunar", str);
            if (txtDay != null) {
                if (txtDay.getTag(R.id.tag_second) != null) {
                    Lunar myLunar = new Lunar();
                    myLunar = (Lunar) txtDay.getTag(R.id.tag_second);

                    Date date = myLunar.getMyDate();
                    calSelected.setTime(date);

                    gAdapter.setSelectedDate(calSelected);
                    gAdapter.notifyDataSetChanged();

                    gAdapter1.setSelectedDate(calSelected);
                    gAdapter1.notifyDataSetChanged();

                    gAdapter3.setSelectedDate(calSelected);
                    gAdapter3.notifyDataSetChanged();
                    String Lunar = myLunar.getLunar();
                    btnLunarday.setText(Lunar);
                    Currentday = date;
                    LoadDaydate(date);
                }

            }

            // Log.i("TEST", "onSingleTapUp -  pos=" + pos);

            return false;
        }
    }
//	/**
//	 * 请求WebService并获得返回的手机号码归属地信息
//	 */
//
//	private static final String nameSpaceAddress = "http://webService.service.sl.xidian/";
//	private    String txtAddress ;
//	private   static final String OUTTYPE = "json" ;
//	private   static final String INTYPE = "json" ;
//	private static final String methodNameAddress = "login";
//	private static final String soapActionAddress = "http://WebXml.com.cn/getMobileCodeInfo";
//	private static final String urlAddress
//			= "http://10.54.40.50/cxfservices/cloudTeminalServiceFacade";
//	public void getTelAddress() {
//		SoapObject soapObject = new
//				SoapObject(nameSpaceAddress, methodNameAddress);//创建SOAP对象
//		//设置属性，这些属性值通过SOAP协议传送给服务器
//		soapObject.addProperty("mobileCode", "15910770570");//要查询的电话号码
//		soapObject.addProperty("userId", "");
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//		envelope.bodyOut = soapObject;
//		envelope.dotNet = true;
//		envelope.setOutputSoapObject(soapObject);
//		HttpTransportSE httpTransportSE = new HttpTransportSE(urlAddress);
//		try {
//			//调用服务
//			httpTransportSE.call(soapActionAddress, envelope);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//获取服务传回的数据，手机归属地信息
//		SoapObject object = (SoapObject) envelope.bodyIn;
//		txtAddress = object.getProperty(0).toString();
    //向主线程发送消息成功，getTelAddress函数执行完毕

//		//调用的方法
//		String methodName = "login";
//		String json = " {'uuEName':'jiangjie','uuPassword':'kk'}";
//		final  String SERVICE_URL = "http://10.54.40.50/cxfservices/cloudTeminalServiceFacade?wsdl";
//		//创建httpTransportSE传输对象
//		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
//		ht.debug = true;
//		//使用soap1.1协议创建Envelop对象
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//		//实例化SoapObject对象
//		SoapObject request = new SoapObject( nameSpaceAddress
//		, methodName);
//		/**
//		 * 设置参数，参数名不一定需要跟调用的服务器端的参数名相同，只需要对应的顺序相同即可
//		 * */
//		request.addProperty("", "methodName");
//		request.addProperty("", json);
//		request.addProperty("", "json");
//		request.addProperty("", "json");
////		request.addProperty("arg5", "1006010054");
//		//将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
//		envelope.bodyOut = request;
//		try{
//			//调用webService
//			ht.call(null, envelope);
//			//txt1.setText("看看"+envelope.getResponse());
//			if(envelope.getResponse() != null){
////				txt2.setText("有返回");
//				txtAddress = "有返回";
//				SoapObject result = (SoapObject) envelope.bodyIn;
//				String name = result.getProperty(0).toString();
////				txt1.setText("返回值 = "+name);
//				txtAddress = "有返回"+name;
//			}else{
////				txt2.setText("无返回");
//				txtAddress = "无返回";
//			}
//		}catch (Exception e) {
//			txtAddress = "异常";
//			e.printStackTrace();
//		}
////		envelope.bodyOut = request;
//		handlerAddress.sendEmptyMessage(0);
//	}
//	Handler handlerAddress = new Handler() {
//		public void handleMessage(Message msg) {
//
//		}
//	};
//
//	public static SoapObject searchWea(String wsdlurl,String
//		method,String cityname) {
//		//指定webservice的命名空间和调用的方法名
//		String namespace="http://terminal.com/";
//		SoapObject  soap=new SoapObject(namespace,method);
////		String json = "{'uuEName':'xtgly','uuPassword':'kk'}";
////		//添加属性，只要设置参数的顺序一致，调用方法的参数名不一定与服务端的WebService类中的方法参数名一致
////		soap.addProperty("","login");
////		soap.addProperty("xml", json);
////		soap.addProperty("out", "json");
////		soap.addProperty("in", "json");
//		soap.addProperty("arg0", "login");   //方法名称
//		soap.addProperty("arg2", "{'uuEName':'xtgly','uuPassword':'kk'}");  //   传入参数
//		soap.addProperty("arg3", "json");
//
//		soap.addProperty("arg4", "json");
//
//		//通过SoapSerializationEnvelope类的构造方法设置SOAP协议的版本号。
//		SoapSerializationEnvelope soapEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//		//设置需要传出的Soap
////		soapEnvelope.bodyOut=soap;
//		soapEnvelope.dotNet=true;
//		soapEnvelope.setOutputSoapObject(soap);
//		//创建http传输对象
//		HttpTransportSE transportSE=new HttpTransportSE(wsdlurl);
//		transportSE.debug = true;
//		//soap操作url
//		String SOAP_ACTION=namespace+method;
//		try {
//			//请求调用WebService方法
//			transportSE.call(SOAP_ACTION, soapEnvelope);
//			//使用getResponse获得WebService方法解析xml的返回结果if (envelope.bodyIn instanceof SoapFault)
//			Log.i("weather","回传的值 ：" + soapEnvelope.getResponse());
//			if (soapEnvelope.bodyIn instanceof SoapFault)
//			{
//				final SoapFault sf = (SoapFault) soapEnvelope.bodyIn;
//				Log.i("weather", "SoapFault---"+sf.toString());
//			}else{
//				SoapObject result= (SoapObject) soapEnvelope.bodyIn;
//				if(result!=null){
//
//					Log.i("weather", "---"+result.toString());
//					return result;
//				}
//				else{
//					Log.i("weather", "result==null");
//				}
//			}
//
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//private void soapmth(){
//	final  String SERVICE_NS = "http://terminal.com/";
//	final  String SERVICE_URL = "http://10.54.40.50/cxfservices/cloudTeminalServiceFacade";
//
//	//调用的方法
//	String methodName = "execute";
//	//创建httpTransportSE传输对象
//	HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
//	ht.debug = true;
//	//使用soap1.1协议创建Envelop对象
//	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//	//实例化SoapObject对象
//	SoapObject request = new SoapObject(SERVICE_NS, methodName);
//	/**
//	 * 设置参数，参数名不一定需要跟调用的服务器端的参数名相同，只需要对应的顺序相同即可
//	 * */
//	//request.addProperty("name", "1006010054");
//	//将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
//	request.addProperty("arg0", "login");   //方法名称
//	request.addProperty("arg2", "{'uuEName':'xtgly','uuPassword':'kk'}");  //   传入参数
//	request.addProperty("arg3", "json");
//
//	request.addProperty("arg4", "json");
//
//	envelope.bodyOut = request;
//	try{
//		//调用webService
//		ht.call(null, envelope);
////		txt2.setText("回传的值 ：" + envelope.getResponse());
//		Log.i("soapmth","回传的值 ：" + envelope.getResponse());
//		if(envelope.getResponse() != null){
//			//处理返回结果
//			SoapObject result = (SoapObject) envelope.bodyIn;
//			SoapObject soapChilds = (SoapObject)result.getProperty(0);
//
////			txt1.setText(soapChilds.toString());
//			Log.i("soapmth", "回传的值 ：" + soapChilds.toString());
//		}else{
////			txt1.setText("无返回");
//			Log.i("soapmth", "无返回 ：" );
//		}
//	}catch (Exception e) {
//		e.printStackTrace();
//	}
//}

    // 自定义adapter
    public class TitleGridAdapter extends BaseAdapter {
        // 将titles存入数组
        int[] titles = new int[]{R.string.Sun, R.string.Mon, R.string.Tue,
                R.string.Wed, R.string.Thu, R.string.Fri, R.string.Sat};

        private Activity activity;
        private int gridHeight = 0;
        // construct
        public TitleGridAdapter(Activity a) {
            activity = a;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        // 设置每个格子填充View的高度
        public void setGridHeight(int height) {
            this.gridHeight = height;
        }

        // 设置每个格子填充View的高度
        public void setGridWidth(int width) {
            this.gridHeight = width;
        }

        // 设置外观
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            Resources res = getResources();
            if (convertView == null) {
                viewHolder = new ViewHolder();
                viewHolder.iv = new LinearLayout(activity);

                viewHolder.txtDay = new TextView(activity);
                viewHolder.txtDay.setFocusable(false);
                viewHolder.txtDay.setBackgroundColor(getResources().getColor(R.color.no_color));
                viewHolder.iv.setOrientation(LinearLayout.HORIZONTAL);
                viewHolder.txtDay.setHeight(this.gridHeight);
                viewHolder.txtDay.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
                viewHolder.txtDay.setTextColor(res
                        .getColor(R.color.waterfall_item_paletextcolor));
                viewHolder.iv.addView(viewHolder.txtDay, lp);
                convertView = viewHolder.iv;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            int i = (Integer) getItem(position);

             if (i == R.string.Sat) {
             // 周六
             viewHolder.txtDay.setTextColor(res
                     .getColor(R.color.activity_title_bg));
             } else
            if (i == R.string.Sun) {
                // 周日
                viewHolder.txtDay.setTextColor(res
                        .getColor(R.color.activity_title_bg));
            } else {
            }
            viewHolder.txtDay.setText((Integer) getItem(position));

            return convertView;
        }
    }

    public class ViewHolder {
        public TextView txtDay;
        public LinearLayout iv;
    }

}
