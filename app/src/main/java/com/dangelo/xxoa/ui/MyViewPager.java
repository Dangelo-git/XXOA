package com.dangelo.xxoa.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author lz.
 * @date on 2017/12/4 19:07.
 */

public class MyViewPager extends ViewPager {

    /*
      更改scrollble的值可设置是否可滑动，默认true为可滑动
       */
    private boolean scrollble = true;

    private int startX;
    private int startY;

    public MyViewPager(Context context)
    {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (!scrollble)
        {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

//                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startX);
                if (Math.abs(dY)>0)  // 说明上下方向滑动了
                {
                    return false;
                } else
                {
                    return true;
                }
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    public boolean isScrollble()
    {
        return scrollble;
    }

    public void setScrollble(boolean scrollble)
    {
        this.scrollble = scrollble;
    }

    //去除页面切换时的滑动翻页效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }
}
