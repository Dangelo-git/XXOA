package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/12/19.
 */
public class meetList {
    /**
     * <meetName memo='会议标题'></meetName>
     <meetSite memo='会议地点'></meetSite>
     <meetType memo='会议类型'></meetType>
     <num memo='类型次数'>5</num>
     <Year memo='年份'>2016</Year>
     <meetDate memo='开始时间'>2016-12-25T00:00:00+08:00</meetDate>
     <endDate memo='截止时间'>2016-11-25T00:00:00+08:00</endDate>
     <Status memo='状态'>1</Status>
     <charge memo='主持人'></charge>
     <contacts memo='联系人'></contacts>
     <phone memo='联系方式'>123456</phone>
     <attendOne memo='出席人员'></attendOne>

     */
    /**
     *   "meetlists": [
     {
     "id": 会议id,
     "meetName": "会议标题",
     " meetSite ": "会议室",
     "meetType": "会议类型",
     "num": 会议次数,
     "Year": 2016,
     "meetDate": "开会日期",
     "attendOne": "出席人员",
     "endDate": "2016年11月25日",
     "charge": "主持人",
     "Status": 状态,
     "contacts": "联系人",
     "phone": "联系电话",
     "attendPeople": "出席人员",
     "attendDept": "列席人员"
     }

     */

    private String id;
    private String meetName;
    private String meetSite;
    private String meetType;
    private String num;
    private String year;
    private String meetDate;
    private String endDate;
    private String status;
    private String charge;
    private String contacts;
    private String phone;
    private String attendOne;

    private String attendPeople;
    private String attendDept;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



    public String getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
        this.meetDate = meetDate;
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

    public String getAttendOne() {
        return attendOne;
    }

    @Override
    public String toString() {
        return "meetList{" +
                "meetName='" + meetName + '\'' +
                ", meetSite='" + meetSite + '\'' +
                ", meetType='" + meetType + '\'' +
                ", num='" + num + '\'' +
                ", Year='" + year + '\'' +
                ", meetDate='" + meetDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", Status='" + status + '\'' +
                ", charge='" + charge + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", attendOne='" + attendOne + '\'' +
                '}';
    }

    public void setAttendOne(String attendOne) {
        this.attendOne = attendOne;
    }


}
