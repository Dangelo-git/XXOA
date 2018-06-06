package com.dangelo.xxoa.calendar;

import android.app.Activity;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dangelo.xxoa.R;
import com.dangelo.xxoa.model.Lunar;
import com.dangelo.xxoa.uitl.ShortCut;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/************************************************************************
 * 项目名字 :带手势滑动功能的日历
 * 
 * @author angelの慧
 * @version 2012-10-08 　* * 添加农历、传统节日、西方节日、固定节日功能 由左右滑动变为上下滑动
 * @author 彩虹之西
 * @version 2013-4-17 　*
 ************************************************************************/
public class CalendarGridViewAdapter extends BaseAdapter {

	private Calendar calStartDate = Calendar.getInstance();// 当前显示的日历
	private Calendar calSelected = Calendar.getInstance(); // 选择的日历
	private int[] checkList;

	private int gridHeight = 0;
	private int gridWidth = 0;
	private String TAG = "CalendarGridViewAdapter";

	public void setSelectedDate(Calendar cal) {
		calSelected = cal;
	}

	private Calendar calToday = Calendar.getInstance(); // 今日
	private int iMonthViewCurrentMonth = 0; // 当前视图月

	// 根据改变的日期更新日历
	// 填充日历控件用
	private void UpdateStartDateForMonth() {
		calStartDate.set(Calendar.DATE, 1); // 设置成当月第一天
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);// 得到当前日历显示的月

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

		calStartDate.add(Calendar.DAY_OF_MONTH, -1);// 周日第一位

	}

	ArrayList<Date> titles;

	private ArrayList<Date> getDates() {

		UpdateStartDateForMonth();

		ArrayList<Date> alArrayList = new ArrayList<Date>();
		// 遍历数组
		for (int i = 1; i <= 42; i++) {
			alArrayList.add(calStartDate.getTime());
			calStartDate.add(Calendar.DAY_OF_MONTH, 1);
		}

		return alArrayList;
	}

	private Activity activity;
	Resources resources;

	// construct
	public CalendarGridViewAdapter(Activity a, Calendar cal,
			int[] mcheckList) {
		calStartDate = cal;
		activity = a;
		resources = activity.getResources();
		titles = getDates();
		checkList = mcheckList;
	}

	public CalendarGridViewAdapter(Activity a) {
		activity = a;
		resources = activity.getResources();
	}

	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public Object getItem(int position) {
		return titles.get(position);
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			viewHolder.iv = new RelativeLayout(activity);
			viewHolder.iv.setId(position + 5000);
			viewHolder.iv.setGravity(Gravity.CENTER);
			// viewHolder.iv.setOrientation(1);
			viewHolder.iv.setBackgroundColor(resources.getColor(R.color.white));

			viewHolder.imageLayout = new LinearLayout(activity);
			viewHolder.imageLayout.setOrientation(LinearLayout.HORIZONTAL);

			viewHolder.txtLunarDay = new TextView(activity);// 日本老黄历
			viewHolder.txtLunarDay.setGravity(Gravity.CENTER_HORIZONTAL);
			viewHolder.txtLunarDay.setTextSize(12);// 字体大小

			viewHolder.txtDay = new TextView(activity);// 日期
			viewHolder.txtDay.setGravity(Gravity.CENTER);
			viewHolder.txtDay.setId(position + 500);
			viewHolder.txtDay.setHeight(this.gridHeight);
			viewHolder.txtDay.setGravity(Gravity.CENTER);
			viewHolder.txtDay.setTextSize(15);
			viewHolder.imagebg = new ImageView(activity);
			viewHolder.imagebg.setVisibility(View.GONE);
			RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp1.addRule(RelativeLayout.CENTER_IN_PARENT);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			viewHolder.iv.addView(viewHolder.imagebg, lp1);
			viewHolder.iv.addView(viewHolder.txtDay, lp);
			
			convertView = viewHolder.iv;
			convertView.setTag(R.id.tag_first, viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag(R.id.tag_first);
		}

		Date myDate = (Date) getItem(position);
		Calendar calCalendar = Calendar.getInstance();
		calCalendar.setTime(myDate);

		final int iMonth = calCalendar.get(Calendar.MONTH);
		final int iDay = calCalendar.get(Calendar.DAY_OF_WEEK);

		// 判断周六周日
		viewHolder.imagebg.setVisibility(View.GONE);
		viewHolder.iv.setBackgroundColor(resources.getColor(R.color.white));
		// if (iDay == 7) {
		// // 周六
		// viewHolder.iv
		// .setBackgroundColor(resources.getColor(R.color.text_6));
		// } else if (iDay == 1) {
		// // 周日
		// viewHolder.iv
		// .setBackgroundColor(resources.getColor(R.color.text_7));
		// } else {
		//
		// }

		// 判断周六周日结束
		// 农历相关
		String str = new Festival().getPermanentFestivalName(calCalendar);
		if (str != null) {
		} else {
			str = LunarCalendar.getLauarTail(calCalendar);
		}
		// viewHolder.txtLunarDay.setText(str);

		// 设置背景颜色结束

		// 日期开始

		// 判断是否是当前月
		if (iMonth == iMonthViewCurrentMonth) {
			// txtToDay.setTextColor(resources.getColor(R.color.ToDayText));
			viewHolder.txtDay.setTextColor(resources
					.getColor(R.color.schadule_calender_monthintextcolor));
		} else {
			viewHolder.txtDay.setTextColor(resources
					.getColor(R.color.schadule_calender_titlelinecolor));
			// txtToDay.setTextColor(resources.getColor(R.color.noMonth));
		}
		if (equalsDate(calToday.getTime(), myDate)) {
			// 当前日期
			// viewHolder.iv.setBackgroundColor(resources
			// .getColor(R.color.event_center));
			viewHolder.txtDay.setTextColor(resources
					.getColor(R.color.activity_text_blue));
		}
		if (equalsDate2(myDate)) {
			// 保存日程日期
			viewHolder.imagebg.setVisibility(View.VISIBLE);
			viewHolder.imagebg
					.setBackgroundResource(R.drawable.calender_red_bg);
		}
		// 设置背景颜色
		if (equalsDate(calSelected.getTime(), myDate)) {
			// 选择的
			viewHolder.txtDay.setTextColor(resources.getColor(R.color.white));
			viewHolder.imagebg.setVisibility(View.VISIBLE);
			viewHolder.imagebg
					.setBackgroundResource(R.drawable.calender_green_bg);
		} else {
			// if (equalsDate(calToday.getTime(), myDate)) {
			// // 当前日期
			// viewHolder.iv.setBackgroundColor(resources
			// .getColor(R.color.calendar_zhe_day));
			// }
		}

		int day = myDate.getDate(); // 日期
		viewHolder.txtDay.setText(String.valueOf(day));
		Lunar myLunar = new Lunar();
		myLunar.setLunar(str);
		myLunar.setMyDate(myDate);
		viewHolder.iv.setTag(R.id.tag_second, myLunar);

		// LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// iv.addView(txtLunarDay, lp1);
		// 日期结束
		// iv.setOnClickListener(new OnClickListener() {
		// });

		return convertView;
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	private Boolean equalsDate(Date date1, Date date2) {

		if (date1.getYear() == date2.getYear()
				&& date1.getMonth() == date2.getMonth()
				&& date1.getDate() == date2.getDate()) {
			return true;
		} else {
			return false;
		}

	}

	private Boolean equalsDate2(Date date) {
		Calendar calCalendar = Calendar.getInstance();
		calCalendar.setTime(date);
		final int iYear = calCalendar.get(Calendar.YEAR);
		final int iMonth = calCalendar.get(Calendar.MONTH);
		final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);
		int mdate = iYear * 10000 + (iMonth + 1) * 100 + iDay;
		int time = mdate- ShortCut.CalendarCheckTime;
//		 Log.i(TAG,
//		 "mdate:"+mdate+"iYear:"+iYear+"iMonth:"+iMonth+"iDay:"+iDay);
		if(checkList[time]==1){
			return true;
		}
		return false;

	}

	public class ViewHolder {
		public TextView txtDay;
		public RelativeLayout iv;
		public LinearLayout imageLayout;
		public TextView txtLunarDay;
		public ImageView imagebg;
	}

}
