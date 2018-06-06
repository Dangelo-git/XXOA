package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/22.
 */
public class meetinfo {
    //    {
//        "id": 会议id,
//            "meetName": "会议标题",
//            "meetSite": "会议室",
//            "meetType": "会议类型",
//            "num": 会议次数,
//            "Year": 2016,
//            "meetDate": "开会日期",
//            "attendOne": "出席人员",
//            "endDate": "2016年11月25日",
//            "charge": "主持人",
//            "Status": 状态,
//            "contacts": "联系人",
//            "phone": "联系电话",
//            "attendPeople": "出席人员",
//            "attendDept": "列席人员"
//    }
    private String id;
    private String meetName;
    private String meetSite;
    private String meetType;
    private String num;
    private String Year;
    private String meetDate;
    private String attendOne;
    private String endDate;
    private String charge;
    private String Status;
    private String contacts;
    private String phone;
    private String attendPeople;
    private String attendDept;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getMeetSite() {
        return meetSite;
    }

    public void setMeetSite(String meetSite) {
        this.meetSite = meetSite;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
        this.meetDate = meetDate;
    }

    public String getAttendOne() {
        return attendOne;
    }

    public void setAttendOne(String attendOne) {
        this.attendOne = attendOne;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAttendPeople() {
        return attendPeople;
    }

    public void setAttendPeople(String attendPeople) {
        this.attendPeople = attendPeople;
    }

    public String getAttendDept() {
        return attendDept;
    }

    public void setAttendDept(String attendDept) {
        this.attendDept = attendDept;
    }
}
