package com.dangelo.xxoa.json;

import android.util.Log;

import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.jiongbull.jlog.JLog;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Administrator on 2016/6/3.
 */
public class InstallJson {
    private static final String TAG = InstallJson.class.toString();
    private static final String MISSMSG = "{\n" +
            "    \"code\": \"00000000\",\n" +
            "    \"description\": \"Success\",\n" +
            "    \"missmsg_list\": [\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000011\",\n" +
            "            \"msg_type\": \"1\",\n" +
            "            \"msg_date\": \"1470398569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000014\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1471491569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000012\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1470192569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000016\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1472594569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000017\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1472193569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000018\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1471099569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"msg_number\": \"13900000019\",\n" +
            "            \"msg_type\": \"3\",\n" +
            "            \"msg_date\": \"1475590569401\",\n" +
            "            \"msg_content\": \"以AS收到发送短信请求的时间为准1\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private static final String MISSRECORD ="{\n" +
            "    \"code\": \"00000000\",\n" +
            "    \"description\": \"Success\",\n" +
            "    \"missrecord_list\": [\n" +
            "        {\n" +
            "            \"record_number\": \"13900000011\",\n" +
            "            \"record_type\": \"2\",\n" +
            "            \"record_date\": \"1408103878424\",\n" +
            "            \"record_duration\": \"300\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"record_number\": \"13900000014\",\n" +
            "            \"record_type\": \"3\",\n" +
            "            \"record_date\": \"14081038789994\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private static final String USERINFO ="{\n" +
            "    \"business_list\": [\n" +
            "        {\n" +
            "            \"business_code\": \"20010001\",\n" +
            "            \"business_info\": \"该业务用于出国接听国内国内电话、收发短信。\",\n" +
            "            \"business_name\": \"语音套餐国外通话业务\",\n" +
            "            \"package_list\": [\n" +
            "                {\n" +
            "                    \"package_code\": \"2011\",\n" +
            "                    \"package_enddate\": \"1408103878424\",\n" +
            "                    \"package_info\": \"该套餐用于出国接听国内电话，有效期7天。\",\n" +
            "                    \"package_name\": \"7天畅听\",\n" +
            "                    \"package_outlet\": \"生效日期后7天内有效\",\n" +
            "                    \"package_price\": \"3.00\",\n" +
            "                    \"package_startdate\": \"1408103878424\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"package_code\": \"1011\",\n" +
            "                    \"package_enddate\": \"1408103878424\",\n" +
            "                    \"package_info\": \"该套餐用于出国收发短信，300条。\",\n" +
            "                    \"package_name\": \"300畅发\",\n" +
            "                    \"package_outlet\": \"生效月有效，300条限额\",\n" +
            "                    \"package_price\": \"3.00\",\n" +
            "                    \"package_startdate\": \"1408103878424\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"code\": \"00000000\",\n" +
            "    \"description\": \"Success\",\n" +
            "    \"sip_code\": \"9994a1f5f40240e78aa4aae29c8b93c8\"\n" +
            "}";
    private static final String USERINFO1 ="{\n" +
            "    \"business_list\": [\n" +
            "        {\n" +
            "            \"business_code\": \"20010001\",\n" +
            "            \"business_info\": \"该业务用于出国接听国内国内电话、收发短信。\",\n" +
            "            \"business_name\": \"语音套餐国外通话业务\",\n" +
            "            \"package_list\": [\n" +
            "                {\n" +
            "                    \"package_code\": \"2011\",\n" +
            "                    \"package_enddate\": \"1408103878424\",\n" +
            "                    \"package_info\": \"该套餐用于出国接听国内电话，有效期7天。\",\n" +
            "                    \"package_name\": \"7天畅听\",\n" +
            "                    \"package_outlet\": \"生效日期后7天内有效\",\n" +
            "                    \"package_price\": \"3.00\",\n" +
            "                    \"package_startdate\": \"1408103878424\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"package_code\": \"1011\",\n" +
            "                    \"package_enddate\": \"1408103878424\",\n" +
            "                    \"package_info\": \"该套餐用于出国收发短信，300条。\",\n" +
            "                    \"package_name\": \"300畅发\",\n" +
            "                    \"package_outlet\": \"生效月有效，300条限额\",\n" +
            "                    \"package_price\": \"3.00\",\n" +
            "                    \"package_startdate\": \"1408103878424\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"business_code\": \"10010002\",\n" +
            "            \"business_info\": \"该业务用于国内出国收发短信。\",\n" +
            "            \"business_name\": \"短信套餐国内通话业务\",\n" +
            "            \"package_list\": [\n" +
            "                {\n" +
            "                    \"package_code\": \"1011\",\n" +
            "                    \"package_enddate\": \"1408103878424\",\n" +
            "                    \"package_info\": \"该套餐用于出国收发短信，300条。\",\n" +
            "                    \"package_name\": \"300畅发\",\n" +
            "                    \"package_outlet\": \"生效月有效，300条限额\",\n" +
            "                    \"package_price\": \"3.00\",\n" +
            "                    \"package_startdate\": \"1408103878424\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"code\": \"00000000\",\n" +
            "    \"description\": \"Success\",\n" +
            "    \"sip_code\": \"9994a1f5f40240e78aa4aae29c8b93c8\"\n" +
            "}";
    private static final String CHANGEPASSWORD="{\n" +
            "    \" appkey \": \"bd97e6df3ea84411a4989d870c071b58\",\n" +
            "    \"timestamp\": \"1408103878424\",\n" +
            "    \"sign\": \"9994a1f5f40240e78aa4aae29c8b93c8\",\n" +
            "    \"bindnumber\": \"13612341234\",\n" +
            "    \"oldpassword\": \"e10adc3949ba59abbe56e057f20f883e\",\n" +
            "    \"newpassword\": \"e10adc3949ba59abbe56e057f20f883f\"\n" +
            "}";
    private static final String RESETPASSWORD="{\n" +
            "\" appkey \":\"bd97e6df3ea84411a4989d870c071b58\",\n" +
            "     \"timestamp\":\"1408103878424\",\n" +
            "     \"sign\":\"9994a1f5f40240e78aa4aae29c8b93c8\",\n" +
            "\"bindnumber\":\"13612341234\" ,\n" +
            "\"verificationcode\":\"654321\",\n" +
            "\"newpassword\":\"e10adc3949ba59abbe56e057f20f883e\"\n" +
            "}";
    private  static final String SMSOK="{\n" +
            "\"code\": \"00000000\",\n" +
            "\"description\": \"Success\"\n" +
            "}\n";
    private  static final String SMSFAIL="{\n" +
            "\"code\": \"00040016\",\n" +
            "\"description\": \"Mo sms failed\"\n" +
            "}\n";
    private  static final String SOAPENV="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ter=\"http://terminal.com/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <ter:execute>\n" +
            "         <!--Optional:-->\n" +
            "         <arg0>login</arg0>\n" +
            "         <!--Optional:-->\n" +
            "         <arg1></arg1>\n" +
            "         <!--Optional:-->\n" +
            "         <arg2>{'uuEName':'xtgly','uuPassword':'kk'}</arg2>\n" +
            "         <!--Optional:-->\n" +
            "         <arg3>json</arg3>\n" +
            "         <!--Optional:-->\n" +
            "         <arg4>json</arg4>\n" +
            "      </ter:execute>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";


    //组装发送参数json实体类
    public static HttpEntity installJsonEntity(String SOAPStringB) {
        Gson gson = new Gson();
//header
        String jsonStr = SOAPStringB;
        JLog.d(TAG, jsonStr);

        HttpEntity entity = null;
        try {
            entity = new StringEntity(jsonStr,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    //组装返回类型json
    public static JSONObject installResponseJson(Json json) {
//        Header header = new Header();
//        if(ShortCut.SMSRESPONSEID==0) {
//            json.setCode("00000000");
//            json.setDescription("Success");
//        }else {
//            json.setCode("00040016");
//            json.setDescription("Mo sms failed");
//        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);
        JSONObject jo = null;
        try {
            jo = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }




    public static JSONObject installmissmessage() {
        JSONObject miss =null;
        try {
             miss = new JSONObject(MISSMSG);

            Log.i(TAG, miss.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return miss;
    }

    public static JSONObject installmissrecord() {
        JSONObject miss =null;
        try {
             miss = new JSONObject(MISSRECORD);

            Log.i(TAG, miss.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return miss;
    }
    public static JSONObject installuserinfo() {
        JSONObject miss =null;
        try {
             miss = new JSONObject(USERINFO);

            Log.i(TAG, miss.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return miss;
    }
    //修改密码请求体
    public static JSONObject installchangePassword() {
        JSONObject changeJson = null;
        try {
            changeJson = new JSONObject(CHANGEPASSWORD);

            Log.i(TAG, changeJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return changeJson;
    }

    public static JSONObject installresetPassword() {
        JSONObject resetJson = null;
        try {
            resetJson = new JSONObject(RESETPASSWORD);

            Log.i(TAG, resetJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resetJson;
    }



}
