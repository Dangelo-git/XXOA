package com.dangelo.xxoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangelo.xxoa.bean.DeptInfo;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.MeetBasis;
import com.dangelo.xxoa.bean.Meetattribute;
import com.dangelo.xxoa.bean.Topics;
import com.dangelo.xxoa.bean.meetList;
import com.dangelo.xxoa.bean.typeCounts;
import com.dangelo.xxoa.bean.wsMeetingApply;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.dangelo.xxoa.uitl.TimeManagement;
import com.google.gson.Gson;
import com.jingchen.timerpicker.CalendarBean;
import com.jingchen.timerpicker.PickerView;

import org.angmarch.views.NiceSpinner;
import org.json.JSONException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/6/8.
 */
public class AddMeetActivity extends BaseActivity {
    private static String TAG = AddMeetActivity.class.getName();
    private final int topicId = 200;
    private final int TOPIC_BACKCODE = 1;
    LinearLayout.LayoutParams LP_FW;
    //    PickerView minute_start_pv;
    PickerView hour_start_pv;
    PickerView year_start_pv;
    //    private ArrayAdapter<String> comperes_adapter;
//    private ArrayAdapter<String> deptList_adapter;
    PickerView month_start_pv;
    PickerView day_start_pv;
    //    PickerView minute_end_pv;
    PickerView hour_end_pv;
    PickerView year_end_pv;
    PickerView month_end_pv;
    PickerView day_end_pv;
    List<Integer> daylist = new ArrayList<Integer>();
    List<String> monthlist = new ArrayList<String>();
    List<String> yearlist = new ArrayList<String>();
    List<String> hourlist = new ArrayList<String>();
    List<String> minutelist = new ArrayList<String>();
    TreeMap<String, List<String>> monthdata = new TreeMap<String, List<String>>();
    TreeMap<String, List<String>> daydatatemp = new TreeMap<String, List<String>>();
    TreeMap<String, TreeMap<String, List<String>>> daydata = new TreeMap<String, TreeMap<String, List<String>>>();
    //	String data[][] ;
    int startyear, startmonth, startday, starthour;//获取日
    String start_selectyear = "";
    String start_selectmonth = "";
    String start_selectday = "";
    String start_selecthour = "";
    String end_selectyear = "";
    String end_selectmonth = "";
    String end_selectday = "";
    String end_selecthour = "";

    private String endDate;
    private String startDate;

    private String number;
    private List<String> sites_list = new ArrayList<>();
    //    private ArrayAdapter<String> sites_adapter;
    private List<String> comperes_list = new ArrayList<>();
    private List<String> deptList_list = new ArrayList<>();
    private List<String> types_list = new ArrayList<>();

    private List<Topics> topics_list = new ArrayList<>();

    private MeetBasis wsMeetBasis;
    private meetList meetList;

    private NiceSpinner sites_sp;
    private NiceSpinner comperes_sp;
    private NiceSpinner types_sp;

    private String sites_str;
    private String comperes_str;
    private String types_str;
    //    private RelativeLayout deptList_layout;
//    private ListView addmeetingList;
//    private TopicListAdapter topicListAdapter;
//    private List<Topics> Topicslist = new ArrayList<>();
    private TextView topic_add_btn;
    private TextView topic_del_btn;
    private LinearLayout lin_father;
    private RelativeLayout timerpicker_start_rl;
    private int count = 0;
    private int size = 3;
    private TextView rightText;


    private EditText content_et;
    private EditText attendone_et;
    private EditText contacts_et;
    private EditText phone_et;
    private TextView meetcount_content_et;

    private String content_str;
    private String attendone_str;
    private String contacts_str;
    private String phone_str;
    private String meetcount_content_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeet);
        initViews();
        LoadmeetingBasis();
        initToolbar("添加会议");
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("添加");
        rightText.setTextColor(getResources().getColor(R.color.white));
        initTimePicker();
//        initControls();

//        initOnclicks();


    }


    private void getData() {
        if (wsMeetBasis == null) {
            Log.d(TAG, "wsMeetBasis==null");
            return;
        }
        sites_list.clear();
        comperes_list.clear();
        deptList_list.clear();
        types_list.clear();
        for (Meetattribute sites : wsMeetBasis.getSites()) {
            sites_list.add(sites.getAttributeName());
        }
        for (Meetattribute Comperes : wsMeetBasis.getComperes()) {
            comperes_list.add(Comperes.getAttributeName());
        }
        for (DeptInfo Dept : wsMeetBasis.getDeptList()) {
            deptList_list.add(Dept.getCName());
        }
        for (Meetattribute type : wsMeetBasis.getTypes()) {
            types_list.add(type.getAttributeName());
        }


    }


    private void initTimePicker() {
//        minute_start_pv = (PickerView) findViewById(R.id.minute_start_pv);
        hour_start_pv = (PickerView) findViewById(R.id.hour_start_pv);
        year_start_pv = (PickerView) findViewById(R.id.year_start_pv);
        month_start_pv = (PickerView) findViewById(R.id.month_start_pv);
        day_start_pv = (PickerView) findViewById(R.id.day_start_pv);

//        minute_end_pv = (PickerView) findViewById(R.id.minute_end_pv);
        hour_end_pv = (PickerView) findViewById(R.id.hour_end_pv);
        year_end_pv = (PickerView) findViewById(R.id.year_end_pv);
        month_end_pv = (PickerView) findViewById(R.id.month_end_pv);
        day_end_pv = (PickerView) findViewById(R.id.day_end_pv);
//点击事件防拦截
        hour_start_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        year_start_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        month_start_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day_start_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        hour_end_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        year_end_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        month_end_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day_end_pv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
//            Calendar ca = Calendar.getInstance();
        Log.i(TAG, "SimpleDateFormat" + str);
        startyear = Integer.parseInt(str.substring(0, str.indexOf("年")));//获取年份
        startmonth = Integer.parseInt(str.substring(str.indexOf("年") + 1, str.indexOf("月")));//获取月份
        startday = Integer.parseInt(str.substring(str.indexOf("月") + 1, str.indexOf("日")));//获取日
        starthour = Integer.parseInt(str.substring(str.indexOf("日") + 1, str.indexOf("时")));//获取日


//        Calendar ca = Calendar.getInstance();
//        startyear = ca.get(Calendar.YEAR);//获取年份
//        startmonth = ca.get(Calendar.MONTH);//获取月份
//        startday = ca.get(Calendar.DATE);//获取日
//        starthour = ca.get(Calendar.HOUR);//获取日


//        startmonth = 1;
//        startday = 30;
        start_selectyear = startyear + "";
        start_selectmonth = startmonth + "";
        start_selectday = startday + "";
        start_selecthour = starthour + "";

        end_selectyear = startyear + "";
        end_selectmonth = startmonth + "";
        end_selectday = startday + "";
        end_selecthour = starthour + "";
        Log.i(TAG, startyear + "-" + startmonth + "-" + startday + " " + starthour);
        CalendarBean cb = new CalendarBean();
        cb.setYMD(startyear, startmonth, startday);
        daylist = cb.getDaylist();
        int tempmonth = startmonth;
        for (int i = 0; i < daylist.size(); i++) {
            List<String> tempday = new ArrayList<String>();

            for (int j = startday; j <= daylist.get(i); j++) {
                tempday.add("" + j);
            }
            startday = 0;
            Log.i(TAG, "month:" + tempmonth + "day:" + daylist.get(i));
            //这里日期先不根据月份建立关系图，月份会重复，根据月份的序号建立关系图
            daydatatemp.put(i + "", tempday);
            monthlist.add(tempmonth + "");

            if (tempmonth == 12) {
                tempmonth = 1;

            } else {
                tempmonth = tempmonth + 1;

            }


        }
        //年份、2016
        int yeartemp = startyear;
        //年的数量
        int yearn = (startmonth + monthlist.size()) / 12;
        //月列表序号
        int monthnumber = 0;
        //每年开始的月份
        int monthstart = Integer.parseInt(monthlist.get(0));
        for (int j = 0; yearn >= j; j++) {
            //临时当前年的月表list
            List<String> tempmonthlist = new ArrayList<String>();
            //临时当前年的月表的月日关系表
            TreeMap<String, List<String>> tempmonthdaylist = new TreeMap<String, List<String>>();
            yearlist.add(yeartemp + "");

            for (; monthstart <= 12 && monthnumber < monthlist.size(); ) {
                //添加月份至临时列表/当年
                Log.i("monthnumber", monthnumber + "  --  " + monthstart + "  monthlist.size():" + monthlist.size());
                tempmonthlist.add(monthlist.get(monthnumber));

//				 TreeMap<String, List<String>> monthdaysingle= new TreeMap<String, List<String>>();
                //根据月份建立月份日期关系表
                tempmonthdaylist.put(monthlist.get(monthnumber), daydatatemp.get(monthnumber + ""));
//				tempmonthdaylist.add(monthdaysingle);
                monthstart++;
                monthnumber++;


            }
//			根据年份添加至列表，年月建立关系
            monthdata.put(yeartemp + "", tempmonthlist);
//          根据年份建立年月日关系表
            daydata.put(yeartemp + "", tempmonthdaylist);
            Log.i("monthnumber", yearn + "  ++  " + yeartemp);
            //每年开始的月份
            monthstart = 1;
            //年的数量
//			yearn--;
            //年份、2016
            yeartemp++;
        }
        for (int i = 0; i < 24; i++) {
            hourlist.add(i + "");
        }
        minutelist.add("00");
        minutelist.add("30");


        year_start_pv.setData(yearlist);
        month_start_pv.setData(monthdata.get(yearlist.get(0)));
        ;
        day_start_pv.setData(daydata.get(yearlist.get(0)).get(monthdata.get(yearlist.get(0)).get(0)));
        hour_start_pv.setData(hourlist);
//        minute_start_pv.setData(minutelist);


        year_start_pv.setSelected(0);
        day_start_pv.setSelected(0);
        month_start_pv.setSelected(0);
        hour_start_pv.setSelected(starthour);
//        minute_start_pv.setSelected(0);
        year_start_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 年",
//                        Toast.LENGTH_SHORT).show();
                month_start_pv.setData(monthdata.get(text.trim().toString()));
                day_start_pv.setData(daydata.get(text.trim().toString()).get(monthdata.get(text.trim().toString())
                        .get(0)));
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					day_start_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					day_start_pv.setData(thirds);
//				}
                day_start_pv.setSelected(0);
                month_start_pv.setSelected(0);
                start_selectyear = text.trim().toString();

            }
        });
        month_start_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 月",
//                        Toast.LENGTH_SHORT).show();
                day_start_pv.setData(daydata.get(start_selectyear).get(text.trim().toString()));
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					day_start_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					day_start_pv.setData(thirds);
//				}
                day_start_pv.setSelected(0);

                start_selectmonth = text.trim().toString();

            }
        });

        day_start_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 日",
//                        Toast.LENGTH_SHORT).show();
                start_selectday = text.trim().toString();
            }
        });
//        year_start_pv.setData(yearlist);
//        month_start_pv.setData(monthdata.get(yearlist.get(0)));
//        ;
//        day_start_pv.setData(daydata.get(yearlist.get(0)).get(monthdata.get(yearlist.get(0)).get(0)));
//        hour_start_pv.setData(hourlist);
//        minute_start_pv.setData(minutelist);
        hour_start_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 时",
//                        Toast.LENGTH_SHORT).show();

                start_selecthour = text.trim().toString();
            }
        });


        year_end_pv.setData(yearlist);
        month_end_pv.setData(monthdata.get(yearlist.get(0)));
        ;
        day_end_pv.setData(daydata.get(yearlist.get(0)).get(monthdata.get(yearlist.get(0)).get(0)));
        hour_end_pv.setData(hourlist);
//        minute_end_pv.setData(minutelist);

        year_end_pv.setSelected(0);
        day_end_pv.setSelected(0);
        month_end_pv.setSelected(0);
        hour_end_pv.setSelected(starthour);
//        minute_end_pv.setSelected(0);
        year_end_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 年",
//                        Toast.LENGTH_SHORT).show();
                month_end_pv.setData(monthdata.get(text.trim().toString()));
                day_end_pv.setData(daydata.get(text.trim().toString()).get(monthdata.get(text.trim().toString()).get
                        (0)));
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					day_end_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					day_end_pv.setData(thirds);
//				}
                day_end_pv.setSelected(0);
                month_end_pv.setSelected(0);
                end_selectyear = text.trim().toString();

            }
        });
        month_end_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 月",
//                        Toast.LENGTH_SHORT).show();
                day_end_pv.setData(daydata.get(end_selectyear).get(text.trim().toString()));
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					day_end_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					day_end_pv.setData(thirds);
//				}
                day_end_pv.setSelected(0);

                end_selectmonth = text.trim().toString();

            }
        });

        day_end_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 日",
//                        Toast.LENGTH_SHORT).show();
                end_selectday = text.trim().toString();
            }
        });
        hour_end_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                Toast.makeText(AddCalenderActivity.this, "选择了 " + text + " 时",
//                        Toast.LENGTH_SHORT).show();
//                end_selecthour = text.trim().toString();

                end_selecthour = text.trim().toString();
            }
        });


    }

    private boolean returnTPDate() throws ParseException {
        String start_returnDate = start_selectyear + "-" + start_selectmonth + "-" + start_selectday;
//        startDate= String.valueOf(startdate);
        if (start_selecthour.trim().toString().length() == 1) {
            start_selecthour = "0" + start_selecthour;
        }
        if (end_selecthour.trim().toString().length() == 1) {
            end_selecthour = "0" + end_selecthour;
        }
        startDate = start_returnDate;
        startDate += " " + start_selecthour + ":00:00";
        String end_returnDate = end_selectyear + "-" + end_selectmonth + "-" + end_selectday;
//        endDate= String.valueOf(enddate);
        endDate = end_returnDate;
        endDate += " " + end_selecthour + ":00:00";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Long startdate = TimeManagement.stringToLongDate1(startDate);
        Long enddate = TimeManagement.stringToLongDate1(endDate);

        if (startdate >= enddate) {
            ShortCut.showToast("结束时间必须大于开始时间！", this);
            return false;
        }


        Log.d(TAG, "startdate=" + startDate + "endDate=" + endDate);
        return true;
    }


    private void initViews() {
        rightText = (TextView) findViewById(R.id.right_text);

        sites_sp = (NiceSpinner) findViewById(R.id.addmeet_sites_sp);
        comperes_sp = (NiceSpinner) findViewById(R.id.addmeet_comperes_sp);
        types_sp = (NiceSpinner) findViewById(R.id.addmeet_types_sp);
        topic_add_btn = (TextView) findViewById(R.id.meet_add_btn);
        topic_del_btn = (TextView) findViewById(R.id.meet_del_btn);
        lin_father = (LinearLayout) findViewById(R.id.activity_topic_layout);


        meetcount_content_et = (TextView) findViewById(R.id.title_meetcount_content_et);
        content_et = (EditText) findViewById(R.id.title_meet_content_et);
        attendone_et = (EditText) findViewById(R.id.title_meet_attendone_et);
        contacts_et = (EditText) findViewById(R.id.title_meet_contacts_et);
        phone_et = (EditText) findViewById(R.id.title_meet_phone_et);

        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LP_FW.setMargins(0, 4 * size, 0, 0);

        LinearLayout topic_item_ly = AddTopicView(topicId);
        lin_father.addView(topic_item_ly, LP_FW);//全部用父结点的布局参数

        topic_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout topic_item_ly = AddTopicView(topicId);
                LP_FW.setMargins(0, 7 * size, 0, 0);
                lin_father.addView(topic_item_ly, LP_FW);//全部用父结点的布局参数AddTopicView();
                AddTopicBean();
            }
        });
        topic_del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    int id = topicId + count * 5;
                    Log.i(TAG, "清除！第" + (count + 1) + "个动态列表,id起始为" + id);
                    lin_father.removeViewAt(count);
                }

            }
        });
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getmeetBean()&&getTopicBean()) {
                    meetingApplyRsq();

                }
            }
        });


    }

    private LinearLayout AddTopicView(int topicid) {
        int id = topicid + count * 5;

        LinearLayout topic_item_ly = new LinearLayout(this);
        topic_item_ly.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp_item_ly = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_item_ly.setMargins(0, 3 * size, 0, 0);
        topic_item_ly.setLayoutParams(lp_item_ly);
        topic_item_ly.setId(id);
        topic_item_ly.setPadding(20 * size, 20 * size, 20 * size, 5 * size);

        //title布局
        LinearLayout topic_item_title_ly = new LinearLayout(this);
        LinearLayout.LayoutParams lp_item_title_ly = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
        topic_item_title_ly.setLayoutParams(lp_item_title_ly);

        TextView item_title_tv = new TextView(this);
        LinearLayout.LayoutParams lp_item_title_tv = new LinearLayout.LayoutParams(
                70 * size, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_item_title_tv.gravity = Gravity.CENTER;
        item_title_tv.setText("议题标题");
        item_title_tv.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_title_tv.setTextSize(14);
        item_title_tv.setGravity(Gravity.CENTER);
        item_title_tv.setLayoutParams(lp_item_title_tv);
        topic_item_title_ly.addView(item_title_tv);

        EditText item_title_et = new EditText(this);
        LinearLayout.LayoutParams lp_item_title_et = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
        topic_item_ly.setPadding(15 * size, 0, 0, 0);
        lp_item_title_et.gravity = Gravity.CENTER;
        item_title_et.setHint("请输入");
//        item_title_et.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_title_et.setBackgroundColor(getResources().getColor(R.color.white));
        item_title_et.setTextSize(14);
        item_title_et.setGravity(Gravity.CENTER_VERTICAL);
        item_title_et.setPadding(25 * size, 0, 0, 0);
        item_title_et.setLayoutParams(lp_item_title_et);
        item_title_et.setId(id + 1);
        topic_item_title_ly.addView(item_title_et);

        //dept布局
        LinearLayout topic_item_dept_ly = new LinearLayout(this);
        LinearLayout.LayoutParams lp_item_dept_ly = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
        lp_item_dept_ly.setMargins(0, 3 * size, 0, 0);
        topic_item_dept_ly.setLayoutParams(lp_item_dept_ly);

        TextView item_dept_tv = new TextView(this);
        LinearLayout.LayoutParams lp_item_dept_tv = new LinearLayout.LayoutParams(
                70 * size, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_item_dept_tv.gravity = Gravity.CENTER;
        item_dept_tv.setText("汇报单位");
        item_dept_tv.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_dept_tv.setTextSize(14);
        item_dept_tv.setGravity(Gravity.CENTER);
        item_dept_tv.setLayoutParams(lp_item_dept_tv);
        topic_item_dept_ly.addView(item_dept_tv);

        NiceSpinner item_dept_ns = new NiceSpinner(this);
        LinearLayout.LayoutParams lp_item_dept_ns = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
//        lp_item_dept_ns.gravity = Gravity.CENTER;
        item_dept_ns.setHint("请输入");
//        item_seet_tv.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_dept_ns.setBackgroundColor(getResources().getColor(R.color.white));
//        item_dept_ns.setTextSize(14);
//        item_dept_ns.setGravity(Gravity.CENTER);
        item_dept_ns.setLayoutParams(lp_item_dept_ns);
        item_dept_ns.setId(id + 2);
        topic_item_dept_ly.addView(item_dept_ns);

        //seet布局
        LinearLayout topic_item_seet_ly = new LinearLayout(this);
        LinearLayout.LayoutParams lp_item_seet_ly = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
        lp_item_seet_ly.setMargins(0, 3 * size, 0, 0);
        topic_item_seet_ly.setLayoutParams(lp_item_seet_ly);

        TextView item_seet_tv = new TextView(this);
        LinearLayout.LayoutParams lp_item_seet_tv = new LinearLayout.LayoutParams(
                70 * size, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_item_seet_tv.gravity = Gravity.CENTER;
        item_seet_tv.setText("列席");
        item_seet_tv.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_seet_tv.setTextSize(14);
        item_seet_tv.setGravity(Gravity.CENTER);
        item_seet_tv.setLayoutParams(lp_item_seet_tv);
        topic_item_seet_ly.addView(item_seet_tv);

        TextView item_seet_et = new TextView(this);
        LinearLayout.LayoutParams lp_item_seet_et = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 50 * size);
        lp_item_seet_et.gravity = Gravity.CENTER;
        topic_item_ly.setPadding(15 * size, 0, 0, 0);
        item_seet_et.setText("请点击选择");
        item_seet_tv.setTextColor(getResources().getColor(R.color.activity_text_grey));
        item_seet_et.setBackgroundColor(getResources().getColor(R.color.white));
        item_seet_et.setTextSize(14);
        item_seet_et.setGravity(Gravity.CENTER_VERTICAL);
        item_seet_et.setPadding(25 * size, 0, 0, 0);
        item_seet_et.setLayoutParams(lp_item_seet_et);
        item_seet_et.setId(id + 3);
        topic_item_seet_ly.addView(item_seet_et);

        topic_item_ly.addView(topic_item_title_ly);
        topic_item_ly.addView(topic_item_dept_ly);
        topic_item_ly.addView(topic_item_seet_ly);

        count++;
        Log.i(TAG, "第" + (count) + "个动态列表,id起始为" + id);
        return topic_item_ly;


    }


    private void AddTopicBean() {

        for (int i = 0; i < count; i++) {
//            count--;
            int id = topicId + i * 5;
            Log.i(TAG, "deptList_list" + deptList_list.size() + "添加！第" + (i + 1) + "个动态列表,id起始为" + id);
            NiceSpinner item_ns = (NiceSpinner) findViewById(id + 2);
            if (deptList_list.size() > 0) {
                item_ns.attachDataSource(deptList_list);
            }
            TextView item_tv = (TextView) findViewById(id + 3);
            item_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent();
                    mIntent.setClass(AddMeetActivity.this, MultiselectActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("viewId", v.getId() + "");
                    mIntent.putExtras(bundle);
                    Log.i(TAG, "viewIdClick:" + v.getId());
                    startActivityForResult(mIntent, TOPIC_BACKCODE);
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
//                if(requestCode == TOPIC_BACKCODE){
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String id = b.getString("viewId");//str即为回传的值
                if (id != null) {
                    Log.i(TAG, "viewId" + id);
                    TextView item_tv = (TextView) findViewById(Integer.parseInt(id));
                    String deptstr = "";
                    for (int i = 0; i < ShortCut.ConfirmdeptList_list.size(); i++) {
                        deptstr += ShortCut.ConfirmdeptList_list.get(i).getCName() + ",";
                    }
                    item_tv.setText(deptstr.substring(0, deptstr.length() - 1));
                } else {
                    Log.i(TAG, "viewId==null");
                }

//                }

                break;
            default:
                break;
        }

    }

    private boolean getmeetBean() {
//        private EditText content_et;
//        private EditText attendone_et;
//        private EditText contacts_et;
//        private EditText phone_et;
//        private TextView meetcount_content_et;
        if (content_et.getText() == null || content_et.getText().toString().equals("")) {
            ShortCut.showToast("标题不能为空", this);
            return false;
        }
        if (attendone_et.getText() == null || attendone_et.getText().toString().equals("")) {
            ShortCut.showToast("出席人员不能为空", this);
            return false;
        }
        if (contacts_et.getText() == null || contacts_et.getText().toString().equals("")) {
            ShortCut.showToast("联系人不能为空", this);
            return false;
        }
        if (phone_et.getText() == null || phone_et.getText().toString().equals("")) {
            ShortCut.showToast("电话不能为空", this);
            return false;
        }
        content_str = content_et.getText().toString();
        attendone_str = attendone_et.getText().toString();
        contacts_str = contacts_et.getText().toString();
        phone_str = phone_et.getText().toString();
//        meetcount_content_str = meetcount_content_et.getText().toString();
        try {
            if (!returnTPDate()) {
                return false;
            }
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sites_str = sites_sp.getText().toString();
        comperes_str = comperes_sp.getText().toString();
        types_str = types_sp.getText().toString();

        meetList = new meetList();
        meetList.setAttendOne(attendone_str);
        meetList.setCharge(comperes_str);
        meetList.setContacts(contacts_str);
        meetList.setEndDate(endDate);
        meetList.setMeetDate(startDate);
        meetList.setMeetName(content_str);
        meetList.setMeetSite(sites_str);
        meetList.setNum(meetcount_content_str);
        meetList.setPhone(phone_str);
        meetList.setYear(startyear + "");
        meetList.setMeetType(types_str);
        meetList.setStatus("1");

        Log.i(TAG, "meetList:" + meetList.toString());
        return true;

    }

    private boolean getTopicBean() {
        Log.i(TAG, "count:" + count);
        topics_list.clear();
        for (int i = 0; i < count; i++) {
            Topics topics = new Topics();
//        topics.setReportDept("请选择");
//        topics.setTopicName("请输入");
//        topics.setAttendDept("请选择")
            int id = topicId + i * 5;
            Log.i(TAG, "获取！第" + (i + 1) + "个动态列表,id起始为" + id);
            NiceSpinner item_ns = (NiceSpinner) findViewById(id + 2);
            if (deptList_list.size() > 0) {
                if (item_ns != null && item_ns.getText() != null) {

                    topics.setReportDept(item_ns.getText().toString());
                }
            }
            TextView item_tv = (TextView) findViewById(id + 3);
            if (item_tv.getText() == null || item_tv.getText().toString().trim().equals("请点击选择")) {
                ShortCut.showToast("列席不能为空", this);
                return false;
            }
            topics.setAttendDept(item_tv.getText().toString());
            EditText item_et = (EditText) findViewById(id + 1);
            if (item_et.getText() == null || item_et.getText().toString().equals("")) {
                ShortCut.showToast("议题标题不能为空", this);
                return false;
            }
            topics.setTopicName(item_et.getText().toString().trim());
            Log.i(TAG, "getTopicBean" + topics.toString());
            topics_list.add(topics);
        }
        return true;

    }

    private void LoadDate() {


//        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
//        Log.i("rushBasis","deptList_list"+deptList_list.size());
        if (types_list.size() > 0) {
            types_sp.attachDataSource(types_list);


        }
        if (comperes_list.size() > 0) {
            comperes_sp.attachDataSource(comperes_list);
//            comperes_sp.setAdapter(sites_adapter);
        }
        if (sites_list.size() > 0) {
            sites_sp.attachDataSource(sites_list);
//            sites_sp.setAdapter(sites_adapter);
        }
        types_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = wsMeetBasis.getTypeCounts().get(position).getCount();
                meetcount_content_str = Integer.parseInt(data) + 1 + "";
                meetcount_content_et.setText(meetcount_content_str + "次");
                Log.i(TAG, "types_sp" + data);
//                for (typeCounts type : wsMeetBasis.getTypeCounts()) {
//                    if (type.getAttributeName().equals(data)) {
//                        Log.i(TAG, "types_spCount：" + type.getCount());
//
//                    }
//                }
                //从spinner中获取被选择的数据
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
        String data = types_sp.getText().toString();
        Log.i(TAG, "types_sp" + data);
        for (typeCounts type : wsMeetBasis.getTypeCounts()) {
            if (type.getAttributeName().equals(data)) {
                meetcount_content_str = Integer.parseInt(type.getCount()) + 1 + "";
                meetcount_content_et.setText(meetcount_content_str + "次");
            }
        }
//            }
//        }, 500);

    }

    //通
    private void LoadmeetingBasis() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())

        Json json = new Json();
        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！", this);
            return;
        }
        json.setUuEName(ShortCut.getUser(this).getUserEnglishName());
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);
        NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(API.Method_MEETINGBASIS, jsonStr, "")
                , new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                wsMeetBasis = result.getWsMeetBasis();
//                Gson gson = new Gson();
//                Json json = gson.fromJson(API.RSPONSE_MEETINGBASIS, Json.class);
//                wsMeetBasis = json.getWsMeetBasis();
                ShortCut.MeetBasis = result.getWsMeetBasis();
                Log.d(TAG, "result=" + "MeetName:" + wsMeetBasis.getMeetName() + " Sites:" + wsMeetBasis.getSites()
                        .get(0).getAttributeName() + " Comperes:" + wsMeetBasis.getComperes().get(0).getAttributeName
                        () + " Types:" + wsMeetBasis.getTypes().get(0).getAttributeName() + " TypeCounts:" +
                        wsMeetBasis.getTypeCounts().get(0).getCount() + "getDeptList:" + wsMeetBasis.getDeptList()
                        .size());
                getData();
                LoadDate();
                AddTopicBean();
//                initViews();

            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);

            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                if (Testswith.Testmeet) {
                    Gson gson = new Gson();
                    Json json = gson.fromJson(API.JSON_SCHEDULALL, Json.class);
                    wsMeetBasis = json.getWsMeetBasis();
                    ShortCut.MeetBasis = json.getWsMeetBasis();
                    Log.d(TAG, "result=" + "MeetName:" + wsMeetBasis.getMeetName() + " Sites:" + wsMeetBasis.getSites()
                            .get(0).getAttributeName() + " Comperes:" + wsMeetBasis.getComperes().get(0)
                            .getAttributeName
                                    () + " Types:" + wsMeetBasis.getTypes().get(0).getAttributeName() + " TypeCounts:" +
                            wsMeetBasis.getTypeCounts().get(0).getAttributeName() + "getDeptList:" + wsMeetBasis
                            .getDeptList().size());
                    getData();
                    LoadDate();
//                getTopicBean();
                    AddTopicBean();
                }

            }
        });


    }

    private void meetingApplyRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        wsMeetingApply mywsMeetApply = new wsMeetingApply();
        mywsMeetApply.setMeetList(meetList);
        mywsMeetApply.setTopics(topics_list);
        mywsMeetApply.setUuEName(ShortCut.getUser(this).getUserEnglishName());
        Json json = new Json();
        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！", this);
            return;
        }
//        json.setWsMeetingApply(mywsMeetApply);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(mywsMeetApply);
        NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(API.Method_MEETINGAPPLY, jsonStr, "")
                , new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
//                wsMeetBasis = result.getWsMeetBasis();
                ShortCut.showToast("添加成功", AddMeetActivity.this);
                finish();

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


    }

}
