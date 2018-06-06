package com.dangelo.xxoa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.schedule;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.togglebutton.ToggleButton;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.TimeManagement;
import com.google.gson.Gson;
import com.jingchen.timerpicker.CalendarBean;
import com.jingchen.timerpicker.PickerView;

import org.json.JSONException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/6/28.
 */
public class AddCalenderActivity extends BaseActivity {

    public static schedule myScheduleDate;
    //    PickerView minute_start_pv;
    PickerView hour_start_pv;
    PickerView year_start_pv;
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
    int endyear, endmonth, endday, endhour;//获取日
    String TAG = AddCalenderActivity.class.getName();
    String start_selectyear = "";
    String start_selectmonth = "";
    String start_selectday = "";
    String start_selecthour = "";
    String end_selectyear = "";
    String end_selectmonth = "";
    String end_selectday = "";
    String end_selecthour = "";
    private TextView rightText;
    private EditText content_et;
    private EditText address_et;
    private TextView constitutor_et;
    private EditText remark_et;
    private ToggleButton isday_shock_switch;
    private String content_str;
    private String address_str;
    private String constitutor_str;
    private String remark_str;
    private String isday;
    private String endDate;
    private String startDate;
    private schedule myschedule;
    private String from = "";
    private RelativeLayout TimerpickerRl;
    private boolean choiceVisiable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcalender);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");//str即为回传的值
            if (!from.equals("")) {
                Log.i(TAG, "from:" + from);
            }

        }
        if (from.equals("CalenderDetailActivity")) {
            initToolbar("修改日程");
        }else{
            initToolbar("添加日程");
        }
//        getData();
        initViews();

        initdate();
        initTimePicker();
    }

    private void initdate() {
        if (from.equals("CalenderDetailActivity")) {
            myScheduleDate = ShortCut.Schedule;
            Log.i(TAG,"myScheduleDate"+myScheduleDate.getId());
            content_et.setText(myScheduleDate.getTopic());
            address_et.setText(myScheduleDate.getAddress());
            constitutor_et.setText(myScheduleDate.getConstitutor());
            remark_et.setText(myScheduleDate.getRemark());


            isday_shock_switch.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
                @Override
                public void onToggle(boolean on) {
                    if (myScheduleDate.getIsallDay().equals("true")) {
                        isday_shock_switch.setToggleOn();
                    } else {
                        isday_shock_switch.setToggleOff();
                    }
                }
            });


            SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日HH时mm分ss秒");
            Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
            String    str    =    formatter.format(curDate);
//            Calendar ca = Calendar.getInstance();
            Log.i(TAG,"SimpleDateFormat"+str);
            startyear = Integer.parseInt(str.substring(0,str.indexOf("年")));//获取年份
            startmonth = Integer.parseInt(str.substring( str.indexOf("年")+1,str.indexOf("月")));//获取月份
            startday = Integer.parseInt(str.substring(str.indexOf("月") + 1, str.indexOf("日")));//获取日
            starthour = Integer.parseInt(str.substring(str.indexOf("日") + 1, str.indexOf("时")));//获取日

            endyear = startyear;//获取年份
            endmonth = startmonth;//获取月份
            endday =startday;//获取日
            endhour = starthour;//获取日

        } else {
//            Calendar ca = Calendar.getInstance();
//
//            startyear = ca.get(Calendar.YEAR);//获取年份
//            startmonth = ca.get(Calendar.MONTH);//获取月份
//            startday = ca.get(Calendar.DATE);//获取日
//            starthour = ca.get(Calendar.HOUR);//获取日
//
//            endyear = ca.get(Calendar.YEAR);//获取年份
//            endmonth = ca.get(Calendar.MONTH);//获取月份
//            endday = ca.get(Calendar.DATE);//获取日
//            endhour = ca.get(Calendar.HOUR);//获取日
            SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日HH时mm分ss秒");
            Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
            String    str    =    formatter.format(curDate);
            Log.i(TAG,"SimpleDateFormat"+str);
            startyear = Integer.parseInt(str.substring(0,str.indexOf("年")));//获取年份
            startmonth = Integer.parseInt(str.substring( str.indexOf("年")+1,str.indexOf("月")));//获取月份
            startday = Integer.parseInt(str.substring(str.indexOf("月") + 1, str.indexOf("日")));//获取日
            starthour = Integer.parseInt(str.substring(str.indexOf("日") + 1, str.indexOf("时")));//获取日

            endyear = startyear;//获取年份
            endmonth = startmonth;//获取月份
            endday =startday;//获取日
            endhour = starthour;//获取日

        }


    }


//    private void getData() {
//        String comefrom = getIntent().getStringExtra("orderstype");
//        if(comefrom!=null&&comefrom.equals("alreadlyorder")){
//            choiceVisiable = false;
//        }
//        mPackageList = (Package_list) getIntent().getSerializableExtra("mpackagelist");
//        sp=getApplication().getSharedPreferences("opition",MODE_PRIVATE);
//        number=sp.getString("name", "");
//        md5password=sp.getString("md5password","");
//
//    }


    private void initViews() {

        rightText = (TextView) findViewById(R.id.right_text);
        rightText.setVisibility(View.VISIBLE);
        if (from.equals("CalenderDetailActivity")) {
            rightText.setText("完成");
        }else{
            rightText.setText("添加");
        }
        rightText.setTextColor(getResources().getColor(R.color.white));
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "rightText");
                if(GetDate()){
                    scheduleAddRsq();
                }


            }
        });
        content_et = (EditText) findViewById(R.id.title_calender_content_et);
        address_et = (EditText) findViewById(R.id.title_calender_address_et);
        constitutor_et = (TextView) findViewById(R.id.title_calender_constitutor_et);
        remark_et = (EditText) findViewById(R.id.title_calender_remark_et);
        isday_shock_switch = (ToggleButton) findViewById(R.id.isday_shock_switch);
        constitutor_et.setText(ShortCut.getUser(this).getUserEnglishName());


        isday_shock_switch.setToggleOn();
        isday = "true";
        isday_shock_switch.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    isday = "true";
                } else {
                    isday = "false";
                }
            }
        });

    }

    private boolean GetDate() {
        Log.i(TAG, "GetDate");
        if (content_et.getText() == null || content_et.getText().toString().equals("")) {
            ShortCut.showToast("标题不能为空", this);
//            Log.i(TAG, "标题不能为空");
            return false;
        }
        if (address_et.getText() == null || address_et.getText().toString().equals("")) {
            ShortCut.showToast("地址不能为空", this);
//            Log.i(TAG, "地址不能为空");
            return false;
        }
        content_str = content_et.getText().toString();
        address_str = address_et.getText().toString();
        remark_str = remark_et.getText().toString();
        try {
            if(!returnDate()){
                return false;
            };
        } catch (ParseException e) {
            e.printStackTrace();
        }

        myschedule = new schedule();
        myschedule.setAddress(address_str);
        myschedule.setConstitutor(ShortCut.getUser(this).getUserEnglishName());
        myschedule.setIsallDay(isday);
        myschedule.setRemark(remark_str);
        myschedule.setStartDate(startDate);
        myschedule.setEndDate(endDate);
        myschedule.setTopic(content_str);
        myschedule.toString();
        Log.i(TAG, " myschedule.toString()" + myschedule.toString());
        return true;
    }

//    private Json getSubscribeJson() {
//        //订购流程
//        Json json = new Json();
//        json.setBindnumber(number);
//        json.setPassword(md5password);
//        json.setRegistration_id(MyApplication.getInstance().getRegistration_id());
//
//        List<Business_list> business_lists = null;
//        List<Package_list> packageLists = null;
//        if (business_lists == null) {
//            business_lists = new ArrayList<Business_list>();
//        }
//        if (packageLists == null) {
//            packageLists = new ArrayList<Package_list>();
//        }
//        Business_list business_list = new Business_list();
//        business_list.setBusiness_code("0001");
//        Package_list packageList = new Package_list();
//        packageList.setPackage_code(mPackageList.getPackage_code());
//        returnDate();
//        packageList.setPackage_startdate(startDate);
//        packageLists.add(packageList);
//        business_list.setPackageList(packageLists);
//        business_lists.add(business_list);
//        json.setBusiness_list(business_lists);
//        // packageLists.clear();
//        return json;
//    }

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
        Log.i(TAG, startyear + "-" + startmonth + "-" + startday+"  "+starthour);
        CalendarBean cb = new CalendarBean();
        cb.setYMD(startyear, startmonth, startday);
        daylist = cb.getDaylist();
        int tempmonth = startmonth;
        for (int i = 0; i < daylist.size(); i++) {
            List<String> tempday = new ArrayList<String>();

            for (int j = startday ; j <= daylist.get(i); j++) {
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
        hour_start_pv.setSelected(start_selecthour);
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
        hour_end_pv.setSelected(end_selecthour);
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

    private boolean returnDate() throws ParseException {
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
        Log.d(TAG, "startdate=" + startDate + "endDate=" + endDate);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Long startdate = TimeManagement.stringToLongDate1(startDate);
        Long enddate = TimeManagement.stringToLongDate1(endDate);

        if (startdate >= enddate) {
            ShortCut.showToast("结束时间必须大于开始时间！", this);
            return false;
        }
        return true;



    }

    //通
    private void scheduleAddRsq() {

//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！", this);
            return;
        }
//        json.setScheduleAdd(myschedule);
        String methed ="";
        if (from.equals("CalenderDetailActivity")) {
            methed = API.Method_SCHEDULUPDATE;
            myschedule.setId(myScheduleDate.getId());
        }else{
            methed = API.Method_SCHEDULADD;
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(myschedule);


        NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(methed, jsonStr,
                ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
//						Json json1 = new Json();
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
//                schedule Schedule = result.getScheduleAdd();
//                ShortCut.ScheduleID = Schedule.getId();
//                Log.d(TAG, "result" + Schedule.getId() + result);
                ShortCut.showToast("添加完成", AddCalenderActivity.this);
                finish();
//						Gson gson = new Gson();
//						Json json = gson.fromJson(result.toString(), Json.class);
//
//						Log.d(TAG, json.toString());


            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
                ShortCut.showToast("添加失败", AddCalenderActivity.this);
            }
        });
    }

}
