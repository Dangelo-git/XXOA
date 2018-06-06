package com.jingchen.timerpicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.jingchen.timerpicker.PickerView.onSelectListener;

/**
 * 更多详解见博客http://blog.csdn.net/zhongkejingwang/article/details/38513301
 *
 * @author chenjing
 *
 */
public class MainActivity extends Activity
{

	PickerView minute_pv;
	PickerView second_pv;
	PickerView year_pv;
	List<Integer> daylist = new ArrayList<Integer>();
	List<String> monthlist = new ArrayList<String>();
	List<String> yearlist = new ArrayList<String>();

	TreeMap<String,List<String>> monthdata = new TreeMap<String, List<String>>();
	TreeMap<String,List<String>> daydatatemp = new TreeMap<String, List<String>>();
	TreeMap<String,TreeMap<String,List<String>>> daydata = new TreeMap<String, TreeMap<String,List<String>>>();
	//	String data[][] ;
	int year ,month,day;//获取日
	String TAG = MainActivity.class.getName();
	String selectyear = "";
	String selectmonth = "";
	String selectday = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		minute_pv = (PickerView) findViewById(R.id.minute_pv);
		second_pv = (PickerView) findViewById(R.id.second_pv);
		year_pv = (PickerView) findViewById(R.id.year_pv);

		Calendar ca = Calendar.getInstance();
//		 year = ca.get(Calendar.YEAR);//获取年份
//		 month=ca.get(Calendar.MONTH);//获取月份
//		 day=ca.get(Calendar.DATE);//获取日
		year = ca.get(Calendar.YEAR);//获取年份

		month=1;
		day=30;



		selectyear = year+"";
		selectmonth = month+"";
		selectday = day+"";
		Log.i(TAG, year + "-" + month + "-" + day);
		CalendarBean cb =new CalendarBean();
		cb.setYMD(year,month,day);
		daylist = cb.getDaylist();
		int tempmonth =month;
		for(int i =0 ;i<daylist.size();i++){
			List<String> tempday = new ArrayList<String>();

			for(int j=day+1;j<=daylist.get(i);j++){
				tempday.add( "" + j);
			}
			day=0;
			Log.i(TAG, "month:" + tempmonth + "day:" + daylist.get(i));
			//这里日期先不根据月份建立关系图，月份会重复，根据月份的序号建立关系图
			daydatatemp.put(i + "", tempday);
			monthlist.add(tempmonth + "");

			if(tempmonth==12){
				tempmonth = 1;

			}else{
				tempmonth = tempmonth+1;

			}


		}
		//处理年份
//		yearlist.add(year+"");
//		int yeartemp = year;
//		int yearn = month+monthlist.size()/12;
//		for(int j=0;yearn>=j;j++){
//
//			yearlist.add(yeartemp + "");
//			for(int k=Integer.parseInt(monthlist.get(0));k<=12;k++){
//				monthdata.put()
//
//			}
//			yeartemp++;
//		}
		//年份、2016
		int yeartemp = year;
		//年的数量
		int yearn = (month+monthlist.size())/12;
		//月列表序号
		int monthnumber=0;
		//每年开始的月份
		int monthstart=Integer.parseInt(monthlist.get(0));
		for(int j=0;yearn>=j;j++){
			//临时当前年的月表list
			List<String> tempmonthlist = new ArrayList<String>();
			//临时当前年的月表的月日关系表
			TreeMap<String,List<String>>  tempmonthdaylist = new  TreeMap<String,List<String>>() ;
			yearlist.add(yeartemp + "");

			for(;monthstart<=12&&monthnumber<monthlist.size();){
				//添加月份至临时列表/当年
				Log.i("monthnumber",monthnumber+"  --  "+monthstart+"  monthlist.size():"+monthlist.size());
				tempmonthlist.add(monthlist.get(monthnumber));

//				 TreeMap<String, List<String>> monthdaysingle= new TreeMap<String, List<String>>();
				//根据月份建立月份日期关系表
				tempmonthdaylist.put(monthlist.get(monthnumber), daydatatemp.get(monthnumber+""));
//				tempmonthdaylist.add(monthdaysingle);
				monthstart++;
				monthnumber++;


			}
//			根据年份添加至列表，年月建立关系
			monthdata.put(yeartemp + "", tempmonthlist);
//          根据年份建立年月日关系表
			daydata.put(yeartemp+"",tempmonthdaylist);
			Log.i("monthnumber", yearn + "  ++  " + yeartemp);
			//每年开始的月份
			monthstart = 1;
			//年的数量
//			yearn--;
			//年份、2016
			yeartemp++;
		}



//		data.add(month+"");
//		for (int i = day; i <= date[0]; i++)
//		{
//			seconds.add( "" + i);
//		}
//		if(date[1]!=0){
//			if(month==12){
//				data.add("1");
//
//			}else{
//				data.add(month+1+"");
//
//			}
//			for (int i = 1; i <= date[1]; i++)
//			{
//				thirds.add( "" + i);
//			}
//		}

		year_pv.setData(yearlist);
		minute_pv.setData(monthdata.get(yearlist.get(0)));
		;
		second_pv.setData(daydata.get(yearlist.get(0)).get( monthdata.get(yearlist.get(0)).get(0)) );
		year_pv.setSelected(0);
		second_pv.setSelected(0);
		minute_pv.setSelected(0);
		year_pv.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				Toast.makeText(MainActivity.this, "选择了 " + text + " 年",
						Toast.LENGTH_SHORT).show();
				minute_pv.setData(monthdata.get(text.trim().toString()));
				second_pv.setData(daydata.get(text.trim().toString()).get(monthdata.get(text.trim().toString()).get(0)));
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					second_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					second_pv.setData(thirds);
//				}
				second_pv.setSelected(0);
				minute_pv.setSelected(0);
				selectyear = text.trim().toString();
				selectmonth = text.trim().toString();

			}
		});
		minute_pv.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				Toast.makeText(MainActivity.this, "选择了 " + text + " 月",
						Toast.LENGTH_SHORT).show();
				second_pv.setData(daydata.get(selectyear).get( text.trim().toString()) );
//				Log.i(TAG,"thirds.size:"+thirds.size());
//				if(text.trim().equals(month+"")){
//					Log.i(TAG,"seconds.size:"+seconds.size());
//					second_pv.setData(seconds);
//				}else{
//					Log.i(TAG,"thirds.size:"+thirds.size()+"text"+text+"month"+month);
//					second_pv.setData(thirds);
//				}
				second_pv.setSelected(0);

				selectmonth = text.trim().toString();

			}
		});

		second_pv.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				Toast.makeText(MainActivity.this, "选择了 " + text + " 日",
						Toast.LENGTH_SHORT).show();
				selectday = text.trim().toString();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
