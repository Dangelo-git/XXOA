package com.dangelo.xxoa.net;

import android.util.Log;
import android.widget.Toast;

import com.dangelo.xxoa.application.XZOAApplication;
import com.dangelo.xxoa.bean.DataBody;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.JsonBody;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ukiy on 15/4/9.
 */
public class MyJsonBodyHttpResponseHandler extends AsyncHttpResponseHandler {
    private XZOAApplication app;
    private String TAG=MyJsonBodyHttpResponseHandler.class.getName();

    public MyJsonBodyHttpResponseHandler() {
        app = XZOAApplication.getInstance();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String responseString="";
        try {
             responseString=new String(responseBody,"UTF-8");
            Log.d(TAG, "threeServerdata（成功）=" + statusCode +"-"+StringEscapeUtils.unescapeHtml(responseString));
//            StringEscapeUtils.unescapeHtml(responseString);
            String responseJosn = StringEscapeUtils.unescapeHtml(responseString);
//            Log.d(TAG, "responseJosn=" +responseJosn);
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
//            Log.d(TAG,"result"+result);
            Gson gson = new Gson();
            JsonBody json = gson.fromJson(responseJosn, JsonBody.class);
            if(json.getCode().equals("00000000")){
                resultSuccess(json.getData());
            }else{
                resultFailure(json.getErrorMsgMap());
            }

            Log.d(TAG,json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            finallyDo();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        String responseString="";
        try {
            Gson gson = new Gson();
            responseString=new String(responseBody,"UTF-8");
            JsonBody json = gson.fromJson(responseString, JsonBody.class);
            resultFailure(json.getErrorMsgMap());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            finallyDo();
        }
        Log.d(TAG, "threeServerdata（失败）=" + statusCode +"-"+responseString);
    }

//    @Override
//    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//        Log.d(TAG, "threeServerdata(成功)=" + responseString);
//
////        try {
////            String code=result.getString("code");
////            if(code.equals("00000000")) {
////                resultSuccess(result);
////            }else {
////                resultFailure(result);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void onSuccess(int statusCode, Header[] headers, String responseString) {
//        Log.d(TAG, "threeServerdata（失败）=" + statusCode + responseString);
////        finallyDo();
//    }

//    @Override
//    public void onSuccess(int statusCode, Header[] headers, JSONObject result) {
//        Log.d(TAG, "threeServerdata(成功)=" + result.toString());
//        //模拟并发
////        if(ShortCut.SMSRESPONSEID==0) {
////            ShortCut.SMSRESPONSEID = 1;
////        }else {
////            ShortCut.SMSRESPONSEID = 0;
////        }
////        Json json=new Json();
////        result= InstallJson.installResponseJson(json);
//
//
//
//
//        try {
//            String code=result.getString("code");
//            if(code.equals("00000000")) {
//                resultSuccess(result);
//            }else {
//                resultFailure(result);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//       Toast.makeText(app, "网络错误：" + statusCode + errorResponse, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "threeServerdata（失败）=" + statusCode + errorResponse);
//        finallyDo();
//    }


    public void resultSuccess(DataBody result) throws JSONException {
//        Toast.makeText(app, result.getString("msg"), Toast.LENGTH_SHORT).show();
    }

    public void resultFailure(Map result) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        map = result;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
//        Toast.makeText(app, result.get("msg"), Toast.LENGTH_SHORT).show();
    }

    public void finallyDo() {
    }



}
