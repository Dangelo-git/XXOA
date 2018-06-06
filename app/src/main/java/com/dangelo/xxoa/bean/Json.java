package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Json implements Serializable {
    private String appkey;
    private String id;
    private String meetID;


    private String timestamp;
    private String sign;
    private String code;
    private String description;
    private String constitutor;

    private String bindnumber;

    private String imei;
    private String timeoutlen;
    private String verificationcode;
    private String password;

    private String content;
    private List<schedule> schedules;
    private List<docinfo> InfoList;
    private schedule  schedule;
    //private Object business_list;
    private String oldpassword;
    private String newpassword;
    //文档
    private String showRange;
    private String docStatus;
    private String uuEName;
    private String uuPassword;
    private String docId;
    private MeetBasis wsMeetBasis;
    private List<Topics> wsTopics;
    private List<meetinfo> meetlists;
    private List<Broese> broese;
    private wsMeeting wsMeeting;


    private user user;
    private org org;
    private schedule scheduleAdd;
//    private schedule schedules;
    private String pageNo;
    private String isInform;
    private wsMeetingApply wsMeetingApply;
    private download Download;

    public download getDownload() {
        return Download;
    }

//    public void setSchedules(com.dangelo.xxoa.bean.schedule schedules) {
//        this.schedules = schedules;
//    }

    public void setDownload(download download) {
        Download = download;
    }

    public String getMeetID() {
        return meetID;
    }

    public void setMeetID(String meetID) {
        this.meetID = meetID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstitutor() {
        return constitutor;
    }

    public void setConstitutor(String constitutor) {
        this.constitutor = constitutor;
    }

    public wsMeetingApply getWsMeetingApply() {
        return wsMeetingApply;
    }

    public void setWsMeetingApply(wsMeetingApply wsMeetingApply) {
        this.wsMeetingApply = wsMeetingApply;
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

    public List<Broese> getBroese() {
        return broese;
    }

    public void setBroese(List<Broese> broese) {
        this.broese = broese;
    }

    public List<meetinfo> getMeetlists() {
        return meetlists;
    }

    public void setMeetlists(List<meetinfo> meetlists) {
        this.meetlists = meetlists;
    }

    public com.dangelo.xxoa.bean.wsMeeting getWsMeeting() {
        return wsMeeting;
    }

    public void setWsMeeting(com.dangelo.xxoa.bean.wsMeeting wsMeeting) {
        this.wsMeeting = wsMeeting;
    }

    public List<Topics> getWsTopics() {
        return wsTopics;
    }

    public void setWsTopics(List<Topics> wsTopics) {
        this.wsTopics = wsTopics;
    }

    public MeetBasis getWsMeetBasis() {
        return wsMeetBasis;
    }

    public void setWsMeetBasis(MeetBasis wsMeetBasis) {
        this.wsMeetBasis = wsMeetBasis;
    }



    public String getUuPassword() {
        return uuPassword;
    }

    public void setUuPassword(String uuPassword) {
        this.uuPassword = uuPassword;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public List<docinfo> getInfoList() {
        return InfoList;
    }

    public void setInfoList(List<docinfo> infoList) {
        InfoList = infoList;
    }

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public List<com.dangelo.xxoa.bean.schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<com.dangelo.xxoa.bean.schedule> schedules) {
        this.schedules = schedules;
    }

    public com.dangelo.xxoa.bean.schedule getScheduleAdd() {
        return scheduleAdd;
    }

    public void setScheduleAdd(com.dangelo.xxoa.bean.schedule scheduleAdd) {
        this.scheduleAdd = scheduleAdd;
    }

    public com.dangelo.xxoa.bean.user getUser() {
        return user;
    }

    public void setUser(com.dangelo.xxoa.bean.user user) {
        this.user = user;
    }

    public com.dangelo.xxoa.bean.org getOrg() {
        return org;
    }

    public void setOrg(com.dangelo.xxoa.bean.org org) {
        this.org = org;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }





    public com.dangelo.xxoa.bean.schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(com.dangelo.xxoa.bean.schedule schedule) {
        this.schedule = schedule;
    }



//    private List<Missrecord_list> missrecord_list;
//    private List<Missmsg_list> missmsg_list;


    public Json() {
    }

    public Json(String appkey, String timestamp, String sign) {
        this.appkey = appkey;
        this.timestamp = timestamp;
        this.sign = sign;
    }






    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBindnumber() {
        return bindnumber;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTimeoutlen() {
        return timeoutlen;
    }

    public void setTimeoutlen(String timeoutlen) {
        this.timeoutlen = timeoutlen;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    @Override
    public String toString() {
        return "Json{" +
                "appkey='" + appkey + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", bindnumber='" + bindnumber + '\'' +
                ", imei='" + imei + '\'' +
                ", timeoutlen='" + timeoutlen + '\'' +
                ", verificationcode='" + verificationcode + '\'' +
                ", password='" + password + '\'' +
                ", content='" + content + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                '}';
    }
}
