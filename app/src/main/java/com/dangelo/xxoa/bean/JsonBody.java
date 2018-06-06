package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dangelo on 18/5/7.
 */

public class JsonBody implements Serializable {
    private String code;
    private String serviceCode;
    private Map errorMsgMap;
    private DataBody data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Map getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public DataBody getData() {
        return data;
    }

    public void setData(DataBody data) {
        this.data = data;
    }
}
