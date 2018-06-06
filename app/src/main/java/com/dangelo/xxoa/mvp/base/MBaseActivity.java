package com.dangelo.xxoa.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.mvp.main.MainActivity;
import com.dangelo.xxoa.uitl.ActivityCollector;
import com.jiongbull.jlog.JLog;
import com.umeng.analytics.MobclickAgent;



/**
 * Created by mga on 2016/7/6.
 */
public abstract class MBaseActivity<A extends MBaseActivity,V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {
    //    private int netMobile;//网络类型
    //    private Toolbar toolbar;
//    private ImageView leftArrIV;
//    private TextView titleToolbar;
//    private String toolbar_name;
//    private ImageView rightArrIV;
    protected String TAG = getClass().getSimpleName();
    protected ActionBar mAppBar;
    protected TextView mAppBarTitle;
    protected ImageView mAppBarRightIcon;
    protected TextView mAppBarRightText;
    protected ImageView mAppBarRedIcon;//抽屉消息提醒小圆点
    protected ImageView mAppbarLeftBackIcon;//返回按钮
    protected Toolbar mToolbar;
    protected T mPresenter;
//    protected A mActivity;
    private boolean mToolbarVisible=true;
    private View mAllToolbarlayout;
    protected String mPageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        JLog.d(TAG, "onCreate");
        initialize();

    }

    public static void toThisActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /**
     * 获得页面布局Id
     *
     * @return 布局Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    protected abstract T createPresenter();
//    protected abstract A saySonActivity();

//    /**
//     * 初始化数据
//     */
//    protected abstract void initData();

    protected void setupAppBar() {
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);
        mAllToolbarlayout = (View) findViewById(R.id.toolbarlayout);

//        JLog.d(TAG, "mToolbarVisible=" + mToolbarVisible+"mAllToolbarlayoutId="+mAllToolbarlayout.getId());
        if(!mToolbarVisible){
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
        mAppBarRightIcon.setOnClickListener(this);
        mAppBarRightText.setOnClickListener(this);
        mAppbarLeftBackIcon.setOnClickListener(this);
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        mAppBar = getSupportActionBar();
    }

    protected void initialize() {
        setContentView(getLayoutId());
        getBundle();
        setupAppBar();
        initView();
//        this.mActivity=saySonActivity();
        ActivityCollector.addActivity(this);
//        initData();
        //创建presenter
        this.mPresenter = createPresenter();
//        关联view
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        //友盟
        MobclickAgent.setDebugMode(true);


                mPageName=getClass().getSimpleName();
//        JLog.d(TAG,"mpagename="+mPageName);
    }
    protected  void showNoToolbar(){
        JLog.d(TAG, "showNoToolbar");
        mToolbarVisible=false;
    }
    protected void showFullScreenIfSupport() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        JLog.d(TAG, "--->onDestroy");
        ActivityCollector.removeActivity(this);
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        JLog.d(TAG, "--->onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        JLog.d(TAG, "--->onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        JLog.d(TAG, "--->onResume");
//        JLog.d(TAG, "--->onResume  mPageName="+mPageName);
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        JLog.d(TAG, "--->onPause");
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(this);
    }

    protected Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK )) {
            System.out.println("按下了back键   onKeyDown()");
            finish();
            return false;
        }else{

            return super.onKeyDown(keyCode, event);
        }
    }
}

