package com.dangelo.xxoa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangelo.xxoa.application.XZOAApplication;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/7/6.
 */
public class BaseActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private ImageView leftArrIV;
    private TextView titleToolbar;
    private String TAG=BaseActivity.class.getName();
    private String toolbar_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addExitActivity(Context c);
        MobclickAgent.setDebugMode(true);


    }



    public void addExitActivitys(Activity c) {
        XZOAApplication.getInstance().addActivity(c);
    }

    public void initToolbar(String toolbarName){
//        toolbar = (Toolbar) findViewById(R.id.bound_send_toolbar);
        titleToolbar = (TextView) findViewById(R.id.title_toolbar);
        titleToolbar.setTextColor(getResources().getColor(R.color.white));
        leftArrIV = (ImageView)findViewById(R.id.left_arr);
        titleToolbar.setText(toolbarName);
        leftArrIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
