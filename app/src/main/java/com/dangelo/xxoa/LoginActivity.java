package com.dangelo.xxoa;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.user;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.togglebutton.ToggleButton;
import com.dangelo.xxoa.ui.MyRoundProgressBar;
import com.dangelo.xxoa.uitl.DataShare;
import com.dangelo.xxoa.uitl.SharedPrefUtil;
import com.dangelo.xxoa.uitl.ShortCut;
import com.dangelo.xxoa.uitl.Testswith;
import com.google.gson.Gson;

import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private final String mPageName = "WelActivity";
    String peerIP = "192.168.1.10";
    String peerPort = "50600";
    String msg = "";
    MyRoundProgressBar myProgressBar;
    private String Flag = "0";
    private EditText CallednameEt;
    private EditText CalledpwdEt;

    private TextView CommitBtn;
    private String TAG = LoginActivity.this.getClass().getName();
    private EditText vertificationEt;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private boolean isAutoLogin;
    private String name;
    private String md5Password;
    private String code;
    private ProgressDialog mProgressDialog;
    private ToggleButton ConnectShock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MobclickAgent.setDebugMode(true);
        Bundle bundle = getIntent().getExtras();

        if (ShortCut.getUser(this) != null) {
            if (SharedPrefUtil.getAudoLogin(this)){
//                Intent intent = new Intent();
//                intent.setClass(WelActivity.this, com.dangelo.xxoa.mvp.main.MainActivity.class);
//                startActivity(intent);
                com.dangelo.xxoa.mvp.main.MainActivity.toThisActivity(this, null);
                finish();
            }

        }
        initview();
//        getLocal();

    }









    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    private void initview() {
        myProgressBar = new MyRoundProgressBar(this);
        CommitBtn = (TextView) findViewById(R.id.setting_login_btn);
        CallednameEt = (EditText) findViewById(R.id.called_name_et);


        CalledpwdEt = (EditText) findViewById(R.id.called_pwd_et);
        CallednameEt.setTextColor(getResources().getColor(R.color.black));
        CalledpwdEt.setTextColor(getResources().getColor(R.color.black));
        CommitBtn.setTextColor(getResources().getColor(R.color.white));


        CommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Testswith.Testloginname){
                    CallednameEt.setText("xtgly");
                    CalledpwdEt.setText("kk");
                }
                if (CallednameEt.getText().equals("")) {
                    msg = "用户名不能为空！";
                    return;
                }
                if (CalledpwdEt.getText().equals("")) {
                    msg = "密码不能为空！";
                    return;
                }
                myProgressBar.initDialog();
                ShortCut.showToast(msg, LoginActivity.this);
//                if (Flag.equals("0")) {
//                    if (CalledIpEt.getText().equals("")) {
//                        msg = "用户名不能为空！";
//                        return;
//                    }
//                    if (CalledRtpEt.getText().equals("")) {
//                        msg = "密码不能为空！";
//                        return;
//                    }
//
//                } else if (Flag.equals("1")) {
//                    if (CalledIpEt.getText().equals("")) {
//                        msg = "IP地址不能为空！";
//                        return;
//                    }
//                    if (CalledRtpEt.getText().equals("")) {
//                        msg = "RTP端口不能为空！";
//                        return;
//                    }
//                    if (CalledIpEt.getText().equals(WifiAdd)) {
//                        msg = "RTP端口不能与本机端口号相同";
//                        return;
//                    }
//
//                }

                myProgressBar.initDialog();
                LogInOnClick();
//


            }
        });
        ConnectShock = (ToggleButton) findViewById(R.id.connect_shock_switch);

        if (SharedPrefUtil.getAudoLogin(this)) {

            ConnectShock.setToggleOn();
        } else {
            ConnectShock.setToggleOff();
        }
        ConnectShock.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                SharedPrefUtil.setAudoLogin(on, LoginActivity.this);
            }
        });



    }


    //	通
    private void LogInOnClick() {
        name = CallednameEt.getText().toString();
        md5Password = CalledpwdEt.getText().toString();
        Json json = new Json();

        json.setUuEName(name);
        json.setUuPassword(md5Password);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);

        NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(API.Method_LOGIN, jsonStr, "")
                , new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);


                user user = result.getUser();
//                Log.d(TAG, "result=" + "uId:" + user.getuId() + " UuCName:" + user.getUuCName() + " UuEName:" + user
//                        .getUuEName() + " UuKeyID:" + user.getUuKeyID());
//                DataShare share = new DataShare(WelActivity.this);
//                share.write("uId", user.getuId() + "");
//                share.write("UuCName", user.getUuCName());
//                share.write("UuEName", user.getUuEName());
//                share.write("UuKeyID", user.getUuKeyID());
//                share.write("Password", md5Password);
////				Log.d(TAG, "result=" + "uId:"+user.getuId()+" UuCName:"+user.getUuCName()+" UuEName:"+user.getUuEName
//// ()+" UuKeyID:"+user.getUuKeyID());
//                ShortCut.showToast("登录成功！", WelActivity.this);
//                myProgressBar.colseDialog();
//                Intent intent = new Intent();
//                intent.setClass(WelActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
                ShortCut.showToast("登录失败！", LoginActivity.this);
                myProgressBar.colseDialog();
            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                myProgressBar.colseDialog();
                if(Testswith.Testlogin){
                    Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                }
//
            }
        });


    }
//    public LocationClient mLocationClient = null;
//    public BDLocationListener myListener = new MyLocationListener();
//    public void getLocal() {
//        Log.i(TAG,"getLocal");
//        mLocationClient = new LocationClient(getApplicationContext());
//        initLocation();
//        //声明LocationClient类
//        mLocationClient.registerLocationListener( myListener );
//        //注册监听函数
//        mLocationClient.start();
//    }
//
//    private void initLocation(){
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//
//        option.setCoorType("bd09ll");
//        //可选，默认gcj02，设置返回的定位结果坐标系
//
//        int span=1000;
//        option.setScanSpan(span);
//        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//
//        option.setIsNeedAddress(true);
//        //可选，设置是否需要地址信息，默认不需要
//
//        option.setOpenGps(true);
//        //可选，默认false,设置是否使用gps
//
//        option.setLocationNotify(true);
//        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//
//        option.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//
//        option.setIsNeedLocationPoiList(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//
//        option.setIgnoreKillProcess(false);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//
//        option.SetIgnoreCacheException(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集
//
//        option.setEnableSimulateGps(false);
//        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//
//        mLocationClient.setLocOption(option);
//    }
//    public class MyLocationListener implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//
//            //获取定位结果
//            StringBuffer sb = new StringBuffer(256);
//
//            sb.append("time : ");
//            sb.append(location.getTime());    //获取定位时间
//
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());    //获取类型类型
//
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());    //获取纬度信息
//
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());    //获取经度信息
//
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());    //获取定位精准度
//
//            if (location.getLocType() == BDLocation.TypeGpsLocation){
//
//                // GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());    // 单位：公里每小时
//
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());    //获取卫星数
//
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());    //获取海拔高度信息，单位米
//
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());    //获取方向信息，单位度
//
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());    //获取地址信息
//
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//
//                // 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());    //获取地址信息
//
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());    //获取运营商信息
//
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
//
//                // 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//
//            }
//
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());    //位置语义化信息
//
//            List<Poi> list = location.getPoiList();    // POI数据
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//
//            Log.i("BaiduLocationApiDem", sb.toString());
//        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//            Log.i("BaiduLocationApiDem", "onConnectHotSpotMessage");
//        }
//
//    }


}
