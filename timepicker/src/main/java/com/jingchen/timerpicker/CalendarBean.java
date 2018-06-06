package com.jingchen.timerpicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dangelo on 16/6/16.
 */
public class CalendarBean {
    //    String day[];
    int year1=2005;
    int month1=0;
    int day1=0;
    int orderdelay=1000;
    List<Integer> monthlist = new ArrayList<Integer>();
    List<Integer> daylist = new ArrayList<Integer>();

    public  void setYMD(int year, int month, int day1){
        this.day1 = day1;
        this.year1=year;
        this.month1=month;

    }
    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }


    public void setYear(int year)
    {
        this.year1=year;
    }

    public int getYear()
    {
        return year1;
    }

    public void setMonth(int month)
    {
        this.month1=month;
    }

    public int getMonth()
    {
        return month1;
    }

    public  int getCalendar(int month,int year)
    {
//        int a[]=new int[2];
//        Calendar date=Calendar.getInstance();
//        date.set(year,month-1,1);
//        int week=date.get(Calendar.DAY_OF_WEEK)-1;
        int day=0;

        //判断大月份
        if(month==1||month==3||month==5||month==7
                ||month==8||month==10||month==12)
        {
            day=31;
        }

        //判断小月
        if(month==4||month==6||month==9||month==11)
        {
            day=30;
        }

        //判断平年与闰年
        if(month==2)
        {
            if(((year%4==0)&&(year%100!=0))||(year%400==0))
            {
                day=29;
            }

            else
            {
                day=28;
            }
        }

//        for(int i=week,n=1;i<week+day;i++)
//        {
//            a[i]=String.valueOf(n) ;
//            n++;
//        }
//        a[0]=day;


        return day;
    }
    //获取接下来所有月份每个月截止日期的列表
    public List<Integer> getDaylist() {
        int temporderdelay = orderdelay;
        int day =  getCalendar(month1,year1);
        //day1 =1 ,orderdelay =61;month1 =2
//        当月剩余时间小于有效期
        while (day-day1<temporderdelay){
            //存入当月最大
            daylist.add(day);
            temporderdelay = temporderdelay-(day-day1);

//            月份++
            month1 = month1+1;
//            获取下个月最大时间
            if(month1==13){
                month1 =1;
                year1 = year1+1;
            }
            day =  getCalendar(month1,year1);
            day1=0;

        }
//        存入有效期剩余时间+当前月当日时间
        daylist.add(temporderdelay+day1);

        return daylist;
    }
}
