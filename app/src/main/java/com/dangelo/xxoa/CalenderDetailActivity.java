package com.dangelo.xxoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dangelo.xxoa.bean.schedule;
import com.dangelo.xxoa.uitl.ShortCut;

/**
 * Created by Administrator on 2016/6/28.
 */
public class CalenderDetailActivity extends BaseActivity {
    public static final String TAG = CalenderDetailActivity.class.getSimpleName();
    private TextView calender_title_tv;
    private TextView calender_address_tv;
    private TextView calender_constitutor_tv;
    private TextView calender_startdate_tv;
    private TextView calender_enddate_tv;
    private TextView calender_isallday_tv;
    private TextView calender_remark_tv;

    private schedule Schedule;
    private TextView rightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenderdetail);
        initToolbar("日程详情");
//        getData();
        initViews();
        rightText = (TextView) findViewById(R.id.right_text);
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("修改");
        rightText.setTextColor(getResources().getColor(R.color.white));
        initOnClick();

    }



    private void initViews() {
        calender_title_tv = (TextView) findViewById(R.id.calender_title_tv);

        calender_address_tv = (TextView) findViewById(R.id.calender_address_tv);
        calender_constitutor_tv = (TextView) findViewById(R.id.calender_constitutor_tv);
        calender_startdate_tv = (TextView) findViewById(R.id.calender_startdate_tv);
        calender_enddate_tv = (TextView) findViewById(R.id.calender_enddate_tv);
        calender_isallday_tv = (TextView) findViewById(R.id.calender_isallday_tv);
        calender_remark_tv = (TextView) findViewById(R.id.calender_remark_tv);

        if(ShortCut.Schedule!=null){
            Schedule = ShortCut.Schedule;
            calender_title_tv.setText(Schedule.getTopic());
            calender_address_tv.setText(Schedule.getAddress());
            calender_constitutor_tv.setText(Schedule.getConstitutor());
            calender_startdate_tv.setText(Schedule.getStartDate());
            calender_enddate_tv.setText(Schedule.getEndDate());
            Log.i(TAG,"Schedule.getIsallday():"+Schedule.getIsallDay()+"Schedule.getTopic()"+Schedule.getTopic());
            if(Schedule.getIsallDay().equals("true")){
                calender_isallday_tv.setText("是");
            }else{
                calender_isallday_tv.setText("否");
            }

            calender_remark_tv.setText(Schedule.getRemark());
        }



    }

    private void initOnClick() {
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShortCut.Schedule = null;
                ShortCut.Schedule = Schedule;

                Intent mIntent = new Intent();
                mIntent.setClass(CalenderDetailActivity.this, AddCalenderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("from", "CalenderDetailActivity");
                mIntent.putExtras(bundle);
                startActivity(mIntent);
                finish();
            }
        });

    }


}
