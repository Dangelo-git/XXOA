package com.dangelo.xxoa.net;

import android.content.Context;
import android.util.Log;

import com.dangelo.xxoa.json.InstallJson;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jiongbull.jlog.JLog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;


/**
 * Created by Ljk on 15/03/20.
 */
public class NetEngine {
    public static final String TAG = NetEngine.class.getSimpleName();
    private static AsyncHttpClient client = new AsyncHttpClient(true,8080,8443);
//    private static AsyncHttpClient client = new AsyncHttpClient();
    // 待部署现网后，将修改为现网访问地址
    //内网地址
//    public final static String BASE_URL = "http://10.1.62.23:8080";
    //公网地址http://124.204.68.210:11083 /transportOA-APP
    public final static String BASE_URL = "http://124.204.68.210:11083/transportOA-APP/";
    //    public final static String GET_URL = "https://api.douban.com/v2/user/abei";
    public final static String GET_URL = "http://10.54.40.50/cxfservices/cloudTeminalServiceFacade";
    public final static String GET_URL1 = "http://10.54.40.50:8088/services/BrowseService";
    private static RequestHandle downloadHandler;


    public static void downloadFile(FileAsyncHttpResponseHandler handler, String url) {
        downloadHandler = client.get(url, handler);
    }

    public static void cancelDownloadFile() {
        if (downloadHandler == null) return;
        downloadHandler.cancel(true);
    }

    public static void submitPostTask( Context c,  String SOAPStringB,  MyJsonHttpResponseHandler handler) {
        Log.d(TAG, "thread==" + Thread.currentThread().getName()+GET_URL);
        //模拟并发
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    if(ShortCut.SMSRESPONSE==0) {
//                        Thread.sleep(10000);
//                    }else{
//                        Thread.sleep(5000);
//                    }

        HttpEntity entity = InstallJson.installJsonEntity(SOAPStringB);
        client.post(c, GET_URL,
                entity, "application/json;charset=UTF-8", handler);
//                    client.get(c, GET_URL, handler);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


    }
    public static void submitPostTask1( Context c,  String SOAPStringB,  MyJsonHttpResponseHandler handler) {
        Log.d(TAG, "thread==" + Thread.currentThread().getName()+GET_URL1);
        //模拟并发
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    if(ShortCut.SMSRESPONSE==0) {
//                        Thread.sleep(10000);
//                    }else{
//                        Thread.sleep(5000);
//                    }

        HttpEntity entity = InstallJson.installJsonEntity(SOAPStringB);
        client.post(c, GET_URL1,
                entity, "application/json;charset=UTF-8", handler);
//                    client.get(c, GET_URL, handler);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


    }

    public static void submitPostTask(Context c, String uri, String SOAPStringB, MyJsonBodyHttpResponseHandler handler) {
        HttpEntity entity = null;
        try {
            JsonObject jsonObject = new JsonParser().parse(SOAPStringB).getAsJsonObject();
            if(!uri.equals(API.Method_LOGIN_USERLOGIN)){
               if(ShortCut.getUser(c)!=null){

                   jsonObject.addProperty("userKey", ShortCut.getUser(c).getUSER_KEY());
               }else{
                   JLog.e(TAG,"(ShortCut.getUser(c)==null");
               }
            }

            entity = InstallJson.installJsonEntity(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


//        client.post(c, BASE_URL+uri, entity, "application/x-www-form-urlencoded;charset=utf-8", handler);
        client.post(c, BASE_URL+uri, entity, "application/json;charset=UTF-8", handler);
    }
    public static void submitPostTask(Context c, String uri, JSONObject jsonObject, JsonHttpResponseHandler handler) {
        HttpEntity entity = null;
        try {
            jsonObject.put("","a");
            entity = new StringEntity(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        client.post(c, BASE_URL+uri, entity, "application/json;charset=UTF-8", handler);
//        client.get(c, GET_URL, handler);
    }

    public static void submitGetTask(Context c, String uri, String[] params, JsonHttpResponseHandler handler) {
        client.get(c, GET_URL, handler);
    }



//    public static SchemeRegistry getSchemeRegistry() {
//        try {
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStore.load(null, null);
//            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
//            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//            HttpParams params = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(params, 10000);
//            HttpConnectionParams.setSoTimeout(params, 10000);
//            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
//            SchemeRegistry registry = new SchemeRegistry();
//            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//            registry.register(new Scheme("https", sf, 443));
//            return registry;
//        } catch (Exception e) {
//            return null;
//        }
//
//
    }
