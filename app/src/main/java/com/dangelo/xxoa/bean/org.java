package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/9/29.
 */
public class org {
    private String json = "\"org\":{\n" +
            "\"orgId\":10,\n" +
            "\"uoEName\":\"TXXXZX\",\n" +
            "\"uoCName\":\"通信信息中心\",\n" +
            "\"uoENameFull\":\"XZJTT.TXXXZX\",\n" +
            "\"uoCNameFull\":\"西藏交通厅.通信信息中心\"\n" +
            "}";
/*"org":{
        "orgId":10,
                "uoEName":"TXXXZX",
                "uoCName":"通信信息中心",
                "uoENameFull":"XZJTT.TXXXZX",
                "uoCNameFull":"西藏交通厅.通信信息中心"
    }*/
    private String orgId;
    private String uoEName;
    private String uoCName;
    private String uoENameFull;//套餐有效期
    private String uoCNameFull;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUoEName() {
        return uoEName;
    }

    public void setUoEName(String uoEName) {
        this.uoEName = uoEName;
    }

    public String getUoCName() {
        return uoCName;
    }

    public void setUoCName(String uoCName) {
        this.uoCName = uoCName;
    }

    public String getUoENameFull() {
        return uoENameFull;
    }

    public void setUoENameFull(String uoENameFull) {
        this.uoENameFull = uoENameFull;
    }

    public String getUoCNameFull() {
        return uoCNameFull;
    }

    public void setUoCNameFull(String uoCNameFull) {
        this.uoCNameFull = uoCNameFull;
    }
}
