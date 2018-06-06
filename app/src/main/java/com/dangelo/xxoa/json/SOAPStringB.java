package com.dangelo.xxoa.json;

import android.util.Log;

/**
 * Created by dangelo on 16/10/23.
 */
public class SOAPStringB {
    static String Soapbefore ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ter=\"http://terminal.com/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <ter:execute>\n";
    static String SoapAfter =  "         <arg3>json</arg3>\n" +
            "         <arg4>json</arg4>\n" +
            "      </ter:execute>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";
    static String Soapbefore1 ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.com/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <web:getBrowseNews>\n";
    static String SoapAfter1 =
            "      </web:getBrowseNews>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";
    static String SoapReturnBefore = "<return>";
    static String SoapReturnAfter = "</return>";
    static String Soapmiddin = "         <arg1></arg1>\n";

    public static String AssembleSoapRequest(String Method,String Jsonparameter,String token) {
        String SoapString = "";
        String MethodString = "         <arg0>"+Method+"</arg0>\n";
        String JsonparameterString = "         <arg2>"+Jsonparameter+"</arg2>\n";
        String TokenString = "         <arg1>"+token+"</arg1>\n";
        SoapString = Soapbefore+MethodString+TokenString+JsonparameterString+SoapAfter;
        Log.i("SOAPStringB","AssembleSoapRequest:\n"+SoapString);
        return SoapString;
    }
    public static String AssembleSoapRequest1(String Jsonparameter) {
        String SoapString = "";
        String JsonparameterString = "         <arg0>"+Jsonparameter+"</arg0>\n";

        SoapString = Soapbefore1+JsonparameterString+SoapAfter1;
        Log.i("SOAPStringB1","AssembleSoapRequest:\n"+SoapString);
        return SoapString;
    }
    public static String parserSoapResPonse(String ResponseString) {
        int astart = ResponseString.indexOf(SoapReturnBefore)+SoapReturnBefore.length();
        int aend = ResponseString.indexOf(SoapReturnAfter);
        String SoapRspString = ResponseString.substring(astart,aend);
        Log.i("SOAPStringB","parserSoapResPonse:\n"+SoapRspString);
        return SoapRspString;
    }
}
