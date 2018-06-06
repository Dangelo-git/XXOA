package com.dangelo.xxoa;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.ShortCut;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ARG_SECTION_NUMBER = "section_number";
    private static int currentIndex = 0;
    protected LinearLayout bottomLayout = null;
    ColorStateList csl;
    ColorStateList csl2;
    DrawerLayout drawer;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private RelativeLayout[] navs = null;
    private ImageButton[] navsImg = null;
    private ImageView RightIcon = null;
    private TextView[] navsText = null;
    private int[] navsImgID = {R.drawable.home,
            R.drawable.calendar, R.drawable.meeting,
            R.drawable.doc, R.drawable.more};
    private int[] navsImgPressID = {R.drawable.home_select, R.drawable.calendar_select,
            R.drawable.meeting_select, R.drawable.doc_select, R.drawable.more_select};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TextView title;
    private TextView rightText;
    //side
    private TextView name;
    private TextView weather;
    private TextView weathertext;
    private ImageView RedIconIv;
    private ImageView sideClose;
    private RelativeLayout sideClockRl;
    private TextView sideClockTv;
    private ImageView sideClockIv;
    private TextView sideClockNum;
    private RelativeLayout sideMeetRl;
    private TextView sideMeetTv;
    private ImageView sideMeetIv;
    private TextView sideMeetNum;
    private RelativeLayout sideDocRl;
    private TextView sideDocTv;
    private ImageView sideDocIv;
    private TextView sideDocNum;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "今天是" + mYear + "年" + mMonth + "月" + mDay + "日" + "，星期" + mWay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGuide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        ShortCut.initDefaultDisplay(this);
        currentIndex = 0;
        fragments.add(FristFragment.newInstance(1));
        fragments.add(CalendarFragment.newInstance(2));
        fragments.add(MeetFragment.newInstance(3));
        fragments.add(DocumentFragment.newInstance(4));
        fragments.add(FiveFragment.newInstance(5));
        title = (TextView) findViewById(R.id.main_title);
        rightText = (TextView) findViewById(R.id.right_text);
        rightText .setOnClickListener(this);
        ImageView iv = (ImageView) findViewById(R.id.right_icon);
        RedIconIv = (ImageView) findViewById(R.id.deliver_red_icon);
        title.setText(getResources().getText(R.string.Main_title_frist));
        title.setTextColor(getResources().getColor(R.color.white));
        iv.setVisibility(View.INVISIBLE);
        rightText.setVisibility(View.INVISIBLE);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        csl2 = (ColorStateList) getResources().getColorStateList(R.color.activity_text_blue);
        initSide();
        drawer.setDrawerListener(toggle);

        toggle.syncState();
        initBottomMenu();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setOleMenuTab(currentIndex);
                currentIndex = position;

                setNewMenuTab(currentIndex);
                showTitle(currentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initContent();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！" + StringData(), this);
            return;
        } else {
            name.setText("早上好，" + ShortCut.getUser(this).getUserEnglishName() + "!");


        }
        weather.setText(StringData());
    }

    protected void initSide() {
        csl = (ColorStateList) getResources().getColorStateList(R.color.white);
        name = (TextView) findViewById(R.id.name);
        name.setTextColor(csl);
        weather = (TextView) findViewById(R.id.weather);
        weather.setTextColor(csl);
//        weathertext = (TextView) findViewById(R.id.weather_text);
//        weathertext.setTextColor(csl2);
        sideClose = (ImageView) findViewById(R.id.side_close);
        sideClose.setOnClickListener(this);

//        sideClockTv = (TextView) findViewById(R.id.side_clock_tv);
//        sideClockTv.setTextColor(csl);
//        sideClockNum = (TextView) findViewById(R.id.side_clock_num);
//        sideClockNum.setTextColor(csl);
//        sideClockIv = (ImageView) findViewById(R.id.side_clock_dom);
//        sideClockRl = (RelativeLayout) findViewById(R.id.side_clock_rl);
//        sideClockRl.setOnClickListener(this);
//
//        sideMeetTv = (TextView) findViewById(R.id.side_meet_tv);
//        sideMeetTv.setTextColor(csl);
//        sideMeetNum = (TextView) findViewById(R.id.side_meet_num);
//        sideMeetNum.setTextColor(csl);
//        sideMeetIv = (ImageView) findViewById(R.id.side_meet_dom);
//        sideMeetRl = (RelativeLayout) findViewById(R.id.side_meet_rl);
//        sideMeetRl.setOnClickListener(this);
//
//        sideDocTv = (TextView) findViewById(R.id.side_doc_tv);
//        sideDocTv.setTextColor(csl);
//        sideDocNum = (TextView) findViewById(R.id.side_doc_num);
//        sideDocNum.setTextColor(csl);
//        sideDocIv = (ImageView) findViewById(R.id.side_doc_dom);
//        sideDocRl = (RelativeLayout) findViewById(R.id.side_doc_rl);
        sideDocRl.setOnClickListener(this);

        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！" + StringData(), this);
//            return;
        } else {
            Log.i(TAG, "getUuCName:"+ShortCut.getUser(this).getUserEnglishName()+"getUuEName:"+ShortCut.getUser(this).getUserEnglishName());
            name.setText("早上好，" + ShortCut.getUser(this).getUserEnglishName() + "!");


        }
        weather.setText(StringData());
    }

    /**
     * initial main body's content
     */
    protected void initContent() {
        Bundle bundle = getIntent().getExtras();
        int index = 0;
        if (bundle != null) {
            currentIndex = bundle.getInt("tabChoose");
            index = bundle.getInt("currentIndex");
        }
        bundle = new Bundle();
        bundle.putInt("currentIndex", index);
        // 显示指定页
        showTab(currentIndex); // 显示目标tab
        showTitle(currentIndex);
        navsImg[index]
                .setBackgroundResource(navsImgPressID[index]);
        navsText[index].setTextColor(getResources()
                .getColor(R.color.red));

//        setNewMenuTab(currentIndex);
    }

    // 设置旧的Menu
    public void setOleMenuTab(int currentIndex) {
        Log.d("Main", "setOleMenuTab:" + currentIndex);
        // navs[currentIndex].setBackgroundResource(R.drawable.nav_1pxbg);

        navsImg[currentIndex].setBackgroundResource(navsImgID[currentIndex]);
        navsText[currentIndex].setTextColor(getResources().getColor(
                R.color.grey));
        fragments.get(currentIndex).onHiddenChanged(true);
    }

    // 设置新的Menu
    public void setNewMenuTab(int index) {
        Log.d("Main", "setNewMenuTab:" + index);
        // navs[currentIndex].setBackgroundResource(R.drawable.nav_selectedbg);
        navsImg[index]
                .setBackgroundResource(navsImgPressID[index]);
        navsText[index].setTextColor(getResources()
                .getColor(R.color.red));
        fragments.get(index).onHiddenChanged(false);
//        switch (index){
//            case 0:
//                 PlaceholderFragment.Resume();
//            case 1:
//                 MeetFragment.Resume() ;
//            case 2:
//                 CalendarFragment.Resume() ;
//            case 3:
//                 PlaceholderFragment.Resume() ;
//            case 4:
//                 PlaceholderFragment.Resume() ;
//            default:
//                 PlaceholderFragment.Resume() ;
//        }

    }

    public void goToTab(int index) {
        Log.d("Main", "goToTab:" + index);
        setOleMenuTab(currentIndex);
        currentIndex = index;
        showTab(index); // 显示目标tab

        setNewMenuTab(currentIndex);
        showTitle(currentIndex);

    }

    private void showTitle(int index) {
        Log.d("Main", "showTitle:" + index);
        switch (index) {
            case 0:
                title.setText(getResources().getText(R.string.Main_title_frist));
                rightText.setVisibility(View.INVISIBLE);
                break;
            case 1:
                title.setText(getResources().getText(R.string.Main_title_second));
                rightText.setVisibility(View.VISIBLE);
                rightText.setText(getResources().getText(R.string.title_second_righttext));
                rightText.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                title.setText(getResources().getText(R.string.Main_title_third));
                rightText.setVisibility(View.VISIBLE);
                rightText.setText(getResources().getText(R.string.title_meet_righttext));
                rightText.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                title.setText(getResources().getText(R.string.Main_title_four));
                rightText.setVisibility(View.INVISIBLE);
                break;
            case 4:
                title.setText(getResources().getText(R.string.Main_title_five));
                rightText.setVisibility(View.INVISIBLE);
                break;

        }
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
//        PlaceholderFragment.newInstance(idx + 1);
        mViewPager.setCurrentItem(idx);
    }

    /**
     * initial bottom menu for this view
     */
    protected void initBottomMenu() {
        bottomLayout = (LinearLayout) findViewById(R.id.bottom_bar);
        bottomLayout.setFocusable(false);
        navs = new RelativeLayout[5];
        navsImg = new ImageButton[5];
        navsText = new TextView[5];
        // 设置点击事件
        bottomLayout.findViewById(R.id.icon1).setOnClickListener(this);
        bottomLayout.findViewById(R.id.icon2).setOnClickListener(this);
        bottomLayout.findViewById(R.id.icon3).setOnClickListener(this);
        bottomLayout.findViewById(R.id.icon4).setOnClickListener(this);
        bottomLayout.findViewById(R.id.icon5).setOnClickListener(this);

        navs[0] = (RelativeLayout) bottomLayout.findViewById(R.id.nav_1);
        navs[1] = (RelativeLayout) bottomLayout.findViewById(R.id.nav_2);
        navs[2] = (RelativeLayout) bottomLayout.findViewById(R.id.nav_3);
        navs[3] = (RelativeLayout) bottomLayout.findViewById(R.id.nav_4);
        navs[4] = (RelativeLayout) bottomLayout.findViewById(R.id.nav_5);
        navsImg[0] = (ImageButton) bottomLayout.findViewById(R.id.icon1);
        navsImg[1] = (ImageButton) bottomLayout.findViewById(R.id.icon2);
        navsImg[2] = (ImageButton) bottomLayout.findViewById(R.id.icon3);
        navsImg[3] = (ImageButton) bottomLayout.findViewById(R.id.icon4);
        navsImg[4] = (ImageButton) bottomLayout.findViewById(R.id.icon5);
        navsText[0] = (TextView) bottomLayout.findViewById(R.id.text1);
        navsText[1] = (TextView) bottomLayout.findViewById(R.id.text2);
        navsText[2] = (TextView) bottomLayout.findViewById(R.id.text3);
        navsText[3] = (TextView) bottomLayout.findViewById(R.id.text4);
        navsText[4] = (TextView) bottomLayout.findViewById(R.id.text5);
        // 设置点击事件
        for (int i = 0; i < navs.length; i++) {
            navs[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (v.getId()) {
            case R.id.nav_1:
                goToTab(0);
                ShortCut.docunmentflag =false;
                break;
            case R.id.icon1:
                goToTab(0);
                ShortCut.docunmentflag =false;
                break;

            case R.id.nav_2:
                goToTab(1);
                ShortCut.docunmentflag =false;
                break;
            case R.id.icon2:
                goToTab(1);
                ShortCut.docunmentflag =false;
                break;
            case R.id.nav_3:
                goToTab(2);
                ShortCut.docunmentflag =false;
                break;
            case R.id.icon3:
                goToTab(2);
                ShortCut.docunmentflag =false;
                break;
            case R.id.nav_4:
                goToTab(3);
                break;
            case R.id.icon4:
                goToTab(3);
                break;
            case R.id.nav_5:
                goToTab(4);
                ShortCut.docunmentflag =false;
                break;
            case R.id.icon5:
                goToTab(4);
                ShortCut.docunmentflag =false;
                break;
            case R.id.side_close:

                drawer.closeDrawer(GravityCompat.START);
                ;
                break;

            case R.id.right_icon:
                goToDetailClock(3);
                break;
            case R.id.right_text:
                Log.d("Main", "showTitle:" + currentIndex);
                switch (currentIndex) {
                    case 1:
                        Intent i = new Intent(MainActivity.this, AddCalenderActivity.class);
//
                        startActivity(i);
                        break;
                    case 2:
                        Intent i1 = new Intent(MainActivity.this, AddMeetActivity.class);
//
                        startActivity(i1);

                        break;
                }

                break;

            default:
                break;
        }
    }

    public void goToDetailClock(int index) {
        Log.d("Main", "goToDetailClock:" + index);
        switch (index) {
            case 1:
                sideClockNum.setVisibility(View.INVISIBLE);
                sideClockIv.setVisibility(View.INVISIBLE);
                onClick(navs[1]);
                break;
            case 2:
                sideMeetNum.setVisibility(View.INVISIBLE);
                sideMeetIv.setVisibility(View.INVISIBLE);
                onClick(navs[2]);
                break;
            case 3:
                sideDocNum.setVisibility(View.INVISIBLE);
                sideDocIv.setVisibility(View.INVISIBLE);
                ShortCut.docunmentflag =true;
                onClick(navs[3]);
                break;
        }
        if (sideClockIv.getVisibility() == View.INVISIBLE && sideMeetIv.getVisibility() == View.INVISIBLE &&
                sideDocIv.getVisibility() == View.INVISIBLE) {
            RedIconIv.setVisibility(View.INVISIBLE);
        }

    }

    private void showGuide() {
        DataShare dataShare = new DataShare(getApplicationContext());
        if (dataShare.read("showGuide").equals("")) {
            final RelativeLayout imageView1 = (RelativeLayout) findViewById(R.id.guildIcon1);
            TextView guildblowe = (TextView) findViewById(R.id.guildblowe);
            guildblowe.setTextColor(getResources().getColor(R.color.white));
            guildblowe.setText("V " + ShortCut.GetVersionCode(MainActivity.this) + "");
            imageView1.setVisibility(View.VISIBLE);
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Log.i("ACM", "imageView1 click");
                    imageView1.setVisibility(View.GONE);
                    // GuideAnimation(imageView1,imageView2);
                }
            });
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    imageView1.setVisibility(View.GONE);
                }
            }, 2000);

//            dataShare.write("showGuide", "Showed");
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            Log.d("Main", "newInstance:" + sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public static void Resume() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
//            rootView.setBackgroundColor(getResources().getColor(R.color.black));
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.d("Main", "getItem:" + position);
            switch (position) {
                case 0:
                    return fragments.get(0);
                case 1:
                    return fragments.get(1);
                case 2:
                    return fragments.get(2);
                case 3:
                    return fragments.get(3);
                case 4:
                    return fragments.get(4);
                default:
                    fragments.add(PlaceholderFragment.newInstance(position + 1));
                    return fragments.get(5);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("Main", "getPageTitle:" + position);
            switch (position) {
                case 0:

                    return "SECTION 1";
                case 1:

                    return "SECTION 2";
                case 2:

                    return "SECTION 3";
                case 3:

                    return "SECTION 3";
                case 4:

                    return "SECTION 3";
            }
            return null;
        }
    }

}
