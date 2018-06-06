package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/22.
 */
public class wsMeeting {
//    {
//        "uuEName": "xtgly",
//            "pageNo": 10,
//            "isInform": "true",
//            "meetDepts": "",
//            "meetlists": ""
//    }
    private String uuEName;
    private String pageNo;
    private String isInform;
    private String meetDepts;
    private String meetlists;

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getIsInform() {
        return isInform;
    }

    public void setIsInform(String isInform) {
        this.isInform = isInform;
    }

    public String getMeetDepts() {
        return meetDepts;
    }

    public void setMeetDepts(String meetDepts) {
        this.meetDepts = meetDepts;
    }

    public String getMeetlists() {
        return meetlists;
    }

    public void setMeetlists(String meetlists) {
        this.meetlists = meetlists;
    }
}
