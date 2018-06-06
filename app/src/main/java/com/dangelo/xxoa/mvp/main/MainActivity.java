package com.dangelo.xxoa.mvp.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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

import com.dangelo.xxoa.AddCalenderActivity;
import com.dangelo.xxoa.AddMeetActivity;
import com.dangelo.xxoa.DocListActivity;
import com.dangelo.xxoa.R;
import com.dangelo.xxoa.mvp.base.ABaseActivity;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.main.five.FiveFragment;
import com.dangelo.xxoa.mvp.main.four.FourFragment;
import com.dangelo.xxoa.mvp.main.frist.FristFragment;
import com.dangelo.xxoa.mvp.main.second.SecondFragment;
import com.dangelo.xxoa.mvp.main.third.ThirdFragment;
import com.dangelo.xxoa.ui.MyViewPager;
import com.dangelo.xxoa.ui.StatusBarUtil;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.DbUtil;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends ABaseActivity
        implements MainContact.View ,FristFragment.Submit{
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ARG_SECTION_NUMBER = "section_number";
    private static int currentIndex = 0;
    protected LinearLayout bottomLayout = null;
    //    ColorStateList csl;
//    ColorStateList csl2;
    DrawerLayout drawer;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private RelativeLayout[] navs = null;
    private ImageButton[] navsImg = null;
    private ImageView RightIcon = null;
    private TextView[] navsText = null;
    private int[] navsImgID = {R.drawable.home,
            R.drawable.send, R.drawable.receive,
            R.drawable.meet, R.drawable.know};
    private int[] navsImgPressID = {R.drawable.home_select, R.drawable.send_select,
            R.drawable.receive_select, R.drawable.meet_select, R.drawable.know_select};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    //    private TextView title;
//    private TextView rightText;
    //side
    private TextView name;
    private TextView weather;
    private TextView weathertext;
    private ImageView RedIconIv;
    private ImageView sideClose;

    private MainPresenter mMainPresenter;
    private int TYPE = 0;


    private RelativeLayout SettingCleanLayout;
    private RelativeLayout SettingDownloadLayout;
    private RelativeLayout SettingCheckupdateLayout;
    private TextView settingLogoutBtn;
    private TextView clean;
    private TextView update;
    private TextView downnumTV;
    private int downNum;
    private boolean UpdateFlag;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private MyViewPager mViewPager;
    private boolean mToolbarVisible = true;
    private View mAllToolbarlayout;

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void toThisNEWActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        initdata();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        mMainPresenter = new MainPresenter(this);
        return mMainPresenter;
    }

    @Override
    protected void initView() {
        ShortCut.initDefaultDisplay(this);
        StatusBarUtil.setStatusBarColor(this, R.color.activity_title_bg);
        showGuide();
        setContentView(getLayoutId());
        getBundle();
        setupAppBar();
        initSide();
        initContainer();
        initBottomMenu();
        initContent();
    }

    protected Bundle getBundle() {
        return getIntent().getExtras();
    }

    protected void setupAppBar() {
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);
        mAllToolbarlayout = (View) findViewById(R.id.toolbarlayout);

//        JLog.d(TAG, "mToolbarVisible=" + mToolbarVisible+"mAllToolbarlayoutId="+mAllToolbarlayout.getId());
        if (!mToolbarVisible) {
            mAllToolbarlayout.setVisibility(View.GONE);
//            JLog.d(TAG, "set no toolbar");
            return;
        }
        mAppBarTitle = (TextView) findViewById(R.id.main_title);
        mAppBarRightIcon = (ImageView) findViewById(R.id.right_icon);
        mAppBarRightText = (TextView) findViewById(R.id.right_text);
        mAppBarRedIcon = (ImageView) findViewById(R.id.deliver_red_icon);
        mAppbarLeftBackIcon = (ImageView) findViewById(R.id.left_back_icon);
        mAppBarTitle.setOnClickListener(this);
        mAppBarTitle.setTextColor(getResources().getColor(R.color.white));
        mAppBarRightIcon.setOnClickListener(this);
        mAppBarRightText.setOnClickListener(this);
        mAppbarLeftBackIcon.setOnClickListener(this);
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        mAppBar = getSupportActionBar();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        drawer.setDrawerListener(toggle);

        toggle.syncState();
//        mAppBarTitle.setText(getResources().getText(R.string.Main_title_frist));
//        mAppBarRightText.setVisibility(View.VISIBLE);
    }

    private void initContainer() {
        currentIndex = 0;
        fragments.add(FristFragment.newInstance(1));
        fragments.add(SecondFragment.newInstance(2));
        fragments.add(ThirdFragment.newInstance(3));
        fragments.add(FourFragment.newInstance(4));
        fragments.add(FiveFragment.newInstance(5));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (MyViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setScrollble(false);

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        initdata();
    }

    protected void initSide() {
        name = (TextView) findViewById(R.id.name);
        weather = (TextView) findViewById(R.id.weather);
//        weathertext = (TextView) findViewById(R.id.weather_text);
//        weathertext.setTextColor(csl2);
        sideClose = (ImageView) findViewById(R.id.side_close);
        sideClose.setOnClickListener(this);


        settingLogoutBtn = (TextView) findViewById(R.id.setting_logout_btn);
        settingLogoutBtn.setOnClickListener(this);
        clean = (TextView) findViewById(R.id.setting_clean);
        update = (TextView) findViewById(R.id.setting_check_update);
        downnumTV = (TextView) findViewById(R.id.setting_download_num);

        SettingCleanLayout = (RelativeLayout) findViewById(R.id.setting_clean_layout);
        SettingCleanLayout.setOnClickListener(this);
        SettingDownloadLayout = (RelativeLayout) findViewById(R.id.setting_download_layout);
        SettingDownloadLayout.setOnClickListener(this);
        SettingCheckupdateLayout = (RelativeLayout) findViewById(R.id.setting_check_update_layout);
        SettingCheckupdateLayout.setOnClickListener(this);

    }

    private void initdata() {
//        if (SharedPrefUtil.getAudoLogin(this)) {
//
//            mMainPresenter.autoLogin();
//        }
        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！" + mMainPresenter.StringData(), this);
        } else {
            Log.i(TAG, "getUuCName:" + ShortCut.getUser(this).getUserEnglishName() + "getUuEName:" + ShortCut.getUser(this)
                    .getUserEnglishName());
            name.setText(getDateSx() + ShortCut.getUser(this).getUserEnglishName() + "!");


        }
        weather.setText(mMainPresenter.StringData());
    }

    public String getDateSx() {
        Calendar cal = Calendar.getInstance();
        String datestr = "";
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 9) {
            datestr = "早上好,";
        } else if (hour >= 9 && hour < 11) {
            datestr = "上午好,";
        } else if (hour >= 11 && hour < 13) {
            datestr = "中午好,";
        } else if (hour >= 13 && hour < 18) {
            datestr = "下午好,";
        } else {
            datestr = "晚上好,";
        }
        return datestr;
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
                .getColor(R.color.white));

    }

    // 设置旧的Menu
    public void setOleMenuTab(int currentIndex) {
        Log.d("Main", "setOleMenuTab:" + currentIndex);
        // navs[currentIndex].setBackgroundResource(R.drawable.nav_1pxbg);

        navsImg[currentIndex].setBackgroundResource(navsImgID[currentIndex]);
        navsText[currentIndex].setTextColor(getResources().getColor(
                R.color.activity_main_button_tv));
        fragments.get(currentIndex).onHiddenChanged(true);
    }

    // 设置新的Menu
    public void setNewMenuTab(int index) {
        Log.d("Main", "setNewMenuTab:" + index);
        // navs[currentIndex].setBackgroundResource(R.drawable.nav_selectedbg);
        navsImg[index]
                .setBackgroundResource(navsImgPressID[index]);
        navsText[index].setTextColor(getResources()
                .getColor(R.color.white));
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
                mAppBarTitle.setText(getResources().getText(R.string.Main_title_frist));
                mAppBarRightText.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mAppBarTitle.setText(getResources().getText(R.string.Main_title_second));
                mAppBarRightText.setVisibility(View.INVISIBLE);
                mAppBarRightText.setText(getResources().getText(R.string.title_second_righttext));
                mAppBarRightText.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                mAppBarTitle.setText(getResources().getText(R.string.Main_title_third));
                mAppBarRightText.setVisibility(View.INVISIBLE);
                mAppBarRightText.setText(getResources().getText(R.string.title_meet_righttext));
                mAppBarRightText.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                mAppBarTitle.setText(getResources().getText(R.string.Main_title_four));
                mAppBarRightText.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mAppBarTitle.setText(getResources().getText(R.string.Main_title_five));
                mAppBarRightText.setVisibility(View.INVISIBLE);
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
                ShortCut.docunmentflag = false;
                break;
            case R.id.icon1:
                goToTab(0);
                ShortCut.docunmentflag = false;
                break;

            case R.id.nav_2:
                goToTab(1);
                ShortCut.docunmentflag = false;
                break;
            case R.id.icon2:
                goToTab(1);
                ShortCut.docunmentflag = false;
                break;
            case R.id.nav_3:
                goToTab(2);
                ShortCut.docunmentflag = false;
                break;
            case R.id.icon3:
                goToTab(2);
                ShortCut.docunmentflag = false;
                break;
            case R.id.nav_4:
                goToTab(3);
                break;
            case R.id.icon4:
                goToTab(3);
                break;
            case R.id.nav_5:
                goToTab(4);
                ShortCut.docunmentflag = false;
                break;
            case R.id.icon5:
                goToTab(4);
                ShortCut.docunmentflag = false;
                break;
            case R.id.side_close:

                drawer.closeDrawer(GravityCompat.START);
                ;
                break;
//            case R.id.side_clock_rl:
//                goToDetailClock(1);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            case R.id.side_meet_rl:
//                goToDetailClock(2);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            case R.id.side_doc_rl:
//                goToDetailClock(3);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            case R.id.right_icon:
//                goToDetailClock(3);
//                break;
            case R.id.setting_download_layout:
                if (downNum > 0) {
                    Intent mIntent = new Intent();
                    mIntent.setClass(this, DocListActivity.class);

                    startActivity(mIntent);
                }
                break;

            case R.id.setting_check_update_layout:
                if (UpdateFlag) {
                    alertdownload();
                }
                break;
            case R.id.setting_logout_btn:
                mMainPresenter.LogOutOnClick();
                break;
            case R.id.setting_clean_layout:
                alertclean();
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

//    public void goToDetailClock(int index) {
//        Log.d("Main", "goToDetailClock:" + index);
//        switch (index) {
//            case 1:
//                sideClockNum.setVisibility(View.INVISIBLE);
//                sideClockIv.setVisibility(View.INVISIBLE);
//                onClick(navs[1]);
//                break;
//            case 2:
//                sideMeetNum.setVisibility(View.INVISIBLE);
//                sideMeetIv.setVisibility(View.INVISIBLE);
//                onClick(navs[2]);
//                break;
//            case 3:
//                sideDocNum.setVisibility(View.INVISIBLE);
//                sideDocIv.setVisibility(View.INVISIBLE);
//                ShortCut.docunmentflag =true;
//                onClick(navs[3]);
//                break;
//        }
//        if (sideClockIv.getVisibility() == View.INVISIBLE && sideMeetIv.getVisibility() == View.INVISIBLE &&
//                sideDocIv.getVisibility() == View.INVISIBLE) {
//            RedIconIv.setVisibility(View.INVISIBLE);
//        }
//
//    }

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

    private void alertclean() {
        TYPE = 1;
        showDialogWithTwoBtn("提示", "您确定要清空缓存？");
    }

    private void alertdownload() {
        TYPE = 2;
        showDialogWithTwoBtn("提示", "您有新的版本，需要更新么？");
    }

    protected void showDialogWithTwoBtn(String title, String msg) {
        new AlertDialog.Builder(this)
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
            FileService.deleteCache2(this);
            DbUtil.getInstance(this).CleanFileInfo();
            mMainPresenter.setValue();
            mMainPresenter.LoadFilenum();
        } else {
            mMainPresenter.doDownLoad();
        }

    }

    @Override
    public void setNumber(String number) {
        name.setText(number);
    }

    @Override
    public void setDate(String FolderSize) {
        if (clean != null) {
            clean.setText(FolderSize);
        }
    }

    @Override
    public void setUpdate(String Androidv, boolean updateFlag) {
        if (update != null)
            update.setText("新版本：" + Androidv);
        UpdateFlag = updateFlag;
    }

    @Override
    public void setLoadFilenum(int LoadFilenum) {
        if (update != null)
            downNum = LoadFilenum;
        downnumTV.setText(LoadFilenum + "");
    }

    @Override
    public void doDownLoad(String Androidvdurl) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri
                .parse(Androidvdurl)));
    }

    @Override
    public void submit(int comStype) {
        goToTab(comStype);
        switch (comStype) {
            case 1:
                ((SecondFragment) fragments.get(currentIndex)).setShowpage(0);
                break;
            case 2:
                ((ThirdFragment) fragments.get(currentIndex)).setShowpage(0);
                break;
            case 3:
                ((FourFragment) fragments.get(currentIndex)).setShowpage(0);
                break;
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
