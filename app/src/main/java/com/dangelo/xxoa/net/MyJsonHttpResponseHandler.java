package com.dangelo.xxoa.net;

import android.util.Log;

import com.dangelo.xxoa.application.XZOAApplication;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.json.SOAPStringB;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ukiy on 15/4/9.
 */
public class MyJsonHttpResponseHandler extends AsyncHttpResponseHandler {
    private XZOAApplication app;
    private String TAG=MyJsonHttpResponseHandler.class.getName();

    public MyJsonHttpResponseHandler() {
        app = XZOAApplication.getInstance();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String responseString="";
        try {
             responseString=new String(responseBody,"UTF-8");
            Log.d(TAG, "threeServerdata（成功）=" + statusCode +"-"+StringEscapeUtils.unescapeHtml(responseString));
//            StringEscapeUtils.unescapeHtml(responseString);
            String responseJosn = SOAPStringB.parserSoapResPonse(StringEscapeUtils.unescapeHtml(responseString));
            Log.d(TAG, "responseJosn=" +responseJosn);
            Json json1=new Json();
//                        result=InstallJson.installResponseJson(InstallJson.installJson());
//            Log.d(TAG,"result"+result);
            Gson gson = new Gson();
            Json json = gson.fromJson(responseJosn, Json.class);
            if(json.getCode().equals("0")){
                resultSuccess(json);
            }else{
                resultFailure(json);
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
            Json json = gson.fromJson("{}", Json.class);
            resultFailure(json);
            responseString=new String(responseBody,"UTF-8");
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


    public void resultSuccess(Json result) throws JSONException {
//        Toast.makeText(app, result.getString("msg"), Toast.LENGTH_SHORT).show();
    }

    public void resultFailure(Json result) throws JSONException {

    }

    public void finallyDo() {
    }



}
